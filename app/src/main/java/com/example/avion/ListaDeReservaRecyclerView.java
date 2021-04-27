package com.example.avion;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaDeReservaRecyclerView extends RecyclerView.Adapter<ListaDeReservaRecyclerView.ViewHolderSillas> {
    ArrayList<Silla> sillasOcupadas;

    ListaDeReservaRecyclerView(ArrayList<Silla> sillasOcupadas, Context context){
        this.sillasOcupadas = sillasOcupadas;
    }
    @NonNull
    @Override
    public ListaDeReservaRecyclerView.ViewHolderSillas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_reserva, parent, false);
        return new ViewHolderSillas(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaDeReservaRecyclerView.ViewHolderSillas holder, int position) {
        Silla silla = this.sillasOcupadas.get(position);
        Pasajero pasajero = silla.getPasajero();

        holder.textNombre.setText(pasajero.getNombre()+" (DNI "+pasajero.getDni()+")");
        if(silla.getClase().equals(Clase.ECONOMICA))
            holder.textSilla.setText(String.valueOf(silla.getNumero())+"EC");
        else
            holder.textSilla.setText(String.valueOf(silla.getNumero())+"EJ");
    }

    @Override
    public int getItemCount() {
        return sillasOcupadas.size();
    }

    public static class ViewHolderSillas extends RecyclerView.ViewHolder {
        private TextView textNombre;
        private TextView textSilla;
        public ViewHolderSillas(@NonNull View itemView) {
            super(itemView);
            textNombre = (TextView) itemView.findViewById(R.id.nombre_de_pasajero);
            textSilla = (TextView) itemView.findViewById(R.id.numero_de_silla);
        }
    }
}
