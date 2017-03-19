package br.com.cristiana.mytravelsdiary.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.cristiana.mytravelsdiary.utils.Constantes;

/**
 * Created by Cristiana on 15/03/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_NAME = "Travel.db";

    //Create Tables
    String CREATE_LOGIN_TABLE = "CREATE TABLE login ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "password TEXT," +
            "user TEXT)";

    String CREATE_TRAVEL_TABLE = "CREATE TABLE travel ( " +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "destiny TEXT," +
            "days TEXT," +
            "value TEXT," +
            "departureDate  TEXT," +
            "returnDate TEXT," +
            "hotel TEXT," +
            "touristHotspots TEXT)";


    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOGIN_TABLE);
        db.execSQL(CREATE_TRAVEL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ Constantes.TABLE_LOGIN);
        db.execSQL("DROP TABLE IF EXISTS "+ Constantes.TABLE_TRAVEL);
        this.onCreate(db);
    }
}
