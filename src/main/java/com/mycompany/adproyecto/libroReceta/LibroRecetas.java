/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.libroReceta;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Vespertino
 */
public class LibroRecetas implements Serializable{
    private int isbn;
    private String nombre;
    private Date fechaPublicacion;
    private int numPags;
    private boolean digital; //true si el libro es digital; false si no

    public LibroRecetas(int isbn, String nombre) {
        this.isbn = isbn;
        this.nombre = nombre;
    }

    public LibroRecetas() {
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public void setNumPags(int numPags) {
        this.numPags = numPags;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public int getNumPags() {
        return numPags;
    }

    public String getDigital() {
        if(digital){
            return "Si";
        }else{
            return "No";
        }
    }
    
    @Override
    public String toString() {
        String fecha="";
        if(this.getFechaPublicacion()==null){
            fecha="";
        }else{
            fecha=this.fechaPublicacion.toString();
        }
        return "LIBRO DE RECETAS:\nISBN: "+this.getIsbn()+"\nNombre: "+this.getNombre()+"\nNúmero de paginas: "+this.numPags+"\nFecha de publicación: "+fecha+"\nDigital: "+this.getDigital();
    }
    
}
