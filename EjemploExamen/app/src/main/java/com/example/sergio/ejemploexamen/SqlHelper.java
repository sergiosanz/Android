package com.example.sergio.ejemploexamen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sergio on 24/01/2018.
 */

public class SqlHelper extends SQLiteOpenHelper {
    String sqlCreation = "CREATE TABLE Numeros (int INTEGER)";

    public SqlHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
            sqLiteDatabase.execSQL(sqlCreation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Numeros");
        sqLiteDatabase.execSQL(sqlCreation);
    }
}
