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

public class QuitarPasajero extends AppCompatActivity {
    Avion avion;
    TextView infoText;
    EditText dniPasajero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quitar_pasajero);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Quitar pasajero");
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GuardarAvion g = new GuardarAvion(getFilesDir().getAbsolutePath()+ File.pathSeparator+"avion.txt");
        avion = g.leerObjeto();
        infoText = (TextView) findViewById(R.id.text_info_buscar);
        dniPasajero = (EditText) findViewById(R.id.input_quitar_dni);
        Button btnBuscar = (Button) findViewById(R.id.btn_buscar);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Silla sillaPasajero = avion.getSillaPasajero(dniPasajero.getText().toString());
                if(sillaPasajero!=null){
                    Pasajero pasajero = sillaPasajero.getPasajero();
                    sillaPasajero.quitarPasajero();
                    String text = "Nombre "+pasajero.getNombre()+System.lineSeparator()
                            +"DNI "+pasajero.getDni()+System.lineSeparator()
                            +"ha quitado";
                    infoText.setText(text);
                    guardarAvion();
                }else{
                    infoText.setTextSize(18.0f);
                    infoText.setTextColor(getResources().getColor(R.color.red_400));
                    infoText.setText(getString(R.string.text_error_pasajero_no_hay));
                }
            }
        });
    }

    private void guardarAvion() {
        GuardarAvion g=new GuardarAvion(avion, getFilesDir().getAbsolutePath()+ File.pathSeparator+"avion.txt");
        g.guardarObjeto();
    }


}