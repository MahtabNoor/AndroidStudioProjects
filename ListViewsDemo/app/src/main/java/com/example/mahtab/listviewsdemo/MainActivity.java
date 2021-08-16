package com.example.mahtab.listviewsdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView myListView = findViewById(R.id.myListView);

        final ArrayList<String> friends = new ArrayList<String>();

        friends.add("Mayank");
        friends.add("Abhishek");
        friends.add("Gulshan");
        friends.add("Ishtpreet");
        friends.add("Flower");
        friends.add("Pranjal");
        friends.add("Harshal");
        friends.add("Ajay");
        friends.add("Sachin");

        ArrayAdapter friendsArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,friends);

        myListView.setAdapter(friendsArrayAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), friends.get(i), Toast.LENGTH_SHORT).show();
            }
        });







    }
}
