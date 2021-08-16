package com.example.mahtab.higherorlower;
import java.util.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int random;
    public void generateRandomNumber(){
        random = new Random().nextInt(20) +1 ;


    }

    public void guessed (View view){
        String message;
        EditText guessEditText = (EditText) findViewById(R.id.guessEditText);

        int userNum  = Integer.parseInt(guessEditText.getText().toString());
        if (guessEditText.getText().toString() == ""){
            message = "Please Enter a valid number" ;
        }

        if ( userNum == random ){
            message ="you have guessed the correct number .Now i have Another no. between 1 to 20 . Can you guess it.";
            generateRandomNumber();
        }
        else if (userNum < random) {
            message ="Higher ";
        }
        else{
            message = "Lower ";
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        generateRandomNumber();
    }
}
