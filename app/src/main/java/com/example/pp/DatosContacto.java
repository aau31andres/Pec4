package com.example.pp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class DatosContacto extends AppCompatActivity {

    String nombreContacto;
    TextView txtNombreContacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_contacto);

        Bundle b = getIntent().getExtras();



        nombreContacto = b.getString("nombre");



        txtNombreContacto = findViewById(R.id.txtNombreContacto);
        txtNombreContacto.setText(nombreContacto);


    }






}