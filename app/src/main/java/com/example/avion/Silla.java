package com.example.avion;

import java.io.Serializable;

enum Clase {
    ECONOMICA,
    EJECUTIVA
}

enum Ubicacion {
    VENTANA,
    PASILLO,
    CENTRO
}

public class Silla implements Serializable {
    private int numero;
    private double precio;
    private Clase clase;
    private Ubicacion ubicacion;
    private Pasajero pasajero;

    Silla(int numero, double precio, Clase clase, Ubicacion ubicacion) {
        this.numero = numero;
        this.precio = precio;
        this.clase = clase;
        this.ubicacion = ubicacion;
        this.pasajero = null;
    }

    public void asignarPasajero(Pasajero pasajero){
        this.pasajero = pasajero;
    }

    public void quitarPasajero(){
        this.pasajero = null;
    }

    public int getNumero() {
        return this.numero;
    }

    public double getPrecio() {
        return this.precio;
    }

    public Clase getClase() {
        return this.clase;
    }

    public Ubicacion getUbicacion() {
        return this.ubicacion;
    }

    public Pasajero getPasajero() {
        return this.pasajero;
    }
}
