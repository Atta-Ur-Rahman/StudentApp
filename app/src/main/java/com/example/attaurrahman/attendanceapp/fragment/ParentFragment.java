package com.example.attaurrahman.attendanceapp.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.dataBase.Crud;
import com.example.attaurrahman.attendanceapp.dataBase.ListHelper;
import com.example.attaurrahman.attendanceapp.genralUtills.StudentAttendanceAdapterRV;
import com.example.attaurrahman.attendanceapp.genralUtills.Utilities;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ParentFragment extends Fragment {

    View parentView;
    Crud crud;
    private List<ListHelper> student_fatherCNIC_listHelpers;


    @BindView(R.id.btnCnicSubmit)
    Button btnCnicSubmit;
    @BindView(R.id.etFatherCnic)
    EditText etFatherCnic;
    @BindView(R.id.ll_parent_cnic_name)LinearLayout llParentCnicName;

    @BindView(R.id.rv_father_cnic)RecyclerView rvFatherCnic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_parent, container, false);

        ButterKnife.bind(this, parentView);
        crud = new Crud(getActivity());
        rvFatherCnic.setHasFixedSize(true);
        rvFatherCnic.setLayoutManager(new LinearLayoutManager(getActivity()));
        Utilities.putValueInEditor(getActivity()).putBoolean("AttendanceList", false).commit();
        Utilities.putValueInEditor(getActivity()).putBoolean("attendance_list",true).commit();

        boolean b = Utilities.getSharedPreferences(getActivity()).getBoolean("ParentList",false);

        if (b){
            String cnic = Utilities.getSharedPreferences(getActivity()).getString("cnic","");
            student_fatherCNIC_listHelpers = crud.father_cnic_data(cnic);
            StudentAttendanceAdapterRV adapter = new StudentAttendanceAdapterRV(student_fatherCNIC_listHelpers,getActivity());
            rvFatherCnic.setAdapter(adapter);
            llParentCnicName.setVisibility(View.GONE);

        }


        btnCnicSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String cnic = etFatherCnic.getText().toString();
                Utilities.putValueInEditor(getActivity()).putString("cnic",cnic).commit();
                student_fatherCNIC_listHelpers = crud.father_cnic_data(cnic);
                StudentAttendanceAdapterRV adapter = new StudentAttendanceAdapterRV(student_fatherCNIC_listHelpers,getActivity());
                rvFatherCnic.setAdapter(adapter);
                Utilities.connectFragmentWithOutBackStack(getActivity(),new ParentFragment());


            }
        });


        return parentView;
    }

}
