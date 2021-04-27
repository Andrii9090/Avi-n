package com.example.avion;

import java.io.Serializable;

public class Pasajero implements Serializable {
    private String nombre;
    private String dni;

    Pasajero(String nombre, String dni){
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getDni() {
        return this.dni;
    }
}
