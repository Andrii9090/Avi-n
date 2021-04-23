package com.example.avion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerClases;
    Spinner spinnerUbicaciones;
    Avion avion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerClases = (Spinner) findViewById(R.id.spinner_clases);
        ArrayAdapter<CharSequence> spinnerClasesAdapter = ArrayAdapter.createFromResource(this,R.array.array_clases, android.R.layout.simple_spinner_item);
        spinnerClasesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClases.setAdapter(spinnerClasesAdapter);

        spinnerUbicaciones = (Spinner) findViewById(R.id.spinner_ubicaciones);
        ArrayAdapter<CharSequence> spinnerUbicacionesAdapter = ArrayAdapter.createFromResource(this,R.array.array_ubicaciones, android.R.layout.simple_spinner_item);
        spinnerUbicacionesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUbicaciones.setAdapter(spinnerUbicacionesAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        avion = new Avion();
    }

    public void reservar(View view) {
        String clase = (String) spinnerClases.getSelectedItem();

    }
}