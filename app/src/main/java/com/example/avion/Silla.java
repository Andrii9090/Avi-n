package com.example.avion;

enum Clase {
    ECONOMICA,
    EJECUTIVA
}

enum Ubicacion {
    VENTANA,
    PASILLO,
    CENTRO
}

public class Silla {
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
        pasajero = null;
    }

    public int getNumero() {
        return numero;
    }

    public double getPrecio() {
        return precio;
    }

    public Clase getClase() {
        return clase;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }
}
