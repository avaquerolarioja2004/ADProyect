/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.receta.IDAO;

import com.mycompany.adproyecto.IDAO.IDAO;
import com.mycompany.adproyecto.receta.Receta;
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
public class RADAOReceta implements IDAO<Receta> {

    private final String name;
    private RandomAccessFile raf;
    private long position;
    private final SimpleDateFormat sdf;

    public RADAOReceta(String name) {
        this.name = name;
        this.sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        this.sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
    }

    @Override
    public boolean alta(Receta e) {
        StringBuffer nombreLibroRecetas;
        StringBuffer date;
        StringBuffer digital;
        try {
            raf = new RandomAccessFile(name, "rw");
            position = (e.getIdLibro() - 1) * 248;
            raf.seek(position);
            raf.writeInt(e.getIdReceta());
            nombreLibroRecetas = new StringBuffer(e.getNombre());
            nombreLibroRecetas.setLength(20);
            raf.writeChars(nombreLibroRecetas.toString());
            raf.writeInt(e.getIdLibro());
            if (e.getFechInvención() != null) {
                date = new StringBuffer(e.getFechInvención().toString());
            } else {
                date = new StringBuffer("vacio");
            }
            date.setLength(80);
            raf.writeChars(date.toString());
            digital = new StringBuffer(e.getVegana());
            digital.setLength(20);
            raf.writeChars(digital.toString());
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }
        try {
            raf.close();
            return true;
        } catch (IOException ex) {
            return false;
        }

    }

    @Override
    public Receta baja(Receta e) {
        try {
            raf = new RandomAccessFile(name, "rw");
            position = (e.getIdLibro() - 1) * 248;
            raf.seek(position);
            raf.writeInt(-1);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (Exception ex) {
        }
        try {
            raf.close();
        } catch (IOException ex) {
        }
        return e;

    }

    @Override
    public boolean modificar(Receta oldT, Receta newT) {
        StringBuffer nombreLibroRecetas;
        StringBuffer date;
        StringBuffer digital;
        try {
            raf = new RandomAccessFile(name, "rw");
            position = (oldT.getIdLibro() - 1) * 248;
            raf.seek(position);
            raf.writeInt(newT.getIdReceta());
            nombreLibroRecetas = new StringBuffer(newT.getNombre());
            nombreLibroRecetas.setLength(20);
            raf.writeChars(nombreLibroRecetas.toString());
            raf.writeInt(newT.getIdLibro());
            if (newT.getFechInvención() != null) {
                date = new StringBuffer(newT.getFechInvención().toString());
            } else {
                date = new StringBuffer("vacio");
            }
            date.setLength(80);
            raf.writeChars(date.toString());
            digital = new StringBuffer(newT.getVegana());
            digital.setLength(20);
            raf.writeChars(digital.toString());
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }
        try {
            raf.close();
            return true;
        } catch (IOException ex) {
            return false;
        }

    }

    @Override
    public Receta consultaId(int id) {
        File f = new File(name);
        char[] nombreReceta = new char[20];
        char[] date = new char[80];
        char[] vegana = new char[20];
        int idLibro;
        Date d = null;
        int idR;
        Receta receta = null;

        try {
            raf = new RandomAccessFile(f, "rw");
            for (int i = 1;; i++) {
                StringBuilder sbDate = new StringBuilder();
                StringBuilder sbNombre = new StringBuilder();
                StringBuffer sbDigital = new StringBuffer();
                position = (i - 1) * 248;
                raf.seek(position);
                idR = raf.readInt();
                if (id == -1) {
                    return null;
                }
                if (idR == id) {
                    for (int j = 0; j < nombreReceta.length; j++) {
                        nombreReceta[j] = raf.readChar();
                        sbNombre.append(nombreReceta[j]);
                    }
                    idLibro = raf.readInt();
                    for (int j = 0; j < date.length; j++) {
                        date[j] = raf.readChar();
                        sbDate.append(date[j]);
                    }

                    for (int j = 0; j < vegana.length; j++) {
                        vegana[j] = raf.readChar();
                        sbDigital.append(vegana[j]);
                    }

                    receta = new Receta(idR, sbNombre.toString());
                    receta.setIdLibro(idLibro);
                    if (!sbDate.toString().contains("vacio")) {
                        d = sdf.parse(sbDate.toString());
                    }
                    receta.setFechInvención(d);
                    if (sbDigital.toString().contains("si")) {
                        receta.setVegana(true);
                    } else {
                        receta.setVegana(false);
                    }
                }

            }
        } catch (EOFException eofx) {
        } catch (FileNotFoundException ex) {
            return null;
        } catch (IOException | ParseException ex) {
            receta = null;
        }
        try {
            raf.close();
        } catch (IOException ex) {
            receta = null;
        }
        return receta;
    }

    @Override
    public ArrayList<Receta> consultaAll() {
        File f = new File(name);
        char[] nombreReceta = new char[20];
        char[] date = new char[80];
        char[] vegana = new char[20];
        int idLibro;
        Date d = null;
        int idR;
        Receta receta = null;
        ArrayList<Receta> lista = new ArrayList<>();

        try {
            raf = new RandomAccessFile(f, "rw");
            for (int i = 1;; i++) {
                StringBuilder sbDate = new StringBuilder();
                StringBuilder sbNombre = new StringBuilder();
                StringBuffer sbDigital = new StringBuffer();
                position = (i - 1) * 248;
                raf.seek(position);
                idR = raf.readInt();
                if (idR != -1) {
                    for (int j = 0; j < nombreReceta.length; j++) {
                        nombreReceta[j] = raf.readChar();
                        sbNombre.append(nombreReceta[j]);
                    }
                    idLibro = raf.readInt();
                    for (int j = 0; j < date.length; j++) {
                        date[j] = raf.readChar();
                        sbDate.append(date[j]);
                    }

                    for (int j = 0; j < vegana.length; j++) {
                        vegana[j] = raf.readChar();
                        sbDigital.append(vegana[j]);
                    }

                    receta = new Receta(idR, sbNombre.toString());
                    receta.setIdLibro(idLibro);
                    if (!sbDate.toString().contains("vacio")) {
                        d = sdf.parse(sbDate.toString());
                    }
                    receta.setFechInvención(d);
                    if (sbDigital.toString().contains("si")) {
                        receta.setVegana(true);
                    } else {
                        receta.setVegana(false);
                    }

                    lista.add(receta);
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
