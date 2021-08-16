package com.example.mahtab.alarmclock;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;
    AudioManager audioManager ;
    MediaPlayer mediaPlayer;
    TextView timeLeftTextView;
    SeekBar alarmLengthSeekBar;
    boolean counterIsActive = false;
    Button startButton;


    public void reset(){
        timeLeftTextView.setText("00:30");
        alarmLengthSeekBar.setEnabled(true);
        alarmLengthSeekBar.setProgress(30);
        startButton.setText("Start");

        countDownTimer.cancel();
        counterIsActive = false;

    }

    public void startButton(View view) {
        if (counterIsActive) {
            reset();

        }
        else {
            startButton.setText("Stop!");
            alarmLengthSeekBar.setEnabled(false);
            counterIsActive = true;



         countDownTimer =   new CountDownTimer(alarmLengthSeekBar.getProgress() * 1000+100, 1000) {

                public void onTick(long l) {
                    updateTimer((int) l / 1000);

                }


                public void onFinish() {
                    mediaPlayer.start();
                    reset();

                }
            }.start();
        }
    }

           public void updateTimer(int secondsLeft){
               int minutes = secondsLeft/60;
               int seconds = secondsLeft - minutes*60;
               String secondsInString = Integer.toString(seconds);
               String minutesInString = Integer.toString(minutes);

               if (seconds <= 9){
                   secondsInString = "0"+seconds;
               }

               if (minutes <= 9){
                   minutesInString = "0"+minutes;
               }


               if (minutesInString.equals("0") ){
                   minutesInString = "00";
               }

               if (secondsInString.equals("0")){
                   secondsInString = "00";
               }


               timeLeftTextView.setText(minutesInString+":"+secondsInString);
           }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(this,R.raw.alarmclocksound);

        timeLeftTextView = (TextView) findViewById(R.id.timeLeftTextView);
        startButton = (Button) findViewById(R.id.startButton);



        alarmLengthSeekBar = (SeekBar) findViewById(R.id.alarmLengthSeekBar);
        alarmLengthSeekBar.setMax(600);
        alarmLengthSeekBar.setProgress(30);

        alarmLengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            updateTimer(i);




            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
