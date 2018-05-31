package com.example.attaurrahman.attendanceapp.dataBase;

/**
 * Created by AttaUrRahman on 2/15/2018.
 */

public class ListHelper {
    private String student_name;
    private String student_class;
    private String student_attendance;
    private int student_roll_number;
    private int father_cnic;
    private String date;
    private String date_format;
    private String rowID;
    private String imge_uri;


    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_name() {
        return this.student_name;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getStudent_class() {
        return this.student_class;
    }

    public void setStudent_attendec(String student_attendance) {
        this.student_attendance = student_attendance;
    }

    public String getStudent_attendec() {
        return this.student_attendance;
    }

    public void setStudent_roll_number(int student_roll_number) {
        this.student_roll_number = student_roll_number;
    }

    public int getStudent_roll_number() {
        return this.student_roll_number;
    }


    public void setFather_cnic(int father_cnic) {
        this.father_cnic = father_cnic;
    }

    public int getFather_cnic() {
        return this.father_cnic;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }


    public void setDate_format(String date_format) {
        this.date_format = date_format;
    }

    public String getDate_format() {
        return this.date_format;
    }

    public void setRowID(String rowID) {
        this.rowID = rowID;
    }
    public String getRowID() {
        return this.rowID;
    }

    public void setImge_uri(String imge_uri) {
        this.imge_uri = imge_uri;
    }

    public String getImge_uri() {
        return this.imge_uri;
    }
}
