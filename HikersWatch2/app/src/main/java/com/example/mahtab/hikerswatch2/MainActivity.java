package com.example.mahtab.hikerswatch2;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;
    TextView hikersTextView;
    TextView latTextView;
    TextView lonTextView;
    TextView accTextView;
    TextView altTextView;
    TextView addTextView;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startListening();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hikersTextView = (TextView) findViewById(R.id.hikersTextView);
        latTextView = (TextView) findViewById(R.id.latTextView);
        lonTextView = (TextView) findViewById(R.id.lonTextView);
        accTextView = (TextView) findViewById(R.id.accTextView);
        altTextView = (TextView) findViewById(R.id.altTextView);
        addTextView = (TextView) findViewById(R.id.addTextView);


        locationManager =(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

              updateLocationInfo(location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }

        };
        if (Build.VERSION.SDK_INT < 23){
          if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ){

              return;
          }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);

        }

        else{
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
              ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
            else{
             locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation!= null){
                updateLocationInfo(lastKnownLocation);
            }
            }
        }


    }

    public void updateLocationInfo(Location location){
        latTextView.setText("Latitude  : "+ Double.toString(location.getLatitude()));
        lonTextView.setText("Longitude  : "+ Double.toString(location.getLongitude()));
        accTextView.setText("Accuracy  : "+ Double.toString(location.getAccuracy()));
        altTextView.setText("Altitude  : "+ Double.toString(location.getAltitude()));

        String address = "Address not found :(";
        Geocoder geocoder = new Geocoder(this,Locale.getDefault());

        try {
            List<Address> listAddress = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
           if (listAddress!= null && listAddress.size() > 0){
               address = "Address :";
               if (listAddress.get(0).getThoroughfare() != null){
                   address += listAddress.get(0).getThoroughfare() + "\n";
               }
               if (listAddress.get(0).getPostalCode() != null){
                   address += listAddress.get(0).getPostalCode() + " ";
               }
               if (listAddress.get(0).getLocality() != null){
                   address += listAddress.get(0).getLocality() + "\n";
               }
               if (listAddress.get(0).getAdminArea() != null){
                   address += listAddress.get(0).getAdminArea() ;
               }
           }

        } catch (Exception e) {
            e.printStackTrace();
        }

        addTextView.setText(address);

    }
    public  void  startListening(){
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }

    }
}
