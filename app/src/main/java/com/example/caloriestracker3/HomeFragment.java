package com.example.caloriestracker3;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class HomeFragment extends Fragment {


    private View vHome;
    private TextView tv_date;
    private TextView tv_test;
    private Button b2;
    private JSONArray parks;
    private TextView tv_user;
    private String loginUser;

    // private EditText et_username;

    // private EditText et_password;
    //  private Button b_sign;

    //private String passwordHash;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        //super.onCreate(savedInstanceState);
        findPark2();

        vHome = inflater.inflate(R.layout.fragment_home, container, false);
        Bundle bundle = getArguments();
        loginUser = bundle.getString("username");
        tv_date = (TextView) vHome.findViewById(R.id.tv_date);
        tv_user = (TextView) vHome.findViewById(R.id.tv_user);
        tv_test = (TextView) vHome.findViewById(R.id.tv_test);
        b2 = (Button) vHome.findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view)
                                  {
                                      Intent map = new Intent(getActivity(),MapsActivity.class);
                                      // Bundle b = new Bundle();
                                      //b.putString("Array",parks.toString());
                                      // map.putExtras(b);

                                      startActivity(map);
                                  }
                              }
            );


        //Calendar calendar = Calendar.getInstance();
       // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
       // tv_date.setText(formatter.format(calendar.getTime()));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        tv_date.setText(dateFormat.format(date));

        tv_user.setText("Welcome " + loginUser);
        tv_user.setTextSize(20);
        tv_user.setTextColor(Color.BLUE);


        // et_username = (EditText) vLogin.findViewById(R.id.et_username);

        //  et_password = (EditText) vLogin.findViewById(R.id.et_password);
        // b_sign = (Button) vLogin.findViewById(R.id.b_sign);


        //tv_register.setOnClickListener(this);
        //tv_test.setText("all1");
       // getConsumptionByID();
       // findPark2();
      //  getParks();
    //  onClick(b2);

        return vHome;
    }
/*
    public void onClick(View v)
    {
        if (v == b2)
        {

         //   tv_test.setText( "hi");
            Intent map = new Intent(getActivity(),MapsActivity.class);
           // Bundle b = new Bundle();
            //b.putString("Array",parks.toString());
          // map.putExtras(b);

             startActivity(map);


        }
        return;

    }*/

    //private static final String BASE_URI = "http://118.138.108.194:8080/smartERDB/webresources";


    public static String findPark3() {
        //final String methodPath = "/smarter.usage/findByResid/2";
       // final String methodPath = "/smarter.usage/";
//initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL("https://maps.googleapis.com/maps/api/place/textsearch/json?&location=-37.962930,145.162420&radius=5000&type=park&key=AIzaSyAOUAfjByh7eSEVrv2ygMRrZo6MHLUB5og");

//open the connection
            conn = (HttpURLConnection) url.openConnection();
//set the timeout
            conn.setReadTimeout(10000);  //?
            conn.setConnectTimeout(15000); //set the connection method to GET
            conn.setRequestMethod("GET");
//add http headers to set your response type to json
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json"); //Read the response
            Scanner inStream = new Scanner(conn.getInputStream()); //read the input steream and store it as string
            while (inStream.hasNextLine()) {
                textResult += inStream.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return textResult;


    }

    public void getParks() {
        //create an anonymous AsyncTask
        //(http://localhost:8080/NewC/webresources/newc.consumption/{id})
        new AsyncTask<Void, Void, JSONArray>() {
            @Override
            protected JSONArray doInBackground(Void... params)
            {
                try
                {
                    JSONObject res = new JSONObject(findPark3());
                    JSONArray  results = res.getJSONArray("results");


                    return results;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return null;
                }

            }

            @Override
            protected void onPostExecute( JSONArray all)
            {

                try{


                    for (int i = 0 ; i <  all.length(); i++) {
                        JSONObject obj =  all.getJSONObject(i);
                        JSONObject geo = obj.getJSONObject("geometry");
                        JSONObject location = geo.getJSONObject("location");
                        //locations.add(location);
                        double lat = location.getDouble("lat");
                        double lng = location.getDouble("lng");

                        String name =  obj.getString("name");
                        tv_test.setText(name);

                        // String B = obj.getString("B");
                        //String C = obj.getString("C");
                        // System.out.println(A + " " + B + " " + C);
                        // tv_test.setText(String.valueOf(parks));



                    }


                }catch (Exception e){
                    e.printStackTrace();

                }


                //tv_test.setTextColor(Color.RED);



            }
        }.execute();
    }

/*
    public static float getFrideUsage(String date,int resid)
    {
        float usage = 0;
        JSONArray jsonArray = null;
        // JSONObject oneObject = null;
        try{
            jsonArray = new JSONArray(findAllUsage("findDailyUsageOfAppliances/" + resid +"/" + date + "?"));
            JSONObject oneObject =  jsonArray.getJSONObject(0);

            usage = Float.valueOf(oneObject.getString("fridge"));

        }catch (Exception e){ e.printStackTrace();
            usage  = (float) 0.0;
        }
        return usage;

    }*/
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

                 //   tv_test.setText(all);
                    //tv_test.setTextColor(Color.RED);



            }
        }.execute();
    }
    // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-37.962930,145.162420&radius=10000&type=park&keyword=cruise&fields=photos,formatted_address,name,rating,opening_hours,geometry&key=AIzaSyAOUAfjByh7eSEVrv2ygMRrZo6MHLUB5og
