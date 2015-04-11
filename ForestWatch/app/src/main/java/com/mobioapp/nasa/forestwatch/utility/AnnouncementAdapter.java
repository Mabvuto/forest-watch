package com.mobioapp.nasa.forestwatch.utility;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobioapp.nasa.forestwatch.DetailsForest;
import com.mobioapp.nasa.forestwatch.MapShow;
import com.mobioapp.nasa.forestwatch.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Rohan on 4/9/2015.
 */
public class AnnouncementAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<HashMap<String, String>> announcements;
    int pos;
    Dialog mDialog;
    Activity activity;
    Button close;
    LayoutInflater inflater;



    public AnnouncementAdapter(Context context, ArrayList<HashMap<String, String>> announcement, Activity activity){

        this.activity = activity;
        this.context = context;
        announcements = announcement;





    }




    @Override
    public int getCount() {
        return announcements.size();
    }

    @Override
    public Object getItem(int position) {
        return announcements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.json_list_item,
                    parent, false);
        }

        convertView.setBackgroundResource(R.drawable.rounded_corners);

        TextView title = (TextView)convertView.findViewById(R.id.textTextView);
        title.setTypeface(Typeface.createFromAsset(context.getAssets(),
                "font/VistaSansAltBlack.ttf"));




         HashMap<String, String> map = announcements.get(position);



        title.setText(map.get("forest_title").toString());



        ImageView img = (ImageView) convertView.findViewById(R.id.jsonListImg);
        //imageLoader.displayImage(map.get("forest_image"), img, options, null);
        Picasso.with(context)
                .load(map.get("forest_image"))
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(img);


        /*convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                HashMap<String, String> map = announcements.get(position);
                if(position == 0){


                 Intent i = new Intent(context, DetailsForest.class);
                    context.startActivity(i);


                }


                if(position == 1){





                }
            }
        });*/



        return convertView;

    }
}
