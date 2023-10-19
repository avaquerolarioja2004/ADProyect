/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.receta;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Vespertino
 */
public class Receta implements Serializable{
    private int idReceta;
    private String nombre;
    private Date fechInvención;
    private boolean vegana; // true si es vegana; false si no lo es
    private int idLibro; //Clave foranea

    public Receta(int idReceta, String nombre) {
        this.idReceta = idReceta;
        this.nombre = nombre;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechInvención(Date fechInvención) {
        this.fechInvención = fechInvención;
    }

    public void setVegana(boolean vegana) {
        this.vegana = vegana;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
    

    public int getIdReceta() {
        return idReceta;
    }

    public String getNombre() {
        return nombre;
    }

    public Date getFechInvención() {
        return fechInvención;
    }

    public String getVegana() {
        if(vegana){
            return "Si";
        }else{
            return "No";
        }
    }

    public int getIdLibro() {
        return idLibro;
    }
    
    public boolean isVegana() {
        return vegana;
    }

    @Override
    public String toString() {
        return "RECETA:\nId: "+this.idReceta+"\nNombre: "+this.nombre+"\nFecha de su invención: "+this.fechInvención+"\nVegana: "+this.getVegana()+"\nId del libro: "+this.idLibro;
    }

    
    
}
