package com.example.caloriestracker3;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mMap.addMarker(new MarkerOptions().position(Melbourne).title("Melbourne"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Melbourne));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(8), 2000, null);

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

}