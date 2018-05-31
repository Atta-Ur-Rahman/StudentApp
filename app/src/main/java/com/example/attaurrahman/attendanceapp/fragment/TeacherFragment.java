package com.example.attaurrahman.attendanceapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.genralUtills.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TeacherFragment extends Fragment implements View.OnClickListener{

    View parentView;
    @BindView(R.id.tvAddStudent)TextView tvAddStudent;
    @BindView(R.id.tvCheckAttendance)TextView tvCheckAttendance;
    @BindView(R.id.tvUpdateAttendance)TextView tvUpdateAttendance;
    @BindView(R.id.tv_attendance_between_date)TextView tvAttendanceBetweenDate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_teacher, container, false);

        ButterKnife.bind(this,parentView);
        tvAddStudent.setOnClickListener(this);
        tvCheckAttendance.setOnClickListener(this);
        tvUpdateAttendance.setOnClickListener(this);
        tvAttendanceBetweenDate.setOnClickListener(this);


        return parentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tvUpdateAttendance:
                Utilities.connectFragment(getActivity(),new UpdateAttendanceFragment());
                break;
            case R.id.tvCheckAttendance:
                Utilities.connectFragment(getActivity(),new CheckAttendanceFragment());
                break;
            case R.id.tvAddStudent:
                Utilities.connectFragment(getActivity(),new StudentAllRecordFragment());
                break;
            case R.id.tv_attendance_between_date:
                Utilities.connectFragment(getActivity(),new StudentAttendanceByDateFragment());
                break;
        }
    }
}
