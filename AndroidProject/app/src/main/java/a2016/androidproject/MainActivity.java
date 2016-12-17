package a2016.androidproject;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends Activity {

    Double latitude;
    Double longitude;

    TextView latitude_txt, longitude_txt, date_txt, work_txt;
    Button saveBt, outputBt, moveBt, cencleBt;
    RadioGroup radioG01;
    RadioButton radi1, radi2, radi3, radi4, radi5;
    EditText work_et;
    Intent it1, it2;

    GPSListener gpsListener = new GPSListener();
    long minTime = 5000; //5초
    float minDistance = 1;
    int etcStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        saveBt = (Button)findViewById(R.id.store);
        outputBt = (Button)findViewById(R.id.output);
        moveBt = (Button)findViewById(R.id.check);
        cencleBt = (Button)findViewById(R.id.cencle);
        latitude_txt = (TextView)findViewById(R.id.latitude);
        longitude_txt = (TextView)findViewById(R.id.longitude);
        date_txt = (TextView)findViewById(R.id.date);
        work_et =(EditText)findViewById(R.id.ed_work);
        work_txt = (TextView)findViewById(R.id.work);
        radioG01 = (RadioGroup)findViewById(R.id.radiogroup);
        radi1 = (RadioButton)findViewById(R.id.Bt1);
        radi2 = (RadioButton)findViewById(R.id.Bt2);
        radi3 = (RadioButton)findViewById(R.id.Bt3);
        radi4 = (RadioButton)findViewById(R.id.Bt4);
        radi5 = (RadioButton)findViewById(R.id.Bt5);



        it1 = new Intent(getApplicationContext(), MapsActivity.class);
        it2 = new Intent(getApplicationContext(), ViewActivity.class);

        checkDangerousPermissions();
        saveBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                saveInformation();
            }
        });

        outputBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outputInformation();
            }
        });
        outputBt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                outputListInformation();
                return false;
            }
        });
        moveBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(it1);
            }
        });
        cencleBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latitude_txt.setText("Latitude");
                longitude_txt.setText("longitude");
                date_txt.setText("Time");
                work_txt.setText("Task or Event");

            }
        });

        radioG01.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.rbtn01:
                        etcStr = 1;
                        break;
                    case R.id.rbtn02:
                        etcStr = 2;
                        break;
                    case R.id.rbtn03:
                        etcStr = 3;
                        break;
                    case R.id.rbtn04:
                        etcStr = 4;
                        break;
                    case R.id.rbtn05:
                        etcStr = 5;
                        break;
                }
            }
        });



    }


    private void checkDangerousPermissions(){
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for(int i=0 ; i< permissions.length; i++){
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if(permissionCheck == PackageManager.PERMISSION_DENIED){
                break;
            }
        }
        if(permissionCheck == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Have Right", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "No Right", Toast.LENGTH_LONG).show();

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,permissions[0])){
                Toast.makeText(this,"Need Explain For Right", Toast.LENGTH_LONG).show();
            }else{
                ActivityCompat.requestPermissions(this,permissions,1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode == 1){
            for(int i=0 ; i<permissions.length ; i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, permissions[i] + "Pass Right", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this,permissions[i] + "Don't Pass Right", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void outputListInformation(){
        startActivity(it2);
    }

    private void outputInformation(){

        String dateStr      = "Time\r\n";
        String latitudeStr  = "Latitude\r\n";
        String longitudeStr = "longitude\r\n";
        String workStr      = "Take or Event\r\n";

        while(cursor.moveToNext()){
            dateStr     += cursor.getString(1) + "\r\n";
            latitudeStr += cursor.getString(2) + "\r\n\r\n";
            longitudeStr+= cursor.getString(3) + "\r\n\r\n";
            workStr     += cursor.getString(4) + "\r\n\r\n";
        }


        date_txt.setText(dateStr);
        latitude_txt.setText(latitudeStr);
        longitude_txt.setText(longitudeStr);
        work_txt.setText(workStr);

        cursor.close();

    }

    private void saveInformation(){
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        try{
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance,gpsListener);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, gpsListener);
            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastLocation != null){
                Double latitude = lastLocation.getLatitude();
                Double longitude = lastLocation.getLongitude();
                String temp_workStr = work_et.getText().toString();

                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd" + "\nHH:mm:ss");
                String strNow = sdfNow.format(date);

                String dateStr = strNow;
                String latitudeStr = latitude.toString();
                String longitudeStr = longitude.toString();
                String workStr = work_et.getText().toString();

                String sql = "INSERT INTO location(date, latitude, longitude, work, etc) VALUES('" + dateStr + "','" +latitudeStr+"', '" +longitudeStr+"', '" +workStr+"', '"+etcStr+"')";

                Toast.makeText(getApplicationContext(), "Data Stored" + etcStr, Toast.LENGTH_SHORT).show();
            }
        }catch (SecurityException ex){
            ex.printStackTrace();
        }
    }
    private class GPSListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            String msg = "\nlatitude : " + latitude + "\nlongitude : " + longitude;
            Log.i("GPSListener", msg);

            Toast.makeText(getApplicationContext(), "Location Update! ", Toast.LENGTH_SHORT).show();


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}
