package com.example.innovafit;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MembresiaDAO {

    private DbHelper _dbHelper;

    public MembresiaDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(String nombre, String fechaInicio, String fechaFin, Integer stock, Integer idTipo, String usuario) throws DAOException {
        Log.i("MembresiaDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{nombre, fechaInicio, fechaFin, String.valueOf(stock), String.valueOf(idTipo), usuario};
            db.execSQL("INSERT INTO membresia(nombre, fecha_inicio, fecha_fin, stock, id_tipo, id_usuario) VALUES(?,?,?,?,?,?)", args);
            Log.i("MembresiaDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("MembresiaDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public Membresia obtener(String usuario) throws DAOException {
        Log.i("MembresiaDAO", "obtener()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        Membresia modelo = new Membresia();
        Usuario user = new Usuario();
        TipoMembresia tipoMembresia = new TipoMembresia();
        try {
            Cursor c = db.rawQuery("select m.id_membresia id_membresia, m.nombre nombre, m.fecha_inicio fecha_inicio, m.fecha_fin fecha_fin, m.stock stock, m.id_tipo id_tipo, m.id_usuario id_usuario, t.cantidad cantidad from membresia m, tipo_membresia t where m.id_tipo = t.id_tipo and id_usuario = '"+usuario+"'", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int idMembresia = c.getInt(c.getColumnIndex("id_membresia"));
                    String nombre = c.getString(c.getColumnIndex("nombre"));
                    String fechaInicio = c.getString(c.getColumnIndex("fecha_inicio"));
                    String fechaFin = c.getString(c.getColumnIndex("fecha_fin"));
                    Integer stock = c.getInt(c.getColumnIndex("stock"));
                    Integer idTipo = c.getInt(c.getColumnIndex("id_tipo"));
                    String idUsuario = c.getString(c.getColumnIndex("id_usuario"));
                    Integer cantidad = c.getInt(c.getColumnIndex("cantidad"));

                    user.setIdUsuario(idUsuario);

                    tipoMembresia.setIdTipo(idTipo);
                    tipoMembresia.setCantidad(cantidad);

                    modelo.setIdMembresia(idMembresia);
                    modelo.setNombre(nombre);
                    modelo.setFechaInicio(fechaInicio);
                    modelo.setFechaFin(fechaFin);
                    modelo.setStock(stock);
                    modelo.setUsuario(user);
                    modelo.setTipoMembresia(tipoMembresia);

                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("MembresiaDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return modelo;
    }

    public void actualizarStock(int idMembresia) throws DAOException {
        Log.i("MembresiaDAO", "actualizarStock()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{String.valueOf(idMembresia)};
            db.execSQL("UPDATE membresia SET stock = stock + 1 WHERE id_membresia = ? ", args);
            Log.i("MembresiaDAO", "Se modificó");
        } catch (Exception e) {
            throw new DAOException("MembresiaDAO: Error al modificar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void actualizarStockNeg(int idMembresia) throws DAOException {
        Log.i("MembresiaDAO", "actualizarStockNeg()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{String.valueOf(idMembresia)};
            db.execSQL("UPDATE membresia SET stock = stock - 1 WHERE id_membresia = ? ", args);
            Log.i("MembresiaDAO", "Se modificó");
        } catch (Exception e) {
            throw new DAOException("MembresiaDAO: Error al modificar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ArrayList<Membresia> buscar() throws DAOException {
        Log.i("MembresiaDAO", "buscar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Membresia> lista = new ArrayList<Membresia>();
        Membresia modelo = new Membresia();
        Usuario user = new Usuario();
        TipoMembresia tipoMembresia = new TipoMembresia();
        try {
            Cursor c = db.rawQuery("select m.id_membresia id_membresia, m.nombre nombre, m.fecha_inicio fecha_inicio, m.fecha_fin fecha_fin, m.stock stock, m.id_tipo id_tipo, m.id_usuario id_usuario, t.cantidad cantidad from membresia m, tipo_membresia t where m.id_tipo = t.id_tipo", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int idMembresia = c.getInt(c.getColumnIndex("id_membresia"));
                    String nombre = c.getString(c.getColumnIndex("nombre"));
                    String fechaInicio = c.getString(c.getColumnIndex("fecha_inicio"));
                    String fechaFin = c.getString(c.getColumnIndex("fecha_fin"));
                    Integer stock = c.getInt(c.getColumnIndex("stock"));
                    Integer idTipo = c.getInt(c.getColumnIndex("id_tipo"));
                    String idUsuario = c.getString(c.getColumnIndex("id_usuario"));
                    Integer cantidad = c.getInt(c.getColumnIndex("cantidad"));

                    user.setIdUsuario(idUsuario);

                    tipoMembresia.setIdTipo(idTipo);
                    tipoMembresia.setCantidad(cantidad);

                    modelo.setIdMembresia(idMembresia);
                    modelo.setNombre(nombre);
                    modelo.setFechaInicio(fechaInicio);
                    modelo.setFechaFin(fechaFin);
                    modelo.setStock(stock);
                    modelo.setUsuario(user);
                    modelo.setTipoMembresia(tipoMembresia);

                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("MembresiaDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }

    public ArrayList<Membresia> buscarById(String usuario) throws DAOException {
        Log.i("MembresiaDAO", "buscar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Membresia> lista = new ArrayList<Membresia>();
        Membresia modelo = new Membresia();
        Usuario user = new Usuario();
        TipoMembresia tipoMembresia = new TipoMembresia();
        try {
            Cursor c = db.rawQuery("select m.id_membresia id_membresia, m.nombre nombre, m.fecha_inicio fecha_inicio, m.fecha_fin fecha_fin, m.stock stock, m.id_tipo id_tipo, m.id_usuario id_usuario, t.cantidad cantidad, t.costo costo from membresia m, tipo_membresia t where m.id_tipo = t.id_tipo and m.id_usuario = '"+usuario+"'", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int idMembresia = c.getInt(c.getColumnIndex("id_membresia"));
                    String nombre = c.getString(c.getColumnIndex("nombre"));
                    String fechaInicio = c.getString(c.getColumnIndex("fecha_inicio"));
                    String fechaFin = c.getString(c.getColumnIndex("fecha_fin"));
                    Integer stock = c.getInt(c.getColumnIndex("stock"));
                    Integer idTipo = c.getInt(c.getColumnIndex("id_tipo"));
                    String idUsuario = c.getString(c.getColumnIndex("id_usuario"));
                    Integer cantidad = c.getInt(c.getColumnIndex("cantidad"));
                    BigDecimal costo = BigDecimal.valueOf(c.getDouble(c.getColumnIndex("costo")));

                    user.setIdUsuario(idUsuario);

                    tipoMembresia.setIdTipo(idTipo);
                    tipoMembresia.setCantidad(cantidad);
                    tipoMembresia.setCosto(costo);

                    modelo.setIdMembresia(idMembresia);
                    modelo.setNombre(nombre);
                    modelo.setFechaInicio(fechaInicio);
                    modelo.setFechaFin(fechaFin);
                    modelo.setStock(stock);
                    modelo.setUsuario(user);
                    modelo.setTipoMembresia(tipoMembresia);

                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("MembresiaDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }
}

