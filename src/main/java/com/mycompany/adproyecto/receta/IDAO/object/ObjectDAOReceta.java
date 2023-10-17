/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.receta.IDAO.object;

import com.mycompany.adproyecto.IDAO.IDAO;
import com.mycompany.adproyecto.receta.Receta;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author mrpox
 */
public class ObjectDAOReceta implements IDAO<Receta> {

    private String nombre;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private MyObjectBin myOut;

    public ObjectDAOReceta(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean alta(Receta e) {
        boolean flag=true;
        File f = new File(nombre);
        try {
            if (f.length() < 1) {
                out = new ObjectOutputStream(new FileOutputStream(nombre, true));
                out.writeObject(e);
                out.close();
            } else {
                myOut = new MyObjectBin(new FileOutputStream(nombre, true));
                myOut.writeObject(e);
                myOut.close();
            }
        } catch (IOException exc) {
            return !flag;
        }
        return flag;
    }

    @Override
    public Receta baja(Receta e) {
        File temp = new File(nombre+"aux");
        File or = new File(nombre);
        Receta outR = null;
        try {
            in = new ObjectInputStream(new FileInputStream(nombre));
            out = new ObjectOutputStream(new FileOutputStream(temp));
            while (true) {
                outR = (Receta) in.readObject();
                if (outR.getIdReceta() != e.getIdReceta()) {
                    out.writeObject(e);
                }
            }

        } catch (EOFException ex) {
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            outR=null;
        }
        try {
            in.close();
            out.close();
            or.delete();
            temp.renameTo(or);
        } catch (IOException ex) {
            outR=null;
        }
        return outR;

    }

    @Override
    public boolean modificar(Receta oldT, Receta newT) {
        boolean flag=true;
        Receta auxR = null;
        int id;
        File fOld = new File(nombre);
        File fNew = new File(nombre+"aux");
        try {
            in = new ObjectInputStream(new FileInputStream(fOld));
            out = new ObjectOutputStream(new FileOutputStream(fNew, true));
            for (int i = 1;; i++) {
                auxR = (Receta) in.readObject();
                id = auxR.getIdReceta();
                if (id == oldT.getIdReceta()) {
                    out.writeObject(newT);
                } else {
                    out.writeObject(auxR);
                }
            }
        } catch (EOFException eof) {
        } catch (IOException | ClassNotFoundException ioe) {
            flag=false;
        }
        try {
            out.close();
            in.close();
            fOld.delete();
            fNew.renameTo(fOld);
        } catch (IOException exc) {
            flag=false;
        }
        
        return flag;

    }

    @Override
    public Receta consultaId(int id) {
        Receta out = null;
        try {
            in = new ObjectInputStream(new FileInputStream(nombre));
            do {
                out = (Receta) in.readObject();

            } while (id != out.getIdReceta());
        } catch (EOFException exc) {

        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            out=null;
        }
        try {
            in.close();
        } catch (IOException ex) {
            out=null;
        }
        return out;

    }

    @Override
    public ArrayList<Receta> consultaAll() {
        Receta out = null;
        ArrayList<Receta> lista = new ArrayList<>();
        try {
            in = new ObjectInputStream(new FileInputStream(nombre));
            do {
                out = (Receta) in.readObject();
                lista.add(out);
            } while (true);
        } catch (EOFException exc) {

        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException | ClassNotFoundException ex) {
            lista=null;
        }
        try {
            in.close();
        } catch (IOException ex) {
            lista=null;
        }
        return lista;

    }
}
