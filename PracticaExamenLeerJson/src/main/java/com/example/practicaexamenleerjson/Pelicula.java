package com.example.practicaexamenleerjson;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Pelicula {

    private int id;
    private String titulo;
    private String fecha;
    private String director;
    private String genero;
    private String ciudad;

    public Pelicula() {
    }

    public Pelicula( String titulo,
                     String fecha,
                     String director,
                     String genero,
                    String ciudad){
        this.titulo = titulo;
        this.fecha = fecha;
        this.director = director;
        this.genero = genero;
        this.ciudad = ciudad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
