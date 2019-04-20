package com.example.caloriestracker3;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {

    private View vHome;
    private EditText tv_date;

    // private EditText et_username;

    // private EditText et_password;
    //  private Button b_sign;

    //private String passwordHash;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        super.onCreate(savedInstanceState);

        vHome = inflater.inflate(R.layout.content_main, container, false);
        tv_date = (EditText) vHome.findViewById(R.id.tv_date);

        //Calendar calendar = Calendar.getInstance();
       // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
       // tv_date.setText(formatter.format(calendar.getTime()));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        tv_date.setText(dateFormat.format(date));


        // et_username = (EditText) vLogin.findViewById(R.id.et_username);

        //  et_password = (EditText) vLogin.findViewById(R.id.et_password);
        // b_sign = (Button) vLogin.findViewById(R.id.b_sign);


        //tv_register.setOnClickListener(this);


        return vHome;
    }
}