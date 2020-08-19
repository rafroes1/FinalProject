package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.finalproject.database.DatabaseHelper;
import com.example.finalproject.fragments.MapFragment;
import com.example.finalproject.model.House;
import com.google.android.gms.maps.SupportMapFragment;

public class MoreDetailsActivity extends AppCompatActivity {


    TextView addressView, cityView, provinceView, countryView, zipCodeView, descriptionView, bedsView, bathView, priceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        DatabaseHelper db = DatabaseHelper.getInstance(this);

        //get data from MainActivity by using intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("HOUSE_ID", 0);

        //get from database selected house with specific id
        House h1 = db.getHouse(id);

        addressView = findViewById(R.id.addressTxt);
        addressView.setText(h1.getStreetAdress());

        cityView = findViewById(R.id.cityTxt);
        cityView.setText(h1.getCity());

        provinceView = findViewById(R.id.provinceTxt);
        provinceView.setText(h1.getProvince());

        countryView = findViewById(R.id.countryTxt);
        countryView.setText(h1.getCountry());

        zipCodeView= findViewById(R.id.zipCodeTxt);
        zipCodeView.setText(h1.getZipcode());

        descriptionView = findViewById(R.id.descriptionTxt);
        descriptionView.setText(h1.getDescription());

        bedsView = findViewById(R.id.bedTxt);
        int bedQty = h1.getBeds();
        bedsView.setText(Integer.toString(bedQty).trim());


        bathView = findViewById(R.id.bathTxt);
        int bathQty = h1.getBaths();
        bathView.setText(Integer.toString(bathQty).trim());

        priceView = findViewById(R.id.priceTxt);
        double price = h1.getPrice();
        priceView.setText(Double.toString(price).trim());

        //pass the house tha is going to be shown in map
        MapFragment mp = new MapFragment(h1);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);

        if (mapFragment != null) {
            mapFragment.getMapAsync(mp);
        }

        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
