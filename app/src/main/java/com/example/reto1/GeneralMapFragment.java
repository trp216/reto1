package com.example.reto1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeneralMapFragment extends Fragment {

    private OnGeneralMapListener listener;

    private GoogleMap mMap;

    private Marker marker;

    private String dir;

    private ArrayList<Post> posts;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            Geocoder coder = new Geocoder(getView().getContext());
            List<Address> addresses;
            LatLng p1 = null;
            try{
                for(int i = 0;i<posts.size();i++){
                    addresses = coder.getFromLocationName(posts.get(i).getLocation(), 5);
                    if(addresses!=null){
                        Address location = addresses.get(0);
                        //Log.e(">>>>>>>>>","LOCATION DECODED: "+location.getLatitude()+", "+ location.getLongitude());

                        p1 = new LatLng(location.getLatitude(), location.getLongitude());
                       // Log.e(">>>>>>>","LATLONG: "+p1);
                        marker = mMap.addMarker(new MarkerOptions().position(p1));
                        marker.setTitle(posts.get(i).getBusiness());
                        marker.setSnippet(posts.get(i).getName());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public static GeneralMapFragment newInstance() {
        GeneralMapFragment fragment = new GeneralMapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_general_map, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);

        }
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public interface OnGeneralMapListener{
        void onGeneralMap();
    }

    public void setListener(OnGeneralMapListener listener){
        this.listener = listener;
    }
}