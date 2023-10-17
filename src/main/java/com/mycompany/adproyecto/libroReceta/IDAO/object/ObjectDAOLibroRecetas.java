/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.libroReceta.IDAO.object;

import com.mycompany.adproyecto.IDAO.IDAO;
import com.mycompany.adproyecto.libroReceta.LibroRecetas;
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
public class ObjectDAOLibroRecetas implements IDAO<LibroRecetas> {

    private String nombre;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private MyObjectBin myOut;

    public ObjectDAOLibroRecetas(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean alta(LibroRecetas e) {
        File f = new File(nombre);
        try {
            if (f.length() < 1) {
                out = new ObjectOutputStream(new FileOutputStream(nombre, true));
                out.writeObject(e);
                out.close();
                return true;
            } else {
                myOut = new MyObjectBin(new FileOutputStream(nombre, true));
                myOut.writeObject(e);
                myOut.close();
                return true;
            }
        } catch (IOException exc) {
            return false;
        }
    }

    @Override
    public LibroRecetas baja(LibroRecetas e) {
        File temp = new File(nombre+"aux");
        File or = new File(nombre);
        LibroRecetas lr = null;
        try {
            in = new ObjectInputStream(new FileInputStream(nombre));
            out = new ObjectOutputStream(new FileOutputStream(temp));
            while (true) {
                lr = (LibroRecetas) in.readObject();
                if (lr.getIsbn() != e.getIsbn()) {
                    out.writeObject(e);
                }
            }

        } catch (EOFException ex) {

        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException ex) {
        }
        try {
            in.close();
            out.close();
            or.delete();
            temp.renameTo(or);
            return lr;
        } catch (IOException ex) {
            return lr;
        }

    }

    @Override
    public boolean modificar(LibroRecetas oldT, LibroRecetas newT) {
        LibroRecetas lr = null;
        int isbn;
        File fOld = new File(nombre);
        File fNew = new File(nombre+"aux");
        try {
            in = new ObjectInputStream(new FileInputStream(fOld));
            out = new ObjectOutputStream(new FileOutputStream(fNew, true));
            for (int i = 1;; i++) {
                lr = (LibroRecetas) in.readObject();
                isbn = lr.getIsbn();
                if (isbn == oldT.getIsbn()) {
                    out.writeObject(newT);
                } else {
                    out.writeObject(lr);
                }
            }
        } catch (EOFException eof) {
        } catch (IOException | ClassNotFoundException ioe) {
        }
        try {
            out.close();
            in.close();
            fOld.delete();
            fNew.renameTo(fOld);
            return true;
        } catch (IOException exc) {
            return false;
        }

    }

    @Override
    public LibroRecetas consultaId(int isbn) {
        LibroRecetas lr = null;
        try {
            in = new ObjectInputStream(new FileInputStream(nombre));
            do {
                lr = (LibroRecetas) in.readObject();

            } while (isbn != lr.getIsbn());
        } catch (EOFException exc) {

        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException ex) {
        }
        try {
            in.close();
            return lr;
        } catch (IOException ex) {
            return lr;
        }

    }

    @Override
    public ArrayList<LibroRecetas> consultaAll() {
        LibroRecetas lr = null;
        ArrayList<LibroRecetas> listrLr = new ArrayList<>();
        try {
            in = new ObjectInputStream(new FileInputStream(nombre));
            do {
                lr = (LibroRecetas) in.readObject();
                listrLr.add(lr);
            } while (true);
        } catch (EOFException exc) {

        } catch (FileNotFoundException ex) {
        } catch (IOException | ClassNotFoundException ex) {
        }
        try {
            in.close();
            return listrLr;
        } catch (IOException ex) {
            return listrLr;
        }

    }
}
