package com.example.harveyticker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.pm.PackageManager;
import android.Manifest;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    FragmentManager fg;
    private CustomViewModel sharedModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedModel = new ViewModelProvider(this).get(SharedViewModel.class);


        //for the broadcast reciever
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){

            String[] permision = new String[]{Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, permision,101);
        }

        fg = getSupportFragmentManager();


        if (savedInstanceState == null) {
            fg.beginTransaction().replace(R.id.fvLeft, new TickerFragment()).commit();
            fg.beginTransaction().replace(R.id.fvRight, new WebFragment()).commit();
        }
    }
}