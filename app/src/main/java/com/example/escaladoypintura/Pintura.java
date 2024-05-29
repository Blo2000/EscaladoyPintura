package com.example.escaladoypintura;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.escaladoypintura.BD.DbHelper;

import java.util.ArrayList;

public class Pintura extends AppCompatActivity{

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private ListView lista;
    private EditText nombre;
    private Button bt1;
    private ArrayList<String> listaModelos = new ArrayList<>();
    private ArrayAdapter<String> adptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pintura);

        lista = findViewById(R.id.lisModelo);
        nombre = findViewById(R.id.nombreModelo);
        bt1 = findViewById(R.id.bt1);

        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = nombre.getText().toString();
                if (!titulo.isEmpty()) {
                    /*ContentValues values = new ContentValues();
                    values.put("nombre", titulo);
                    db.insert(DbHelper.TABLA_PARTES, null, values);
                    nombre.setText("");*/
                    listaModelos.add(titulo);
                    nombre.getText().clear();
                    adptador = new ArrayAdapter<>(Pintura.this, android.R.layout.simple_list_item_1, listaModelos);
                    lista.setAdapter(adptador);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        db.close();
        dbHelper.close();
        super.onDestroy();
    }


}


