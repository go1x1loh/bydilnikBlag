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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmActivity extends AppCompatActivity {
    private Ringtone ringtone;
    private Button buttonBack;
    private Button buttonOplt;
    private TextView textViewCount;
    private Timer timer;
    private NewTimerTask newTimerTask;
    private int count;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        count = 0;
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(this, notification);
        if (ringtone == null) {
            notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            ringtone = RingtoneManager.getRingtone(this, notification);
        }
        if (ringtone != null) {
            ringtone.play();
        }

        buttonBack = (Button)findViewById(R.id.back_button);
        buttonOplt = (Button)findViewById(R.id.pay_button);
        textViewCount = findViewById(R.id.text_view_count);

        buttonBack.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AlarmActivity.this, MainActivity.class);
                    startActivity(intent);
                    timer.cancel();
                    ringtone.stop();
                }
            }
        );

        buttonOplt.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), OplataActivity.class);
                    startActivity(intent);
                    timer.cancel();
                    ringtone.stop();
                }
            }
        );

        timer = new Timer();
        newTimerTask = new NewTimerTask();
        timer.schedule(newTimerTask, 0, 1000);
    }

    @Override
    protected void onDestroy() {
        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop();
        }
        super.onDestroy();
    }

    class NewTimerTask extends TimerTask {

        @Override
        public void run() {
            count++;

            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    textViewCount.setText(count + " руб");
                }
            });
        }
    }
}
