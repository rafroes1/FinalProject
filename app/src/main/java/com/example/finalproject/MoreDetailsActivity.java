package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.finalproject.database.DatabaseHelper;
import com.example.finalproject.fragments.MapFragment;
import com.example.finalproject.model.House;
import com.google.android.gms.maps.SupportMapFragment;

public class MoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        DatabaseHelper db = DatabaseHelper.getInstance(this);

        /*
             Use this to test map if you want
        House h1 = new House();
        h1.setZipcode("N2N 3R9");
        h1.setStreetAdress("1330 Countrystone Dr");
        h1.setProvince("ON");
        h1.setPrice(500000.00);
        h1.setLongitude(-80.557174);
        h1.setLatitude(43.436018);
        h1.setDescription("Beautiful house near the boardwalk");
        h1.setCountry("Canada");
        h1.setCity("Waterloo");
        h1.setBeds(3);
        h1.setBaths(3);

        long houseId = db.insertHouse(h1);

        */

        //has to pass the ID that came from main activity
        MapFragment mp = new MapFragment(db.getHouse(2));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);

        if (mapFragment != null) {
            mapFragment.getMapAsync(mp);
        }
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
