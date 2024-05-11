package com.example.escaladoypintura;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

public class Escalado extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escalado);

        // opciones del despelgable
        String[] opciones = {"1/72", "1/76", "1/48", "1/35", "1/32"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, opciones);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.desplegable);
        autoCompleteTextView.setAdapter(adapter);

    }
}
