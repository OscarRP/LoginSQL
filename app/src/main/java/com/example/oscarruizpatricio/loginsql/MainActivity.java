package com.example.oscarruizpatricio.loginsql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String textoUsuario = "";
    private String textoPassword = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botonLogin = (Button)findViewById(R.id.loginButton);
        Button botonRegistro = (Button)findViewById(R.id.registerButton);
        EditText userText = (EditText)findViewById(R.id.userEditText);
        EditText passText = (EditText)findViewById(R.id.passEditText);

        View.OnClickListener listener = new Listening (this, userText, passText);
        botonLogin.setOnClickListener(listener);
        botonRegistro.setOnClickListener(listener);


        //----------------------Prueba-------------------------//
        /*BaseDatosUsuarios bd = new BaseDatosUsuarios(this,"Mibd", null, 1);

        Usuario usuario = new Usuario("oscar","oscaruser","correo@correo.com","oscarpassword");
        bd.insertarUsuario(usuario);*/


        /*List<Usuario> lista_usuarios = null;
        //BaseDatosUsuarios bd = new BaseDatosUsuarios(this, "Mibd", null, 1);
        lista_usuarios = bd.listarUsuarios();*/

    }

}
