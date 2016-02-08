package com.reser.luisesh1.lista.Models;

import io.realm.RealmObject;

/**
 * Created by Luisesh1 on 06/02/2016.
 */
public class Elements extends RealmObject {

    private int id;
    private String titulo;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
