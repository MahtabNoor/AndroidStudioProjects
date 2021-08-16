package com.example.mahtab.toastdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickme (View view){

    EditText nameEditText = (EditText) findViewById(R.id.nameEditText);

        Toast.makeText(this, " Hello " + nameEditText.getText().toString()  , Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}