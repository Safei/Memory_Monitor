package com.example.servicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

//�㲥������
public class clsReceiver2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
    	String action = intent.getAction();
    	Toast.makeText(context, "��̬:"+action, 500).show();
    }
}
