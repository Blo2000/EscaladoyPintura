package com.example.escaladoypintura;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Escalado extends AppCompatActivity {
    private Number num;
    private Spinner desplegable;
    private TextView resultado;
    private EditText datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escalado);
        resultado = findViewById(R.id.resultado);
        desplegable = (Spinner) findViewById(R.id.desplegable);
        datos = findViewById(R.id.datos);

        // opciones del despelgable+
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("Escala");
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
                String op = (String) desplegable.getAdapter().getItem(position);
                String datosStr = datos.getText().toString();
                if (!datosStr.isEmpty()) {
                    try {
                        double dat = Double.parseDouble(datosStr);
                        double resultadoEscala = 0;

                        switch (op) {
                            case "1/72":
                                resultadoEscala = escalar(dat, 72);
                                break;
                            case "1/76":
                                resultadoEscala = escalar(dat, 76);
                                break;
                            case "1/48":
                                resultadoEscala = escalar(dat, 48);
                                break;
                            case "1/35":
                                resultadoEscala = escalar(dat, 35);
                                break;
                            case "1/32":
                                resultadoEscala = escalar(dat, 32);
                                break;
                        }

                        resultado.setText(String.format(String.valueOf(resultadoEscala)));
                    } catch (NumberFormatException e) {
                        datos.setError("Numero no valido");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public static double escalar(double medida, double escala){
        return medida/escala;
    }
}
