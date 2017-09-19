package com.example.chapa.sqlitelistview;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    DBAdapter bd;

    AdaptadorListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.lista);

        bd=new DBAdapter(getApplicationContext());

        bd.open();
        bd.insertContact("Raúl Carvajal","raul@gmail.com","311-125-67-63");
        bd.insertContact("Lidia Carvajal","lidia@gmail.com","311-125-67-63");
        bd.insertContact("Alian Carvajal","alian@gmail.com","311-125-67-63");

        int n=bd.lengthQuery();

        Toast.makeText(this,""+n, Toast.LENGTH_SHORT).show();

        String [] nombre = new String[n];
        String [] email = new String[n];
        String [] telefono = new String[n];
        int ids[] = new int[n];

        int i=0;
        Cursor result=bd.getAllContacts();
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


        //ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,bd.getAllContactsVector());
        //ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,getTabla(num));//Cambia tamaño
        //lista.setAdapter(adp);*/
        bd.close();
    }
}
