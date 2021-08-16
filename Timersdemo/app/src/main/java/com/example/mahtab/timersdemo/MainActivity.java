package com.example.mahtab.timersdemo;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    new CountDownTimer(20000,1000){
        public void onTick(long timeleftinseconds){
     Log.i("print",String.valueOf(timeleftinseconds/1000));
        }

        @Override
        public void onFinish() {
            Log.i("yay!","countdown over");
        }
    }.start();

 /*
        final Handler handler = new Handler();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.i("running time in seconds","1 second passed");
                handler.postDelayed(this,1000);
            }
        };
        handler.post(run);
*/
    }
}
