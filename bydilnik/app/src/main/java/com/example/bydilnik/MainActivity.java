package com.example.bydilnik;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button setAlarm;
    public static int hour;
    public static int min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        final int yearC = calendar.get(Calendar.YEAR);
        final int monthC = calendar.get(Calendar.MONTH);
        final int dayC = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        setAlarm = findViewById(R.id.alarm_button);
        setAlarm.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int day) {

                            MaterialTimePicker materialTimePicker = new MaterialTimePicker.Builder()
                                    .setTimeFormat(TimeFormat.CLOCK_24H)
                                    .setHour(12)
                                    .setMinute(0)
                                    .setTitleText("выберете время пробуждения")
                                    .build();

                            materialTimePicker.addOnPositiveButtonClickListener(view1 -> {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(Calendar.SECOND, 0);
                                calendar.set(Calendar.MILLISECOND, 0);
                                calendar.set(Calendar.MINUTE, materialTimePicker.getMinute());
                                calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());

                                calendar.set(Calendar.DAY_OF_MONTH, day);
                                hour = materialTimePicker.getHour();
                                min = materialTimePicker.getMinute();
                                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                                AlarmManager.AlarmClockInfo alarmClockInfo = new AlarmManager.AlarmClockInfo(calendar.getTimeInMillis(), getAlarmInfoPendingIntent());

                                alarmManager.setAlarmClock(alarmClockInfo, getAlarmActionPendingIntent());
                                Toast.makeText(MainActivity.this, "будильник установлен на "+ simpleDateFormat.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
                            });

                            materialTimePicker.show(getSupportFragmentManager(), "tag_picker");
                        }
                    }, yearC, monthC, dayC);
            datePickerDialog.show();
        });
    }

    private PendingIntent getAlarmInfoPendingIntent() {
        Intent alarmInfointent = new Intent(this, MainActivity.class);
        alarmInfointent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(this, 0, alarmInfointent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private PendingIntent getAlarmActionPendingIntent() {
        Intent intent = new Intent(this, AlarmActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}