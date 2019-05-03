package com.example.caloriestracker3;


import android.app.Fragment;
import android.os.AsyncTask;
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
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MapFragment extends Fragment {

    private View vMap;


    // private EditText et_username;

    // private EditText et_password;
    //  private Button b_sign;

    //private String passwordHash;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        super.onCreate(savedInstanceState);

        vMap = inflater.inflate(R.layout.fragment_map, container, false);

        return vMap;
    }


}
