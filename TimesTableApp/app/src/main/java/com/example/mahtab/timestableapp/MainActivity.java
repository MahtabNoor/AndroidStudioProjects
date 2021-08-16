package com.example.mahtab.timestableapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView tableListView;


    public void generatenumber( int timesTableNumber){




        ArrayList tableArray = new ArrayList() ;

        for (int j=1;j<=10;j++){
            tableArray.add(j*timesTableNumber);
        }

        ArrayAdapter tableArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,tableArray);

        tableListView.setAdapter(tableArrayAdapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableListView = findViewById(R.id.tableListView);

        int max = 20;
        int currentPosition = 5;
        generatenumber(currentPosition);
        final SeekBar tableSeekBar = (SeekBar) findViewById(R.id.tableSeekBar);

        tableSeekBar.setMax(20);
        tableSeekBar.setProgress(3);

        tableSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int timesTableNumber = i;
                int min = 1;

                if (timesTableNumber< min){
                    timesTableNumber = min;
                    tableSeekBar.setProgress(min);
                }
                else{

                    timesTableNumber = i;
                }

                generatenumber(timesTableNumber);





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
