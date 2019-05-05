package com.example.caloriestracker3;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DietFragment extends Fragment {

    private View vDiet;

    private EditText et_food;
    private TextView tv_info;
    private ImageView foodpic;
    private Button bt_search;

    // private EditText et_password;
    //  private Button b_sign;

    //private String passwordHash;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        super.onCreate(savedInstanceState);

        vDiet = inflater.inflate(R.layout.fragment_diet, container, false);

        // et_username = (EditText) vLogin.findViewById(R.id.et_username);

        et_food = (EditText) vDiet.findViewById(R.id.et_food);
        tv_info = (TextView) vDiet.findViewById(R.id.tv_info);
        foodpic = (ImageView) vDiet.findViewById(R.id.foodpic);
        bt_search = (Button) vDiet.findViewById(R.id.bt_search);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
            final String keyword = et_food.getText().toString(); //create an anonymous AsyncTask

        new AsyncTask<String, Void, JSONArray>() {
                @Override
                protected JSONArray doInBackground(String... params) {try
                {
                    JSONArray  res = new JSONArray ("["+SearchGoogleAPI.search(keyword)+"]");

                    // JSONArray  results = res.getJSONArray("cse_image");
                    // JSONArray res = new JSONArray(findpic());

                    //  JSONArray  results = res.getJSONArray(0);


                    return res;
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

                    JSONObject obj1 =  all.getJSONObject(0);
                    JSONArray items =  obj1.getJSONArray("items");

                    JSONObject obj2 =  items.getJSONObject(1);
                    JSONObject pagemap =  obj2.getJSONObject("pagemap");
                    JSONArray img =  pagemap.getJSONArray("cse_image");
                    JSONObject obj3 =  img.getJSONObject(0);
                    String pic1 =  obj3.getString("src");


                    //  tv_info.setText(String.valueOf(pic1));


                    //  for (int i = 0 ; i <  all.length(); i++) {
                    //     JSONObject obj1 =  all.getJSONObject(0);
                    //   JSONArray picArray = obj1.getJSONArray("cse_image");
                    //JSONObject pic1obj = all.getJSONObject(0);
                    //locations.add(location);
                    // double lat2 = location.getDouble("lat");
                    //double lng2 = location.getDouble("lng");
                    //   String pic1 =  pic1obj.getString("src");

                    // String pic1 = "https://www.foodiesfeed.com/wp-content/uploads/2019/02/raw-lean-beef-meat-with-herbs-and-spices.jpg";
                    //  LatLng newlocation = new LatLng(lat2,  lng2);
                    Picasso.get().load(pic1).into(foodpic);

                    //  }

                }catch (Exception e){
                    e.printStackTrace();

                }

                //tv_test.setTextColor(Color.RED);

            }
        }.execute();
        }






        });


        return vDiet;

}
}



/*

    public static String findpic(String foodName) {
        //final String methodPath = "/smarter.usage/findByResid/2";
        // final String methodPath = "/smarter.usage/";
//initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?q="+foodName+"&cx=000737364164595517200:49pdk4kd-bg&key=AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs");

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
*/

/*
    public void setpic(String food) {
        //create an anonymous AsyncTask
        //(http://localhost:8080/NewC/webresources/newc.consumption/{id})
        new AsyncTask<String, Void, JSONArray>() {
            @Override
            protected JSONArray doInBackground(String...param)
            {
                try
                {
                    JSONArray  res = new JSONArray ("["+findpic("noodles")+"]");

                   // JSONArray  results = res.getJSONArray("cse_image");
                   // JSONArray res = new JSONArray(findpic());

                  //  JSONArray  results = res.getJSONArray(0);


                    return res;
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

                    JSONObject obj1 =  all.getJSONObject(0);
                    JSONArray items =  obj1.getJSONArray("items");

                    JSONObject obj2 =  items.getJSONObject(1);
                    JSONObject pagemap =  obj2.getJSONObject("pagemap");
                    JSONArray img =  pagemap.getJSONArray("cse_image");
                    JSONObject obj3 =  img.getJSONObject(0);
                    String pic1 =  obj3.getString("src");


                  //  tv_info.setText(String.valueOf(pic1));


                  //  for (int i = 0 ; i <  all.length(); i++) {
                  //     JSONObject obj1 =  all.getJSONObject(0);
                    //   JSONArray picArray = obj1.getJSONArray("cse_image");
                      //JSONObject pic1obj = all.getJSONObject(0);
                        //locations.add(location);
                       // double lat2 = location.getDouble("lat");
                        //double lng2 = location.getDouble("lng");
                 //   String pic1 =  pic1obj.getString("src");

                      // String pic1 = "https://www.foodiesfeed.com/wp-content/uploads/2019/02/raw-lean-beef-meat-with-herbs-and-spices.jpg";
                      //  LatLng newlocation = new LatLng(lat2,  lng2);
                    Picasso.get().load(pic1).into(foodpic);

                    //  }

                }catch (Exception e){
                    e.printStackTrace();

                }

                //tv_test.setTextColor(Color.RED);

            }
        }.execute();
    }
*/

//https://www.googleapis.com/customsearch/v1?q=apple&cx=000737364164595517200:49pdk4kd-bg&key=AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs
   //https://cse.google.com/cse?cx=000737364164595517200:49pdk4kd-bg&q=orange&key=AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vsq=cars&filetype=png&alt=json
           // https://www.googleapis.com/customsearch/v1?q=apple&key="IzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs&alt=json";

//AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs

//https://www.googleapis.com/customsearch/v1?q=cars&filetype=png&cx=appple&searchType=image&rights=cc_publicdomain&num=10&key=AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs
//https://www.googleapis.com/customsearch/v1?q=cars&filetype=png&cx=000737364164595517200:49pdk4kd-bg&searchType=image&rights=cc_publicdomain&num=10&key=AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs