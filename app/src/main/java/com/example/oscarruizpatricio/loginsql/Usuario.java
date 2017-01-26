package com.example.oscarruizpatricio.loginsql;

/**
 * Created by oscarruizpatricio on 19/1/17.
 */

public class Usuario {

    private int id;
    private String nombre;
    private String user;
    private String email;
    private String password;

    public Usuario (Integer id, String nombre, String user, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.user = user;
        this.email = email;
        this.password = password;
    }

    public Usuario (String nombre, String user, String email, String password) {
        this.nombre = nombre;
        this.user = user;
        this.email = email;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
