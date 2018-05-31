package com.example.attaurrahman.attendanceapp.fragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class CheckAttendanceFragment extends Fragment {
    View parentView;

    Crud crud;
    List<ListHelper> student_attendance_listHelpers;


    @BindView(R.id.btnSetDate)
    Button btnSetDate;
    @BindView(R.id.rv_student_check_attendance)RecyclerView rvStudentCheckAttendance;

    private PopupWindow calendarPopup;
    String str_date, str_button_date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_check_attendance, container, false);


        ButterKnife.bind(this, parentView);
        crud = new Crud(getActivity());

        rvStudentCheckAttendance.setHasFixedSize(true);
        rvStudentCheckAttendance.setLayoutManager(new LinearLayoutManager(getActivity()));

        Utilities.putValueInEditor(getActivity()).putBoolean("AttendanceList",false).commit();
        Utilities.putValueInEditor(getActivity()).putBoolean("attendance_list",true).commit();

        str_date = Utilities.getSharedPreferences(getActivity()).getString("Date","");
        student_attendance_listHelpers = crud.CheckAllAttendance(str_date);
        StudentAttendanceAdapterRV adapter = new StudentAttendanceAdapterRV(student_attendance_listHelpers,getActivity());
        rvStudentCheckAttendance.setAdapter(adapter);
        btnSetDate.setOnClickListener(onButtonClickListener);

        return parentView;
    }

    private View.OnClickListener onButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
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
    };

    private CalendarNumbersView.DateSelectionListener dateSelectionListener = new CalendarNumbersView.DateSelectionListener() {
        @Override
        public void onDateSelected(Calendar selectedDate) {
            if (calendarPopup.isShowing()) {
                calendarPopup.getContentView().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calendarPopup.dismiss();
                    }
                }, 500);//For clarity, we close the popup not immediately.
            }


            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            str_date = (formatter.format(selectedDate.getTime()));

            SimpleDateFormat formatterButtonDate = new SimpleDateFormat("yyyy-MM-dd");
            str_button_date = (formatterButtonDate.format(selectedDate.getTime()));
            btnSetDate.setText(str_button_date);


            student_attendance_listHelpers = crud.CheckAllAttendance(str_date);
            StudentAttendanceAdapterRV adapter = new StudentAttendanceAdapterRV(student_attendance_listHelpers,getActivity() );
            rvStudentCheckAttendance.setAdapter(adapter);

            Utilities.putValueInEditor(getActivity()).putString("Date",str_date).commit();


        }
    };
}
