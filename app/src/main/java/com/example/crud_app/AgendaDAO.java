package com.example.crud_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AgendaDAO {
    SQLiteDatabase db;
    Agenda agenda;
    ArrayList<Agenda> lista;
    String nombreBD = "AgendaBD";
    Context context;
    String tabla = "create table if not exists agenda(id integer primary key autoincrement not null," +
                    "nombre text, telefono text,email text,edad integer)";

    //Implementar el método constructor
    public AgendaDAO(Context context){
        this.context    = context;
        db              = context.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        db.execSQL(tabla);
        lista           = new ArrayList<>();
    }


    // Los métodos operacionales
    public boolean insertar(Agenda agenda){
        ContentValues contenedor    = new ContentValues();
        contenedor.put("nombre", agenda.getNombre());
        contenedor.put("telefono", agenda.getTelefono());
        contenedor.put("email", agenda.getEmail());
        contenedor.put("edad", agenda.getEdad());

        return (db.insert("agenda", null, contenedor)) > 0;
    }
    public boolean eliminar(int id){


        return (db.delete("agenda", "id = " + id, null )) > 0;
    }
    public boolean editar(Agenda agenda){
        ContentValues contenedor    = new ContentValues();
        contenedor.put("nombre", agenda.getNombre());
        contenedor.put("telefono", agenda.getTelefono());
        contenedor.put("email", agenda.getEmail());
        contenedor.put("edad", agenda.getEdad());

        return (db.update("agenda", contenedor, "id = " + agenda.getId(), null)) > 0;
    }
    public Agenda verUno(int pos){
        Cursor cursor       = db.rawQuery("select * from agenda", null);
        cursor.moveToPosition(pos);
        agenda  = new Agenda(cursor.getInt(0),
                            cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4));

        return agenda;
    }

    /**
     *
     * @return Arraylist - listado de todos los elementos de la tabla
     */
    public ArrayList<Agenda> verTodos(){
        Cursor cursor       = db.rawQuery("select * from agenda", null);
        if (cursor!= null && cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                lista.add(new Agenda(cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getInt(4)));
            }while(cursor.moveToNext());
        }

        return lista;
    }
}
