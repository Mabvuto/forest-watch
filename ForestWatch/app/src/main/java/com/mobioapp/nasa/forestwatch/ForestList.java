package com.mobioapp.nasa.forestwatch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Rohan on 4/8/2015.
 */
public class ForestList extends ActionBarActivity {



    String names[] = new String[]{
            " Sagano Forest",
            " Giant Sequoia,California",
            " Redwood National Park",
            " Black Forest",
            " Crooked Forest",
            " Jiuzhaigou Valley",
            " Monteverde Cloud Forest",
            " Daintree Rainforest",
    };

    ListView fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forest_list);

        fl = (ListView)findViewById(R.id.listFl);
        fl.setAdapter(new MyCustomAdapter(this,names));

        fl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;

                if(position == 0){

                        Intent i = new Intent(ForestList.this,MapShow.class);
                        startActivity(i);


                }
            }
        });

    }



    public class MyCustomAdapter extends BaseAdapter {

        String[] names;
        Context c;


        public MyCustomAdapter(Context c, String[] names) {
            // TODO Auto-generated constructor stub


            this.c = c;
            this.names = names;


        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            LayoutInflater vi = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.forest_name, null);


            TextView tt = (TextView) v.findViewById(R.id.textForestName);
            tt.setText(names[position]);
            return v;
        }

    }







    }
