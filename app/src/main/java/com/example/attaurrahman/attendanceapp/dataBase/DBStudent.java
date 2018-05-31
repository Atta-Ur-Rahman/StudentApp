package com.example.attaurrahman.attendanceapp.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by AttaUrRahman on 2/14/2018.
 */

public class DBStudent extends SQLiteOpenHelper {

    private static String DB_NAME = "STUDENT";
    public static int DB_VERSION = 1;

    public DBStudent(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query =  "CREATE TABLE STUDENT_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, STUDENT_NAME TEXT,STUDENT_CLASS TEXT, " +
                "STUDENT_ROLL_NUMBER INTEGER,STUDENT_ATTENDANCE TEXT,STUDENT_FATHER_CNIC INTEGER,STUDENT_DATE INTEGER,DATE_FORMATE TEXT,IMAGE BLOB)";

        String query1 =  "CREATE TABLE ATTENDANCE_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, STUDENT_NAME TEXT,STUDENT_CLASS TEXT, " +
                "STUDENT_ROLL_NUMBER INTEGER,STUDENT_ATTENDANCE TEXT,STUDENT_FATHER_CNIC INTEGER,STUDENT_DATE INTEGER,DATE_FORMATE TEXT,IMAGE BLOB)";
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL(query1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
