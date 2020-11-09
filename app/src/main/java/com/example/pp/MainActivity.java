package com.example.pp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lista;

    ArrayList mobileArray;
    ArrayList nombre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inicializar();


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            mobileArray = nombreContactos();
        } else {
            pedirPermisos();
        }



        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.mi_layout, R.id.txtNombre, mobileArray);

        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(this);

    }




    /*private ArrayList numeroContactos(){
        ArrayList<String> numerList = new ArrayList<>();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        while (phones.moveToNext())
        {

            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            numerList.add(phoneNumber);


        }
        phones.close();
        return numerList;
    }*/


    private ArrayList nombreContactos(){
        ArrayList<String> nameList = new ArrayList<>();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        while (phones.moveToNext())
        {

            String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

            nameList.add(name);


        }
        phones.close();
        return nameList;
    }





    /*private ArrayList getAllContacts() {
        ArrayList<String> nameList = new ArrayList<>();
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                nameList.add(name);
                if (cur.getInt(cur.getColumnIndex( ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    pCur.close();
                }
            }
        }
        if (cur != null) {
            cur.close();
        }
        return nameList;
    }*/


    public void pedirPermisos(){
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS );

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "no tiene permisos", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS }, 225);
        } else {
            Toast.makeText(this, "siiii tiene permisos", Toast.LENGTH_SHORT).show();
        }
    }



    public void inicializar(){

        lista = findViewById(R.id.lista);


    }

    @Override
    public void onBackPressed(){
        finish();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String nombre = parent.getItemAtPosition(position).toString();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        Toast.makeText(this, "Nombre: " + nombre, Toast.LENGTH_SHORT).show();

        Intent datosContactos = new Intent(this,DatosContacto.class);
        String name= String.valueOf(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));


        datosContactos.putExtra("nombre", name);


        startActivity(datosContactos);


        finish();

    }
}