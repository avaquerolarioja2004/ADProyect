/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.IDAO;

import java.util.ArrayList;

/**
 *
 * @author Vespertino
 */
public interface IDAO <T>{
    public abstract boolean alta (T e);
    public abstract T baja (T e);
    public abstract boolean modificar (T oldT, T newT);
    public abstract T consultaId(int id);
    public abstract ArrayList<T> consultaAll();
}
