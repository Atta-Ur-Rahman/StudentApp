package com.example.attaurrahman.attendanceapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daprlabs.cardstack.SwipeDeck;
import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.dataBase.Crud;
import com.example.attaurrahman.attendanceapp.dataBase.ListHelper;
import com.example.attaurrahman.attendanceapp.genralUtills.CardStackAdapter;
import com.example.attaurrahman.attendanceapp.genralUtills.StudentClassAdapater;
import com.example.attaurrahman.attendanceapp.genralUtills.StudentClassAdapterRecyclerView;
import com.example.attaurrahman.attendanceapp.genralUtills.Utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateAttendanceFragment extends Fragment {

    View parentView;
    Crud crud;
    String str_date, str_date1,strAttendce;
    List<ListHelper> attendance_list_helper;
    @BindView(R.id.tvUpadateAttendanceDate)
    TextView tvUpadateAttendanceDate;
    @BindView(R.id.rv_student)
    RecyclerView rv_student;
    @BindView(R.id.swipe_deck)
    SwipeDeck cardStack;
    CardStackAdapter adapter;
    int i =0;
    int intIncrement = 0;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_update_attendance, container, false);


        ButterKnife.bind(this, parentView);
        crud = new Crud(getActivity());


        rv_student.setHasFixedSize(true);
        rv_student.setLayoutManager(new LinearLayoutManager(getActivity()));

        Utilities.putValueInEditor(getActivity()).putBoolean("isStudentAllRecord", false).commit();

        //getting date and pass create new database for new attendance in crud class

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMdd ");
        str_date = mdformat.format(calendar.getTime());
        crud.CreateNewDabaseAndGetDate(str_date);


        ////set heder date
        SimpleDateFormat mdformat1 = new SimpleDateFormat("yyyy-MM-dd ");
        str_date1 = mdformat1.format(calendar.getTime());
        tvUpadateAttendanceDate.setText(str_date1);


        attendance_list_helper = crud.NewAttendancList(str_date);

        adapter = new CardStackAdapter(attendance_list_helper, getActivity());
        cardStack.setAdapter(adapter);

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                rowID = Utilities.getSharedPreferences(getActivity()).getString("rowId", "");
//                Toast.makeText(getActivity(), rowID, Toast.LENGTH_SHORT).show();
//            }
//        }, 1000);


        cardStack.setEventCallback(new SwipeDeck.SwipeEventCallback() {
            @Override
            public void cardSwipedLeft(int position) {

                strAttendce = "Absent";

                if (position == 0){
                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber0", "");
                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId0", "");
                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
                }else
                if (position == 1){
                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber1", "");
                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId1", "");
                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
                }else if (position == 2){
                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber2", "");
                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId2", "");
                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
                }else if (position == 3){
                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber3", "");
                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId3", "");
                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
                }else if (position == 4){
                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber4", "");
                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId4", "");
                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
                }
               // Toast.makeText(getActivity(), String.valueOf(intIncrement), Toast.LENGTH_SHORT).show();
//
//                if (position == intIncrement){
//
//                    Utilities.putValueInEditor(getActivity()).putInt("incrementPosition",intIncrement).commit();
//
//                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber", "");
//                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId", "");
//                    String strAttendce = "Absent";
//                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
//                    intIncrement = i += 1;
//                }
            }

            @Override
            public void cardSwipedRight(int position) {
                strAttendce = "Present";

                if (position == 0){
                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber0", "");
                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId0", "");
                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
                }else
                if (position == 1){
                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber1", "");
                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId1", "");
                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
                }else if (position == 2){
                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber2", "");
                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId2", "");
                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
                }else if (position == 3){
                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber3", "");
                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId3", "");
                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
                }else if (position == 4){
                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber4", "");
                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId4", "");
                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
                }

                  // Toast.makeText(getActivity(), String.valueOf(intIncrement), Toast.LENGTH_SHORT).show();
//
//                if (position == intIncrement){
//                    Utilities.putValueInEditor(getActivity()).putInt("incrementPosition",intIncrement).commit();
//                    String strRollNumber = Utilities.getSharedPreferences(getActivity()).getString("rollNumber", "");
//                    String strRowID = Utilities.getSharedPreferences(getActivity()).getString("rowId", "");
//                    String strAttendce = "Present";
//                    crud.UpdateAttendance(strRollNumber, strAttendce, str_date, strRowID);
//                    intIncrement = i += 1;
//                }
            }

            @Override
            public void cardsDepleted() {
                Toast.makeText(getActivity(), "Completed", Toast.LENGTH_SHORT).show();
                Utilities.putValueInEditor(getActivity()).putInt("incrementPosition",0).commit();

            }

            @Override
            public void cardActionDown() {


            }

            @Override
            public void cardActionUp() {

            }
        });

        return parentView;
    }

}