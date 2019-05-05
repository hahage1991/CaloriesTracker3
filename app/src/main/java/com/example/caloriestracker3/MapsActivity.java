package com.example.caloriestracker3;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // findPark();
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        double lat = getLat("60 Lawn road noble park vic Australia");
        double lon = getLng("60 Lawn road noble park vic Australia");
        LatLng Melbourne = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(Melbourne).title("Me"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Melbourne));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11), 2000, null);
        getParks();

    }


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
                        double lat2 = location.getDouble("lat");
                        double lng2 = location.getDouble("lng");

                        String name =  obj.getString("name");
                        LatLng newlocation = new LatLng(lat2,  lng2);
                        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).position(newlocation).title(name));

                    }

                }catch (Exception e){
                    e.printStackTrace();

                }

                //tv_test.setTextColor(Color.RED);

            }
        }.execute();
    }

   // https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-37.962930,145.162420&radius=10000&type=park&keyword=cruise&key=AIzaSyAOUAfjByh7eSEVrv2ygMRrZo6MHLUB5og
//https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyBWIWQZFj_cDRcorR-jip_djf3HR4MTCaY
    public double getLat(String address) {

        Geocoder coder = new Geocoder(getApplicationContext());
        try {
            ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(address, 50);
            for (Address add : adresses) {
                double longitude = add.getLongitude();
                double latitude = add.getLatitude();
                return latitude;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }



    public double getLng(String address) {

        Geocoder coder = new Geocoder(getApplicationContext());
        try {
            ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(address, 50);
            for (Address add : adresses) {
                // if (statement) {//Controls to ensure it is right address such as country etc.
                double longitude = add.getLongitude();
                double latitude = add.getLatitude();
                return longitude;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }




    public void findPark(){

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {


                String url = "https://maps.googleapis.com/maps/api/place/textsearch/json?&location=-37.962930,145.162420&radius=5000&type=park&key=AIzaSyAOUAfjByh7eSEVrv2ygMRrZo6MHLUB5og";
                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray  results = response.getJSONArray("results");
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


                            }




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
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(jor);

                return null;
            }

            protected void onPostExecute(String all)
            {

                //tv_test.setText("hii");
                //tv_test.setTextColor(Color.RED);



            }

        }.execute();
    }
}


