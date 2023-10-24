package com.example.harveyticker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.Manifest;
import android.media.MediaCodec;
import android.os.Bundle;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    FragmentManager fg;
    private CustomViewModel sharedModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedModel = new ViewModelProvider(this).get(CustomViewModel.class);


        //for the broadcast reciever
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){

            String[] permission = new String[]{Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, permission,101);
        }

        //fragments
        fg = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fg.beginTransaction().replace(R.id.fvLeft, new TickerFragment()).commit();
            fg.beginTransaction().replace(R.id.fvRight, new WebFragment()).commit();
        }


    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    String message = intent.getStringExtra("sms");

    Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    String regex3 = "Ticker:<<[a-zA-Z][a-zA-Z][a-zA-Z]>>";
    String regex4 = "Ticker:<<[a-zA-Z][a-zA-Z][a-zA-Z][a-zA-Z]>>";
    String tick = "";

    boolean match3 = Pattern.matches(regex3, message);
    boolean match4 = Pattern.matches(regex4, message);

    if (match3 == true || match4 == true) {
        if (match3 == true) {
            char[] ch = message.toCharArray();
            char a, b, c;
            a = ch[10];
            b = ch[11];
            c = ch[12];
            tick = new StringBuilder().append("").append(a).append(b).append(c).toString();
        }
        if (match4 == true) {
            char[] ch = message.toCharArray();
            char a, b, c, d;
            a = ch[10];
            b = ch[11];
            c = ch[12];
            d = ch[13];
            tick = new StringBuilder().append("").append(a).append(b).append(c).append(d).toString();
        }

        String format = tick.toUpperCase();

        sharedModel.addTicker(format);


    } else {
        Toast.makeText(this, "SMS does not match format Ticker:<<XXX>>", Toast.LENGTH_LONG).show();
        recreate();
    }


}

}