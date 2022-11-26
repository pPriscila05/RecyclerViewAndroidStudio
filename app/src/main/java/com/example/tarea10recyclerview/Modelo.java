package com.example.tarea10recyclerview;

public class Modelo {
    private int id;
    private String nombre, tipo, descripcion;

    public Modelo(int id, String nombre, String tipo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
