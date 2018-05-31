package com.example.attaurrahman.attendanceapp.genralUtills;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.dataBase.Crud;
import com.example.attaurrahman.attendanceapp.dataBase.ListHelper;
import com.example.attaurrahman.attendanceapp.fragment.UpdateStudentRecordFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AttaUrRahman on 3/13/2018.
 */

public class StudentClassAdapterRecyclerView extends RecyclerView.Adapter<StudentClassAdapterRecyclerView.ViewHolder> {

    private List<ListHelper> listHelpers;
    private Context context;
    private String str_student_attendce, str_rollNumber, str_date;
    private Crud crud;

    public StudentClassAdapterRecyclerView(List<ListHelper> listHelpers, Context context) {
        this.listHelpers = listHelpers;
        this.context = context;
        crud = new Crud(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_record_list, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ListHelper listHelper = listHelpers.get(position);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMdd ");
        str_date = mdformat.format(calendar.getTime());

        str_rollNumber = String.valueOf(listHelper.getStudent_roll_number());

        boolean aBoolean = Utilities.getSharedPreferences(context).getBoolean("isStudentAllRecord", false);

        if (aBoolean) {
            holder.llAttendanceButton.setVisibility(View.GONE);
            holder.tvAttendance.setVisibility(View.GONE);
            holder.tvAttendanceTitle.setVisibility(View.GONE);
            holder.tvListDate.setVisibility(View.VISIBLE);
            holder.tvListDateTitle.setVisibility(View.VISIBLE);

            holder.llStudentRecrod.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    CharSequence colors[] = new CharSequence[]{"Delete", "Update"};
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                    builder.setTitle("Edit Menu");
                    builder.setItems(colors, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {

                                crud.Delete(str_rollNumber);
                            } else {

                                Utilities.putValueInEditor(context).putString("NAME", listHelper.getStudent_name().toString()).commit();
                                Utilities.putValueInEditor(context).putString("CLASS", listHelper.getStudent_class().toString()).commit();
                                Utilities.putValueInEditor(context).putString("ROLL_NUMBER", String.valueOf(listHelper.getStudent_roll_number())).commit();
                                Utilities.putValueInEditor(context).putString("FATHER_CNIC", String.valueOf(listHelper.getFather_cnic())).commit();
                                Utilities.connectFragment(context, new UpdateStudentRecordFragment());
                            }
                        }
                    });
                    builder.show();

                    return false;
                }
            });
        }

            holder.tvStudentName.setText(listHelper.getStudent_name().toString());
            holder.tvStudentClass.setText(listHelper.getStudent_class().toString());
            holder.tvRollNumber.setText(String.valueOf(listHelper.getStudent_roll_number()));
            holder.tvFacterCNIC.setText(String.valueOf(listHelper.getFather_cnic()));
            holder.tvListDate.setText(String.valueOf(listHelper.getDate_format()));
            holder.tvAttendance.setText(String.valueOf(listHelper.getStudent_attendec()));
            holder.ivProfileImage.setImageURI(Uri.parse(listHelper.getImge_uri()));

            holder.btnPresent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String rowID = String.valueOf(listHelper.getRowID());
                    str_rollNumber = String.valueOf(listHelper.getStudent_roll_number());
                    str_student_attendce = "Present";
                    crud.UpdateAttendance(str_rollNumber, str_student_attendce, str_date, rowID);
                    listHelpers = crud.NewAttendancList(str_date);
                    // Toast.makeText(context, "Present", LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });

            holder.btnAbsent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String rowID = String.valueOf(listHelper.getRowID());
                    str_rollNumber = String.valueOf(listHelper.getStudent_roll_number());
                    str_student_attendce = "Absent";
                    crud.UpdateAttendance(str_rollNumber, str_student_attendce, str_date, rowID);
                    listHelpers = crud.NewAttendancList(str_date);
                    //Toast.makeText(context, "Absent", LENGTH_SHORT).show();
                    notifyDataSetChanged();


                }
            });




    }

    @Override
    public int getItemCount() {
        return listHelpers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvStudentName, tvStudentClass, tvRollNumber, tvFacterCNIC, tvListDate, tvListDateTitle, tvAttendance, tvAttendanceTitle;

        CircleImageView ivProfileImage;
        Button btnPresent, btnAbsent;
        LinearLayout llStudentRecrod, llAttendanceButton;

        public ViewHolder(View itemView) {
            super(itemView);

            tvStudentName = itemView.findViewById(R.id.tvStudnetName);
            tvStudentClass = itemView.findViewById(R.id.tvStudentClass);
            tvRollNumber = itemView.findViewById(R.id.tvStudentRollNumber);
            tvFacterCNIC = itemView.findViewById(R.id.tvFatherCNIC);
            tvListDate = itemView.findViewById(R.id.tvListDate);
            tvListDateTitle = itemView.findViewById(R.id.tvDateTitle);
            tvAttendance = itemView.findViewById(R.id.tvAttendance);
            tvAttendanceTitle = itemView.findViewById(R.id.tvAttendanceTitle);
            ivProfileImage = itemView.findViewById(R.id.iv_profile_image);

            btnPresent = itemView.findViewById(R.id.btnPresent);
            btnAbsent = itemView.findViewById(R.id.btnAbsent);

            llStudentRecrod = itemView.findViewById(R.id.ll_student_record);
            llAttendanceButton = itemView.findViewById(R.id.llAttendanceButton);

        }
    }
}
