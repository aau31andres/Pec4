package com.example.pp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView lista;

    ArrayList mobileArray;
    ArrayList nombre;
    ArrayList<String> numerList = new ArrayList<>();
    ArrayList<String> numerList2 = new ArrayList<>();
    ArrayList String;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        inicializar();


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                == PackageManager.PERMISSION_GRANTED) {
            mobileArray = numeroContactos();
        } else {
            pedirPermisos();
        }


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.mi_layout, R.id.txtNombre, mobileArray);

        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(this);

    }





    private ArrayList numeroContactos(){
        numerList = new ArrayList<>();
        numerList2 = new ArrayList<>();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION, ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        while (phones.moveToNext())
        {
            String nombre = phones.getString(phones.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));




            numerList.add(nombre);
            numerList2.add(phoneNumber);



        }
        phones.close();
        return numerList;
    }


    /*private ArrayList nombreContactos() {
        ArrayList<String> nameList = new ArrayList<>();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, , null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        while (phones.moveToNext()) {

            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));


            nameList.add(name);


        }
        phones.close();


        return nameList;
    }*/


    /*private ArrayList getNumbers() {
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


    public void pedirPermisos() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "no tiene permisos", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 225);
        } else {
            Toast.makeText(this, "siiii tiene permisos", Toast.LENGTH_SHORT).show();
        }
    }


    public void inicializar() {

        lista = findViewById(R.id.lista);


    }

    @Override
    public void onBackPressed() {
        finish();
    }



    private static final String[] PROJECTION = new String[]{

            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };








    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



            Intent datosContactos = new Intent(this, DatosContacto.class);


            String name = parent.getItemAtPosition(position).toString();

            String selection = "DISPLAY_NAME='"+ name +"'";

            datosContactos.putExtra("nombre", name);


        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION, selection, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        //String phoneNumber = phones.getString(Integer.parseInt(phones.toString()));


            phones.moveToFirst();
            String n = phones.getString(1);




            //String numero = numerList2.get(position);

            datosContactos.putExtra("numero",n);


            startActivity(datosContactos);

            //finish();

        }


}






        /*datosContactos.putExtra("nombre", name);
        datosContactos.putExtra("numero", pho);

        startActivity(datosContactos);


        finish();*/









        /*Map<String, String> namePhoneMap = new HashMap<String, String>();

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        // Loop Through All The Numbers
        while (phones.moveToNext()) {

            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            // Cleanup the phone number
            phoneNumber = phoneNumber.replaceAll("[()\\s-]+", "");

            // Enter Into Hash Map
            namePhoneMap.put(phoneNumber, name);

        }

        // Get The Contents of Hash Map in Log
        for (Map.Entry<String, String> entry : namePhoneMap.entrySet()) {
            String key = entry.getKey();
            Toast.makeText(this, key, Toast.LENGTH_SHORT).show();
            String value = entry.getValue();
            Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
        }

        phones.close();*/

















        /*ArrayList<String> numberlista = new ArrayList<>();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
        Toast.makeText(this, "Nombre: " + nombre, Toast.LENGTH_SHORT).show();
        String name = parent.getItemAtPosition(position).toString();
        //String numero= phones.get((ContactsContract.CommonDataKinds.Phone.NUMBER)).toString();
        //String numero = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));*/
        //String name2 = (String) parent.getItemAtPosition(Integer.parseInt(ContactsContract.CommonDataKinds.Phone.NUMBER.toString()));



