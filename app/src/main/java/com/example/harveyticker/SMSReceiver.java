package com.example.harveyticker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();

        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){
            try {
                if (bundle != null) {

                    final Object[] pdusObj = (Object[]) bundle.get("pdus");
                    String format = bundle.getString("format").toString();

                    for (int i = 0; i < pdusObj.length; i++) {
                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                        //stopped at 8:02 in video
                        String senderNum = currentMessage.getDisplayOriginatingAddress();
                        String message = currentMessage.getDisplayMessageBody();

                        Log.i("SMSReceived", "senderNum: " + senderNum + "; message: " + message);

                        int duration = Toast.LENGTH_LONG;
                        Toast.makeText(context, "senderNum: " + senderNum + ", message: " + message, duration).show();
                    }

                }
            }catch(Exception e){
                Log.e("SMSReceived", "Exception on smsReceiver" + e);
            }
        }
    }
}