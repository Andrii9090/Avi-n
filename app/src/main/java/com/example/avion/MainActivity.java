package com.example.avion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private Spinner spinnerClases;
    private Spinner spinnerUbicaciones;
    ArrayAdapter<CharSequence> spinnerUbicacionesAdapter;
    ArrayAdapter<CharSequence> spinnerClasesAdapter;
    Avion avion;

    Clase claseSelect;
    Ubicacion ubicacionSelect;

    EditText nombre;
    EditText dni;
    TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerClases = (Spinner) findViewById(R.id.spinner_clases);
        spinnerClasesAdapter = ArrayAdapter.createFromResource(this, R.array.array_clases, android.R.layout.simple_spinner_item);
        spinnerClasesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClases.setAdapter(spinnerClasesAdapter);

        spinnerUbicaciones = (Spinner) findViewById(R.id.spinner_ubicaciones);
        spinnerUbicacionesAdapter = ArrayAdapter.createFromResource(this, R.array.array_ubicaciones_economica, android.R.layout.simple_spinner_item);
        spinnerUbicacionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUbicaciones.setAdapter(spinnerUbicacionesAdapter);

        nombre = findViewById(R.id.nombre);
        dni = findViewById(R.id.dni);
        textInfo = findViewById(R.id.text_info);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        avion = new Avion();

        Button btnReservar = (Button) findViewById(R.id.button_reservar);
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservar(v);
            }
        });

        Button btnBuscar = (Button) findViewById(R.id.get_pasajero);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarPasajero(v);
            }
        });

        Button btnQuitarPasajero = (Button) findViewById(R.id.btn_quitar_pasajero);
        btnQuitarPasajero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitarPasajero(v);
            }
        });

        Button btnListaRes = (Button) findViewById(R.id.btn_lista_de_reserva);
        btnListaRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListaRes(v);
            }
        });

        spinnerClases.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = spinnerClasesAdapter.getItem(position).toString();
                String[] arrayClases = getResources().getStringArray(R.array.array_clases);
                if(itemSelected.equals(arrayClases[0])){
                    spinnerUbicacionesAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.array_ubicaciones_ejecutiva, android.R.layout.simple_spinner_item);
                    spinnerUbicacionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerUbicaciones.setAdapter(spinnerUbicacionesAdapter);
                    claseSelect = Clase.EJECUTIVA;
                }
                if(itemSelected.equals(arrayClases[1])){
                    spinnerUbicacionesAdapter = ArrayAdapter.createFromResource(view.getContext(), R.array.array_ubicaciones_economica, android.R.layout.simple_spinner_item);
                    spinnerUbicacionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerUbicaciones.setAdapter(spinnerUbicacionesAdapter);
                    claseSelect = Clase.ECONOMICA;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerUbicaciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = spinnerUbicacionesAdapter.getItem(position).toString();
                String[] arrayUbicaion = getResources().getStringArray(R.array.array_ubicaciones_economica);
                if(itemSelected.equals(arrayUbicaion[0])){
                    ubicacionSelect = Ubicacion.VENTANA;
                }
                if(itemSelected.equals(arrayUbicaion[1])){
                    ubicacionSelect = Ubicacion.CENTRO;
                }
                if(itemSelected.equals(arrayUbicaion[2])){
                    ubicacionSelect = Ubicacion.PASILLO;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getListaRes(View v) {
        Intent intent = new Intent(this, ListaDeReserva.class);
        startActivity(intent);
    }

    private void quitarPasajero(View v) {
        Intent intent = new Intent(this, QuitarPasajero.class);
        startActivity(intent);
    }

    public void reservar(View view) {
        Pasajero pasajero = new Pasajero(nombre.getText().toString(), dni.getText().toString());
        Silla sillaLibra = avion.getSillaLibre(claseSelect, ubicacionSelect);
        if(sillaLibra!=null){
            if(!avion.hayHomonicasPasajeros(dni.getText().toString())) {
                sillaLibra.asignarPasajero(pasajero);
                guardarAvion();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                String infoText = getString(R.string.text_pasajero_reservado)
                        + System.lineSeparator() + getString(R.string.text_hint_nombre) + String.valueOf(sillaLibra.getNumero())
                        + System.lineSeparator() + getString(R.string.text_clase_name) + String.valueOf(sillaLibra.getClase());
                textInfo.setTextSize(14.0f);
                textInfo.setTextColor(getResources().getColor(R.color.indigo_900));
                textInfo.setText(infoText);
                nombre.setText("");
                dni.setText("");
            }else{
                String error = getString(R.string.pasajero_ya_reservado);
                textInfo.setText(error);
                textInfo.setTextSize(18.0f);
                textInfo.setTextColor(getResources().getColor(R.color.red_400));
            }
        }else{
            String error = getString(R.string.no_hay_sillas) + " "+ claseSelect.name() + " " + getString(R.string.text_en_ubication)+ " " + ubicacionSelect.name();
            textInfo.setText(error);
            textInfo.setTextSize(18.0f);
            textInfo.setTextColor(getResources().getColor(R.color.red_400));
        }
    }

    private void guardarAvion() {
        GuardarAvion g=new GuardarAvion(avion, getFilesDir().getAbsolutePath()+ File.pathSeparator+"avion.txt");
        g.guardarObjeto();
    }

    public void desocuparAvion(View view) {
        avion.desocuparAvion();
        textInfo.setText("");
        guardarAvion();
        Toast toast = Toast.makeText(this, getString(R.string.text_desoucpado), Toast.LENGTH_SHORT);
        toast.show();
    }

    public void buscarPasajero(View view) {
        Intent intent = new Intent(this, BusquedaActivity.class);
        startActivity(intent);
    }
}