package com.example.zhangyuanke.istudyandroid.FCGPS;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.example.zhangyuanke.istudyandroid.BaseActivity;
import com.example.zhangyuanke.istudyandroid.R;

import java.util.List;

public class FCGPSActivity extends BaseActivity {

    private TextView positonTextView;
    private LocationManager locationManager;
    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_fcgps);

        //
        positonTextView = (TextView) findViewById(R.id.fc_position_text_view);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //  获取所有可用的位置提供器
        List<String> providerList = locationManager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        } else if (providerList.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            //
            Toast.makeText(this, "No Location Provider to use", Toast.LENGTH_SHORT).show();
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            showLocation(location);
        }
        locationManager.requestLocationUpdates(provider, 3000, 10, locationListener);

        setupMapView(savedInstanceState);
    }


/*
*
* keytool  -list -keystore android.keystore
输入密钥库口令:

密钥库类型: JKS
密钥库提供方: SUN

您的密钥库包含 1 个条目

android, 2016-10-31, PrivateKeyEntry,
证书指纹 (SHA1): 58:CF:F1:47:E7:D2:81:6C:3C:4D:48:D1:01:DB:95:A2:06:C0:D7:25
* */

    /*
    * keytool  -list -keystore debug.keystore
    *
    *
输入密钥库口令:android

密钥库类型: JKS
密钥库提供方: SUN

您的密钥库包含 1 个条目

androiddebugkey, 2016-4-21, PrivateKeyEntry,
证书指纹 (SHA1): 33:15:38:F9:AB:6B:10:35:6C:F8:66:9B:60:17:41:EA:4E:19:56:A0
*/
//    MapView
    private MapView mapView = null;
    // mapView
    protected  void setupMapView(Bundle savedInstanceState)
    {
        // 百度SDK集成:http://blog.csdn.net/syc000666/article/details/50756551
        mapView = (MapView)findViewById(R.id.fc_map_view);
//        mapView.onCreate(savedInstanceState);// 此方法必须重写

//        mapController.setTrafficEnabled(true);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
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
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates(locationListener);
        }
    }

    protected void showLocation(Location location)
    {
        String pos = "latitude is " + location.getLatitude() + "\n" +  "longitude is " + location.getLongitude();
        positonTextView.setText(pos);
    }
}
