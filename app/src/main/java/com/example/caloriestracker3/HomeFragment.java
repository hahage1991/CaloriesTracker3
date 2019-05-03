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

public class HomeFragment extends Fragment {

    private View vHome;
    private TextView tv_date;
    private TextView tv_test;

    // private EditText et_username;

    // private EditText et_password;
    //  private Button b_sign;

    //private String passwordHash;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        //super.onCreate(savedInstanceState);

        vHome = inflater.inflate(R.layout.fragment_home, container, false);
        tv_date = (TextView) vHome.findViewById(R.id.tv_date);
        tv_test = (TextView) vHome.findViewById(R.id.tv_test);

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
        //tv_test.setText("all1");
        getConsumptionByID();
        return vHome;
    }

    public void getConsumptionByID() {
        //create an anonymous AsyncTask
        //(http://localhost:8080/NewC/webresources/newc.consumption/{id})
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params)
            {
                return RestClient.findAllConsumption("1");                     //RestClient.getResidByuserame(loginUsername)
            }

            @Override
            protected void onPostExecute(String all)
            {

                    tv_test.setText(all);
                    //tv_test.setTextColor(Color.RED);



            }
        }.execute();
    }
}