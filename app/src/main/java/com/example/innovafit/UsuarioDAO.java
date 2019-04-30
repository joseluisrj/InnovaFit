package com.example.innovafit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UsuarioDAO {

    private DbHelper _dbHelper;
    private Usuario usuario;
    private String mensaje;

    public UsuarioDAO(Context c) {
        _dbHelper = new DbHelper(c);
    }

    public void insertar(String nombres, String apellidos, String correo, String contrasena) throws DAOException {
        Log.i("UsuarioDAO", "insertar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{correo, contrasena, nombres, apellidos, "1"};
            db.execSQL("INSERT INTO usuario(id_usuario, clave, nombres, apellidos, estado) VALUES(?,?,?,?,?)", args);
            Log.i("UsuarioDAO", "Se insertó");
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al insertar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void modificar(String nombres, String apellidos, String correo, String telefono, String fechaNacimiento, String genero) throws DAOException {
        Log.i("UsuarioDAO", "modificar()");
        SQLiteDatabase db = _dbHelper.getWritableDatabase();
        try {
            String[] args = new String[]{nombres, apellidos, telefono, fechaNacimiento, genero, correo};
            db.execSQL("UPDATE usuario SET nombres = ?, apellidos = ?, telefono = ?, fecha_nac = ?, sexo = ? WHERE id_usuario = ?", args);
            Log.i("UsuarioDAO", "Se modificó");
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al modificar: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public void validar(String correo, String contrasena) throws DAOException {
        Log.i("UsuarioDAO", "validar()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        Usuario modelo = new Usuario();
        try {
            Cursor c = db.rawQuery("select id_usuario, clave, nombres, apellidos, imagen, telefono, fecha_nac, sexo, estado from usuario where id_usuario ='"+correo+"' and clave = '"+contrasena+"'", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    String idUsuario = c.getString(c.getColumnIndex("id_usuario"));
                    String clave = c.getString(c.getColumnIndex("clave"));
                    String nombres = c.getString(c.getColumnIndex("nombres"));
                    String apellidos = c.getString(c.getColumnIndex("apellidos"));
                    String imagen = c.getString(c.getColumnIndex("imagen"));
                    String telefono = c.getString(c.getColumnIndex("telefono"));
                    String fechaNac = c.getString(c.getColumnIndex("fecha_nac"));
                    String sexo = c.getString(c.getColumnIndex("sexo"));
                    String estado = c.getString(c.getColumnIndex("estado"));

                    modelo.setIdUsuario(idUsuario);
                    modelo.setClave(clave);
                    modelo.setNombres(nombres);
                    modelo.setApellidos(apellidos);
                    modelo.setImagen(imagen);
                    modelo.setTelefono(telefono);
                    modelo.setFechaNacimiento(fechaNac);
                    modelo.setSexo(sexo);
                    modelo.setEstado(estado);

                } while (c.moveToNext());
                this.setMensaje("");
                this.setUsuario(modelo);
            } else {
                this.setMensaje("Usuario o clave invalida");
                this.setUsuario(null);
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public Usuario obtener(String usuario) throws DAOException {
        Log.i("UsuarioDAO", "obtener()");
        SQLiteDatabase db = _dbHelper.getReadableDatabase();
        Usuario modelo = new Usuario();
        try {
            Cursor c = db.rawQuery("select id_usuario, clave, nombres, apellidos, imagen, telefono, fecha_nac, sexo, estado from usuario where id_usuario ='"+usuario+"'", null);
            if (c.getCount() > 0) {
                c.moveToFirst();
                do {
                    String idUsuario = c.getString(c.getColumnIndex("id_usuario"));
                    String clave = c.getString(c.getColumnIndex("clave"));
                    String nombres = c.getString(c.getColumnIndex("nombres"));
                    String apellidos = c.getString(c.getColumnIndex("apellidos"));
                    String imagen = c.getString(c.getColumnIndex("imagen"));
                    String telefono = c.getString(c.getColumnIndex("telefono"));
                    String fechaNac = c.getString(c.getColumnIndex("fecha_nac"));
                    String sexo = c.getString(c.getColumnIndex("sexo"));
                    String estado = c.getString(c.getColumnIndex("estado"));

                    modelo.setIdUsuario(idUsuario);
                    modelo.setClave(clave);
                    modelo.setNombres(nombres);
                    modelo.setApellidos(apellidos);
                    modelo.setImagen(imagen);
                    modelo.setTelefono(telefono);
                    modelo.setFechaNacimiento(fechaNac);
                    modelo.setSexo(sexo);
                    modelo.setEstado(estado);

                } while (c.moveToNext());
                this.setMensaje("");
                this.setUsuario(modelo);
            }
            c.close();
        } catch (Exception e) {
            throw new DAOException("UsuarioDAO: Error al obtener: " + e.getMessage());
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return modelo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}

