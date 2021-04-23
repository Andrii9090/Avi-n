package com.example.avion;

import java.util.ArrayList;
import java.util.HashMap;

public class Avion {

    private int SILLAS_EJECUTIVAS = 8;
    private int SILLAS_ECONOMICAS = 42;
    private double PRECIO_EJECUTIVAS = 165;
    private double PRECIO_ECONOMICAS = 65;

    private Silla[] sillasEjecutivas;
    private Silla[] sillasEconimicas;
    HashMap<Clase, Integer> dataSillas;

    Avion() {
        dataSillas.put(Clase.ECONOMICA, SILLAS_ECONOMICAS);
        dataSillas.put(Clase.EJECUTIVA, SILLAS_EJECUTIVAS);
        crearSillas(Clase.ECONOMICA, SILLAS_ECONOMICAS);
    }

    private void crearSillas(Clase clase, int sillasCantidades) {
        if (clase.equals(Clase.ECONOMICA)) {
            for (int i = 0; i < SILLAS_ECONOMICAS; i++) {
                if (i < SILLAS_ECONOMICAS / 3) {
                    crearSilla(i, clase, Ubicacion.VENTANA, PRECIO_ECONOMICAS);
                } else if (i < SILLAS_ECONOMICAS / 2 / 3) {
                    crearSilla(i, clase, Ubicacion.CENTRO, PRECIO_ECONOMICAS);
                } else {
                    crearSilla(i, clase, Ubicacion.PASILLO, PRECIO_ECONOMICAS);
                }
            }
        }
        if (clase.equals(Clase.EJECUTIVA)) {
            for (int i = 0; i < SILLAS_EJECUTIVAS; i++) {
                if (i < SILLAS_EJECUTIVAS / 2) {
                    crearSilla(i, clase, Ubicacion.VENTANA, PRECIO_EJECUTIVAS);
                } else {
                    crearSilla(i, clase, Ubicacion.PASILLO, PRECIO_EJECUTIVAS);
                }
            }
        }
    }

    private void crearSilla(int numero, Clase clase, Ubicacion ubication, double precio) {
        sillasEconimicas[numero] = new Silla(numero + 1, precio, clase, ubication);
    }

    public void eliminarResarvas() {
        for (int i = 0; i < SILLAS_ECONOMICAS; i++) {
            if (i < SILLAS_EJECUTIVAS) {
                sillasEjecutivas[i].quitarPasajero();
            }
            sillasEconimicas[i].quitarPasajero();
        }
    }

    public ArrayList<Silla> getSillasOcupadas(Clase clase) {
        ArrayList<Silla> sillasOcupadas = new ArrayList<>();
        for (int i = 0; i < dataSillas.get(clase); i++) {
            if (clase.equals(Clase.EJECUTIVA)) {
                if (sillasEjecutivas[i].getPasajero() != null) {
                    sillasOcupadas.add(sillasEjecutivas[i]);
                }
            }
            if (clase.equals(Clase.ECONOMICA)) {
                if (sillasEconimicas[i].getPasajero() != null) {
                    sillasOcupadas.add(sillasEconimicas[i]);
                }
            }
        }
        if (sillasOcupadas.size() > 0) {
            return sillasOcupadas;
        } else {
            return null;
        }
    }

    public Silla getSillaPasajero(String dni) {
        for (int i = 0; i < SILLAS_ECONOMICAS; i++) {
            if (i < SILLAS_EJECUTIVAS) {
                if (sillasEjecutivas[i].getPasajero().getDni().equals(dni)) {
                    return sillasEjecutivas[i];
                }
            }
            if (sillasEconimicas[i].getPasajero().getDni().equals(dni)) {
                return sillasEjecutivas[i];
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
            if (clase.equals(Clase.ECONOMICA) && sillasEjecutivas[i].getUbicacion().equals(ubicacion)) {
                if (sillasEconimicas[i].getPasajero() == null) {
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

    public boolean anularReservaPasajero(String dni){
        Silla silla = getSillaPasajero(dni);
        if(silla!=null){
            silla.quitarPasajero();
            return true;
        }
        return false;
    }

    public boolean hayHomonicasPasajeros(String dni){
        ArrayList<Silla> pasajeroDouble = new ArrayList<>();
        for (int i = 0; i < SILLAS_ECONOMICAS; i++) {
            if (i<SILLAS_EJECUTIVAS) {
                if (sillasEjecutivas[i].getPasajero().getDni().equals(dni)) {
                    pasajeroDouble.add(sillasEjecutivas[i]);
                }
            }
            if (sillasEconimicas[i].getPasajero().getDni().equals(dni)) {
                pasajeroDouble.add(sillasEconimicas[i]);
            }
        }
        if(pasajeroDouble.size()>0){
            return true;
        }
        return false;
    }

    public void desocuparAvion(){
        ArrayList<Silla> sillasEconomicosOcupadas = getSillasOcupadas(Clase.ECONOMICA);
        ArrayList<Silla> sillasEjecutivasOcupadas = getSillasOcupadas(Clase.EJECUTIVA);
        for (int i=0; i<sillasEconomicosOcupadas.size();i++){
            sillasEconomicosOcupadas.get(i).quitarPasajero();
        }

        for (int i=0; i<sillasEjecutivasOcupadas.size();i++){
            sillasEjecutivasOcupadas.get(i).quitarPasajero();
        }
    }

}
