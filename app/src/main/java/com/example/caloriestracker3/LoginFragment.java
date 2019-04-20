package com.example.caloriestracker3;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;




public class LoginFragment extends Fragment {

    private View vLogin;

    // private EditText et_username;

    // private EditText et_password;
    //  private Button b_sign;

    //private String passwordHash;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        super.onCreate(savedInstanceState);

        vLogin = inflater.inflate(R.layout.fragment_login, container, false);

        // et_username = (EditText) vLogin.findViewById(R.id.et_username);

        //  et_password = (EditText) vLogin.findViewById(R.id.et_password);
        // b_sign = (Button) vLogin.findViewById(R.id.b_sign);


        //tv_register.setOnClickListener(this);



        return vLogin;
    }


    private String md5(String s) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(s.getBytes(), 0, s.length());
            BigInteger i = new BigInteger(1, m.digest());
            return String.format("%1$032x", i);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
