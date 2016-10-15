package com.example.tomek.pogoda_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String[] MOJE_MIASTA = {"WARSZAWA","PARYŻ","TOKIO","MOSKWA","TORONTO"};
    String wybrane_miasto="";
    Button PRZEJDZ;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner =(Spinner)findViewById(R.id.spinner1);

        PRZEJDZ = (Button)findViewById(R.id.button1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, MOJE_MIASTA);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                wybrane_miasto = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        PRZEJDZ.setOnClickListener(new View.OnClickListener() {
            private final int REQUEST_CODE = 1;
            @Override
            public void onClick(View v) {

                switch(wybrane_miasto){

                    case "WARSZAWA":
                        intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("miasto_url", "Warsaw");
                        intent.putExtra("miasto_wynik", "Warszawa");
                        startActivityForResult(intent,REQUEST_CODE);
                        break;

                    case "PARYŻ":
                        intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("miasto_url", "Paris");
                        intent.putExtra("miasto_wynik", "Paryż");
                        startActivityForResult(intent,REQUEST_CODE);
                        break;

                    case "TOKIO":
                        intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("miasto_url", "Tokyo");
                        intent.putExtra("miasto_wynik", "Tokio");
                        startActivityForResult(intent,REQUEST_CODE);
                        break;

                    case "MOSKWA":
                        intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("miasto_url", "Moscow");
                        intent.putExtra("miasto_wynik", "Moskwa");
                        startActivityForResult(intent,REQUEST_CODE);
                        break;

                    case "TORONTO":
                        intent = new Intent(MainActivity.this, SecondActivity.class);
                        intent.putExtra("miasto_url", "Toronto");
                        intent.putExtra("miasto_wynik", "Toronto");
                        startActivityForResult(intent,REQUEST_CODE);
                        break;

                }
            }
        });

    }
}
