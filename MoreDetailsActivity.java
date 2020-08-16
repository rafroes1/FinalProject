package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.finalproject.database.DatabaseHelper;
import com.example.finalproject.model.House;

public class MoreDetailsActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Context context;
    TextView addressView, cityView, provinceView, countryView, zipCodeView, descriptionView, bedsView, bathView, priceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_details);

        // getting values from database is still not working
        /*DatabaseHelper db = DatabaseHelper.getInstance(context);
        House house = db.getHouse(Integer.parseInt(String.valueOf(1)));*/


        addressView = (TextView)findViewById(R.id.addressTxt);
        addressView.setText("King st. 305");

        cityView = (TextView)findViewById(R.id.cityTxt);
        cityView.setText("Waterloo");

        provinceView = (TextView)findViewById(R.id.provinceTxt);
        provinceView.setText("Ontario");

        countryView = (TextView)findViewById(R.id.countryTxt);
        countryView.setText("Canada");

        zipCodeView= (TextView)findViewById(R.id.zipCodeTxt);
        zipCodeView.setText("N2J029");

        descriptionView = (TextView)findViewById(R.id.descriptionTxt);
        descriptionView.setText("Beautiful");

        bedsView = (TextView)findViewById(R.id.bedTxt);
        bedsView.setText("2");


        bathView = (TextView)findViewById(R.id.bathTxt);
        bathView.setText("2");

        priceView = (TextView)findViewById(R.id.priceTxt);
        priceView.setText("$500.000");

        Button btnMoreActivity = findViewById(R.id.btnDashboard);

        btnMoreActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDashboard();
            }
        });

    }
    private void goToDashboard(){
        Intent intent = new Intent(MoreDetailsActivity.this, Dashboard.class);
        startActivity(intent);
    }

}
