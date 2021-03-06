package com.example.attaurrahman.attendanceapp.genralUtills;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.attaurrahman.attendanceapp.R;

import java.util.regex.Pattern;

/**
 * Created by kashif on 1/10/18.
 */

public class Utilities {
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static Button button;
    public static TextView textView;
    public static EditText editText;
    public static LinearLayout linearLayout;
    public static RadioButton radioButton;
    public static RadioGroup radioGroup;
    public static TextWatcher textWatcher;
    public static View view;
    private static final Pattern EMAIL_PATTERN = Pattern
            .compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");



    public static SharedPreferences.Editor putValueInEditor(Context context){
        sharedPreferences = getSharedPreferences(context);
        editor = sharedPreferences.edit();
        return editor;
    }
    public static SharedPreferences getSharedPreferences(Context context){
        //sharedPreferences = context.getSharedPreferences(Configuration.MY_PREF, 0);
        return context.getSharedPreferences(Configurations.MY_PREF, 0);
    }
    public static Fragment connectFragment(Context context, Fragment fragment) {
        ((AppCompatActivity) context).getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("true").commit();
        return fragment;
    }
    public static Fragment connectFragmentWithOutBackStack(Context contextwithout, Fragment fragmentWithOut) {
        ((AppCompatActivity) contextwithout).getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentWithOut).commit();
        return fragmentWithOut;
    }
    public static Fragment connectFragmentForMyComplaint(Context context, Fragment fragment) {
        ((AppCompatActivity) context).getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        return fragment;
    }

    public static Button buttonDeclaration(int buttonID, View view) {
        button = view.findViewById(buttonID);
        return button;
    }

    public static View viewDeclaration(int viewID, View view) {
        view = view.findViewById(viewID);
        return view;
    }

    public static TextView textViewDeclaration(int textViewID, View view) {
        textView = view.findViewById(textViewID);

        //    movieList = new ArrayList<>();
//    postCardListAdapter = new BooksAdapter(movieList);
//    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(postCardListAdapter);
//    prepareMovieData();

        return textView;
    }
    public static EditText editTextDeclaration(int edtihTextID, View view){

        editText = view.findViewById(edtihTextID);

        return editText;
    }

    public static LinearLayout linearLayoutDeclaration(int linearLayoutID,View view ){
        linearLayout = view.findViewById(linearLayoutID);
        return linearLayout;
    }
    public static RadioButton radioButtonDeclaration(int radioButtonID, View view ){
        radioButton = view.findViewById(radioButtonID);
        return radioButton;
    }

    public static RadioGroup radioGroupDeclaration(int radioGroupID, View view ){
        radioGroup = view.findViewById(radioGroupID);
        return radioGroup;
    }
    public static TextWatcher textWatcherDeclaration(int textWatcherID, View view){
        textWatcher = view.findViewById(textWatcherID);
        return textWatcher;
    }
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


    private static class Configurations {
        public static final String MY_PREF = null;
    }
}
