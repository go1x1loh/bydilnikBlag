package com.example.bydilnik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.craftman.cardform.CardForm;

public class OplataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oplata);
        CardForm cardForm = (CardForm)findViewById(R.id.cardform);
    }
}