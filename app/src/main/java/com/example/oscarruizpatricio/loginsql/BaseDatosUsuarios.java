package com.example.oscarruizpatricio.loginsql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oscarruizpatricio on 19/1/17.
 */

public class BaseDatosUsuarios extends SQLiteOpenHelper {

    private static final String sqlCreacionTabla = "CREATE TABLE USUARIO (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, user TEXT, email TEXT, password TEXT)";

    public BaseDatosUsuarios (Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("prueba", "entrando en onCreate");
        db.execSQL(sqlCreacionTabla);
        Log.d("prueba", "se ha creado la tabla Usuarios");
    }

    @Override
    public void onUpgrade (SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void cerrarBaseDatos (SQLiteDatabase bd) { bd.close(); }

    public void insertarUsuario (Usuario user) {
        Log.d("prueba", "entrando en insertarUsuario");
        SQLiteDatabase baseDatos = this.getWritableDatabase();
        baseDatos.execSQL("INSERT INTO USUARIO (nombre, user, email, password) VALUES ('" + user.getNombre()+"' , '" + user.getUser()+"' , '" + user.getEmail()+"' , '" + user.getPassword()+"')");
        cerrarBaseDatos(baseDatos);
        Log.d("prueba", "saliendo de insertarUsuario");
    }

    public Usuario buscarUsuario (String user) {
        Log.d("prueba", "entrando en buscar usuario");
        Usuario usuario = null;
        Integer id = 0;
        String nombre = "";
        String campoUsuario = "";
        String email = "";
        String password = "";

        String consulta = "SELECT id, nombre, user, email, password FROM USUARIO WHERE user = '"+user+"'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(consulta, null);

        if (cursor.moveToFirst() == false) { Log.d("prueba", "movetofirst es false"); }
        if (cursor!=null && cursor.moveToFirst()) {
            Log.d("prueba", "Cursor diferente de null");
            cursor.moveToFirst();
            Log.d("prueba", "Cursor moved to first");
            id = cursor.getInt(0);
            nombre = cursor.getString(1);
            Log.d("prueba", "Cursor nombre");
            campoUsuario = cursor.getString(2);
            Log.d("prueba", "Cursor usuario");
            email = cursor.getString(3);
            password = cursor.getString(4);
            usuario = new Usuario (nombre, campoUsuario, email, password);
        }
        cursor.close();
        cerrarBaseDatos(db);

        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        Log.d("prueba", "entrando en listar usuarios");
        List<Usuario> lista_usuarios = null;
        Usuario usuario = null;
        Integer id = 0;
        String nombre = "";
        String campoUsuario = "";
        String email = "";
        String password = "";

        String consulta = "SELECT * FROM USUARIO";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(consulta, null);

        Integer n_resultados = cursor.getCount();
        if (cursor.moveToNext() == false) { Log.d("prueba", "move to es false"); }
        if (cursor!=null && cursor.moveToNext()) {
            Log.d("prueba", "Cursor diferente de null");
            cursor.moveToFirst();
            lista_usuarios = new ArrayList<Usuario>(n_resultados);

            do {
                id = cursor.getInt(0);
                nombre = cursor.getString(1);
                campoUsuario = cursor.getString(2);
                email = cursor.getString(3);
                password = cursor.getString(4);
                usuario = new Usuario(id,nombre,campoUsuario,email,password);
                lista_usuarios.add(usuario);
                Log.d("prueba", "user: " + campoUsuario);
            } while (cursor.moveToNext() == true);
        }
        cursor.close();
        cerrarBaseDatos(db);

        return lista_usuarios;
    }

    public boolean usuarioExiste (String nombre_usuario) {
        boolean existe = false;
        String user = "";

        String consulta = "SELECT * FROM USUARIO";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(consulta, null);
        if (cursor!=null && cursor.moveToNext()) {
            cursor.moveToFirst();

            do {
                user = cursor.getString(2);
                Log.d("prueba", "usuario: " + user);
                if (user.equals(nombre_usuario)) {
                    existe = true;
                }
                cursor.moveToNext();
                Log.d("prueba", "existe: " + existe);

            } while (cursor.moveToNext() == true);
        }
        cursor.close();
        cerrarBaseDatos(db);
        return existe;
    }

    public boolean Login (String usuario, String password) {

        Log.d("prueba", "entrando en método Login");
        boolean loginCorrecto;
        String user = "";
        String pass = "";
        String consulta = "SELECT user, password FROM USUARIO WHERE user = '"+usuario+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consulta, null);

        Log.d("prueba", "cursor: " + cursor);
        if (cursor!=null && cursor.moveToFirst()) {

            cursor.moveToFirst();

            user = cursor.getString(0);
            pass = cursor.getString(1);

            Log.d("prueba", "user: " + user);
            Log.d("prueba", "pass: " + pass);

            if (user.equals(usuario) && pass.equals(password)) {
                loginCorrecto = true;
            } else {
                loginCorrecto = false;
            }
            cursor.close();
        } else {
            loginCorrecto = false;
        }

        cerrarBaseDatos(db);
        return loginCorrecto;
    }
}
