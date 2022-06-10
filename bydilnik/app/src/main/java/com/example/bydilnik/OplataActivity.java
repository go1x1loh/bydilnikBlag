package com.example.bydilnik;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;


//import com.craftman.cardform.Card;
//import com.craftman.cardform.CardForm;
//import com.craftman.cardform.OnPayBtnClickListner;

import com.braintreepayments.cardform.view.CardForm;
//implementation 'com.craftman.cardform:cardform:0.0.2'
//implementation 'com.braintreepayments:card-form:3.1.1'



public class OplataActivity extends AppCompatActivity {
    private CardForm cardForm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oplata);

        cardForm = (CardForm)findViewById(R.id.cardform);
        /*
        НЕ РАБОТАЕТ

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

         */

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(OplataActivity.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
    }
}