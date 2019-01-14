package com.app.bndgram.ui.map;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.app.bndgram.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment map = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.e("","");

        LatLng myLocation = new LatLng(27.1852171,56.2901924);

        MarkerOptions marker = new MarkerOptions()
                .position(myLocation )
                .title("Emam khomini street")
                .snippet("Fava")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));


        LatLng location2 = new LatLng(27.1872785,56.2791846);

        MarkerOptions marker2 = new MarkerOptions()
                .position(location2 )
                .title("Borke")
                .snippet("Test")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.location));



        googleMap.addMarker(marker);
        googleMap.addMarker(marker2);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(myLocation).zoom(15).build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        googleMap.getUiSettings().setZoomControlsEnabled(true);



        googleMap.addPolygon(new PolygonOptions().add(myLocation,location2)
        .strokeWidth(2)
        .strokeColor(Color.parseColor("#ff0000")));


        googleMap.addCircle(new CircleOptions().center(myLocation)
        .fillColor(Color.parseColor("#66000000"))
        .radius(1000)
        );




    }
}
