package com.example.bydilnik;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeReceiver extends BroadcastReceiver {
    Context context;
    private final String BOOT_ACTION = "android.intent.action.BOOT_COMPLETED";

    public TimeReceiver() {}



    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String action = intent.getAction();

        StringBuilder msgStr = new StringBuilder("Текущее время: ");
        Format formatter = new SimpleDateFormat("hh:mm:ss a");
        msgStr.append(formatter.format(new Date()));
        Toast.makeText(context, msgStr, Toast.LENGTH_SHORT).show();
        Log.d("Time", String.valueOf(msgStr));


        //if (action.equalsIgnoreCase(BOOT_ACTION)) {

            Intent intentNew = new Intent(context, MyIntentService.class);//.putExtra("min", MainActivity.min).putExtra("hour", MainActivity.hour);
            context.startService(intentNew);
            //startService((new Intent(this, MyIntentService.class)).putExtra("hour", 1));
        //}
    }
}