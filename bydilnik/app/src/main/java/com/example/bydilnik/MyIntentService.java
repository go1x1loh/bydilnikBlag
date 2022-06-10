package com.example.bydilnik;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.Date;


public class MyIntentService extends IntentService {

    private Ringtone ringtone;



    public MyIntentService() {
        super("MyIntentService");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate");

    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            int min = MainActivity.min;//intent.getIntExtra("min", 0);
            int hour = MainActivity.hour;//intent.getIntExtra("hour", 0);
            Log.d("MyService", "I HAVE HOUR " + hour);
            Date date = new Date();
            int hourPresent = date.getHours();
            int minPresent = date.getMinutes();
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            ringtone = RingtoneManager.getRingtone(this, notification);
            MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.m1);
            if (hourPresent == hour && minPresent == min) {

                mediaPlayer.start();
                //ringtone.play();
                Log.d("MyService", "on");
            }
            else {
                mediaPlayer.stop();
            }

            Log.d("MyService", minPresent + " " + hourPresent);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestroy");
    }
}