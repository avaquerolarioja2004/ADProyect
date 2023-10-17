/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.libroReceta.IDAO;

import com.mycompany.adproyecto.IDAO.IDAO;
import com.mycompany.adproyecto.libroReceta.LibroRecetas;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Vespertino
 */
public class BinaryDAOLibroRecetas implements IDAO<LibroRecetas> {

    private final String nombre;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private final SimpleDateFormat sdf;

    public BinaryDAOLibroRecetas(String nombre) {
        this.sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        this.sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        this.nombre = nombre;
    }

    @Override
    public boolean alta(LibroRecetas e) {
        salida = null;
        try {
            salida = new DataOutputStream(new FileOutputStream(nombre, true));
            salida.writeInt(e.getIsbn());
            salida.writeUTF(e.getNombre());
            salida.writeInt(e.getNumPags());
            if (e.getFechaPublicacion() != null) {
                salida.writeUTF(e.getFechaPublicacion().toString());
            } else {
                salida.writeUTF("");
            }
            salida.writeUTF(e.getDigital());
        } catch (FileNotFoundException ex) {
            return false;
        } catch (IOException ex) {
            return false;
        }
        try {
            salida.close();
            return true;
        } catch (IOException ex) {
            return false;
        }

    }

    @Override
    public LibroRecetas baja(LibroRecetas e) {
        int isbn;
        String nombreLr;
        String fechaPublicacion;
        int numPags;
        String digital;
        File fOld = new File(nombre);
        File fNew = new File(nombre + "aux");
        LibroRecetas out = null;
        try {
            entrada = new DataInputStream(new FileInputStream(nombre));
            salida = new DataOutputStream(new FileOutputStream(nombre + "aux", true));
            while (true) {
                isbn = entrada.readInt();
                nombreLr = entrada.readUTF();
                numPags = entrada.readInt();
                fechaPublicacion = entrada.readUTF();
                digital = entrada.readUTF();
                if (isbn != e.getIsbn()) {
                    salida.writeInt(isbn);
                    salida.writeUTF(nombreLr);
                    salida.writeInt(numPags);
                    salida.writeUTF(fechaPublicacion);
                    salida.writeUTF(digital);
                } else {
                    out = new LibroRecetas(isbn, nombreLr);
                    out.setNumPags(numPags);
                    if (fechaPublicacion != null && !fechaPublicacion.isBlank()) {
                        out.setFechaPublicacion(sdf.parse(fechaPublicacion));
                    } else {
                        out.setFechaPublicacion(null);
                    }
                    if (digital.equalsIgnoreCase("si")) {
                        out.setDigital(true);
                    } else {
                        out.setDigital(false);
                    }
                    break;
                }
            }
        } catch (EOFException eof) {
        } catch (IOException | ParseException ioe) {
            return null;
        }
        try {
            entrada.close();
            salida.close();
            fOld.delete();
            fNew.renameTo(fOld);
            return out;
        } catch (IOException exc) {
            return null;
        }

    }

    @Override
    public boolean modificar(LibroRecetas oldT, LibroRecetas newT) {
        int isbn;
        String nombreLr;
        String fechaPublicacion;
        int numPags;
        String digital;
        File fOld = new File(nombre);
        File fNew = new File(nombre + "aux");
        try {
            entrada = new DataInputStream(new FileInputStream(nombre));
            salida = new DataOutputStream(new FileOutputStream(nombre + "aux", true));
            while (true) {
                isbn = entrada.readInt();
                nombreLr = entrada.readUTF();
                numPags = entrada.readInt();
                fechaPublicacion = entrada.readUTF();
                digital = entrada.readUTF();
                if (isbn == oldT.getIsbn()) {
                    salida.writeInt(newT.getIsbn());
                    salida.writeUTF(newT.getNombre());
                    salida.writeInt(newT.getNumPags());
                    if (newT.getFechaPublicacion() != null && !newT.getFechaPublicacion().toString().isBlank()) {
                        salida.writeUTF(newT.getFechaPublicacion().toString());
                    } else {
                        salida.writeUTF("");
                    }
                    salida.writeUTF(newT.getDigital());
                } else {
                    salida.writeInt(isbn);
                    salida.writeUTF(nombreLr);
                    salida.writeInt(numPags);
                    salida.writeUTF(fechaPublicacion);
                    salida.writeUTF(digital);
                }
            }
        } catch (EOFException eof) {
        } catch (IOException ioe) {
            return false;
        } catch (Exception ex) {
            return false;
        }
        try {
            entrada.close();
            salida.close();
            fOld.delete();
            fNew.renameTo(fOld);
            return true;
        } catch (IOException exc) {
            return false;
        }

    }

    @Override
    public LibroRecetas consultaId(int isbn) {
        int isbnLeido;
        String nombreLr;
        String fechaPublicacion;
        int numPags;
        Date d;
        String digital;
        LibroRecetas lr = null;
        try {
            entrada = new DataInputStream(new FileInputStream(nombre));
            while (true) {
                isbnLeido = entrada.readInt();
                nombreLr = entrada.readUTF();
                numPags = entrada.readInt();
                fechaPublicacion = entrada.readUTF();
                digital = entrada.readUTF();
                if (isbnLeido == isbn) {
                    lr = new LibroRecetas(isbnLeido, nombreLr);
                    lr.setNumPags(numPags);
                    if (fechaPublicacion != null && !fechaPublicacion.isEmpty()) {
                        d = sdf.parse(fechaPublicacion);
                        lr.setFechaPublicacion(d);
                    } else {
                        lr.setFechaPublicacion(null);
                    }
                    if (digital.equalsIgnoreCase("si")) {
                        lr.setDigital(true);
                    } else {
                        lr.setDigital(false);
                    }
                    break;
                }
            }
        } catch (EOFException eof) {
        } catch (IOException ioe) {
        } catch (ParseException ex) {
        }
        try {
            entrada.close();
        } catch (IOException exc) {
        }
        return lr;

    }

    @Override
    public ArrayList<LibroRecetas> consultaAll() {
        int isbnLeido;
        String nombreLr;
        String fechaPublicacion;
        int numPags;
        Date d;
        ArrayList<LibroRecetas> listaLr = new ArrayList<>();
        String digital;
        LibroRecetas lr = null;
        try {
            entrada = new DataInputStream(new FileInputStream(nombre));
            while (true) {
                isbnLeido = entrada.readInt();
                nombreLr = entrada.readUTF();
                numPags = entrada.readInt();
                fechaPublicacion = entrada.readUTF();
                digital = entrada.readUTF();
                lr = new LibroRecetas(isbnLeido, nombreLr);
                lr.setNumPags(numPags);
                if (fechaPublicacion != null && !fechaPublicacion.isEmpty()) {
                    d = sdf.parse(fechaPublicacion);
                    lr.setFechaPublicacion(d);
                } else {
                    lr.setFechaPublicacion(null);
                }
                if (digital.equalsIgnoreCase("si")) {
                    lr.setDigital(true);
                } else {
                    lr.setDigital(false);
                }

                listaLr.add(lr);

            }
        } catch (EOFException eof) {
        } catch (IOException | ParseException ioe) {
        }
        try {
            entrada.close();
            return listaLr;
        } catch (IOException exc) {
            return listaLr;
        }

    }

}
