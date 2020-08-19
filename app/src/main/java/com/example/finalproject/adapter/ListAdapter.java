package com.example.finalproject.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Callbacks.OnRowCallBack;
import com.example.finalproject.R;
import com.example.finalproject.model.House;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<House> mList;
    private OnRowCallBack onRowCallBack;

    public ListAdapter(List<House> list, OnRowCallBack onRowCallBack) {
        super();
        mList = list;
        this.onRowCallBack = onRowCallBack;
    }

    //init on click event when users click on each line data
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtStreetAdress, txtDescription;
        ViewHolder(View itemView){
            super(itemView);
            //find widget by id
            txtStreetAdress = itemView.findViewById(R.id.txtStreetAdress);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            //set on click event for recycle view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRowCallBack.onClickRow(getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        House house = mList.get(position);
        //set data to view
        ((ViewHolder) holder).txtStreetAdress.setText(house.getStreetAdress() + ", " + house.getCity() + ", " + house.getProvince() + ", " + house.getCountry());
        ((ViewHolder) holder).txtDescription.setText(house.getDescription());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
