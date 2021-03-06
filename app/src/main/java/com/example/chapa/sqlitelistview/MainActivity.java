package com.example.chapa.sqlitelistview;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    FloatingActionButton fab;
    DBAdapter bd;

    AdaptadorListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista = (ListView) findViewById(R.id.lista);
        fab=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        bd=new DBAdapter(getApplicationContext());
        llenarLista();
    }

    private void llenarLista(){
        bd.open();
        /*
        bd.insertContact("Raúl Carvajal","raul@gmail.com","311-125-67-63");
        bd.insertContact("Lidia Carvajal","lidia@gmail.com","311-125-67-63");
        bd.insertContact("Alian Carvajal","alian@gmail.com","311-125-67-63");
        bd.insertContact("Norma Brambila","norma@gmail.com","311-125-67-63");
        bd.insertContact("Oliver Galaviz","oliver@gmail.com","311-125-67-63");
        bd.insertContact("Eduardo Barbosa","eduardo@gmail.com","311-125-67-63");
        bd.insertContact("Daniel Medrano","daniel@gmail.com","311-125-67-63");
        bd.insertContact("Julio Bautista","julio @gmail.com","311-125-67-63");*/

        int n=bd.lengthQuery();

        String [] nombre = new String[n];
        String [] email = new String[n];
        String [] telefono = new String[n];
        int ids[] = new int[n];

        int i=0;
        Cursor result=bd.getAllContactsAZ();
        result.moveToFirst();
        while (!result.isAfterLast()) {
            int id=result.getInt(0);
            String name=result.getString(1);
            String email1=result.getString(2);
            String phone=result.getString(3);

            ids[i]=id;
            nombre[i]=name;
            email[i]=email1;
            telefono[i]=phone;

            i++;

            result.moveToNext();
        }
        result.close();

        adapter = new AdaptadorListView(this, nombre,ids,email,telefono);
        lista.setAdapter(adapter);

        bd.close();
    }
}
