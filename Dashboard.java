package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class Dashboard extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_dashboard);

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
        private void goToMoreDetails() {
                Intent intent = new Intent(Dashboard.this, MoreDetailsActivity.class);
                startActivity(intent);
        }
}
