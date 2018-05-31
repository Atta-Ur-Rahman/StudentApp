package com.example.attaurrahman.attendanceapp.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.fragment.StudentAllRecordFragment;
import com.example.attaurrahman.attendanceapp.genralUtills.Utilities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by AttaUrRahman on 2/14/2018.
 */

public class Crud {
    private static SQLiteDatabase database, database1;
    private Context context;

    public Crud(Context context) {
        DBStudent dbStudent = new DBStudent(context);
        database = dbStudent.getWritableDatabase();
        database1 = dbStudent.getWritableDatabase();
        this.context = context;

    }
    ///insert new student record

    public void insertData(String student_name, String student_roll_number, String student_class, String student_father_cnic, String student_attendance, String str_date, String str_date_formate, String strImageUri) {

        ///cursor check roll number and roll number matching in database

        Cursor cursor = this.database.rawQuery("SELECT * FROM STUDENT_TABLE WHERE STUDENT_ROLL_NUMBER = '" + student_roll_number + "' ", null);

        if (cursor.moveToFirst()) {

            Toast.makeText(context, "Already Exist", Toast.LENGTH_SHORT).show();

        } else {

            ///here insert new student in database
            ContentValues values = new ContentValues();
            values.put("STUDENT_NAME", student_name);
            values.put("STUDENT_ROLL_NUMBER", student_roll_number);
            values.put("STUDENT_CLASS", student_class);
            values.put("STUDENT_ATTENDANCE", student_attendance);
            values.put("STUDENT_FATHER_CNIC", student_father_cnic);
            values.put("STUDENT_DATE", str_date);
            values.put("DATE_FORMATE", str_date_formate);
            values.put("IMAGE", strImageUri);

            database.insert("STUDENT_TABLE", null, values);
            Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
            //  Utilities.connectFragmentWithOutBackStack(context,new AddStudentFragment());

        }
    }


///create new database for all student from student_table Tabele for new attendance

    public List CreateNewDabaseAndGetDate(String date) {

        List<String> list = new ArrayList<>();
        String query = "SELECT  * FROM STUDENT_TABLE";
        Cursor cursor = database.rawQuery(query, null);

        ////cursor1 is check data matching
        Cursor cursor1 = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_DATE = '" + date + "' ", null);

        if (cursor1.moveToFirst()) {

            Toast.makeText(context, "Already Attendance Updated", Toast.LENGTH_SHORT).show();

        } else {

            if (cursor.moveToFirst()) {
                do {

                    //getting data from student_table Tabale

                    String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                    String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                    String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                    String str_father_cnic = String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_FATHER_CNIC")));
                    String str_image = (cursor.getString(cursor.getColumnIndex("IMAGE")));

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd ");
                    String str_date_formate = mdformat.format(calendar.getTime());
                    ////insert data from student table in insert into Attendance table

                    ContentValues values = new ContentValues();
                    values.put("STUDENT_NAME", name);
                    values.put("STUDENT_ROLL_NUMBER", roll_number);
                    values.put("STUDENT_CLASS", str_class);
                    values.put("STUDENT_FATHER_CNIC", str_father_cnic);
                    values.put("STUDENT_DATE", date);
                    values.put("DATE_FORMATE", str_date_formate);
                    values.put("IMAGE", str_image);

                    database1.insert("ATTENDANCE_TABLE", null, values);

                }
                while (cursor.moveToNext());
            }
        }
        return list;
    }

