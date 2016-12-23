package com.yamankod.murat.map;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationService locationService;
    private  Context mContext;
    private  final static  String TAG="_MapsActivity";

    TextView tv;
    CoordinatorLayout c;

    public String adress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mContext =getApplicationContext();
        locationService = new LocationService(getApplicationContext());
         c = (CoordinatorLayout)findViewById(R.id.main_content);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    //Location Serviceden Aldığı Degerler sayesinde Konumu haritaya basar.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng location = new LatLng(locationService.getLatitude(), locationService.getLongitude());
        mMap.addMarker(new MarkerOptions().position(location).title("Konumunuz"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));


        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(location, 15);
        mMap.animateCamera(yourLocation);

        Double latitude =  Double.valueOf(locationService.getLatitude());
        Double longitude =  Double.valueOf(locationService.getLongitude());

         adress=getCompleteAddressString(latitude, longitude);

        Snackbar snackbar = Snackbar.make(c, adress.toString(), Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    //Aldıgı enlem boylam degerleri sayesinde adress bilgisini dondorür
    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.d(TAG, "getCompleteAddressString: " + strReturnedAddress.toString());
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strAdd;
    }
}
