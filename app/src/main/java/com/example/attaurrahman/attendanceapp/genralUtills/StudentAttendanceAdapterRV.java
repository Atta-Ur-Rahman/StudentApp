package com.example.attaurrahman.attendanceapp.genralUtills;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.dataBase.Crud;
import com.example.attaurrahman.attendanceapp.dataBase.ListHelper;
import com.example.attaurrahman.attendanceapp.fragment.AttendanceListFragment;

import java.util.List;

/**
 * Created by AttaUrRahman on 3/13/2018.
 */

public class StudentAttendanceAdapterRV extends RecyclerView.Adapter<StudentAttendanceAdapterRV.ViewHolder> {

    private List<ListHelper> listHelpers;
    private Context context;
    private Crud crud;

    public StudentAttendanceAdapterRV(List<ListHelper> listHelpers, Context context) {
        this.listHelpers = listHelpers;
        this.context = context;
        crud = new Crud(context);
    }


    @Override
    public StudentAttendanceAdapterRV.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_father_cnic_data_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentAttendanceAdapterRV.ViewHolder holder, int position) {

        final ListHelper listHelper = listHelpers.get(position);

        holder.tvStudentNameFcnic.setText(listHelper.getStudent_name().toString());
        holder.tvStudentClassFcnic.setText(listHelper.getStudent_class().toString());
        holder.tvStudentRollNumberFcnic.setText(String.valueOf(listHelper.getStudent_roll_number()));
        holder.tvStudentAttendanceFcnic.setText(String.valueOf(listHelper.getStudent_attendec()));
        holder.tvStudentRollNumberFcnic.setVisibility(View.GONE);

        boolean attendance = Utilities.getSharedPreferences(context).getBoolean("AttendanceList",false);

        if (attendance){

            holder.tvDateTitile.setText(listHelper.getDate_format().toString());
            holder.llTitleAttendance.setVisibility(View.GONE);
            holder.tvDateTitile.setVisibility(View.VISIBLE);


        }

        boolean b = Utilities.getSharedPreferences(context).getBoolean("attendance_list",false);

        if (b){
            holder.llStudentFatherCNICRecord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strRollNumber = String.valueOf(listHelper.getStudent_roll_number()).toString();
                    crud.getCountAttendance(strRollNumber);
                    Utilities.putValueInEditor(context).putString("RollNumber", strRollNumber).commit();
                    Utilities.connectFragment(context, new AttendanceListFragment());

                }
            });

        }



    }

    @Override
    public int getItemCount() {
        return listHelpers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvStudentNameFcnic, tvStudentClassFcnic, tvStudentRollNumberFcnic, tvStudentAttendanceFcnic, tvDateTitile;
        LinearLayout llStudentFatherCNICRecord, llTitleAttendance;

        public ViewHolder(View itemView) {
            super(itemView);

             tvStudentNameFcnic = itemView.findViewById(R.id.tvStudentNameFcnic);
             tvStudentClassFcnic = itemView.findViewById(R.id.tvStudentClassFcnic);
             tvStudentRollNumberFcnic = itemView.findViewById(R.id.tvStudentRollNumberFcnic);
             tvStudentAttendanceFcnic = itemView.findViewById(R.id.tvStudentAttendanceFcnic);
             tvDateTitile = itemView.findViewById(R.id.tv_date_title);
             llStudentFatherCNICRecord = itemView.findViewById(R.id.ll_student_father_cnic_record);
             llTitleAttendance=itemView.findViewById(R.id.ll_title_attendance);


        }
    }
}
