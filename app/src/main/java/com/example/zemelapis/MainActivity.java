package com.example.zemelapis;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;


public class MainActivity extends AppCompatActivity implements LocationListener {
    MapView map = null;
    private MyLocationNewOverlay mLocationOverlay;
    private String marker_icon;
    Button plusBTN;
    Button s, st, l, lb;
    private double longi, lat;
    private Button b;
    private TextView t;
    private String tipas = null;
    private Boolean follow = true;
    private LocationManager locationManager;
    private LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button b = (Button) findViewById(R.id.track);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                follow=true;
            }
        });
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_main);
        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        map = (MapView) findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.setMinZoomLevel(8.0);
        map.setMaxZoomLevel(21.0);


        Button plusBTN = (Button) findViewById(R.id.plusButton);
        plusBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialogas_pasirinkimo, null);
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
                l = view.findViewById(R.id.lauzaviete);
                st = view.findViewById(R.id.stovyklaviete);
                s = view.findViewById(R.id.saltinis);
                lb = view.findViewById(R.id.stalai);


            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int actionType = ev.getAction();
        Projection proj = map.getProjection();
        IGeoPoint loc = proj.fromPixels((int) ev.getX(), (int) ev.getY());
        longi = loc.getLongitude();
        lat = loc.getLatitude();
        String longitude = Double.toString((double) longi);
        String latitude = Double.toString((double) lat);
        Marker marker = new Marker(map);
        marker.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.blue_marker, null));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        switch (actionType) {
            case MotionEvent.ACTION_UP:
                Toast toast = Toast.makeText(getApplicationContext(), "Longitude: " + longitude + " Latitude: " + latitude, Toast.LENGTH_LONG);
                toast.show();
                follow = false;
                if(tipas !=null) {
                    // startMarker.setPosition(new GeoPoint(54.6872, 25.2797));
                    marker.setPosition(new GeoPoint(lat, longi));
                    map.getOverlays().add(marker);
                    map.invalidate();
                }
        }
//
        return super.dispatchTouchEvent(ev);
    }
    public void onResume(){
        super.onResume();
        map.onResume();
    }

    public void onPause(){
        super.onPause();
        map.onPause();
    }


    @Override
    public void onLocationChanged(Location location) {

        double lati = location.getLatitude();
        double longit =location.getLongitude();
        Log.i("Geo_Location", "Latitude: " + lati + ", Longitude: " + longit);
        Log.i("Geo_Location", "ar juda"+ follow);
        IMapController mapController = map.getController();
        mapController.setZoom(18.5);
        if(follow==true) {

            GeoPoint startPoint = new GeoPoint(lati, longit);
            mapController.setCenter(startPoint);
        }
        Marker marker = new Marker(map);
        marker.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.round_navigation_white_48, null));
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setPosition(new GeoPoint(lati, longit));
        map.getOverlays().add(marker);
        map.invalidate();
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
}

