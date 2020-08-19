package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
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


             //Use this to test map if you want
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

        addressView = (TextView)findViewById(R.id.addressTxt);
        addressView.setText(h1.getStreetAdress());

        cityView = (TextView) findViewById(R.id.cityTxt);
        cityView.setText(h1.getCity());

        provinceView = (TextView)findViewById(R.id.provinceTxt);
        provinceView.setText(h1.getProvince());

        countryView = (TextView)findViewById(R.id.countryTxt);
        countryView.setText(h1.getCountry());

        zipCodeView= (TextView)findViewById(R.id.zipCodeTxt);
        zipCodeView.setText(h1.getZipcode());

        descriptionView = (TextView)findViewById(R.id.descriptionTxt);
        descriptionView.setText(h1.getDescription());

        bedsView = (TextView)findViewById(R.id.bedTxt);
        Integer bedQty = h1.getBeds();
        bedsView.setText(bedQty.toString().trim());


        bathView = (TextView)findViewById(R.id.bathTxt);
        Integer bathQty = h1.getBaths();
        bathView.setText(bathQty.toString().trim());

        priceView = (TextView)findViewById(R.id.priceTxt);
        Double price = h1.getPrice();
        priceView.setText(price.toString().trim());




        //has to pass the ID that came from main activity
        MapFragment mp = new MapFragment(db.getHouse(2));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMap);

        if (mapFragment != null) {
            mapFragment.getMapAsync(mp);
        }
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
