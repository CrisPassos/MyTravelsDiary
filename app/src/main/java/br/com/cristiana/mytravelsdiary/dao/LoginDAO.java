package br.com.cristiana.mytravelsdiary.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import br.com.cristiana.mytravelsdiary.model.Login;
import br.com.cristiana.mytravelsdiary.utils.Constantes;

/**
 * Created by Cristiana on 15/03/2017.
 */

public class LoginDAO {

    private SQLiteDatabase dataBase;
    private SQLiteHelper banco;

    private static final String KEY_ID = "id";
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD = "password";

    private static final String[] COLUMNS = {KEY_ID, KEY_PASSWORD, KEY_USER};

    public LoginDAO(Context context){
        banco = new SQLiteHelper(context);
    }

    public String add(Login login){
        long result;
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, login.getPassword());
        values.put(KEY_USER, login.getUser());

        result = db.insert(Constantes.TABLE_LOGIN,
                null,
                values);
        db.close();

        if(result == -1) {
            return "Error - Insert Register";
        } else {
            return "Sucess - Insert Register";
        }
    }

    public List<Login> getLogin(){
        List<Login> loginSelect = new LinkedList<>();

        String query = "SELECT  * FROM " + Constantes.TABLE_LOGIN;

        SQLiteDatabase baseDados = banco.getWritableDatabase();
        Cursor cursor = baseDados.rawQuery(query, null);

        Login login = null;
        if (cursor.moveToFirst()) {
            do {
                login = new Login();
                login.setPassword(cursor.getString(1));
                login.setUser(cursor.getString(2));

                loginSelect.add(login);
            } while (cursor.moveToNext());
        }
        return loginSelect;
    }

}