//&fields=photos,formatted_address,name,rating,opening_hours,geometry
//https://maps.googleapis.com/maps/api/place/textsearch/json?query=123+main+street&location=-37.962930,145.162420&radius=10000&type=park&key=AIzaSyAOUAfjByh7eSEVrv2ygMRrZo6MHLUB5og
    //https://maps.googleapis.com/maps/api/place/textsearch/json?&location=-37.962930,145.162420&radius=5000&type=park&key=AIzaSyAOUAfjByh7eSEVrv2ygMRrZo6MHLUB5og
    public void findPark2(){

        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {


                // JSONArray parks1 = new JSONArray();
                String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?&location=-37.962930,145.162420&radius=5000&type=park&key=AIzaSyAOUAfjByh7eSEVrv2ygMRrZo6MHLUB5og";
                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray  results = response.getJSONArray("results");
                            parks = results;
                            JSONArray parks1 = new JSONArray();
                           // parks1 = results;
                            for (int i = 0 ; i <  results .length(); i++) {
                                JSONObject obj =  results .getJSONObject(i);
                                JSONObject geo = obj.getJSONObject("geometry");
                                JSONObject location = geo.getJSONObject("location");
                                //locations.add(location);
                                double lat = location.getDouble("lat");
                                double lng = location.getDouble("lng");

                                String A =  obj.getString("name");

                               // String B = obj.getString("B");
                                //String C = obj.getString("C");
                               // System.out.println(A + " " + B + " " + C);
                              // tv_test.setText(String.valueOf(parks));



                            }





                           // JSONObject main_object = response.getJSONObject("results");
                           // JSONObject array =
                            //JSONArray array = response.getJSONArray("location");
                           // JSONObject object = array.getJSONObject(0);
                           // String lat = String.valueOf(main_object.getDouble("lat"));
                           // String description = object.getString("description");
                            //String city = response.getString("name");
                           // System.out.println(String.valueOf( main_object));
                           // System.out.println(String.valueOf( array ));
                           // System.out.println(String.valueOf( object ));
                         //   System.out.println(lat);







                        } catch (JSONException e)
                        {
                            e.printStackTrace();

                        }



                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
                );
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                queue.add(jor);
                return null;

               //return url;
            }

            protected void onPostExecute(String res)
            {

                  tv_test.setText(res);
                //tv_test.setTextColor(Color.RED);



            }

        }.execute();
    }




}