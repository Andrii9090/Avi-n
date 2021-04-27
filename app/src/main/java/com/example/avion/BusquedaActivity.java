package com.example.avion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.Serializable;

public class BusquedaActivity extends AppCompatActivity {
    Avion avion;
    TextView infoText;
    EditText dniPasajero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busqueda_activity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle(getResources().getString(R.string.text_busqueda_title));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GuardarAvion g = new GuardarAvion(getFilesDir().getAbsolutePath()+ File.pathSeparator+"avion.txt");
        avion = g.leerObjeto();
        infoText = (TextView) findViewById(R.id.text_info_buscar);
        dniPasajero = (EditText) findViewById(R.id.input_busca_dni);
        Button btnBuscar = (Button) findViewById(R.id.btn_buscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Silla sillaPasajero = avion.getSillaPasajero(dniPasajero.getText().toString());
                if(sillaPasajero!=null){
                    Pasajero pasajero = sillaPasajero.getPasajero();
                    if(pasajero!=null){
                        Log.e("Busqueda", pasajero.getDni());
                    }
                    String text = "Nombre "+pasajero.getNombre()+System.lineSeparator()
                            +"DNI "+pasajero.getDni()+System.lineSeparator()
                            +"Numero de silla " + String.valueOf(sillaPasajero.getNumero())+System.lineSeparator()
                            +"Clase "+sillaPasajero.getClase().name()+System.lineSeparator()
                            +"Ubicación "+ sillaPasajero.getUbicacion();
                    infoText.setText(text);
                }else{
                    infoText.setTextSize(18.0f);
                    infoText.setTextColor(getResources().getColor(R.color.red_400));
                    infoText.setText(getString(R.string.text_error_pasajero_no_hay));
                }
            }
        });
    }

}