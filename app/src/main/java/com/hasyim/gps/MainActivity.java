package com.hasyim.gps;

import android.Manifest;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnShowLocation;
    TextView Lat,Long;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
        Lat = (TextView) findViewById(R.id.Lat);
        Long = (TextView) findViewById(R.id.Long);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // create class object
                GPSTracker gps = new GPSTracker(getApplicationContext());
                Location l = gps.getLocation();
                // check if GPS enabled
                if(l != null){
                    double latitude = l.getLatitude();
                    double longitude = l.getLongitude();

                    Lat.setText("Latitude : "+latitude);
                    Long.setText("Longitude : "+longitude);
                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    //gps.showSettingsAlert();
                    Toast.makeText(MainActivity.this, "GPS Anda tidak aktif", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}