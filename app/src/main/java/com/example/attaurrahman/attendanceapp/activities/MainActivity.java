package com.example.attaurrahman.attendanceapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.attaurrahman.attendanceapp.R;
import com.example.attaurrahman.attendanceapp.fragment.FirstLogInFragment;
import com.example.attaurrahman.attendanceapp.genralUtills.Utilities;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Utilities.connectFragmentWithOutBackStack(this,new FirstLogInFragment());
    }
}
