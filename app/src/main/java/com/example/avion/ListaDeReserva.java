package com.example.avion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;

public class ListaDeReserva extends AppCompatActivity {
    Avion avion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_reserva);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_main);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle(getResources().getString(R.string.text_list_de_reserva_title));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GuardarAvion g = new GuardarAvion(getFilesDir().getAbsolutePath()+ File.pathSeparator+"avion.txt");
        avion = g.leerObjeto();
        RecyclerView rv = (RecyclerView)findViewById(R.id.lista_de_reserva_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        avion.getSillasOcupadas();
        ArrayList<Silla> sillasOcupadas = avion.sillasEconomicasOcupadas;
        sillasOcupadas.addAll(avion.sillasEjecutivasOcupadas);
        ListaDeReservaRecyclerView adapter = new ListaDeReservaRecyclerView(sillasOcupadas, this);
        rv.setAdapter(adapter);
    }
}