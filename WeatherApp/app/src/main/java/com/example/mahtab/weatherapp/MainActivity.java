package com.example.mahtab.weatherapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String result;
    TextView cityTextView;
    EditText nameTextView;
    TextView result1TextView;
    Button weatherbutton;
    JSONObject jsonpart;

    public void weatherOfCity(View view){

        try {
            DownloadTask task = new DownloadTask();
            String chosenCity = nameTextView.getText().toString();
            String encodedChosenCity = URLEncoder.encode(chosenCity,"UTF-8");
            task.execute("http://api.openweathermap.org/data/2.5/weather?q=" + encodedChosenCity + "&appid=52670d076fd00fc91e1b949200a0d3ee").get();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(nameTextView.getWindowToken(),0);
        }  catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Could not find Weather :(",Toast.LENGTH_LONG).show();
                                 
            }

        }



   public class DownloadTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                result = "";
                while(data!= -1){
                    char current = (char) data;
                    result+= current;
                    data = reader.read();
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();

                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject = new JSONObject(s);
                String allCitiesWeather = jsonObject.getString("weather");
                JSONArray arr = new JSONArray(allCitiesWeather);
                JSONObject allCitiesMinMaxTemp = new JSONObject(jsonObject.getString("main"));
                String current_Temp = allCitiesMinMaxTemp.getString("temp");
                String max_Temp = allCitiesMinMaxTemp.getString("temp_max");
                String min_Temp = allCitiesMinMaxTemp.getString("temp_min");

                for (int i=0;i<arr.length();i++){
                    jsonpart = arr.getJSONObject(i);
                }
                
                String message = "";
                String main = jsonpart.getString("main");
                String description = jsonpart.getString("description");
                if(!main.equals("") && !description.equals("")){
                    message+= main+" : "+description+"\r\n";
                }
                else {
                    Toast.makeText(getApplicationContext(),"Could not find Weather :(",Toast.LENGTH_LONG).show();

                }
                if (!message.equals("")){
                    result1TextView.setText(message+"\nCurrent temp = "+current_Temp+"\nMin. Temp. = "+min_Temp+"\nMax Temp. = "+max_Temp);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Could not find Weather :(",Toast.LENGTH_LONG).show();

                }



            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),"Could not find Weather :(",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityTextView = (TextView) findViewById(R.id.cityTextView);
        nameTextView = (EditText) findViewById(R.id.nameTextView);
        result1TextView = (TextView) findViewById(R.id.result1TextView);
        weatherbutton = (Button) findViewById(R.id.weatherButton);



    }
    }



