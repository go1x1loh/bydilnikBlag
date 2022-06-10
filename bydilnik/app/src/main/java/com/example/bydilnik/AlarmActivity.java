package com.example.bydilnik;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {
    private Ringtone ringtone;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(this, notification);
        if (ringtone == null) {
            notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            ringtone = RingtoneManager.getRingtone(this, notification);
        }
        if (ringtone != null) {
            //ringtone.play();
        }
        //startService((new Intent(this, MyIntentService.class)).putExtra("hour", 1));

        Button buttonBack = (Button)findViewById(R.id.back_button);
        Button buttonOplt = (Button)findViewById(R.id.pay_button);
        buttonBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AlarmActivity.this, MainActivity.class);
                        startActivity(intent);
                        ringtone.stop();
                    }
                }
        );
        buttonOplt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(AlarmActivity.this, OplataActivity.class);
                        startActivity(intent);

                    }
                }
        );


    }

    @Override
    protected void onDestroy() {
        if (ringtone != null && ringtone.isPlaying()) {
            //ringtone.stop();

        }
        super.onDestroy();
    }

}
