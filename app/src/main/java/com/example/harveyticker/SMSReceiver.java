package com.example.harveyticker;

import static android.app.PendingIntent.getActivity;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();



        if (intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)) {
            try {
                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    String format = bundle.getString("format").toString();

                    for (int i = 0; i < pdusObj.length; i++) {
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                        //stopped at 8:02 in video
                        String senderNum = currentMessage.getDisplayOriginatingAddress();
                        String message = currentMessage.getDisplayMessageBody();


                        Intent intent1 = new Intent(context, MainActivity.class);


                        intent1.putExtra("sms", message);
                        intent1.setAction(Intent.ACTION_SEND);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent1);


                    }

                }
            } catch (Exception e) {
                Log.e("SMSReceived", "Exception on smsReceiver" + e);
            }
        }

    }
}