package com.example.attaurrahman.attendanceapp.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.dataBase.Crud;
import com.example.attaurrahman.attendanceapp.dataBase.ListHelper;
import com.example.attaurrahman.attendanceapp.genralUtills.StudentClassAdapterRecyclerView;
import com.example.attaurrahman.attendanceapp.genralUtills.Utilities;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StudentAllRecordFragment extends Fragment {

    View parentView;


    Crud crud;
    List<ListHelper> student_listHelpers;
    @BindView(R.id.ivAdd)
    ImageView ivAdd;
    @BindView(R.id.rv_student_record)RecyclerView rv_student_record;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_student_all_record, container, false);
        ButterKnife.bind(this, parentView);

        crud = new Crud(getActivity());
        rv_student_record.setHasFixedSize(true);
        rv_student_record.setLayoutManager(new LinearLayoutManager(getActivity()));

        student_listHelpers = crud.StudentAllRecordList();
        StudentClassAdapterRecyclerView adapter = new StudentClassAdapterRecyclerView(student_listHelpers,getActivity());
        rv_student_record.setAdapter(adapter);




        Utilities.putValueInEditor(getActivity()).putBoolean("isStudentAllRecord",true).commit();

        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.connectFragment(getActivity(), new AddStudentFragment());
            }
        });


        return parentView;
    }


}
