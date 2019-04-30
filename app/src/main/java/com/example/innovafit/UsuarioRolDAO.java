package com.example.innovafit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UsuarioRolDAO {

    private DbHelper _dbHelper;

    public UsuarioRolDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(String idUsuario, String idRol) throws DAOException {
        Log.i("UsuarioRolDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{idUsuario, idRol};
            db.execSQL("INSERT INTO usuario_rol(id_usuario, id_rol) VALUES(?,?)", args);
            Log.i("UsuarioRolDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("UsuarioRolDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}

