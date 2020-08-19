package com.example.finalproject.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalproject.R;
import com.example.finalproject.model.House;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    private House house;


    public MapFragment( House house) {
        this.house = house;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    /***
     * Callback for initialization of Google Maps.
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        //set camera to zoom in house location
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(this.house.getLatitude(), this.house.getLongitude()), 17.0f));

        //mark house location in map
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(this.house.getLatitude(), this.house.getLongitude())));

    }
}
