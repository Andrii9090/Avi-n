package com.example.avion;

public class Pasajero {
    private String nombre;
    private String dni;

    Pasajero(String nombre, String dni){
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }
}
