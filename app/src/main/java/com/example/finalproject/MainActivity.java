package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalproject.Callbacks.OnRowCallBack;
import com.example.finalproject.database.DatabaseHelper;
import com.example.finalproject.model.House;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnRowCallBack {
    private DatabaseHelper db = DatabaseHelper.getInstance(this);
    private RecyclerView mRecyclerView;
    private List<House> mList;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button btnMoreActivity = findViewById(R.id.btnMoreDetails);
        mRecyclerView = findViewById(R.id.recyclerView);

        //load houses from db
        loadData();
        //bind data to recycle view
        bindAdapter();

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

    private void loadData() {
        mList = db.getAllHouses();
        //check if empty then generate data sample
        if(mList.isEmpty()) {
            generateData();
            mList = db.getAllHouses();
        }
    }

    private void bindAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new ListAdapter(mList, this, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void generateData() {
        for(int i = 1; i <= 5; i++) {
            House house = new House();
            house.setStreetAdress(i + " King st");
            house.setCity("Kitchener");
            house.setProvince("ON");
            house.setCountry("Canada");
            house.setZipcode("H2Z Y5K");
            house.setDescription("Description House " + i);
            house.setBeds(3);
            house.setBaths(2);
            house.setPrice(i * 1000);
            house.setLatitude(1.1111);
            house.setLongitude(2.2222);
            db.insertHouse(house);
        }
    }

    //callback func to receive position when clicking on view item
    @Override
    public void onClickRow(final int position) {
        //create dialog
        new AlertDialog.Builder(this)
                .setTitle("Select Action")
                .setMessage("Do you want to see more detail of this house?")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //check click event to redirect to the house detail activity
                        House house = mList.get(position);
                        Intent intent = new Intent(MainActivity.this, HouseDetailActivity.class);
                        //pass house id
                        intent.putExtra("HOUSE_ID", house.getId());
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
