package com.example.escaladoypintura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button bt2;
    Button bt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);

        bt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent pintura = new Intent(MainActivity.this, Pintura.class);
                MainActivity.this.startActivity(pintura);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent escalar = new Intent(MainActivity.this, Escalado.class);
                MainActivity.this.startActivity(escalar);
            }
        });

    }
}