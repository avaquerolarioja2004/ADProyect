/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.libroReceta.IDAO;

import com.mycompany.adproyecto.IDAO.IDAO;
import com.mycompany.adproyecto.libroReceta.LibroRecetas;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author mrpox
 */
public class RADAOLibroRecetas implements IDAO<LibroRecetas> {

    private final String name;
    private RandomAccessFile raf;
    private long position;
    private final SimpleDateFormat sdf;

    public RADAOLibroRecetas(String name) {
        this.name = name;
        this.sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        this.sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
    }

    @Override
    public boolean alta(LibroRecetas e) {
        boolean flag = true;
        StringBuffer nombreLibroRecetas;
        StringBuffer date;
        StringBuffer digital;
        File f = new File(name);
        try {
            raf = new RandomAccessFile(name, "rw");
            position = f.length();
            raf.seek(position);
            raf.writeInt(e.getIsbn());
            nombreLibroRecetas = new StringBuffer(e.getNombre());
            nombreLibroRecetas.setLength(20);
            raf.writeChars(nombreLibroRecetas.toString() + "");
            raf.writeInt(e.getNumPags());
            if (e.getFechaPublicacion() != null) {
                date = new StringBuffer(e.getFechaPublicacion().toString());
            } else {
                date = new StringBuffer("vacio");
            }
            date.setLength(80);
            raf.writeChars(date.toString());
            digital = new StringBuffer(e.getDigital());
            digital.setLength(20);
            raf.writeChars(digital.toString());
        } catch (FileNotFoundException ex) {
            flag = false;
        } catch (IOException ex) {
            flag = false;
        } catch (Exception ex) {
            flag = false;
        }
        try {
            raf.close();
        } catch (IOException ex) {
            return false;
        }

        return flag;

    }

    @Override
    public LibroRecetas baja(LibroRecetas e) {
        try {
            raf = new RandomAccessFile(name, "rw");
            position = (e.getIsbn() - 1) * 248;
            raf.seek(position);
            raf.writeInt(-1);
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException ex) {
            e = null;
        } catch (Exception ex) {
            e = null;
        }
        try {
            raf.close();
        } catch (IOException ex) {
            return null;
        }
        return e;

    }

    @Override
    public boolean modificar(LibroRecetas oldT, LibroRecetas newT) {
        StringBuffer nombreLibroRecetas;
        StringBuffer date;
        StringBuffer digital;
        boolean flag = true;
        if (consultaId(oldT.getIsbn()) == null) {
            return false;
        }
        try {
            raf = new RandomAccessFile(name, "rw");
            position = (oldT.getIsbn() - 1) * 248;
            raf.seek(position);
            raf.writeInt(newT.getIsbn());
            nombreLibroRecetas = new StringBuffer(newT.getNombre());
            nombreLibroRecetas.setLength(20);
            raf.writeChars(nombreLibroRecetas.toString() + "");
            raf.writeInt(newT.getNumPags());
            if (newT.getFechaPublicacion() != null) {
                date = new StringBuffer(newT.getFechaPublicacion().toString());
            } else {
                date = new StringBuffer("vacio");
            }
            date.setLength(80);
            raf.writeChars(date.toString());
            digital = new StringBuffer(newT.getDigital());
            digital.setLength(20);
            raf.writeChars(digital.toString());
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            flag = false;
        } catch (Exception ex) {
            flag = false;
        }
        try {
            raf.close();
        } catch (IOException ex) {
            return false;
        }
        return flag;

    }

    @Override
    public LibroRecetas consultaId(int id) {
        File f = new File(name);
        char[] nombreLibroRecetas = new char[20];
        char[] date = new char[80];
        char[] digital = new char[20];
        int numPags;
        Date d = null;
        int isbn;
        LibroRecetas lr = null;

        try {
            raf = new RandomAccessFile(f, "rw");
            for (int i = 1;; i++) {
                StringBuilder sbDate = new StringBuilder();
                StringBuilder sbNombre = new StringBuilder();
                StringBuffer sbDigital = new StringBuffer();
                position = (i - 1) * 248;
                raf.seek(position);
                isbn = raf.readInt();
                if (id == -1) {
                    return null;
                }
                if (isbn == id) {
                    for (int j = 0; j < nombreLibroRecetas.length; j++) {
                        nombreLibroRecetas[j] = raf.readChar();
                        sbNombre.append(nombreLibroRecetas[j]);
                    }
                    numPags = raf.readInt();
                    for (int j = 0; j < date.length; j++) {
                        date[j] = raf.readChar();
                        sbDate.append(date[j]);
                    }

                    for (int j = 0; j < digital.length; j++) {
                        digital[j] = raf.readChar();
                        sbDigital.append(digital[j]);
                    }

                    lr = new LibroRecetas(isbn, sbNombre.toString());
                    lr.setNumPags(numPags);
                    if (!sbDate.toString().contains("vacio")) {
                        d = sdf.parse(sbDate.toString());
                    }
                    lr.setFechaPublicacion(d);
                    if (sbDigital.toString().contains("si")) {
                        lr.setDigital(true);
                    } else {
                        lr.setDigital(false);
                    }
                }

            }
        } catch (EOFException eofx) {
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException | ParseException ex) {
            lr = null;
        }
        try {
            raf.close();
        } catch (IOException ex) {
            lr = null;
        }
        return lr;
    }

    @Override
    public ArrayList<LibroRecetas> consultaAll() {
        File f = new File(name);
        char[] nombreLibroRecetas = new char[20];
        char[] date = new char[80];
        char[] digital = new char[20];
        int numPags;
        Date d = null;
        int isbn;
        LibroRecetas lr;
        ArrayList<LibroRecetas> lista = new ArrayList<>();

        try {
            raf = new RandomAccessFile(f, "rw");
            for (int i = 1;; i++) {
                StringBuilder sbDate = new StringBuilder();
                StringBuilder sbNombre = new StringBuilder();
                StringBuffer sbDigital = new StringBuffer();
                position = (i - 1) * 248;
                raf.seek(position);
                isbn = raf.readInt();
                if (isbn != -1) {
                    for (int j = 0; j < nombreLibroRecetas.length; j++) {
                        nombreLibroRecetas[j] = raf.readChar();
                        sbNombre.append(nombreLibroRecetas[j]);
                    }
                    numPags = raf.readInt();
                    for (int j = 0; j < date.length; j++) {
                        date[j] = raf.readChar();
                        sbDate.append(date[j]);
                    }

                    for (int j = 0; j < digital.length; j++) {
                        digital[j] = raf.readChar();
                        sbDigital.append(digital[j]);
                    }

                    lr = new LibroRecetas(isbn, sbNombre.toString());
                    lr.setNumPags(numPags);
                    if (!sbDate.toString().contains("vacio")) {
                        d = sdf.parse(sbDate.toString());
                    }
                    lr.setFechaPublicacion(d);
                    if (sbDigital.toString().contains("si")) {
                        lr.setDigital(true);
                    } else {
                        lr.setDigital(false);
                    }

                    lista.add(lr);
                }

            }
        } catch (EOFException eofx) {
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException | ParseException ex) {
            lista = null;
        }
        try {
            raf.close();
        } catch (IOException ex) {
            lista = null;
        }
        return lista;
    }

}
