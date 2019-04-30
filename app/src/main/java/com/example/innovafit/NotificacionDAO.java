package com.example.innovafit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class NotificacionDAO {

    private DbHelper _dbHelper;

    public NotificacionDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(String titulo, String detalle, String fecha, String estado) throws DAOException {
        Log.i("NotificacionDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{titulo, detalle, fecha, estado};
            db.execSQL("INSERT INTO notificacion (titulo, detalle, fecha, estado) VALUES(?,?,?,?)", args);
            Log.i("NotificacionDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("NotificacionDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ArrayList<Notificacion> buscar() throws DAOException {
        Log.i("NotificacionDAO", "buscar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Notificacion> lista = new ArrayList<Notificacion>();
        try {
            Cursor c = db.rawQuery("select id_notificacion, titulo, detalle, fecha, estado from notificacion", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int idNotificacion = c.getInt(c.getColumnIndex("id_notificacion"));
                    String titulo = c.getString(c.getColumnIndex("titulo"));
                    String detalle = c.getString(c.getColumnIndex("detalle"));
                    String fecha = c.getString(c.getColumnIndex("fecha"));
                    String estado = c.getString(c.getColumnIndex("estado"));

                    Notificacion notificacion = new Notificacion();

                    notificacion.setIdNotificacion(idNotificacion);
                    notificacion.setTitulo(titulo);
                    notificacion.setDetalle(detalle);
                    notificacion.setFecha(fecha);
                    notificacion.setEstado(estado);
                    lista.add(notificacion);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("NotificacionDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }
}

