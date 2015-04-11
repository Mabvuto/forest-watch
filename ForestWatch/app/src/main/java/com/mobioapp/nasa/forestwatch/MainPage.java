package com.mobioapp.nasa.forestwatch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import nl.changer.polypicker.ImagePickerActivity;


public class MainPage extends ActionBarActivity {

    private static final int INTENT_REQUEST_GET_IMAGES = 13;
    private static final int INTENT_REQUEST_GET_N_IMAGES = 14;


    CheckBox LocateCheck;
    GPSTracker gps;
    TextView Latitude, Longitude, city;
    Button imgButton,submit;
       ImageView imgSample;

    boolean oneChecked = false;

    public void getImages() {
        Intent intent = new Intent(MainPage.this, ImagePickerActivity.class);
// limit image pick count to only 3 images.
        intent.putExtra(ImagePickerActivity.EXTRA_SELECTION_LIMIT, 1);
        startActivityForResult(intent, INTENT_REQUEST_GET_N_IMAGES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resuleCode, Intent intent) {
        super.onActivityResult(requestCode, resuleCode, intent);
        if (resuleCode == Activity.RESULT_OK) {
            if (requestCode == INTENT_REQUEST_GET_IMAGES || requestCode == INTENT_REQUEST_GET_N_IMAGES) {
                Parcelable[] parcelableUris = intent.getParcelableArrayExtra(ImagePickerActivity.EXTRA_IMAGE_URIS);
                if (parcelableUris == null) {
                    return;
                }
// Java doesn't allow array casting, this is a little hack
                Uri[] uris = new Uri[parcelableUris.length];
                System.arraycopy(parcelableUris, 0, uris, 0, parcelableUris.length);
                if (uris != null) {
                    /*for (Uri uri : uris) {
                        //Log.i(TAG, " uri: " + uri);
                        Picasso.with(MainPage.this).load(uri.getLastPathSegment()).error(R.drawable.mobio).resize(200, 200)
                                .centerCrop().into(imgSample);
                        Toast.makeText(MainPage.this, "" + uri.toString(), Toast.LENGTH_LONG).show();
                    }*/

                    Picasso.with(MainPage.this).load(new File(uris[0].getPath().toString())).error(R.drawable.mobio).resize(200, 200).centerCrop().into(imgSample);

                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_form);


        LocateCheck = (CheckBox) findViewById(R.id.checkBoxLocate);
        Latitude = (TextView) findViewById(R.id.textLatitude);
        Longitude = (TextView) findViewById(R.id.textLongitude);

        imgButton = (Button) findViewById(R.id.buttonImg);
        imgSample = (ImageView)findViewById(R.id.imgCapture);
        submit = (Button)findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainPage.this,"Form is Submitted",Toast.LENGTH_SHORT).show();
            }
        });

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getImages();

            }
        });

        LocateCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(((CheckBox) v).isChecked()) {

                    oneChecked = true;

                    gps = new GPSTracker(MainPage.this);

                    // check if GPS enabled
                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();


                        Latitude.setText(String.valueOf("latitude of " + latitude));
                        Longitude.setText(String.valueOf("longitude of" + longitude));


                        // \n is for new line
                        //Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                    } else {
                        // can't get location
                        // GPS or Network is not enabled
                        // Ask user to enable GPS/network in settings
                        gps.showSettingsAlert();
                    }


                }else if (oneChecked){


                    Latitude.setText("");
                    Longitude.setText("");

                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
