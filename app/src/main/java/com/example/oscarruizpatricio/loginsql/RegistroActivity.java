package com.example.oscarruizpatricio.loginsql;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }

    public void Registrar (View view) {

        BaseDatosUsuarios baseDatosUsuarios = new BaseDatosUsuarios(this, "Mibd", null, 1);

        String nombre;
        String usuario;
        String email;
        String password;
        String password1;
        boolean existe;
        boolean campoVacio = false;
        boolean passwordsDiferentes = true;

        EditText name = (EditText)findViewById(R.id.nameEditText);
        EditText user = (EditText)findViewById(R.id.userEditText);
        EditText email1 = (EditText)findViewById(R.id.emailEditText);
        EditText pass = (EditText)findViewById(R.id.passwordEditText);
        EditText pas1 = (EditText)findViewById(R.id.passwordEditText1);

        nombre = name.getText().toString();
        usuario = user.getText().toString();
        email = email1.getText().toString();
        password = pass.getText().toString();
        password1 = pas1.getText().toString();

        if (nombre.equals("")||usuario.equals("")||email.equals("")||password.equals("")) {
            campoVacio = true;
            Log.d("prueba", "Rellena todos los campos");
        }
        if (password.equals(password1)) {
                passwordsDiferentes = false;
        } else { Log.d("prueba", "Passwords diferentes"); }

        existe = baseDatosUsuarios.usuarioExiste(usuario);
        if (existe) {
                Log.d("prueba", "El usuario Existe");
        }
        if (campoVacio==false&&passwordsDiferentes==false&&existe==false) {
                Usuario nuevoUsuario = new Usuario(nombre, usuario, email,password);
                baseDatosUsuarios.insertarUsuario(nuevoUsuario);

                Log.d("Prueba", "Ha tocado el botón registar");

                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
        }
    }
}
