package org.example.domain;

import java.io.Serializable;

public class Paciente implements Serializable {
   private static final long serialVersionaUID = 1L;
    private int id;
    private String nombre;

    // Constructores, getters y setters
    public Paciente(){
    }

    public Paciente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Paciente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
