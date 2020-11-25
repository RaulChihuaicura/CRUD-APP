package com.example.crud_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Agenda> lista;
    AgendaDAO dao;
    Agenda agenda;
    Activity a;
    int id = 0;

    public Adaptador(Activity a, ArrayList<Agenda> lista, AgendaDAO dao) {
        this.lista = lista;
        this.dao = dao;
        this.a = a;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v  = view;
        if (v == null){
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item, null);
        }
        agenda = lista.get(posicion);
        TextView nombre   = v.findViewById(R.id.txt_nombre);
        TextView telefono = v.findViewById(R.id.txt_telefono);
        TextView email    = v.findViewById(R.id.txt_email);
        TextView edad     = v.findViewById(R.id.txt_edad);

        Button editar     = v.findViewById(R.id.btn_editar);  // *** Agregar botones en vista ****
        Button eliminar   = v.findViewById(R.id.btn_eliminar); //*** Agregar botones en vista ***

        nombre.setText(agenda.getNombre());
        telefono.setText(agenda.getTelefono());
        email.setText(agenda.getEmail());
        edad.setText(agenda.getEdad()+"");

        editar.setTag(posicion);
        eliminar.setTag(posicion);

        return null;
    }
}


