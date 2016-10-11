package com.example.tomek.pogoda_v2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TokioActivity extends AppCompatActivity {

    String miasto="Tokyo";
    String api_key="538dd70cc3c1820cd692c99ed8671edd";
    String jezyk="pl";
    String url = "http://api.openweathermap.org/data/2.5/weather?q=" + miasto + "&appid=" + api_key + "&lang=" + jezyk + "&units=metric";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tokio);


        TextView textView = (TextView) findViewById(R.id.textView);
        new GetWeatherTask(textView).execute(url);
    }
    private class GetWeatherTask extends AsyncTask<String, Void, String> {
        private TextView textView;


        public GetWeatherTask(TextView textView) {
            this.textView = textView;
        }

        @Override
        protected String doInBackground(String... strings) {
            String temperatura = "";
            String wilgotnosc = "";
            String cisnienie = "";
            String wiatr = "";
            String zachmurzenie = "";
            String pogoda = "";
            int znak_stopnia = 176;
            char c = (char)znak_stopnia;

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection polaczenie = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(polaczenie.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                JSONObject topLevel = new JSONObject(builder.toString());
                JSONObject main = topLevel.getJSONObject("main");
                temperatura = String.valueOf(main.getDouble("temp"));
                wilgotnosc = String.valueOf(main.getDouble("humidity"));
                cisnienie = String.valueOf(main.getDouble("pressure"));

                JSONObject wind = topLevel.getJSONObject("wind");
                wiatr = String.valueOf(wind.getDouble("speed"));

                JSONObject clouds = topLevel.getJSONObject("clouds");
                zachmurzenie = String.valueOf(clouds.getDouble("all"));

                pogoda = ("Temperatura: " + temperatura + c + "C" + "\n" + "Wilgotnosc: "+ wilgotnosc + "%" + "\n" + "Cisnienie: "+ cisnienie + " hPa" + "\n" + "Predkosc wiatru: "+ wiatr + " m/s" + "\n" + "Zachmurzenie: "+ zachmurzenie + "%");

                polaczenie.disconnect();

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return pogoda;
        }

        @Override
        protected void onPostExecute(String weather) {
            textView.setText("Pogoda dla Tokio: " + "\n" + weather);
        }
    }
}
