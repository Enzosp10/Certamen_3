package com.example.certamen_3;

public class Usuario {

    private String Id;
    private String Nombre;
    private String Contraseña;

    public Usuario(String id, String nombre, String contraseña) {
        Id = id;
        Nombre = nombre;
        Contraseña = contraseña;
    }

    public String getId() {
        return Id;
    }

    public Usuario() {
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }
}
