package com.example.oscarruizpatricio.loginsql;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.List;

/**
 * Created by oscarruizpatricio on 19/1/17.
 */

public class Listening implements View.OnClickListener {

    private Context context;
    private EditText vistaUsuario;
    private EditText vistaPassword;

    public Listening (Context context) {
        this.context = context;
    }
    public Listening (Context context, EditText vistaUsuario, EditText vistaPassword) {
        this.context = context;
        this.vistaUsuario = vistaUsuario;
        this.vistaPassword = vistaPassword;
    }

    @Override
    public void onClick(View boton) {
        int id_boton = boton.getId();

        switch (id_boton) {
            case R.id.registerButton:
                Intent intent = new Intent(context, RegistroActivity.class);
                context.startActivity(intent);
                break;
            case R.id.buttonListar:
                BaseDatosUsuarios bd = new BaseDatosUsuarios(context,"Mibd", null, 1);
                List<Usuario> lista_usuarios = null;
                lista_usuarios = bd.listarUsuarios();
                break;
            case R.id.loginButton:
                BaseDatosUsuarios bd1 = new BaseDatosUsuarios(context, "Mibd", null, 1);
                String textoUsuario = vistaUsuario.getText().toString();
                String textoPassword = vistaPassword.getText().toString();
                Log.d("prueba", "texto usuario: " + textoUsuario);
                Log.d("prueba", "texto password: " + textoPassword);
                if (bd1.Login(textoUsuario, textoPassword)) {
                    Log.d("prueba", "login correcto");
                } else {
                    Log.d("prueba", "login incorrecto");
                }
        }
    }
}
