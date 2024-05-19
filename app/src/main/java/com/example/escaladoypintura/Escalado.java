package com.example.escaladoypintura;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Escalado extends AppCompatActivity {
    Number num;
    private Spinner desplegable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escalado);

        desplegable = (Spinner) findViewById(R.id.desplegable);
        // opciones del despelgable+
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("1/72");
        opciones.add("1/76");
        opciones.add("1/48");
        opciones.add("1/35");
        opciones.add("1/32");

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, opciones);

        desplegable.setAdapter(adp);
        desplegable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String op  = (String) desplegable.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
