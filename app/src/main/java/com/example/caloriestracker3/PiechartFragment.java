package com.example.caloriestracker3;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

public class PiechartFragment extends Fragment implements View.OnClickListener {
    private PieChart pieChart ;
    private ArrayList<PieEntry> entries ;
    private ArrayList<String> PieEntryLabels ;
    private PieDataSet pieDataSet ;
    private PieData pieData ;
    private View vPiechart;
    private Calendar myCalendar;
    private TextView tv_selectDate;
    private String selectedDate;
    private String loginUsername;
    private Integer loginResid;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        loginUsername = sharedPref.getString("username", "not logged in");
        vPiechart = inflater.inflate(R.layout.fragment_piechart, container, false);
        myCalendar = Calendar.getInstance();
        tv_selectDate = (TextView) vPiechart.findViewById(R.id.tv_selectDate);
        tv_selectDate.setOnClickListener(this);


        return vPiechart;
    }

    public void setValues()
    {
        new AsyncTask<Void, Void,float[] >() {
            @Override
            protected float[]  doInBackground(Void... params)
            {
               // loginResid = RestClient.getResidByuserame(loginUsername);
                float[] usages = new float[3];
                usages[0] = RestClient.getCaloriesBurned(selectedDate,1);
                 usages[1] = RestClient. getlCaloriesConsumed(selectedDate,1);
                 usages[2] = RestClient.getlRemainingCalorie(selectedDate,1);
               // usages[0] = RestClient.getFrideUsage(selectedDate,loginResid);
               // usages[1] = RestClient.getAcUsage(selectedDate,loginResid);
              //  usages[2] = RestClient.getWmUsage(selectedDate,loginResid);
                return usages;
            }
            @Override
            protected void onPostExecute(float[]  usages)
            {
                pieChart = (PieChart) vPiechart.findViewById(R.id.chart1);

                entries = new ArrayList<PieEntry>();
                entries.add(new PieEntry(usages[0], "Total calories burned"));
                entries.add(new PieEntry(usages[1], "Total calories consumed,"));
                entries.add(new PieEntry(usages[2], "The remaining calorie"));

                PieEntryLabels = new ArrayList<String>();

                AddValuesToPieEntryLabels();

                pieDataSet = new PieDataSet(entries,"Daily usage");

                pieData = new PieData(pieDataSet);

                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                pieChart.setData(pieData);

                pieChart.animateY(3000);

            }
        }.execute();
    }


    public void AddValuesToPieEntryLabels(){

        PieEntryLabels.add("Fridge");
        PieEntryLabels.add("Air conditioner");
        PieEntryLabels.add("Wash machine");;

    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };



    @Override
    public void onClick(View v) {

        new DatePickerDialog(this.getActivity(), date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void updateLabel()
    {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        //tv_selectDate.setText(sdf.format(myCalendar.getTime()));
        selectedDate = sdf.format(myCalendar.getTime());
        setValues();
    }



}