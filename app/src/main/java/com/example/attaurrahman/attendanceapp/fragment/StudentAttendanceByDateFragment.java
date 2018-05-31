package com.example.attaurrahman.attendanceapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.dataBase.Crud;
import com.example.attaurrahman.attendanceapp.dataBase.ListHelper;
import com.example.attaurrahman.attendanceapp.genralUtills.StudentAttendanceAdapterRV;
import com.example.attaurrahman.attendanceapp.genralUtills.Utilities;
import com.phlox.datepick.CalendarNumbersView;
import com.phlox.datepick.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentAttendanceByDateFragment extends Fragment {

    @BindView(R.id.btn_first_date)
    Button btnFirstDate;
    @BindView(R.id.btn_last_date)
    Button btnLastDate;
    @BindView(R.id.et_student_roll_number)
    EditText etRollNumber;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    String strFirstDate, strLastDate, strRollNumber;

    @BindView(R.id.rv_attendance_between)
    RecyclerView rvAttendanceBetweenDate;

    private PopupWindow calendarPopup;

    Crud crud;
    List<ListHelper> student_attendance_listHelpers;

    String strDate, strFormateDate;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View parentView = inflater.inflate(R.layout.fragment_student_attendance_by_date, container, false);

        crud = new Crud(getActivity());
        ButterKnife.bind(this, parentView);


        rvAttendanceBetweenDate.setHasFixedSize(true);
        rvAttendanceBetweenDate.setLayoutManager(new LinearLayoutManager(getActivity()));


        Utilities.putValueInEditor(getActivity()).putBoolean("AttendanceList", false).commit();
        Utilities.putValueInEditor(getActivity()).putBoolean("attendance_list",true).commit();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strRollNumber = etRollNumber.getText().toString();

                student_attendance_listHelpers = crud.AttendanceBetweenDate(parentView, strRollNumber, strFirstDate, strLastDate);
                StudentAttendanceAdapterRV adapter = new StudentAttendanceAdapterRV(student_attendance_listHelpers, getActivity());
                rvAttendanceBetweenDate.setAdapter(adapter);


            }
        });


        btnFirstDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFirstDate.setText("");
                if (calendarPopup == null) {
                    calendarPopup = new PopupWindow(getActivity());
                    CalendarPickerView calendarView = new CalendarPickerView(getActivity());
                    calendarView.setListener(dateSelectionListener);
                    calendarPopup.setContentView(calendarView);
                    calendarPopup.setWindowLayoutMode(
                            View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    calendarPopup.setHeight(1);
                    calendarPopup.setWidth(view.getWidth());
                    calendarPopup.setOutsideTouchable(true);
                }
                calendarPopup.showAsDropDown(view);

            }
        });
        btnLastDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLastDate.setText("");
                if (calendarPopup == null) {
                    calendarPopup = new PopupWindow(getActivity());
                    CalendarPickerView calendarView = new CalendarPickerView(getActivity());
                    calendarView.setListener(dateSelectionListener);
                    calendarPopup.setContentView(calendarView);
                    calendarPopup.setWindowLayoutMode(
                            View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                            ViewGroup.LayoutParams.WRAP_CONTENT);
                    calendarPopup.setHeight(1);
                    calendarPopup.setWidth(view.getWidth());
                    calendarPopup.setOutsideTouchable(true);
                }
                calendarPopup.showAsDropDown(view);
            }
        });


        return parentView;
    }



    private CalendarNumbersView.DateSelectionListener dateSelectionListener = new CalendarNumbersView.DateSelectionListener() {
        @Override
        public void onDateSelected(Calendar selectedDate) {
            if (calendarPopup.isShowing()) {
                calendarPopup.getContentView().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calendarPopup.dismiss();
                    }
                }, 100);//For clarity, we close the popup not immediately.
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            strDate = (formatter.format(selectedDate.getTime()));

            SimpleDateFormat formatterButtonDate = new SimpleDateFormat("yyyy-MM-dd");
            strFormateDate = (formatterButtonDate.format(selectedDate.getTime()));

            btnSubmit.setVisibility(View.VISIBLE);
            if (btnFirstDate.getText().toString().length() == 0) {
                btnFirstDate.setText(strFormateDate);
                strFirstDate = strDate;
            } else{
                btnLastDate.setText(strFormateDate);
                strLastDate = strDate;
            }



//            Utilities.putValueInEditor(getActivity()).putString("Date",str_date).commit();


        }
    };

}
