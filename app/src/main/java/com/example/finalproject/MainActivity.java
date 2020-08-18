package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finalproject.database.DatabaseHelper;
import com.example.finalproject.fragments.MapFragment;
import com.example.finalproject.model.House;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper db = DatabaseHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button btnMoreActivity = findViewById(R.id.btnMoreDetails);

        btnMoreActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMoreDetails();
            }
        });
    }

    //Use this function to go to the other activity, don't forget to pass the house ID
    //and test in the other activity if you are receiving the id correctly
    //This function should be called once the user clicks in a recycler view item, after that
    //feel free to delete 'btnMoreActivity'
    private void goToMoreDetails(){
        Intent intent = new Intent(MainActivity.this, MoreDetailsActivity.class);
        startActivity(intent);
    }
}
