package com.example.attaurrahman.attendanceapp.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.dataBase.Crud;
import com.example.attaurrahman.attendanceapp.dataBase.ListHelper;
import com.example.attaurrahman.attendanceapp.genralUtills.StudentAttendanceAdapterRV;
import com.example.attaurrahman.attendanceapp.genralUtills.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AttendanceListFragment extends Fragment {
    View parentView;
    String strRollNumber;
    Crud crud;
    List<ListHelper> allAtendanceList;
    @BindView(R.id.rv_all_attendance)RecyclerView rvAllAttendance;

    @BindView(R.id.tv_absent)TextView tvAbsent;
    @BindView(R.id.tv_present)TextView tvPresent;
    @BindView(R.id.tv_classes)TextView tvClasses;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_attendance_list, container, false);
        ButterKnife.bind(this, parentView);
        crud = new Crud(getActivity());
        rvAllAttendance.setHasFixedSize(true);
        rvAllAttendance.setLayoutManager(new LinearLayoutManager(getActivity()));

        Utilities.putValueInEditor(getActivity()).putBoolean("attendance_list",false).commit();


        Utilities.putValueInEditor(getActivity()).putBoolean("AttendanceList",true).commit();

        strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("RollNumber","");
        allAtendanceList = crud.CheckAllAttendanceByRollNumber(strRollNumber);
        StudentAttendanceAdapterRV adapter = new StudentAttendanceAdapterRV(allAtendanceList,getActivity());
        rvAllAttendance.setAdapter(adapter);

        tvAbsent.setText(Utilities.getSharedPreferences(getActivity()).getString("absent","0"));
        tvPresent.setText(Utilities.getSharedPreferences(getActivity()).getString("present","0"));
        tvClasses.setText(Utilities.getSharedPreferences(getActivity()).getString("classes","0"));
        Utilities.putValueInEditor(getActivity()).putString("absent", "0").commit();
        Utilities.putValueInEditor(getActivity()).putString("present", "0").commit();
        Utilities.putValueInEditor(getActivity()).putString("classes", "0").commit();



            return parentView;
    }

}
