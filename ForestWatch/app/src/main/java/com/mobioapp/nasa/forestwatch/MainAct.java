package com.mobioapp.nasa.forestwatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Rohan on 4/8/2015.
 */
public class MainAct extends Activity{



    Button form,CheckList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        form = (Button)findViewById(R.id.buttonFormMain);

        CheckList = (Button)findViewById(R.id.buttonCheckList);

        form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent i = new Intent(MainAct.this,MainPage.class);
                    startActivity(i);


            }
        });




        CheckList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainAct.this,ForestJsonList.class);
                startActivity(i);

            }
        });



    }
}
