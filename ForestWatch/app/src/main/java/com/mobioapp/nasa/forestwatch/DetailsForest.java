package com.mobioapp.nasa.forestwatch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobioapp.nasa.forestwatch.utility.AnnouncementAdapter;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Rohan on 4/9/2015.
 */
public class DetailsForest extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_click);



        TextView title = (TextView)findViewById(R.id.viewClickTitle);
        TextView desc = (TextView)findViewById(R.id.viewClickDesCrip);
        ImageView imageT = (ImageView)findViewById(R.id.viewClickImageT);
        Button mapClickButton = (Button)findViewById(R.id.viewMapButton);

        final String ti = getIntent().getStringExtra("forest_name");
        final String des = getIntent().getStringExtra("forest_description");
        final String img = getIntent().getStringExtra("forest_image");
        final String lat = getIntent().getStringExtra("forest_latitude");
        final String longL = getIntent().getStringExtra("forest_longitude");


        mapClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent (DetailsForest.this, MapShow.class);
                i.putExtra("desc",des);
                i.putExtra("title",ti);
                i.putExtra("lat",lat);
                i.putExtra("long",longL);

                startActivity(i);
            }
        });


        title.setText(ti);
        desc.setText(des);


        Picasso.with(DetailsForest.this)
                .load(img)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(imageT);

        final Uri phototUri = Uri.parse(img);



        final Button Close = (Button)findViewById(R.id.viewCloseButton);
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,
                        des);
                intent.setType("image/png");
                intent.putExtra(Intent.EXTRA_STREAM,phototUri);
                v.getContext().startActivity(
                        Intent.createChooser(intent, "Share with"));


            }
        });






    }
}
