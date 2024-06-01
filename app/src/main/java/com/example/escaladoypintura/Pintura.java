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
import android.widget.ImageButton;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.escaladoypintura.BD.DbHelper;

import java.util.ArrayList;

public class Pintura extends AppCompatActivity{

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private ListView lista;
    private EditText nombre;
    private ImageButton bt1;
    private ArrayList<String> listaModelos = new ArrayList<>();
    private ArrayAdapter<String> adaptador;
    private ImageButton volver;

    private ImageButton eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pintura);

        lista = findViewById(R.id.lisModelo);
        nombre = findViewById(R.id.nombreModelo);
        bt1 = findViewById(R.id.bt1);

        dbHelper = new DbHelper(this);
        db = dbHelper.getWritableDatabase();

        cargarDatosDesdeBD();
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaModelos);
        lista.setAdapter(adaptador);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = nombre.getText().toString();
                if (!titulo.isEmpty()) {
                    listaModelos.add(titulo);
                    adaptador.notifyDataSetChanged();
                    nombre.getText().clear();


                    ContentValues values = new ContentValues();
                    values.put("nombre", titulo);
                    db.insert(DbHelper.TABLA_PARTES, null, values);
                }
            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombreSelecionado = listaModelos.get(position);
                Intent intent = new Intent(Pintura.this, Colores.class);
                intent.putExtra("nombreModelo", nombreSelecionado);
                startActivity(intent);
            }
        });

        volver = findViewById(R.id.btvolver);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void cargarDatosDesdeBD() {
        Cursor cursor = db.query(
                DbHelper.TABLA_PARTES,
                new String[]{"nombre"},
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            do {
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                listaModelos.add(nombre);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }


    @Override
    protected void onDestroy() {
        db.close();
        dbHelper.close();
        super.onDestroy();
    }


}


