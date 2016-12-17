package a2016.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Cursor cursor;
    Intent i;
    PolylineOptions polylineOptions;
    MarkerOptions markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        polylineOptions = new PolylineOptions();
        markerOptions = new MarkerOptions();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        String sql = "SELECT * FROM location";
        cursor = sqlite.rawQuery(sql, null);

        while(cursor.moveToNext()){
            LatLng temp = new LatLng(Double.parseDouble(cursor.getString(2)), Double.parseDouble(cursor.getString(3)));
            switch (cursor.getString(2)){
                case "1":
                    markerOptions.snippet("Study");
                    break;
                case "2":
                    markerOptions.snippet("Work");
                    break;
                case "3":
                    markerOptions.snippet("Rest");
                    break;
                case "4":
                    markerOptions.snippet("Eat");
                    break;
                case "5":
                    markerOptions.snippet("Move");
                    break;
            }
            mMap.addMarker((markerOptions.position(temp).title(cursor.getString(4))));
            mMap.setMinZoomPreference(13);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));

            Polyline line = mMap.addPolyline(polylineOptions.add(temp).width(5).color(Color.RED));
        }

    }
}
