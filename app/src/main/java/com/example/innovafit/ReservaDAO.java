package com.example.innovafit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ReservaDAO {

    private DbHelper _dbHelper;

    public ReservaDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(int idClase, int idMembresia) throws DAOException {
        Log.i("ReservaDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{String.valueOf(idClase), String.valueOf(idMembresia), "0"};
            db.execSQL("INSERT INTO reserva(id_clase, id_membresia, estado) VALUES(?,?,?)", args);
            Log.i("ReservaDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("ReservaDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ArrayList<Reserva> buscar(int idMembresia) throws DAOException {
        Log.i("ReservaDAO", "buscar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Reserva> lista = new ArrayList<Reserva>();
        try {
            Cursor c = db.rawQuery("select id_reserva, r.id_clase id_clase, r.id_membresia id_membresia, r.estado estado, c.nombre nombre_clase, entrenador, fecha, hora_inicio, hora_fin, c.id_sede id_sede, s.nombre nombre_sede from reserva r, clase c, sede s where r.id_clase = c.id_clase and c.id_sede = s.id_sede and id_membresia = '" + idMembresia+ "'", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int idReserva = c.getInt(c.getColumnIndex("id_reserva"));
                    int idClase = c.getInt(c.getColumnIndex("id_clase"));
                    int idMembresia1 = c.getInt(c.getColumnIndex("id_membresia"));
                    String estado = c.getString(c.getColumnIndex("estado"));
                    String nombreClase = c.getString(c.getColumnIndex("nombre_clase"));
                    String entrenador = c.getString(c.getColumnIndex("entrenador"));
                    String fecha = c.getString(c.getColumnIndex("fecha"));
                    String horaInicio = c.getString(c.getColumnIndex("hora_inicio"));
                    String horaFin = c.getString(c.getColumnIndex("hora_fin"));
                    int idSede = c.getInt(c.getColumnIndex("id_sede"));
                    String nombreSede = c.getString(c.getColumnIndex("nombre_sede"));

                    System.out.println ("idReserva==="+idReserva);
                    System.out.println ("idClase==="+idClase);
                    System.out.println ("idMembresia1==="+idMembresia1);
                    System.out.println ("nombreClase==="+nombreClase);
                    System.out.println ("entrenador==="+entrenador);
                    System.out.println ("fecha==="+fecha);
                    System.out.println ("horaInicio==="+horaInicio);
                    System.out.println ("horaFin==="+horaFin);
                    System.out.println ("idSede==="+idSede);
                    System.out.println ("nombreSede==="+nombreSede);




                    Reserva modelo = new Reserva();
                    Clase clase = new Clase();
                    Sede sede = new Sede();
                    Membresia membresia = new Membresia();

                    sede.setIdSede(idSede);
                    sede.setNombre(nombreSede);

                    clase.setIdClase(idClase);
                    clase.setNombre(nombreClase);
                    clase.setEntrenador(entrenador);
                    clase.setFecha(fecha);
                    clase.setHoraInicio(horaInicio);
                    clase.setHoraFin(horaFin);
                    clase.setSede(sede);

                    membresia.setIdMembresia(idMembresia1);

                    modelo.setIdReserva(idReserva);
                    modelo.setClase(clase);
                    modelo.setMembresia(membresia);
                    modelo.setEstado(estado);

                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("ReservaDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }

    public void eliminar(int idReserva) throws DAOException {
        Log.i("ReservaDAO", "eliminar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();

        try {
            String[] args = new String[]{String.valueOf(idReserva)};
            db.execSQL("DELETE FROM reserva WHERE id_reserva=?", args);
        } catch (Exception e) {
            throw new DAOException("ReservaDAO: Error al eliminar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public ArrayList<Reserva> buscar(Integer idMembresia, String fecha1) throws DAOException {
        Log.i("ReservaDAO", "buscar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        ArrayList<Reserva> lista = new ArrayList<Reserva>();
        try {
            Cursor c = db.rawQuery("select id_reserva, r.id_clase id_clase, r.id_membresia id_membresia, r.estado estado, c.nombre nombre_clase, entrenador, fecha, hora_inicio, hora_fin, c.id_sede id_sede, s.nombre nombre_sede from reserva r, clase c, sede s where r.id_clase = c.id_clase and c.id_sede = s.id_sede and r.id_membresia = '" + idMembresia + "' and fecha = '" + fecha1 + "'", null);

            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    int idReserva = c.getInt(c.getColumnIndex("id_reserva"));
                    int idClase = c.getInt(c.getColumnIndex("id_clase"));
                    int idMembresia1 = c.getInt(c.getColumnIndex("id_membresia"));
                    String estado = c.getString(c.getColumnIndex("estado"));
                    String nombreClase = c.getString(c.getColumnIndex("nombre_clase"));
                    String entrenador = c.getString(c.getColumnIndex("entrenador"));
                    String fecha = c.getString(c.getColumnIndex("fecha"));
                    String horaInicio = c.getString(c.getColumnIndex("hora_inicio"));
                    String horaFin = c.getString(c.getColumnIndex("hora_fin"));
                    int idSede = c.getInt(c.getColumnIndex("id_sede"));
                    String nombreSede = c.getString(c.getColumnIndex("nombre_sede"));

                    System.out.println ("idReserva==="+idReserva);
                    System.out.println ("idClase==="+idClase);
                    System.out.println ("idMembresia1==="+idMembresia1);
                    System.out.println ("nombreClase==="+nombreClase);
                    System.out.println ("entrenador==="+entrenador);
                    System.out.println ("fecha==="+fecha);
                    System.out.println ("horaInicio==="+horaInicio);
                    System.out.println ("horaFin==="+horaFin);
                    System.out.println ("idSede==="+idSede);
                    System.out.println ("nombreSede==="+nombreSede);




                    Reserva modelo = new Reserva();
                    Clase clase = new Clase();
                    Sede sede = new Sede();
                    Membresia membresia = new Membresia();

                    sede.setIdSede(idSede);
                    sede.setNombre(nombreSede);

                    clase.setIdClase(idClase);
                    clase.setNombre(nombreClase);
                    clase.setEntrenador(entrenador);
                    clase.setFecha(fecha);
                    clase.setHoraInicio(horaInicio);
                    clase.setHoraFin(horaFin);
                    clase.setSede(sede);

                    membresia.setIdMembresia(idMembresia1);

                    modelo.setIdReserva(idReserva);
                    modelo.setClase(clase);
                    modelo.setMembresia(membresia);
                    modelo.setEstado(estado);

                    lista.add(modelo);
                } while (c.moveToNext());
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("ReservaDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return lista;
    }


}
