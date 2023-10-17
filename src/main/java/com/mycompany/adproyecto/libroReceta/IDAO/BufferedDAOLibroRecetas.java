/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.libroReceta.IDAO;

import com.mycompany.adproyecto.IDAO.IDAO;
import com.mycompany.adproyecto.libroReceta.LibroRecetas;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProfVespertino
 */
public class BufferedDAOLibroRecetas implements IDAO<LibroRecetas> {

    private BufferedReader br;
    private BufferedWriter bw;
    private final String nombre;
    private final SimpleDateFormat sdf;

    public BufferedDAOLibroRecetas(String nombre) {
        this.nombre = nombre;
        this.sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        this.sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
    }

    @Override
    public boolean alta(LibroRecetas e) {
        try {
            bw = new BufferedWriter(new FileWriter(nombre, true));
            String reg;
            reg = e.getIsbn() + ";" + e.getNombre() + ";" + e.getNumPags() + ";" + e.getFechaPublicacion() + ";" + e.getDigital();
            bw.write(reg);
            bw.newLine();
            bw.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public LibroRecetas baja(LibroRecetas e) {
        String linea;
        String[] libroRecetas = new String[4];
        File temp = new File(nombre + "aux");
        File or = new File(nombre);
        LibroRecetas out = null;
        Date d = null;
        int isbn;
        try {
            bw = new BufferedWriter(new FileWriter(nombre + "aux"));
            br = new BufferedReader(new FileReader(nombre));
            while ((linea = br.readLine()) != null) {
                libroRecetas = linea.split(";");
                isbn = Integer.parseInt(libroRecetas[0]);
                if (e.getIsbn() != isbn) {
                    bw.write(linea);
                    bw.newLine();
                } else {
                    out = new LibroRecetas(Integer.parseInt(libroRecetas[0]), libroRecetas[1]);

                    try {
                        if (!libroRecetas[3].equalsIgnoreCase("null") && !libroRecetas[3].equalsIgnoreCase("")) {
                            d = sdf.parse(libroRecetas[3]);
                            out.setFechaPublicacion(d);
                        } else {
                            out.setFechaPublicacion(null);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(BufferedDAOLibroRecetas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    out.setNumPags(Integer.parseInt(libroRecetas[2]));
                    if (libroRecetas[4].equalsIgnoreCase("si")) {
                        out.setDigital(true);
                    } else {
                        out.setDigital(false);
                    }
                }
            }
            bw.close();
            br.close();
            or.delete();
            temp.renameTo(new File(nombre));
            return out;
        } catch (IOException io) {
            return out;
        }
    }

    @Override
    public boolean modificar(LibroRecetas oldT, LibroRecetas newT) {
        String linea;
        String[] libroRecetas = new String[4];
        File temp = new File(nombre + "aux");
        File or = new File(nombre);
        String lr = newT.getIsbn() + ";" + newT.getNombre() + ";" + newT.getNumPags() + ";" + newT.getFechaPublicacion() + ";" + newT.getDigital();
        int isbn;
        try {
            bw = new BufferedWriter(new FileWriter(nombre + "aux"));
            br = new BufferedReader(new FileReader(nombre));
            while ((linea = br.readLine()) != null) {
                libroRecetas = linea.split(";");
                isbn = Integer.parseInt(libroRecetas[0]);
                if (isbn == oldT.getIsbn()) {
                    bw.write(lr);
                    bw.newLine();
                } else {
                    bw.write(linea);
                    bw.newLine();
                }
            }
            bw.close();
            br.close();
            or.delete();
            temp.renameTo(new File(nombre));
            return true;
        } catch (IOException | NumberFormatException e) {
        }
        return false;
    }

    @Override
    public LibroRecetas consultaId(int isbn) {
        String[] libroRecetas = new String[4];
        String linea;
        LibroRecetas lr = null;
        Date d;
        try {
            br = new BufferedReader(new FileReader(nombre));
            while ((linea = br.readLine()) != null) {
                libroRecetas = linea.split(";");
                if (isbn == Integer.parseInt(libroRecetas[0])) {
                    lr = new LibroRecetas(Integer.parseInt(libroRecetas[0]), libroRecetas[1]);
                    lr.setNumPags(Integer.parseInt(libroRecetas[2]));
                    if (!libroRecetas[3].equalsIgnoreCase("null") && !libroRecetas[3].equalsIgnoreCase("")) {
                        d = sdf.parse(libroRecetas[3]);
                        lr.setFechaPublicacion(d);
                    } else {
                        lr.setFechaPublicacion(null);
                    }
                    if (libroRecetas[4].equalsIgnoreCase("si")) {
                        lr.setDigital(true);
                    } else {
                        lr.setDigital(false);
                    }
                    break;
                }
            }
        } catch (IOException | NumberFormatException ex) {
            return null;
        } catch (ParseException ex) {
        }
        try {
            br.close();
        } catch (IOException ex) {
        }
        return lr;

    }

    @Override
    public ArrayList<LibroRecetas> consultaAll() {
        ArrayList<LibroRecetas> lista = new ArrayList<>();
        LibroRecetas lr;
        String[] libroRecetas = new String[4];
        String linea;
        try {
            br = new BufferedReader(new FileReader(nombre));
            while ((linea = br.readLine()) != null) {
                libroRecetas = linea.split(";");
                lr = new LibroRecetas(Integer.parseInt(libroRecetas[0]), libroRecetas[1]);
                Date d = null;
                try {
                    if (!libroRecetas[3].equalsIgnoreCase("null") && !libroRecetas[3].equalsIgnoreCase("")) {
                        d = sdf.parse(libroRecetas[3]);
                        lr.setFechaPublicacion(d);
                    } else {
                        lr.setFechaPublicacion(null);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(BufferedDAOLibroRecetas.class.getName()).log(Level.SEVERE, null, ex);
                }
                lr.setNumPags(Integer.parseInt(libroRecetas[2]));
                if (libroRecetas[4].equalsIgnoreCase("si")) {
                    lr.setDigital(true);
                } else {
                    lr.setDigital(false);
                }
                lista.add(lr);
            }
        } catch (IOException | NumberFormatException ex) {
            return null;
        }
        try {
            br.close();
        } catch (IOException ex) {
        }
        return lista;

    }

}
