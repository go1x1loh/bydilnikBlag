package com.example.bydilnik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class OplataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oplata);
        CardForm cardForm = (CardForm)findViewById(R.id.cardform);
        TextView txtDes = (TextView)findViewById(R.id.payment_amount);
        Button btnPay = (Button)findViewById(R.id.btn_pay);
        txtDes.setText("50 rub");
        btnPay.setText(String.format("Payer %s", txtDes.getText()));
        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                Toast.makeText(OplataActivity.this, "Name "+ card.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}