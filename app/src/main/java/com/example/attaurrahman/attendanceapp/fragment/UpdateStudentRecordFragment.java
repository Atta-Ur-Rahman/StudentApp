package com.example.attaurrahman.attendanceapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.dataBase.Crud;
import com.example.attaurrahman.attendanceapp.genralUtills.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UpdateStudentRecordFragment extends Fragment {
    View parentView;
    Crud crud;
    @BindView(R.id.et_update_student_name)EditText etUpadateName;
    @BindView(R.id.et_update_student_class)EditText etUpdateClass;
    @BindView(R.id.et_update_student_roll_number)EditText etUpdateRollNumber;
    @BindView(R.id.et_update_student_father_CNIC)EditText etUpdateFatherCNIC;
    @BindView(R.id.btn_update)Button btnUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_update_student_record, container, false);
        ButterKnife.bind(this,parentView);
        crud =new Crud(getActivity());

        etUpadateName.setText(Utilities.getSharedPreferences(getActivity()).getString("NAME",""));
        etUpdateClass.setText(Utilities.getSharedPreferences(getActivity()).getString("CLASS",""));
        etUpdateRollNumber.setText(Utilities.getSharedPreferences(getActivity()).getString("ROLL_NUMBER",""));
        etUpdateFatherCNIC.setText(Utilities.getSharedPreferences(getActivity()).getString("FATHER_CNIC",""));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strOldRollNumber = Utilities.getSharedPreferences(getActivity()).getString("ROLL_NUMBER","").toString();

                if (etUpadateName.length() < 2) {
                    Toast.makeText(getActivity(), "Enter name", Toast.LENGTH_SHORT).show();
                } else if (etUpdateRollNumber.length() < 0) {
                    Toast.makeText(getActivity(), "Enter Roll Number", Toast.LENGTH_SHORT).show();
                } else if (etUpdateClass.length() < 0) {
                    Toast.makeText(getActivity(), "Enter Class", Toast.LENGTH_SHORT).show();
                } else if (etUpdateFatherCNIC.length() < 4) {
                    Toast.makeText(getActivity(), "Enter Father CNIC", Toast.LENGTH_SHORT).show();
                } else{
                String strName=etUpadateName.getText().toString();
                String strClass=etUpdateClass.getText().toString();
                String strRollNumber=etUpdateRollNumber.getText().toString();
                String strFatherCNIC=etUpdateFatherCNIC.getText().toString();

                crud.UpdateStudentRecord(strOldRollNumber,strName,strRollNumber,strClass,strFatherCNIC);
                }
            }
        });



        return parentView;
    }

}
