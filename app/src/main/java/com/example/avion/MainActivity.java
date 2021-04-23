package com.example.avion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinnerClases = (Spinner) findViewById(R.id.spinner_clases);
        ArrayAdapter<CharSequence> spinnerClasesAdapter = ArrayAdapter.createFromResource(this,R.array.array_clases, android.R.layout.simple_spinner_item);
        spinnerClasesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClases.setAdapter(spinnerClasesAdapter);
    }
}