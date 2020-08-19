package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.database.DatabaseHelper;
import com.example.finalproject.model.House;

public class HouseDetailActivity extends AppCompatActivity {

    TextView txtHouseId, txtStreetAddress, txtCity, txtProvince, txtCountry, txtZipcode, txtDescription, txtBeds, txtBaths, txtPrice;
    Button btnBack;
    private DatabaseHelper db = DatabaseHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_detail);

        //get data from MainActivity by using intent
        Intent intent = getIntent();
        int id = intent.getIntExtra("HOUSE_ID", 0);
        //get house by id
        House house = db.getHouse(id);
        //get widgets
        txtHouseId = (TextView) findViewById(R.id.txtHouseId);
        txtStreetAddress = (TextView) findViewById(R.id.txtStreetAddress);
        txtCity = (TextView) findViewById(R.id.txtCity);
        txtProvince = (TextView) findViewById(R.id.txtProvince);
        txtCountry = (TextView) findViewById(R.id.txtCountry);
        txtZipcode = (TextView) findViewById(R.id.txtZipcode);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtBeds = (TextView) findViewById(R.id.txtBeds);
        txtBaths = (TextView) findViewById(R.id.txtBaths);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        btnBack = (Button) findViewById(R.id.btnBack);
        //display house detail
        txtHouseId.setText("Id: " + house.getId());
        txtStreetAddress.setText("Street: " + house.getStreetAdress());
        txtCity.setText("City: " + house.getCity());
        txtProvince.setText("Province: " + house.getProvince());
        txtCountry.setText("Country: " + house.getCountry());
        txtZipcode.setText("Zipcode: " + house.getZipcode());
        txtDescription.setText("Description: " + house.getDescription());
        txtBeds.setText("Bed(s): " + house.getBeds());
        txtBaths.setText("Bath(s): " + house.getBaths());
        txtPrice.setText("Price: $" + house.getPrice());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go back to MainActivity
                Intent intent = new Intent(HouseDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
