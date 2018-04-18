package com.vinod.runtimepermissionapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.TokenWatcher;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  final static int FINE_LOC_REQUEST =1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClkAddPermissions(View view)
    {
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M) {
            if (checkIfAlreadyhavePermission()) {
                toast("Granted");
            } else {
                toast("Not Granted");
                ActivityCompat.requestPermissions(this,
                        new String[]{ Manifest.permission.ACCESS_FINE_LOCATION}
                        ,FINE_LOC_REQUEST);
            }
        }
        else
        {
            toast("below Marshmallow");

        }
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED ? true: false;
    }
    void toast(String str)
    {
        Toast.makeText(this, str+"", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case FINE_LOC_REQUEST:
                if (checkIfAlreadyhavePermission()) {
                    toast("Granted");
                } else {
                    toast("Not Granted");
                }


                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
