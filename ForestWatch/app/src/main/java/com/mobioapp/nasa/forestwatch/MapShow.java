package com.mobioapp.nasa.forestwatch;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobioapp.nasa.forestwatch.utility.AnnouncementAdapter;
import com.mobioapp.nasa.forestwatch.utility.ConnectionDetector;
import com.mobioapp.nasa.forestwatch.BasicJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rohan on 4/8/2015.
 */
public class MapShow extends ActionBarActivity implements LocationListener {



    private ArrayList<BasicJson> BasicList;

    BasicJson bj;



        /*public MapShow(ArrayList<BasicJson> BasicList){

            this.BasicList = BasicList;

        }*/

    //double src_lat = 23.764850;
    //double src_long = 90.385634;


    double src_lat;
    double src_long;

    private GoogleMap googleMap;
    MarkerOptions markerOptions;

    GPSTracker gps;

    LocationManager manager;
    Location location;
    View frag;


    Marker myMarker = null, myMark = null;
    ConnectionDetector cd;



    Boolean isGPSEnabled, isNetworkEnabled;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

          bj = new BasicJson();




        String la = getIntent().getStringExtra("lat");
        String lon = getIntent().getStringExtra("long");
        String title = getIntent().getStringExtra("title");

        String desc = getIntent().getStringExtra("desc");



        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mappp)).getMap();
        }

        frag = findViewById(R.id.mappp);


        cd = new ConnectionDetector(MapShow.this);


        googleMap.setMyLocationEnabled(true);




        //gps = new GPSTracker(MapShow.this);

        //if (gps.canGetLocation()) {

            //src_lat = gps.getLatitude();
            //src_long = gps.getLongitude();

        src_lat = Double.valueOf(la);
        src_long = Double.valueOf(lon);



            LatLng dhkLatlong = new LatLng(src_lat, src_long);

            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dhkLatlong, 5.9f));


        /*try {
            // After getting current location set them into two variable

            src_lat = location.getLatitude();
            src_long = location.getLongitude();

            LatLng dhkLatlong = new LatLng(src_lat, src_long);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dhkLatlong, 11.9f));

            System.out.println("Longitude" + location.getLongitude() + "    "
                    + "Lattitude" + location.getLatitude());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }*/

            if (cd.isConnectingToInternet()) {

                frag.setVisibility(View.VISIBLE);

            }


            //plotMarkers(mMyMarkersArray);

            LatLng srcLatLng = new LatLng(src_lat, src_long);
            //String snipp = bj.getTitle();



        //BasicJson b = BasicList.get(0);
        //String snipp = BasicList.


        /*myMarker = googleMap.addMarker(new MarkerOptions()
                .position(srcLatLng)
                .title("Tracking")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.mark)));*/

            myMark = googleMap.addMarker(new MarkerOptions().position(srcLatLng)
                    .title(title).snippet(desc)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mark)));




        /*}else{


            gps.showSettingsAlert();
        }*/
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
