package com.example.escaladoypintura.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NOMBRE = "paleta.db";
    public static final String TABLA_PARTES = "partes";
    public static final String TABLA_COLORES = "colores";

    public DbHelper(@Nullable Context context) {
        super(context, NOMBRE, null, VERSION);
    }


    private static final String SQL_CREATE_PARTES =
            "CREATE TABLE " + TABLA_PARTES + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT NOT NULL)";

    private static final String SQL_CREATE_COLORES =
            "CREATE TABLE " + TABLA_COLORES + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "color TEXT NOT NULL, " +
                    "parte_id INTEGER NOT NULL, " +
                    "FOREIGN KEY(parte_id) REFERENCES " + TABLA_PARTES + "(id))";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PARTES);
        db.execSQL(SQL_CREATE_COLORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_COLORES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_PARTES);
        onCreate(db);
    }
}
