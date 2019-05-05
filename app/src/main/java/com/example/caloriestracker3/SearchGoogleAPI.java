package com.example.caloriestracker3;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SearchGoogleAPI {
    //private static final String API_KEY = "YOUR KEY";
    //private static final String SEARCH_ID_cx = "YOUR SEARCH ID CX";
    public static String search(String keyword)
    {
        keyword = keyword.replace(" ", "+");
        HttpURLConnection conn = null;
        String textResult = "";
        URL url = null;
        //url = new URL("https://www.googleapis.com/customsearch/v1?q="+keyword+"&cx=000737364164595517200:49pdk4kd-bg&key=AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs");

        HttpURLConnection connection = null;
        /*
        String textResult = ""; String query_parameter="";
        if (params!=null && values!=null){
            for (int i =0; i < params.length; i ++){
                query_parameter += "&"; query_parameter += params[i]; query_parameter += "="; query_parameter += values[i];
            } }
        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?key="+ API_KEY+ "&cx="+
                    SEARCH_ID_cx + "&q="+ keyword + query_parameter);
            connection = (HttpURLConnection)url.openConnection(); connection.setReadTimeout(10000); connection.setConnectTimeout(15000); connection.setRequestMethod("GET"); connection.setRequestProperty("Content-Type", "application/json");

            connection.setRequestProperty("Accept", "application/json"); Scanner scanner = new Scanner(connection.getInputStream()); while (scanner.hasNextLine()) {
                textResult += scanner.nextLine(); }
        }catch (Exception e){ e.printStackTrace();
        }finally{ connection.disconnect();
        }
        "https://www.googleapis.com/customsearch/v1?q="+keyword+"&cx=000737364164595517200:xzf3eyfibyo:49pdk4kd-bg&key=AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs
        https://cse.google.com/cse?cx=000737364164595517200:xzf3eyfibyo
        return textResult; */

        //https://www.googleapis.com/customsearch/v1?q=beef&cx=000737364164595517200:xzf3eyfibyo&key=AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs
        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?q="+keyword+"&cx=000737364164595517200:49pdk4kd-bg&key=AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs");

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

    public static String search2(String keyword)
    {
        keyword = keyword.replace(" ", "+");
        HttpURLConnection conn = null;
        String textResult = "";
        URL url = null;

        HttpURLConnection connection = null;


        //https://www.googleapis.com/customsearch/v1?q=beef&cx=000737364164595517200:xzf3eyfibyo&key=AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs
        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?q="+keyword+"&cx=000737364164595517200:xzf3eyfibyo&key=AIzaSyCzX79DkxzA3DCJijK9W0o4xMVGWAFI9Vs");

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


