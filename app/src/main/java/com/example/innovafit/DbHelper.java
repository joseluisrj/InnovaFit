package com.example.innovafit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context) {
        // null porque se va a usar el SQLiteCursor
        super(context, "db_innova_v1.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS sede (id_sede INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, direccion TEXT NOT NULL, marcador_1 TEXT NOT NULL, marcador_2 TEXT NOT NULL, marcador_3 TEXT NOT NULL, estado TEXT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS clase (id_clase INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, entrenador TEXT NOT NULL, fecha TEXT NOT NULL, hora_inicio TEXT NOT NULL, hora_fin TEXT NOT NULL, cantidad INT NOT NULL, stock INT NOT NULL, id_sede INT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS usuario (id_usuario TEXT NOT NULL PRIMARY KEY, clave TEXT NOT NULL, nombres TEXT NOT NULL, apellidos TEXT NOT NULL, imagen TEXT, telefono TEXT, fecha_nac TEXT, sexo TEXT, estado TEXT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS rol (id_rol INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS usuario_rol (id_usuario TEXT NOT NULL, id_rol INT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS notificacion (id_notificacion INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT NOT NULL, detalle TEXT NOT NULL, fecha TEXT NOT NULL, estado TEXT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS tipo_membresia (id_tipo INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, costo NUMERIC NOT NULL, cantidad INT NOT NULL, estado TEXT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS membresia (id_membresia INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT NOT NULL, fecha_inicio TEXT NOT NULL, fecha_fin TEXT NOT NULL, stock INT NOT NULL, id_tipo INT NOT NULL, id_usuario TEXT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS reserva (id_reserva INTEGER PRIMARY KEY AUTOINCREMENT, id_clase INT NOT NULL, id_membresia INT NOT NULL, estado TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sede");
        db.execSQL("DROP TABLE IF EXISTS clase");
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS rol");
        db.execSQL("DROP TABLE IF EXISTS usuario_rol");
        db.execSQL("DROP TABLE IF EXISTS notificacion");
        db.execSQL("DROP TABLE IF EXISTS tipo_membresia");
        db.execSQL("DROP TABLE IF EXISTS membresia");
        db.execSQL("DROP TABLE IF EXISTS reserva");
        onCreate(db);
    }
}