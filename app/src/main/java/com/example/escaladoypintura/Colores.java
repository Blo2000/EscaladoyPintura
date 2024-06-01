package com.example.escaladoypintura;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.escaladoypintura.BD.DbHelper;
public class Colores extends AppCompatActivity {

    private TextView nomb;
    private EditText colorUsados;
    private String textoColores;
    private String nombreModelo;

    private ImageButton volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colores);
        nomb = findViewById(R.id.modelo_nom);
        nombreModelo = getIntent().getStringExtra("nombreModelo");
        nomb.setText(nombreModelo);

        colorUsados = findViewById(R.id.colorUsados);

        textoColores = obtenerTexto(nombreModelo);
        colorUsados.setText(textoColores);

        colorUsados.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String textoEdi = s.toString();
                guardarTexto (textoEdi,nombreModelo);
                ajustar(s.toString());
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

    private String obtenerTexto(String nombreModelo) {
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String texto = "";

        String[] columnas = {"color"};
        String seleccion = "parte_id = (SELECT id FROM " + DbHelper.TABLA_PARTES + " WHERE nombre = ?)";
        String[] seleccionArgs = {nombreModelo};

        Cursor cursor = db.query(DbHelper.TABLA_COLORES, columnas, seleccion, seleccionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String color = cursor.getString(cursor.getColumnIndexOrThrow("color"));
                texto += color + "\n";
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return texto;
    }

    private void guardarTexto(String texto, String nombreModelo) {
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("color", texto);
        String seleccion = "parte_id = (SELECT id FROM " + DbHelper.TABLA_PARTES + " WHERE nombre = ?)";
        String[] seleccionArgs = {nombreModelo};
        int rowsAffected = db.update(DbHelper.TABLA_COLORES, values, seleccion, seleccionArgs);

        if (rowsAffected == 0) {
            values.put("parte_id", posicion(nombreModelo));
            db.insert(DbHelper.TABLA_COLORES, null, values);
        }

        db.close();
    }
    private int posicion(String nombreModelo) {
        DbHelper dbHelper = new DbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {"id"};
        String seleccion = "nombre = ?";
        String[] seleccionArgs = {nombreModelo};

        Cursor cursor = db.query(DbHelper.TABLA_PARTES, columnas, seleccion, seleccionArgs, null, null, null);

        int idModelo = -1;
        if (cursor != null && cursor.moveToFirst()) {
            idModelo = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            cursor.close();
        }

        db.close();
        return idModelo;
    }

    private void ajustar(String texto) {
        int lineCount = colorUsados.getLineCount();
        int maxLines = colorUsados.getMaxLines();

        if (lineCount > maxLines) {
            colorUsados.setMaxLines(lineCount);
        }
    }


}
