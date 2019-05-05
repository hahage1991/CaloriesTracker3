package com.example.caloriestracker3;


import java.util.ArrayList;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

public class BarchartFragment extends Fragment {
    private BarChart barChart ;
    private ArrayList<BarEntry> entries ;
    private ArrayList<String> BarEntryLabels ;
    private BarDataSet barDataSet ;
    private BarData barData ;
    private Spinner s_select;
    private View vBarchart;
    String loginUsername;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        loginUsername = sharedPref.getString("username", "not logged in");
        vBarchart = inflater.inflate(R.layout.fragment_barchart, container, false);
        s_select = (Spinner) vBarchart .findViewById(R.id.s_select);

        s_select.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(s_select.getSelectedItem().toString().equalsIgnoreCase("Hourly"))
                    setValuesHourly();
                if(s_select.getSelectedItem().toString().equalsIgnoreCase("Daily"))
                    setValuesDaily();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        return vBarchart;
    }

    public void setValuesHourly()
    {
        new AsyncTask<Void, Void,float[] >() {
            @Override
            protected float[]  doInBackground(Void... params)
            {
                float[] usages = new float[24];
               // usages = RestClient.getHourlyUsageArray(2, "2018-2-13"); //RestClient.getResidByuserame()
                return usages;
            }
            @Override
            protected void onPostExecute(float[]  usages)
            {

                barChart = (BarChart) vBarchart.findViewById(R.id.chart1);
                entries = new ArrayList<BarEntry>();

                BarEntryLabels = new ArrayList<String>();
                for (int i = 0; i <= 23; i++ )
                {
                    entries.add(new BarEntry(i, usages[i]));
                }


                barDataSet = new BarDataSet(entries,"");

                barData = new BarData(barDataSet);

                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                barChart.setData(barData);

                barChart.animateY(3000);



            }
        }.execute();
    }

    public void setValuesDaily()
    {
        new AsyncTask<Void, Void,float[] >() {
            @Override
            protected float[]  doInBackground(Void... params)
            {
                float[] usages = new float[31];
                for (int j = 0; j < 31; j++ )
                {
                  //  usages[j] = RestClient.getDailyUsage(2,"2018-02" + "-" + (j+1)); //example //calender
                }
                return usages;


            }
            @Override
            protected void onPostExecute(float[]  usages)
            {

                barChart = (BarChart) vBarchart.findViewById(R.id.chart1);
                entries = new ArrayList<BarEntry>();

                BarEntryLabels = new ArrayList<String>();
                for (int i = 0; i < 31; i++ )
                {
                    entries.add(new BarEntry(i, usages[i]));
                }

                barDataSet = new BarDataSet(entries,"");

                barData = new BarData(barDataSet);

                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                barChart.setData(barData);

                barChart.animateY(3000);


            }
        }.execute();
    }





}
