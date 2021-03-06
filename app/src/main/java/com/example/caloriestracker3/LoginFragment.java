package com.example.caloriestracker3;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;




public class LoginFragment extends Fragment {

    private View vLogin;
    private EditText et_userName;
    private EditText et_password;
    private Button bt_sign;
    private EditText et_res;

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

        et_userName = (EditText) vLogin.findViewById(R.id.et_userName);

         et_password = (EditText) vLogin.findViewById(R.id.et_password);
        et_res = (EditText) vLogin.findViewById(R.id.et_res);
         bt_sign = (Button) vLogin.findViewById(R.id.bt_sign);
        bt_sign.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view)
                                  {
                                      compare();
                                  }
                              }
        );


        //tv_register.setOnClickListener(this);

        //et_userName.setText(RestClient.findAllCredential());

        return vLogin;
    }

    private void compare()
    {

        new AsyncTask<Void, Void,String >() {
            @Override
            protected String  doInBackground(Void... params)
            {
                String pas2 = RestClient.getPasswordByUsername( String.valueOf(et_userName.getText()));
                return pas2;

            }
            @Override
            protected void onPostExecute(String  pas2)
            {
                String pas1 =  md5(String.valueOf(et_password.getText()));
                if (pas1.equals(pas2))
                {
                    Fragment nextFragment = new HomeFragment();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_frame, nextFragment).commit();
                     Bundle bundle = new Bundle();
                     bundle.putString("username", String.valueOf(et_userName.getText()));
                    nextFragment.setArguments(bundle);


                   // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                   // drawer.closeDrawer(GravityCompat.START);

                    et_res.setText("successful");
                }
                else
                {
                    et_res.setText("Wrong username or password");
                }
            }
        }.execute();






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
