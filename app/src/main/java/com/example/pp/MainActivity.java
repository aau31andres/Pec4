package com.example.pp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.lang.String;


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

        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, phones, PROJECTION2, new int[]{android.R.id.text1});

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



    public void pedirPermisos() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "no tiene permisos", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 225);
        }
    }


    public void inicializar() {

        lista = findViewById(R.id.lista);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private static final String[] PROJECTION2 = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts._ID
    };

    private static final String[] PROJECTION = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Intent datosContactos = new Intent(this, DatosContacto.class);

            String name = java.lang.String.valueOf((((TextView)view).getText()));

            String selection = "DISPLAY_NAME='"+ name +"'";

            datosContactos.putExtra("nombre", name);

            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PROJECTION, selection, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");

            phones.moveToFirst();
            String n = phones.getString(1);

            datosContactos.putExtra("numero",n);

            startActivity(datosContactos);

            //finish();

        }


}






