package com.example.sergio.ejemploexamen;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SqlHelper numerosSQLite = new SqlHelper(this, "DBUsuarios", null, 1);

        SQLiteDatabase db = numerosSQLite.getWritableDatabase();

        db.execSQL("DELETE FROM Numeros");

        for (int i = 0; i < 5; i++)
            db.execSQL("INSERT INTO Numeros (int) VALUES (" + i + ",'Numero" + i + "')");

        String numero;
        List<String> personas = new ArrayList<String>();

        Cursor cursor = db.rawQuery("SELECT * FROM Usuarios", null);

        if (cursor.moveToFirst()) {
            do {
                numero = "";
                numero += cursor.getInt(0) + "-";
                numero += cursor.getString(1);
                personas.add(numero);
                //cursor.getString(0);
            } while (cursor.moveToNext());
        }

        String[] campos = new String[]{"codigo", "nombre"};

        String[] argumentos = new String[]{"2", "4"};

        Cursor cursor2 = db.query("Usuarios", campos, "codigo=? or codigo=?", argumentos, null, null, null);

        if (cursor2.moveToFirst()) {
            do {
                numero = "";
                numero += cursor2.getInt(0) + "-";
                numero += cursor2.getString(1);
                personas.add(numero);
                //cursor.getString(0);
            } while (cursor2.moveToNext());
        }


        ListView listview = (ListView) findViewById(R.id.lista);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, personas);

        listview.setAdapter(adapter);

        db.close();
    }
}
