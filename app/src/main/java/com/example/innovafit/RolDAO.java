package com.example.innovafit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RolDAO {

    private DbHelper _dbHelper;

    public RolDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(String nombre) throws DAOException {
        Log.i("RolDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{nombre};
            db.execSQL("INSERT INTO rol(descripcion) VALUES(?)", args);
            Log.i("RolDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("RolDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}

