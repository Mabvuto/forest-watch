package com.mobioapp.nasa.forestwatch;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mobioapp.nasa.forestwatch.utility.AnnouncementAdapter;
import com.mobioapp.nasa.forestwatch.utility.ConnectionDetector;
import com.mobioapp.nasa.forestwatch.utility.JSONParser;
import com.mobioapp.nasa.forestwatch.utility.URLs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rohan on 4/8/2015.
 */
public class ForestJsonList extends ActionBarActivity {


    ListView lv;
    HashMap<String, String> map;
    ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> offlineList = new ArrayList<HashMap<String, String>>();


    String forest_id = " ";
    String forest_title = " ";
    String forest_description = " ";
    String forest_image = " ";
    String forest_latitude = " ";
    String forest_longitude = " ";

    JSONArray notifications = null;
    JSONObject json;
    ConnectionDetector cd;
    JSONParser jParser;

    String jon;

    String path = null;

    ArrayList<BasicJson> jsonList = new ArrayList<BasicJson>();


    AnnouncementAdapter mAnnouncementAdapter;

    MapShow mpShow;
    ProgressDialog Progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forest_list);




        cd = new ConnectionDetector(ForestJsonList.this);

        Progress = new ProgressDialog(ForestJsonList.this);

        lv = (ListView)findViewById(R.id.listFl);




           // path = "forestinfo/forest_data.json";


        if (cd.isConnectingToInternet()) {

            NotificationTask nt = new NotificationTask();
            nt.execute();



        }

        else{

            Toast toast = Toast.makeText(getApplicationContext(),
                    "No internet connection", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

        }


    }



    /*public String loadJSONFromAsset() {
        String Json = null;
        try {

            InputStream is = getAssets().open("forestinfo/forest_data.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            Json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return Json;

    }*/

    public class NotificationTask extends AsyncTask<Void, String, String>{


        @Override
        protected void onPreExecute() { // TODO Auto-generated method stub
            super.onPreExecute();
            Progress.setMessage("Fetching Updates...");
            Progress.show();
            Progress.setCancelable(false);

        }


        @Override
        protected String doInBackground(Void... params) {

            jParser = new JSONParser();
            json = jParser.getJSONFromUrl(URLs.BASE_URL);
            /*try {
                json = new JSONObject(loadJSONFromAsset());
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
            System.out.println("JSON:::" + json.toString());



            try {
                // Getting Array of Contacts
               // notifications = json.getJSONArray("messages");
                notifications = json.getJSONArray("watch");
                System.out.println("JSON:::" + notifications.toString());
                // looping through All Contacts
                if (notifications.length() == 0) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(ForestJsonList.this,
                                    "There is no new update available",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                for (int i = 0; i < notifications.length(); i++) {
                    JSONObject c = notifications.getJSONObject(i);

                    // Storing each json item in variable
                    forest_title = c.getString("forest_title");
                    forest_image = c.getString("forest_image");
                    forest_description = c.getString("forest_description");
                    forest_latitude = c.getString("forest_latitude");
                    forest_longitude = c.getString("forest_longitude");


                    BasicJson bJ = new BasicJson();

                    bJ.setTitle(c.getString("forest_title"));
                    bJ.setDescription(c.getString("forest_description"));
                    bJ.setLatitude(c.getString("forest_latitude"));
                    bJ.setLongitude(c.getString("forest_longitude"));
                    bJ.setImg(c.getString("forest_image"));
                    jsonList.add(bJ);


                    //mpShow = new MapShow(jsonList);

                    // saveTodatabase();

                    Log.e("title", forest_title);

                    map = new HashMap<String, String>();
                    map.put("forest_title", forest_title);
                    map.put("forest_image", forest_image);
                    map.put("forest_description",forest_description);
                    map.put("forest_latitude",forest_latitude);
                    map.put("forest_longitude",forest_longitude);


                    mylist.add(map);
                    Log.e("List", mylist.toString());

                    System.out.println("Json inside");

                    // savePreference(title, time);
                }




            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }


        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            Progress.dismiss();

            try {

                if (mylist != null) {
                    if (mylist.size() > 0) {




                        mAnnouncementAdapter = new AnnouncementAdapter(
                                ForestJsonList.this, mylist,
                                ForestJsonList.this);
                        lv.setAdapter(mAnnouncementAdapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent i = new Intent(ForestJsonList.this,DetailsForest.class);

                                String forest_name = jsonList.get(position).getTitle();

                                String forest_image = jsonList.get(position).getImg();

                                String forest_desc = jsonList.get(position).getDescription();

                                String lati = jsonList.get(position).getLatitude();
                                String longg = jsonList.get(position).getLongitude();


                                i.putExtra("forest_name", forest_name);
                                i.putExtra("forest_image", forest_image);
                                i.putExtra("forest_description", forest_desc);
                                i.putExtra("forest_latitude", lati);
                                i.putExtra("forest_longitude", longg);


                                startActivity(i);
                            }
                        });

                    }
                }

                else {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Nothing New!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }



    }

}
