package br.com.cristiana.mytravelsdiary.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import br.com.cristiana.mytravelsdiary.model.Travel;
import br.com.cristiana.mytravelsdiary.utils.Constantes;

/**
 * Created by Cristiana on 18/03/2017.
 */

public class TravelDAO {

    private SQLiteDatabase dataBase;
    private SQLiteHelper banco;

    private static final String KEY_ID = "id";
    private static final String KEY_DESTINY = "destiny";
    private static final String KEY_DAYS = "days";
    private static final String KEY_VALUE = "value";
    private static final String KEY_DEPARTURE = "departureDate";
    private static final String KEY_RETURN = "returnDate";
    private static final String KEY_HOTEL = "hotel";
    private static final String KEY_TOURIST = "touristHotspots";

    private static final String[] COLUMNS = {KEY_ID, KEY_DESTINY, KEY_DAYS, KEY_VALUE, KEY_DEPARTURE, KEY_RETURN,  KEY_HOTEL, KEY_TOURIST};

    public TravelDAO(Context context) {
        banco = new SQLiteHelper(context);
    }

    public TravelDAO() { }

    public String add(Travel travel){
        long result;
        long id = travel.getId();
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESTINY, travel.getDestiny());
        values.put(KEY_DAYS, travel.getDays());
        values.put(KEY_VALUE, travel.getValue());
        values.put(KEY_DEPARTURE, travel.getDepartureDate());
        values.put(KEY_RETURN, travel.getReturnDate());
        values.put(KEY_HOTEL, travel.getHotel());
        values.put(KEY_TOURIST, travel.getTouristsHotspots());

        try {
           result = db.insert(Constantes.TABLE_TRAVEL, null, values);
        }finally {
            db.close();
        }

        if(result == -1) {
            return "Error - Insert Register";
        } else {
            return "Sucess - Insert Register";
        }
    }

    public List<Travel> getAll(){
        List<Travel> travelSelect = new LinkedList<>();

        String query = "SELECT  * FROM " + Constantes.TABLE_TRAVEL;

        SQLiteDatabase db = banco.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Travel travel = null;
        if (cursor.moveToFirst()) {
            do {
                travel = new Travel();
                travel.setId(cursor.getInt(0));
                travel.setDestiny(cursor.getString(1));
                travel.setDays(cursor.getString(2));
                travel.setValue(cursor.getString(3));
                travel.setDepartureDate(cursor.getString(4));
                travel.setReturnDate(cursor.getString(5));
                travel.setHotel(cursor.getString(6));
                travel.setTouristsHotspots(cursor.getString(7));

                travelSelect.add(travel);
            } while (cursor.moveToNext());
        }

        db.close();

        return travelSelect;
    }

    public int delete(Travel travel){
        SQLiteDatabase db = banco.getWritableDatabase();

        try {
            int count = db.delete(Constantes.TABLE_TRAVEL, "id = ?", new String[]{String.valueOf(travel.getId())});
            Log.i(null, "Registro [ "+ count + " ]deletado" );
            return count;
        } finally {
            db.close();
        }
    }

    public Travel getById(long todo_id) {
        SQLiteDatabase db = banco.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + Constantes.TABLE_TRAVEL + " WHERE "
                + KEY_ID + " = " + todo_id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Travel travel = new Travel();
        travel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        travel.setDestiny((c.getString(c.getColumnIndex(KEY_DESTINY))));
        travel.setDays((c.getString(c.getColumnIndex(KEY_DAYS))));
        travel.setValue((c.getString(c.getColumnIndex(KEY_VALUE))));
        travel.setDepartureDate((c.getString(c.getColumnIndex(KEY_DEPARTURE))));
        travel.setReturnDate((c.getString(c.getColumnIndex(KEY_RETURN))));
        travel.setHotel((c.getString(c.getColumnIndex(KEY_HOTEL))));
        travel.setTouristsHotspots((c.getString(c.getColumnIndex(KEY_TOURIST))));

        return travel;
    }

    public int updateTravel(Travel travel) {
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DESTINY, travel.getDestiny());
        values.put(KEY_DAYS, travel.getDays());
        values.put(KEY_VALUE, travel.getValue());
        values.put(KEY_DEPARTURE, travel.getDepartureDate());
        values.put(KEY_RETURN, travel.getReturnDate());
        values.put(KEY_HOTEL, travel.getHotel());
        values.put(KEY_TOURIST, travel.getTouristsHotspots());

        // updating row
        return db.update(Constantes.TABLE_TRAVEL, values, KEY_ID + " = ?",
                new String[] { String.valueOf(travel.getId()) });

    }
}
