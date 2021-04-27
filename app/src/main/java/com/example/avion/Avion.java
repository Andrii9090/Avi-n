package com.example.avion;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Avion implements Serializable {

    private int SILLAS_EJECUTIVAS = 8;
    private int SILLAS_ECONOMICAS = 42;
    private double PRECIO_EJECUTIVAS = 165;
    private double PRECIO_ECONOMICAS = 65;

    private Silla[] sillasEjecutivas = new Silla[SILLAS_EJECUTIVAS];
    private Silla[] sillasEconimicas = new Silla[SILLAS_ECONOMICAS];
    HashMap<Clase, Integer> dataSillas;

    ArrayList<Silla> sillasEconomicasOcupadas = new ArrayList<Silla>();
    ArrayList<Silla> sillasEjecutivasOcupadas = new ArrayList<Silla>();

    Avion() {
        dataSillas = new HashMap<Clase, Integer>();
        dataSillas.put(Clase.ECONOMICA, SILLAS_ECONOMICAS);
        dataSillas.put(Clase.EJECUTIVA, SILLAS_EJECUTIVAS);
        crearSillas();
    }

    private void crearSillas() {
        for (int i = 0; i < SILLAS_ECONOMICAS; i++) {
            if (i < SILLAS_ECONOMICAS / 3) {
                crearSilla(i, Clase.ECONOMICA, Ubicacion.VENTANA, PRECIO_ECONOMICAS);
            } else if (i < SILLAS_ECONOMICAS / 2 / 3) {
                crearSilla(i, Clase.ECONOMICA, Ubicacion.CENTRO, PRECIO_ECONOMICAS);
            } else {
                crearSilla(i, Clase.ECONOMICA, Ubicacion.PASILLO, PRECIO_ECONOMICAS);
            }
        }
        for (int i = 0; i < SILLAS_EJECUTIVAS; i++) {
            if (i < SILLAS_EJECUTIVAS / 2) {
                crearSilla(i, Clase.EJECUTIVA, Ubicacion.VENTANA, PRECIO_EJECUTIVAS);
            } else {
                crearSilla(i, Clase.EJECUTIVA, Ubicacion.PASILLO, PRECIO_EJECUTIVAS);
            }
        }
    }

    private void crearSilla(int numero, Clase clase, Ubicacion ubication, double precio) {
        if (clase.equals(Clase.ECONOMICA)) {
            sillasEconimicas[numero] = new Silla(numero + 1, precio, clase, ubication);
            Log.e("AVION1", String.valueOf(sillasEconimicas[numero].getNumero()));
        }
        if (clase.equals(Clase.EJECUTIVA)) {
            sillasEjecutivas[numero] = new Silla(numero + 1, precio, clase, ubication);
            Log.e("AVION", String.valueOf(sillasEjecutivas[numero].getNumero()));
        }
    }

    public void eliminarResarvas() {
        for (int i = 0; i < SILLAS_ECONOMICAS; i++) {
            if (i < SILLAS_EJECUTIVAS) {
                sillasEjecutivas[i].quitarPasajero();
            }
            sillasEconimicas[i].quitarPasajero();
        }
    }

    public void getSillasOcupadas() {
        for (int i = 0; i < dataSillas.get(Clase.ECONOMICA)-1; i++) {
            if(i<sillasEjecutivas.length) {
                if (sillasEjecutivas[i].getPasajero() != null) {
                    sillasEjecutivasOcupadas.add(sillasEjecutivas[i]);
                }
            }
            if (sillasEconimicas[i].getPasajero() != null) {
                sillasEconomicasOcupadas.add(sillasEconimicas[i]);
            }
        }
    }

    public Silla getSillaPasajero(String dni) {
        for (int i = 0; i < SILLAS_ECONOMICAS; i++) {
            if (i < SILLAS_EJECUTIVAS) {
                if (sillasEjecutivas[i].getPasajero() != null && sillasEjecutivas[i].getPasajero().getDni().equals(dni)) {
                    return sillasEjecutivas[i];
                }
            }
            if (sillasEconimicas[i].getPasajero() != null && sillasEconimicas[i].getPasajero().getDni().equals(dni)) {
                return sillasEconimicas[i];
            }
        }
        return null;
    }

    public Silla getSillaLibre(Clase clase, Ubicacion ubicacion) {
        for (int i = 0; i < dataSillas.get(clase); i++) {
            if (clase.equals(Clase.EJECUTIVA)) {
                if (sillasEjecutivas[i].getPasajero() == null && sillasEjecutivas[i].getUbicacion().equals(ubicacion)) {
                    return sillasEjecutivas[i];
                }
            }
            if (clase.equals(Clase.ECONOMICA)) {
                if (sillasEconimicas[i].getPasajero() == null && sillasEconimicas[i].getUbicacion().equals(ubicacion)) {
                    return sillasEconimicas[i];
                }
            }
        }
        return null;
    }

    public boolean asignarPasajero(Pasajero pasajero, Clase clase, Ubicacion ubicacion) {
        Silla silla = getSillaLibre(clase, ubicacion);
        if (silla != null) {
            silla.asignarPasajero(pasajero);
            return true;
        }
        return false;
    }

    public boolean anularReservaPasajero(String dni) {
        Silla silla = getSillaPasajero(dni);
        if (silla != null) {
            silla.quitarPasajero();
            return true;
        }
        return false;
    }

    public boolean hayHomonicasPasajeros(String dni) {
        ArrayList<Silla> pasajeroDouble = new ArrayList<>();
        for (int i = 0; i < SILLAS_ECONOMICAS; i++) {
            if (i < SILLAS_EJECUTIVAS) {
                if (sillasEjecutivas[i].getPasajero()!= null && sillasEjecutivas[i].getPasajero().getDni().equals(dni)) {
                    pasajeroDouble.add(sillasEjecutivas[i]);
                }
            }
            if (sillasEconimicas[i].getPasajero() != null && sillasEconimicas[i].getPasajero().getDni().equals(dni)) {
                pasajeroDouble.add(sillasEconimicas[i]);
            }
        }
        if (pasajeroDouble.size() > 0) {
            return true;
        }
        return false;
    }

    public void desocuparAvion() {
        getSillasOcupadas();
        if (sillasEconomicasOcupadas != null)
            for (int i = 0; i < sillasEconomicasOcupadas.size(); i++) {
                Log.e("AVION", String.valueOf(sillasEconomicasOcupadas.get(i).getNumero()));
                sillasEconomicasOcupadas.get(i).quitarPasajero();
            }
        if (sillasEjecutivasOcupadas != null)
            for (int i = 0; i < sillasEjecutivasOcupadas.size(); i++) {
                sillasEjecutivasOcupadas.get(i).quitarPasajero();
            }
    }

}
