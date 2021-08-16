package com.example.mahtab.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    int i;
    public void animate(View view) {

        ImageView gokuImageView = (ImageView) findViewById(R.id.gokuImageView);
        ImageView vegetaImageView = (ImageView) findViewById(R.id.vegetaImageView);
        gokuImageView.animate().translationXBy(1050).rotation(1800).alpha(1).setDuration(3000);   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView gokuImageView = (ImageView) findViewById(R.id.gokuImageView);
        gokuImageView.animate().translationX(-1000);
    }
}
