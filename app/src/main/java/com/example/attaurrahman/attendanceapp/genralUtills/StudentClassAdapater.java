package com.example.attaurrahman.attendanceapp.genralUtills;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.dataBase.Crud;
import com.example.attaurrahman.attendanceapp.dataBase.ListHelper;
import com.example.attaurrahman.attendanceapp.fragment.UpdateAttendanceFragment;
import com.example.attaurrahman.attendanceapp.fragment.UpdateStudentRecordFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AttaUrRahman on 3/5/2018.
 */


public class StudentClassAdapater extends ArrayAdapter<ListHelper> {

    List<ListHelper> student_listHelpers;
    Context context;
    String str_student_attendce, str_rollNumber, str_date, rowId, rollNumber;
    Crud crud;
    ListHelper listHelper;
    View view;
    String[] itemPosition;


    public StudentClassAdapater(@NonNull Context context, List<ListHelper> student_listHelpers) {

        super(context, R.layout.student_record_list, student_listHelpers);
        this.context = context;
        this.student_listHelpers = student_listHelpers;
        crud = new Crud(context);
    }

    @Override
    public int getCount() {
        return student_listHelpers.size();
    }

    @Override
    public ListHelper getItem(int position) {
        return student_listHelpers.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"ViewHolder", "ClickableViewAccessibility"})
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_record_list, parent, false);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMdd ");
        str_date = mdformat.format(calendar.getTime());


        listHelper = student_listHelpers.get(position);

        TextView tvStudentName = view.findViewById(R.id.tvStudnetName);
        TextView tvStudentClass = view.findViewById(R.id.tvStudentClass);
        TextView tvRollNumber = view.findViewById(R.id.tvStudentRollNumber);
        TextView tvFacterCNIC = view.findViewById(R.id.tvFatherCNIC);
        TextView tvListDate = view.findViewById(R.id.tvListDate);
        TextView tvListDateTitle = view.findViewById(R.id.tvDateTitle);
        final TextView tvAttendance = view.findViewById(R.id.tvAttendance);
        TextView tvAttendanceTitle = view.findViewById(R.id.tvAttendanceTitle);
        CircleImageView ivProfileImage = view.findViewById(R.id.iv_profile_image);


        LinearLayout llStudentRecrod = view.findViewById(R.id.ll_student_record);
        LinearLayout llAttendanceButton = view.findViewById(R.id.llAttendanceButton);

        boolean aBoolean = Utilities.getSharedPreferences(context).getBoolean("isStudentAllRecord", false);

        if (aBoolean) {
            llAttendanceButton.setVisibility(View.GONE);
            tvAttendance.setVisibility(View.GONE);
            tvAttendanceTitle.setVisibility(View.GONE);
            tvListDate.setVisibility(View.VISIBLE);
            tvListDateTitle.setVisibility(View.VISIBLE);

            llStudentRecrod.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    str_rollNumber = String.valueOf(student_listHelpers.get(position).getStudent_roll_number());
                    CharSequence colors[] = new CharSequence[]{"Delete", "Update"};

                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
                    builder.setTitle("Edit Menu");
                    builder.setItems(colors, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == 0) {

                                crud.Delete(str_rollNumber);
                                notifyDataSetChanged();
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

        tvStudentName.setText(listHelper.getStudent_name());
        tvStudentClass.setText(listHelper.getStudent_class());
        tvRollNumber.setText(String.valueOf(listHelper.getStudent_roll_number()));
        tvFacterCNIC.setText(String.valueOf(listHelper.getFather_cnic()));
        tvListDate.setText(String.valueOf(listHelper.getDate_format()));
        tvAttendance.setText(String.valueOf(listHelper.getStudent_attendec()));
        ivProfileImage.setImageURI(Uri.parse(listHelper.getImge_uri()));




        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Click View", Toast.LENGTH_SHORT).show();
                Utilities.putValueInEditor(context).putString("rollNumber", String.valueOf(student_listHelpers.get(position).getStudent_roll_number())).commit();
                Utilities.putValueInEditor(context).putString("rowId", student_listHelpers.get(position).getRowID()).commit();
            }
        });




        return view;


    }


}
