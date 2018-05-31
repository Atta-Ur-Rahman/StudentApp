package com.example.attaurrahman.attendanceapp.genralUtills;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.dataBase.Crud;
import com.example.attaurrahman.attendanceapp.dataBase.ListHelper;
import com.example.attaurrahman.attendanceapp.fragment.UpdateStudentRecordFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by AttaUrRahman on 3/14/2018.
 */

public class CardStackAdapter extends BaseAdapter {
    private List<ListHelper> listHelpers;
    private Context context;
    private ListHelper listHelper;
    String str_student_attendce, str_rollNumber, str_date, rowId, rollNumber;
    View view;
    Crud crud;
    int i =0;
    int intIncrement = 0;



    public CardStackAdapter(List<ListHelper> list, Context context) {

        this.listHelpers = list;
        this.context = context;
        crud = new Crud(context);

    }

    @Override
    public int getCount() {
        return listHelpers.size();
    }

    @Override
    public Object getItem(int position) {
        return listHelpers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_layout, parent, false);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMdd ");
        str_date = mdformat.format(calendar.getTime());


        intIncrement = Utilities.getSharedPreferences(context).getInt("incrementPosition",0);
        listHelper = listHelpers.get(position);
       // Toast.makeText(context, "abe", Toast.LENGTH_SHORT).show();

        if (position == 0){
            Utilities.putValueInEditor(context).putString("rollNumber0", String.valueOf(listHelper.getStudent_roll_number())).commit();
            Utilities.putValueInEditor(context).putString("rowId0", listHelper.getRowID()).commit();
        }else
            if (position == 1){
            Utilities.putValueInEditor(context).putString("rollNumber1", String.valueOf(listHelper.getStudent_roll_number())).commit();
            Utilities.putValueInEditor(context).putString("rowId1", listHelper.getRowID()).commit();
        }else if (position == 2){
            Utilities.putValueInEditor(context).putString("rollNumber2", String.valueOf(listHelper.getStudent_roll_number())).commit();
            Utilities.putValueInEditor(context).putString("rowId2", listHelper.getRowID()).commit();
        }else if (position == 3){
            Utilities.putValueInEditor(context).putString("rollNumber3", String.valueOf(listHelper.getStudent_roll_number())).commit();
            Utilities.putValueInEditor(context).putString("rowId3", listHelper.getRowID()).commit();
        }else if (position == 4){
                Utilities.putValueInEditor(context).putString("rollNumber4", String.valueOf(listHelper.getStudent_roll_number())).commit();
                Utilities.putValueInEditor(context).putString("rowId4", listHelper.getRowID()).commit();
            }


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
                    str_rollNumber = String.valueOf(listHelper.getStudent_roll_number());
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
                Utilities.putValueInEditor(context).putString("rollNumber", String.valueOf(listHelpers.get(position).getStudent_roll_number())).commit();
                Utilities.putValueInEditor(context).putString("rowId", listHelpers.get(position).getRowID()).commit();
                Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show();

            }
        });
        
        return view;


    }
}
