package com.example.finalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject.Callbacks.OnRowCallBack;
import com.example.finalproject.adapter.ListAdapter;
import com.example.finalproject.database.DatabaseHelper;
import com.example.finalproject.model.House;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnRowCallBack {
    private DatabaseHelper db = DatabaseHelper.getInstance(this);
    private RecyclerView mRecyclerView;
    private List<House> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);

        //load houses from db
        loadData();
        //bind data to recycler view
        bindAdapter();
    }

    private void loadData() {
        mList = db.getAllHouses();

        //check if empty then generate data sample
        if(mList.isEmpty()) {
            generateData();
            mList = db.getAllHouses();
        }
    }

    //bind all the data to the recycler view
    private void bindAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        ListAdapter mAdapter = new ListAdapter(mList, this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    //add fake data to database for testing purposes if database is empty
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
                .setIcon(R.drawable.ic_info_outline_black_24dp)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //check click event to redirect to the house detail activity
                        House house = mList.get(position);
                        Intent intent = new Intent(MainActivity.this, MoreDetailsActivity.class);
                        //pass house id to the more detail activity
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
