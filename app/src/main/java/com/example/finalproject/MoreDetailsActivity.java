package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;

import com.example.finalproject.database.DatabaseHelper;
import com.example.finalproject.fragments.MapFragment;
import com.example.finalproject.model.House;
import com.google.android.gms.maps.SupportMapFragment;

public class MoreDetailsActivity extends AppCompatActivity {
    private DatabaseHelper db = DatabaseHelper.getInstance(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);
//        House h1 = new House();
//        h1.setId(1);
//        h1.setZipcode("");
//        h1.setStreetAdress("111212");
//        h1.setProvince("onta");
//        h1.setPrice(1212);
//        h1.setLongitude(80);
//        h1.setLatitude(40);
//        h1.setDescription("");
//        h1.setCountry("wewew");
//        h1.setCity("Toronto");
//        h1.setBeds(1);
//        h1.setBaths(1);
//        db.insertHouse(h1);
        MapFragment mp = new MapFragment(db.getHouse(1));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);

        if (mapFragment != null) {
            mapFragment.getMapAsync(mp);
        }
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
