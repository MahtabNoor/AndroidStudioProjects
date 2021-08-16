package com.example.mahtab.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Scanner;


public class MainActivity extends AppCompatActivity {
    public void onClick(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        int number = Integer.parseInt(nameEditText.getText().toString());
        if (nameEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please,Enter a number", Toast.LENGTH_SHORT).show();
        } else {

            if (((Math.pow(number, 0.5)) % 1 == 0) && (((Math.pow((1 + 8 * number), 0.5) - 1) / 2) % 1 == 0)) {
                Toast.makeText(this, number +" is Both, a Square number as well as Triangular number", Toast.LENGTH_LONG).show();
            } else if (((Math.pow(number, 0.5)) % 1 == 0)) {
                Toast.makeText(this, number+" is only a Square number but not a Triangular number", Toast.LENGTH_LONG).show();
            } else if (((Math.pow((1 + (8 * number)), 0.5) - 1) / 2) % 1 == 0) {
                Toast.makeText(this, number+" is only a Triangular number but not a Square number", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, number+" is neither a Triangular Number not a Square Number", Toast.LENGTH_LONG).show();
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
