package com.example.innovafit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ClaseDAO {

    private DbHelper _dbHelper;

    public ClaseDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public ArrayList<Clase> buscarClasePorSedeFecha(int idSede, String fecha) throws DAOException {
        Log.i("ClaseDAO", "buscarClasePorSedeFecha()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Clase> lista = new ArrayList<Clase>();
        try {
            Cursor c = db.rawQuery("select c.id_clase id_clase, c.id_sede id_sede, c.nombre nombre_clase, c.entrenador entrenador, c.fecha fecha, c.hora_inicio hora_inicio, c.hora_fin hora_fin, c.cantidad cantidad, c.stock stock, s.nombre nombre_sede from clase c, sede s where c.id_sede = s.id_sede and c.id_sede = "+idSede+" and c.fecha = '"+fecha+"'", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int idClase = c.getInt(c.getColumnIndex("id_clase"));
                    idSede = c.getInt(c.getColumnIndex("id_sede"));
                    String nombreClase = c.getString(c.getColumnIndex("nombre_clase"));
                    String entrenador = c.getString(c.getColumnIndex("entrenador"));
                    fecha = c.getString(c.getColumnIndex("fecha"));
                    String horaInicio = c.getString(c.getColumnIndex("hora_inicio"));
                    String horaFin = c.getString(c.getColumnIndex("hora_fin"));
                    Integer cantidad = c.getInt(c.getColumnIndex("cantidad"));
                    Integer stock = c.getInt(c.getColumnIndex("stock"));
                    String nombreSede = c.getString(c.getColumnIndex("nombre_sede"));

                    Clase modelo = new Clase();
                    Sede sede = new Sede();

                    sede.setIdSede(idSede);
                    sede.setNombre(nombreSede);

                    modelo.setIdClase(idClase);
                    modelo.setNombre(nombreClase);
                    modelo.setEntrenador(entrenador);
                    modelo.setFecha(fecha);
                    modelo.setHoraInicio(horaInicio);
                    modelo.setHoraFin(horaFin);
                    modelo.setCantidad(cantidad);
                    modelo.setStock(stock);
                    modelo.setSede(sede);

                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("ClaseDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }

    public void actualizarStock(int idClase) throws DAOException {
        Log.i("ClaseDAO", "actualizarStock()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{String.valueOf(idClase)};
            db.execSQL("UPDATE clase SET stock = stock + 1 WHERE id_clase = ? ", args);
            Log.i("ClaseDAO", "Se modificó");
        } catch (Exception e) {
            throw new DAOException("ClaseDAO: Error al modificar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void actualizarStockNeg(int idClase) throws DAOException {
        Log.i("ClaseDAO", "actualizarStockNeg()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{String.valueOf(idClase)};
            db.execSQL("UPDATE clase SET stock = stock - 1 WHERE id_clase = ? ", args);
            Log.i("ClaseDAO", "Se modificó");
        } catch (Exception e) {
            throw new DAOException("ClaseDAO: Error al modificar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void insertar(String nombre, String entrenador, String fecha, String horaInicio, String horaFin, Integer cantidad, Integer stock, String idSede) throws DAOException {
        Log.i("ClaseDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{nombre, entrenador, fecha, horaInicio, horaFin, String.valueOf(cantidad), String.valueOf(stock), idSede};
            db.execSQL("INSERT INTO clase(nombre, entrenador, fecha, hora_inicio, hora_fin, cantidad, stock, id_sede) VALUES(?,?,?,?,?,?,?,?)", args);
            Log.i("ClaseDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("ClaseDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ArrayList<Clase> buscarClasePorSedeFecha() throws DAOException {
        Log.i("ClaseDAO", "buscarClasePorSedeFecha2()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Clase> lista = new ArrayList<Clase>();
        try {
            Cursor c = db.rawQuery("select c.id_clase id_clase, c.id_sede id_sede, c.nombre nombre_clase, c.entrenador entrenador, c.fecha fecha, c.hora_inicio hora_inicio, c.hora_fin hora_fin, c.cantidad cantidad, c.stock stock, s.nombre nombre_sede from clase c, sede s where c.id_sede = s.id_sede", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int idClase = c.getInt(c.getColumnIndex("id_clase"));
                    int idSede = c.getInt(c.getColumnIndex("id_sede"));
                    String nombreClase = c.getString(c.getColumnIndex("nombre_clase"));
                    String entrenador = c.getString(c.getColumnIndex("entrenador"));
                    String fecha = c.getString(c.getColumnIndex("fecha"));
                    String horaInicio = c.getString(c.getColumnIndex("hora_inicio"));
                    String horaFin = c.getString(c.getColumnIndex("hora_fin"));
                    Integer cantidad = c.getInt(c.getColumnIndex("cantidad"));
                    Integer stock = c.getInt(c.getColumnIndex("stock"));
                    String nombreSede = c.getString(c.getColumnIndex("nombre_sede"));

                    Clase modelo = new Clase();
                    Sede sede = new Sede();

                    sede.setIdSede(idSede);
                    sede.setNombre(nombreSede);

                    modelo.setIdClase(idClase);
                    modelo.setNombre(nombreClase);
                    modelo.setEntrenador(entrenador);
                    modelo.setFecha(fecha);
                    modelo.setHoraInicio(horaInicio);
                    modelo.setHoraFin(horaFin);
                    modelo.setCantidad(cantidad);
                    modelo.setStock(stock);
                    modelo.setSede(sede);

                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("ClaseDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }
}

