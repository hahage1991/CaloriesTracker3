package com.example.caloriestracker3;



/**
 * Created by wudaopeng on 6/4/18.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class RestClient {

    //192.168.0.101
    private static final String BASE_URI = "http://192.168.0.101:8080//NewC/webresources";

  //http://192.168.0.101:8080//NewC/webresources/newc.consumption/1
    public static String findAllConsumption(String subPath) {
        //final String methodPath = "/smarter.usage/findByResid/2";
        final String methodPath = "/newc.consumption/";
//initialise
        URL url = null;
        HttpURLConnection conn = null;
        String textResult = "";
//Making HTTP request
        try {
            url = new URL(BASE_URI + methodPath + subPath);
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

}
