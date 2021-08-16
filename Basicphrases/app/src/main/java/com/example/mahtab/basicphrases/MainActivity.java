package com.example.mahtab.basicphrases;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer playMediaPlayer;



    public void voice(View view) {

        Button click = (Button) view;
        String tappedClick = click.getTag().toString();
        Log.i("voices",tappedClick);
        playMediaPlayer = MediaPlayer.create(this,getResources().getIdentifier(tappedClick, "raw", getPackageName()));

        playMediaPlayer.start();

    }
    {}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
