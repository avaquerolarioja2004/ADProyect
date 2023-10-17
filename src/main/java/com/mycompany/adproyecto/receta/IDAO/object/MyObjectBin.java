/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.receta.IDAO.object;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author Vespertino
 */
public class MyObjectBin extends ObjectOutputStream{
    
    public MyObjectBin(OutputStream out) throws IOException{
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // Evita poner headers
    }
    
    
    
}
