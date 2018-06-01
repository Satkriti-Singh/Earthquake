package com.example.prateek.earthquake;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<Earthquake> earthquakes=QueryUtils.extractEarthquakes();



        final ListView earthquakelistview=(ListView) findViewById(R.id.list);

        final EarthAdapter adapter=new EarthAdapter(this,earthquakes);

        earthquakelistview.setAdapter(adapter);

      earthquakelistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Earthquake currentearthquake=adapter.getItem(position);
                Uri earthquakes=Uri.parse(currentearthquake.getMurl());
                Intent Websiteinetnet=new Intent(Intent.ACTION_VIEW,earthquakes);
                startActivity(Websiteinetnet);

            }
        });

    }
}