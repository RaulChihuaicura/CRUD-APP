package com.example.crud_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AgendaDAO dao;
    Adaptador adapter;
    ArrayList<Agenda> lista;
    Agenda agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao             = new AgendaDAO(this);
        lista           = dao.verTodos();
        adapter         = new Adaptador(this, lista, dao);
        ListView list   = (ListView)findViewById(R.id.lista);

        Button agregar  = (Button)findViewById(R.id.btn_agregar);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //realizaremos la vista previa de registro en vista.xml
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Realizaremos el agregar en dialogo.xml

                final Dialog dialogo  = new Dialog(MainActivity.this);
                dialogo.setTitle("Nuevo Registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo);
                dialogo.show();

                // Trabajamos los contenidos de la ventana

                final EditText nombre   = dialogo.findViewById(R.id.txt_nombre);
                final EditText telefono = dialogo.findViewById(R.id.txt_telefono);
                final EditText email     = dialogo.findViewById(R.id.txt_email);
                final EditText edad     = dialogo.findViewById(R.id.txt_edad);
                Button guardar    = dialogo.findViewById(R.id.btn_agregar);
                Button cancelar    = dialogo.findViewById(R.id.btn_cancelar);

                //Eventos para los botones

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // codificar el boton guardar
                        // falta incorporar el Try catch

                        agenda  = new Agenda(nombre.getText().toString(),
                                    telefono.getText().toString(),
                                    email.getText().toString(),
                                    Integer.parseInt(edad.getText().toString()));

                        dao.insertar(agenda);
                        adapter.notifyDataSetChanged();
                        dialogo.dismiss();

                    }

                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // codificar el boton cancelar
                        dialogo.dismiss();
                    }
                });

            }
        });
    }
}