package com.example.pp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.EditText;
import android.widget.TextView;

public class DatosContacto extends AppCompatActivity {

    String nombreContacto;
    String numeroContacto;
    TextView txtNombreContacto;
    TextView txtNumeroContacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_contacto);
        txtNombreContacto = findViewById(R.id.txtNombreContacto);
        txtNumeroContacto = findViewById(R.id.txtNumeroContacto);

        Bundle b = getIntent().getExtras();



        nombreContacto = b.getString("nombre");
        numeroContacto = b.getString("numero");



        txtNombreContacto.setText(nombreContacto);
        txtNumeroContacto.setText(numeroContacto);


    }


    @Override
    public void onBackPressed(){
        finish();
    }






}