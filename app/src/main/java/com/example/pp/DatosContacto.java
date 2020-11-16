package com.example.pp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DatosContacto extends AppCompatActivity implements AdapterView.OnClickListener {

    String nombreContacto;
    String numeroContacto;
    TextView txtNombreContacto;
    TextView txtNumeroContacto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_contacto);



        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {

        } else {
            pedirPermisosCall();
        }






        txtNombreContacto = findViewById(R.id.txtNombreContacto);
        txtNumeroContacto = findViewById(R.id.txtNumeroContacto);

        Bundle b = getIntent().getExtras();



        nombreContacto = b.getString("nombre");
        numeroContacto = b.getString("numero");



        txtNombreContacto.setText(nombreContacto);
        txtNumeroContacto.setText(numeroContacto);
        txtNumeroContacto.setOnClickListener((View.OnClickListener) this);

    }


    @Override
    public void onBackPressed(){
        finish();
    }

public void pedirPermisosCall(){
    int permissionCheck = ContextCompat.checkSelfPermission(
            this, Manifest.permission.CALL_PHONE);
    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 225);
    } else {

    }
}

    @Override
    public void onClick(View v) {
        Uri call = Uri.parse(numeroContacto);
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" +call));
        startActivity(intent);

    }
}