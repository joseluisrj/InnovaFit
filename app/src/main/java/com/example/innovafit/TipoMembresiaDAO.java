package com.example.innovafit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;

public class TipoMembresiaDAO {

    private DbHelper _dbHelper;

    public TipoMembresiaDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(String nombre, BigDecimal costo, Integer cantidad, String estado) throws DAOException {
        Log.i("TipoMembresiaDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{nombre, String.valueOf(costo), String.valueOf(cantidad), estado};
            db.execSQL("INSERT INTO tipo_membresia(nombre, costo, cantidad, estado) VALUES(?,?,?,?)", args);
            Log.i("TipoMembresiaDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("TipoMembresiaDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ArrayList<Sede> buscar() throws DAOException {
        Log.i("SedeDAO", "buscar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Sede> lista = new ArrayList<Sede>();
        try {
            Cursor c = db.rawQuery("select id_sede, nombre, direccion, marcador_1, marcador_2, marcador_3, estado from sede", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int idSede = c.getInt(c.getColumnIndex("id_sede"));
                    String nombre = c.getString(c.getColumnIndex("nombre"));
                    String direccion = c.getString(c.getColumnIndex("direccion"));
                    String marcador1 = c.getString(c.getColumnIndex("marcador_1"));
                    String marcador2 = c.getString(c.getColumnIndex("marcador_2"));
                    String marcador3 = c.getString(c.getColumnIndex("marcador_3"));
                    String estado = c.getString(c.getColumnIndex("estado"));

                    Sede sede = new Sede();

                    sede.setIdSede(idSede);
                    sede.setNombre(nombre);
                    sede.setDireccion(direccion);
                    sede.setMarcador1(marcador1);
                    sede.setMarcador2(marcador2);
                    sede.setMarcador3(marcador3);
                    sede.setEstado(estado);

                    lista.add(sede);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("SedeDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }
}

