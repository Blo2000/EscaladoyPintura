package com.example.escaladoypintura;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
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

        // opciones del despelgable
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
                actualizarResultado();
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        datos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                actualizarResultado();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void actualizarResultado() {
        String op = (String) desplegable.getSelectedItem();
        String datosStr = datos.getText().toString();
        if (!datosStr.isEmpty() && !op.equals("Escala")) {
            try {
                double dat = Double.parseDouble(datosStr);
                double resultadoEscala = 0;
                DecimalFormat df = new DecimalFormat("#.##");

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

                String resultadoFormateado = df.format(resultadoEscala);
                resultado.setText(resultadoFormateado);
            } catch (NumberFormatException e) {
                datos.setError("Número no válido");
            }
        } else {
            resultado.setText("");
        }
    }

    public static double escalar(double medida, double escala){
        return medida/escala;
    }
}