    public List<ListHelper> NewAttendancList(String str_date) {
        List<ListHelper> list = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_DATE = '" + str_date + "' ", null);
        if (cursor.moveToFirst()) {

            if (cursor.moveToFirst()) {
                do {
                    ListHelper helpers = new ListHelper();
                    String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                    helpers.setStudent_name(name);
                    String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                    helpers.setStudent_roll_number(Integer.parseInt(roll_number));
                    String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                    helpers.setStudent_class(str_class);
                    String str_attendance = (cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                    helpers.setStudent_attendec(str_attendance);
                    String str_father_cnic = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_FATHER_CNIC"))));
                    helpers.setFather_cnic(Integer.parseInt(str_father_cnic));
                    String date = (cursor.getString(cursor.getColumnIndex("STUDENT_DATE")));
                    helpers.setDate(date);
                    String rowID = (cursor.getString(cursor.getColumnIndex("ID")));
                    helpers.setRowID(rowID);
                    String str_image = (cursor.getString(cursor.getColumnIndex("IMAGE")));
                    helpers.setImge_uri(str_image);
                    list.add(helpers);

                } while (cursor.moveToNext());
            }


        } else {
            Toast.makeText(context, "no exist", Toast.LENGTH_SHORT).show();
        }
        return list;
    }

    public void UpdateAttendance(String roll_number, String attendance, String strDate, String rowID) {
        String whereClause = "ID = '" + rowID + "'";

        ContentValues values = new ContentValues();
        values.put("STUDENT_ATTENDANCE", attendance);
        this.database.update("ATTENDANCE_TABLE", values, whereClause, null);

        String whereClause1 = "STUDENT_ROLL_NUMBER = '" + roll_number + "'";
        ContentValues values1 = new ContentValues();
        values1.put("STUDENT_DATE", strDate);
        values1.put("STUDENT_ATTENDANCE", attendance);
        this.database.update("STUDENT_TABLE", values1, whereClause1, null);


    }

    public List<ListHelper> CheckAllAttendance(String date) {
        List<ListHelper> list = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_DATE = '" + date + "' ", null);
        if (cursor.moveToFirst()) {

            if (cursor.moveToFirst()) {
                do {
                    ListHelper helpers = new ListHelper();
                    String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                    helpers.setStudent_name(name);
                    String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                    helpers.setStudent_roll_number(Integer.parseInt(roll_number));
                    String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                    helpers.setStudent_class(str_class);
                    String str_attendance = (cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                    helpers.setStudent_attendec(str_attendance);
                    list.add(helpers);
                } while (cursor.moveToNext());
            }


        } else {
            Toast.makeText(context, "no exist", Toast.LENGTH_SHORT).show();
        }
        return list;
    }


    public List<ListHelper> father_cnic_data(String cnic) {
        List<ListHelper> list = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM STUDENT_TABLE WHERE STUDENT_FATHER_CNIC = '" + cnic + "' ", null);
        if (cursor.moveToFirst()) {

            if (cursor.moveToFirst()) {
                do {
                    ListHelper helpers = new ListHelper();

                    String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                    helpers.setStudent_name(name);
                    String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                    helpers.setStudent_roll_number(Integer.parseInt(roll_number));
                    String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                    helpers.setStudent_class(str_class);
                    String str_attendance = (cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                    helpers.setStudent_attendec(str_attendance);
                    list.add(helpers);

                    Utilities.putValueInEditor(context).putBoolean("ParentList", true).commit();
                } while (cursor.moveToNext());
            }
        } else {
            Toast.makeText(context, "no exist", Toast.LENGTH_SHORT).show();
        }
        return list;
    }

    public List<ListHelper> CheckAllAttendanceByRollNumber(String strRollNumber) {
        List<ListHelper> list = new ArrayList<>();
        Cursor cursor = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_ROLL_NUMBER = '" + strRollNumber + "' ", null);
        if (cursor.moveToFirst()) {

            if (cursor.moveToFirst()) {
                do {
                    ListHelper helpers = new ListHelper();
                    String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                    helpers.setStudent_name(name);
                    String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                    helpers.setStudent_roll_number(Integer.parseInt(roll_number));
                    String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                    helpers.setStudent_class(str_class);
                    String str_attendance = (cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                    helpers.setStudent_attendec(str_attendance);
                    String str_date_formate = (cursor.getString(cursor.getColumnIndex("DATE_FORMATE")));
                    helpers.setDate_format(str_date_formate);
                    String str_image = (cursor.getString(cursor.getColumnIndex("IMAGE")));
                    helpers.setImge_uri(str_image);
                    list.add(helpers);
                } while (cursor.moveToNext());
            }


        } else {
            Toast.makeText(context, "no exist", Toast.LENGTH_SHORT).show();
        }
        return list;
    }


    public List<ListHelper> StudentAllRecordList() {

        List<ListHelper> list = new ArrayList<>();
        String query = "SELECT  * FROM STUDENT_TABLE";
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {

                ListHelper helpers = new ListHelper();
                String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                helpers.setStudent_name(name);
                String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                helpers.setStudent_roll_number(Integer.parseInt(roll_number));
                String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                helpers.setStudent_class(str_class);
                String str_attendance = (cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                helpers.setStudent_attendec(str_attendance);
                String str_father_cnic = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_FATHER_CNIC"))));
                helpers.setFather_cnic(Integer.parseInt(str_father_cnic));
                String date = (cursor.getString(cursor.getColumnIndex("STUDENT_DATE")));
                helpers.setDate(date);
                String date_format = (cursor.getString(cursor.getColumnIndex("DATE_FORMATE")));
                helpers.setDate_format(date_format);
                String str_image = (cursor.getString(cursor.getColumnIndex("IMAGE")));
                helpers.setImge_uri(str_image);
                list.add(helpers);

            }
            while (cursor.moveToNext());
        }
        return list;
    }


    public void Delete(String roll_number) {

        Cursor cursor = this.database.rawQuery("SELECT * FROM STUDENT_TABLE WHERE STUDENT_ROLL_NUMBER = '" + roll_number + "' ", null);

        if (cursor.moveToFirst()) {
            this.database.delete("STUDENT_TABLE", "STUDENT_ROLL_NUMBER = '" + roll_number + "'", null);
            String strRollnumber = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));

            Toast.makeText(context, "This Roll Number " + strRollnumber + " Deleted", Toast.LENGTH_SHORT).show();
            Utilities.connectFragmentWithOutBackStack(context, new StudentAllRecordFragment());
        } else {
            Toast.makeText(context, "No Exist", Toast.LENGTH_SHORT).show();
        }


    }

    public void UpdateStudentRecord(String roll_number, String strNewName, String strNewRollNumber, String strNewClass, String strNewFatherCNIC) {
        String whereClause = "STUDENT_ROLL_NUMBER = '" + roll_number + "'";

        ContentValues values = new ContentValues();
        values.put("STUDENT_NAME", strNewName);
        values.put("STUDENT_ROLL_NUMBER", strNewRollNumber);
        values.put("STUDENT_CLASS", strNewClass);
        values.put("STUDENT_FATHER_CNIC", strNewFatherCNIC);
        this.database.update("STUDENT_TABLE", values, whereClause, null);

        Utilities.connectFragmentWithOutBackStack(context, new StudentAllRecordFragment());
        Toast.makeText(context, "Record Updated", Toast.LENGTH_SHORT).show();
    }


    public int getCountAttendance(String strRollNumber) {


        String strPresent = "Present";
        String strAbsent = "Absent";

        Cursor cursorCountPresent = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_ROLL_NUMBER = '" + strRollNumber + "' AND STUDENT_ATTENDANCE = '" + strPresent + "'", null);
        Cursor cursorCountAbsent = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_ROLL_NUMBER = '" + strRollNumber + "' AND STUDENT_ATTENDANCE = '" + strAbsent + "'", null);
        Cursor cursorCountClasses = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_ROLL_NUMBER = '" + strRollNumber + "'", null);

        if (cursorCountPresent.moveToFirst()) {
            int i = 0;
            if (cursorCountPresent.moveToFirst()) {
                do {
                    String strIncrement = String.valueOf(i += 1);
                    Utilities.putValueInEditor(context).putString("present", strIncrement).commit();
                } while (cursorCountPresent.moveToNext());
            }
        }
        if (cursorCountAbsent.moveToFirst()) {
            int i = 0;
            if (cursorCountAbsent.moveToFirst()) {
                do {
                    String strIncrement = String.valueOf(i += 1);
                    Utilities.putValueInEditor(context).putString("absent", strIncrement).commit();
                } while (cursorCountAbsent.moveToNext());
            }
        }
        if (cursorCountClasses.moveToFirst()) {
            int i = 0;
            if (cursorCountClasses.moveToFirst()) {
                do {
                    String strIncrement = String.valueOf(i += 1);
                    Utilities.putValueInEditor(context).putString("classes", strIncrement).commit();
                } while (cursorCountClasses.moveToNext());
            }
        } else {
            // Toast.makeText(context, "no exist", Toast.LENGTH_SHORT).show();
        }
        return 0;
    }


    public List<ListHelper> AttendanceBetweenDate(View view,String strRollNumber, String strFirstDate, String strLastDate) {
        List<ListHelper> list = new ArrayList<>();
//1st method
        //Cursor cursor = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_ROLL_NUMBER = '" + strRollNumber + "'AND STUDENT_DATE >= '" + strFirstDate + "' AND STUDENT_DATE <= '" + strLastDate + "'  ", null);

        //2nd method
        Cursor cursor = this.database.rawQuery("SELECT * FROM ATTENDANCE_TABLE WHERE STUDENT_ROLL_NUMBER = '" + strRollNumber + "'AND STUDENT_DATE BETWEEN '" + strFirstDate + "' AND'" + strLastDate + "'  ", null);


        if (cursor.moveToFirst()) {

            if (cursor.moveToFirst()) {
                do {
                    ListHelper helpers = new ListHelper();
                    String name = (cursor.getString(cursor.getColumnIndex("STUDENT_NAME")));
                    helpers.setStudent_name(name);
                    String roll_number = (String.valueOf(cursor.getInt(cursor.getColumnIndex("STUDENT_ROLL_NUMBER"))));
                    helpers.setStudent_roll_number(Integer.parseInt(roll_number));
                    String str_class = (cursor.getString(cursor.getColumnIndex("STUDENT_CLASS")));
                    helpers.setStudent_class(str_class);
                    String str_attendance = (cursor.getString(cursor.getColumnIndex("STUDENT_ATTENDANCE")));
                    helpers.setStudent_attendec(str_attendance);
                    list.add(helpers);

                    Utilities.buttonDeclaration(R.id.btn_submit,view).setVisibility(View.GONE);
                    Utilities.linearLayoutDeclaration(R.id.ll_attendance_between_date_title,view).setVisibility(View.VISIBLE);
                    Utilities.viewDeclaration(R.id.view_between_attendance,view).setVisibility(View.VISIBLE);

                } while (cursor.moveToNext());
            }


        } else {
            Toast.makeText(context, "no exist", Toast.LENGTH_SHORT).show();
        }
        return list;
    }

}
