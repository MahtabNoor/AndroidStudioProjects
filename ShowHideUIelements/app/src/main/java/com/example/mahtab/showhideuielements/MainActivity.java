package com.example.mahtab.showhideuielements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public  void show(View view){
        Button showButton = (Button) findViewById(R.id.showButton);
        TextView helloTextView = (TextView) findViewById(R.id.helloTextView);
        helloTextView.setVisibility(View.VISIBLE);


    }
    public  void hide(View view){
        Button showButton = (Button) findViewById(R.id.showButton);
        TextView helloTextView = (TextView) findViewById(R.id.helloTextView);
        helloTextView.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
