package com.example.mahtab.imageswitcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void Clicked(View view) {

        Log.i("Info","Button is Working" );
        ImageView image = (ImageView) findViewById(R.id.catImageView);
        image.setImageResource(R.drawable.pic5);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
