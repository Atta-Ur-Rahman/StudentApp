package com.example.attaurrahman.attendanceapp.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.genralUtills.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FirstLogInFragment extends Fragment {

    View parentVeiw;
    @BindView(R.id.btn_parent)
    Button btnParent;
    @BindView(R.id.btn_teacher)
    Button btnTeacher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentVeiw = inflater.inflate(R.layout.fragment_first_log_in, container, false);
        ButterKnife.bind(this, parentVeiw);

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.connectFragment(getActivity(), new TeacherFragment());

            }
        });
        btnParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.connectFragment(getActivity(), new ParentFragment());
            }
        });


        return parentVeiw;
    }


}
