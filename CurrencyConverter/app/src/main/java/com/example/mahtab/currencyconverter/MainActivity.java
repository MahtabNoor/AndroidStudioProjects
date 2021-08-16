package com.example.mahtab.currencyconverter;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void Clicked(View view) {

        EditText valueEditText = (EditText) findViewById(R.id.valueEditText);
        String dollar = valueEditText.getText().toString();
        double doubledollar = Double.parseDouble(dollar);
        double dollartorupee = doubledollar* 75.14;
        double  x1 = dollartorupee *100 ;
        double roundrupee = (double)((int) x1);
        double x2 = roundrupee /100;
        String dollartorupeestr =Double.toString(x2);



        Toast.makeText(this, "$ " +dollar+" is " +dollartorupeestr + " Rupees", Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
