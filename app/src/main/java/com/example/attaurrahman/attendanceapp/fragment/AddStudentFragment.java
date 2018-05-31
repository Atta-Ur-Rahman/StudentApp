package com.example.attaurrahman.attendanceapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.dataBase.Crud;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class AddStudentFragment extends Fragment {
    View parentView;
    Crud crud;
    Uri image_uri;
    String str_student_name, str_student_roll_number, str_student_class, str_student_father_cnic, str_student_attendce, str_date,str_date_formate;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.etStudentName)
    EditText etStudentName;
    @BindView(R.id.etClass)
    EditText etClass;
    @BindView(R.id.etRollNumber)
    EditText etRollNumber;
    @BindView(R.id.etFatherCNIC)
    EditText etFatherCNIC;
    @BindView(R.id.iv_add_image)ImageView ivAddImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_add_student, container, false);

        crud = new Crud(getActivity());
        ButterKnife.bind(this, parentView);

        ivAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);//one can be replaced with any action code
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etStudentName.length() < 2) {
                    Toast.makeText(getActivity(), "Enter name", Toast.LENGTH_SHORT).show();
                } else if (etRollNumber.length() < 0) {
                    Toast.makeText(getActivity(), "Enter Roll Number", Toast.LENGTH_SHORT).show();
                } else if (etClass.length() < 0) {
                    Toast.makeText(getActivity(), "Enter Class", Toast.LENGTH_SHORT).show();
                } else if (etFatherCNIC.length() < 0) {
                    Toast.makeText(getActivity(), "Enter Father CNIC", Toast.LENGTH_SHORT).show();
                } else{

                    str_student_name = etStudentName.getText().toString();
                    str_student_roll_number = etRollNumber.getText().toString();
                    str_student_class = etClass.getText().toString();
                    str_student_father_cnic = etFatherCNIC.getText().toString();

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyyMMdd ");
                    str_date = mdformat.format(calendar.getTime());

                    SimpleDateFormat mdformat1 = new SimpleDateFormat("yyyy-MM-dd ");
                    str_date_formate = mdformat1.format(calendar.getTime());

                    String image =image_uri.toString();

                    crud.insertData(str_student_name, str_student_roll_number, str_student_class, str_student_father_cnic, null, str_date,str_date_formate,image);

                }
            }
        });


        return parentView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {


            case 0:
                if (resultCode == RESULT_OK) {


                    Bitmap bm = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                    File sourceFile = new File(Environment.getExternalStorageDirectory(),
                            System.currentTimeMillis() + ".jpg");
                    FileOutputStream fo;
                    try {
                        sourceFile.createNewFile();
                        fo = new FileOutputStream(sourceFile);
                        fo.write(bytes.toByteArray());
                        fo.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ivAddImage.setImageBitmap(bm);





                } else {

                    Toast.makeText(getActivity(), "No Image Selected", Toast.LENGTH_SHORT).show();
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    image_uri = imageReturnedIntent.getData();
                    ivAddImage.setImageURI(image_uri);


                } else {
                    Toast.makeText(getActivity(), "No Image Selected", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }


}
