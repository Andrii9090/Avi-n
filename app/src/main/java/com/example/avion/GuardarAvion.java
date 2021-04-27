package com.example.avion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GuardarAvion {
    Avion obj;
    String file_path;

    FileOutputStream fos;
    ObjectOutputStream salida;
    FileInputStream fis;
    ObjectInputStream entrada;

    GuardarAvion(Avion obj, String file_path){
        this.obj = obj;
        this.file_path = file_path;
    }
    GuardarAvion(String file_path){
        this.file_path = file_path;
    }

    public void guardarObjeto(){
        try {
            fos = new FileOutputStream(this.file_path);
            salida = new ObjectOutputStream(fos);
            salida.writeObject(this.obj);
        } catch (FileNotFoundException e) {
            System.out.println("1"+e.getMessage());
        } catch (IOException e) {
            System.out.println("2"+e.getMessage());
        } finally {
            try {
                if(fos!=null){
                    fos.close();
                }
                if(salida!=null){
                    salida.close();
                }
            } catch (IOException e) {
                System.out.println("3"+e.getMessage());
            }
        }
    }

    public Avion leerObjeto(){

        try {
            fis = new FileInputStream(this.file_path);
            entrada = new ObjectInputStream(fis);
            Avion avion = (Avion) entrada.readObject();
            return avion;

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }
}
