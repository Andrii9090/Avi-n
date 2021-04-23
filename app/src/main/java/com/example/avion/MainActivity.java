package com.example.avion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner spinnerClases;
    private Spinner spinnerUbicaciones;
    ArrayAdapter<CharSequence> spinnerUbicacionesAdapter;
    Avion avion;

    Clase claseSelect;
    Ubicacion ubicacionSelect;

    EditText nombre;
    EditText dni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerClases = (Spinner) findViewById(R.id.spinner_clases);
        ArrayAdapter<CharSequence> spinnerClasesAdapter = ArrayAdapter.createFromResource(this, R.array.array_clases, android.R.layout.simple_spinner_item);
        spinnerClasesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClases.setAdapter(spinnerClasesAdapter);

        spinnerUbicaciones = (Spinner) findViewById(R.id.spinner_ubicaciones);
        spinnerUbicacionesAdapter = ArrayAdapter.createFromResource(this, R.array.array_ubicaciones, android.R.layout.simple_spinner_item);
        spinnerUbicacionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUbicaciones.setAdapter(spinnerUbicacionesAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        avion = new Avion();

        spinnerClases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(id == 1){
                    claseSelect = Clase.ECONOMICA;
                    if(spinnerUbicacionesAdapter.getPosition("Centro")==-1) {
                        spinnerUbicacionesAdapter.add("Centro");
                    }
                }
                if(id == 2){
                    claseSelect = Clase.EJECUTIVA;
                    spinnerUbicacionesAdapter.remove("Centro");
                }
            }
        });
    }

    public void reservar(View view) {
        String ubicacion = (String) spinnerUbicaciones.getSelectedItem();

        if(ubicacion.equals("Ventana")){
            ubicacionSelect = Ubicacion.VENTANA;
        }
        if(ubicacion.equals("Centro")){
            ubicacionSelect = Ubicacion.VENTANA;
        }
        if(ubicacion.equals("Pasillo")){
            ubicacionSelect = Ubicacion.VENTANA;
        }
        nombre = view.findViewById(R.id.nombre);
        dni = view.findViewById(R.id.dni);
        Pasajero pasajero = new Pasajero(nombre.getText().toString(), dni.getText().toString());

    }
}