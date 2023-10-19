/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.receta.IDAO;

import com.mycompany.adproyecto.IDAO.IDAO;
import com.mycompany.adproyecto.receta.Receta;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author ProfVespertino
 */
public class BufferedDAOReceta implements IDAO<Receta> {

    private BufferedReader br;
    private BufferedWriter bw;
    private final String nombre;
    private final SimpleDateFormat sdf;

    public BufferedDAOReceta(String nombre) {
        this.nombre = nombre;
        this.sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        this.sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
    }

    @Override
    public boolean alta(Receta e) {
        boolean flag=true;
        try {
            bw = new BufferedWriter(new FileWriter(nombre, true));
            String reg;
            reg = e.getIdReceta() + ";" + e.getNombre() + ";" + e.getIdLibro() + ";" + e.getFechInvención() + ";" + e.getVegana();
            bw.write(reg);
            bw.newLine();
        } catch (IOException ex) {
            flag=false;
        }
        
        try {
            bw.close();
        } catch (IOException ex) {
            flag=false;
        }
        return flag;
    }

    @Override
    public Receta baja(Receta e) {
        String linea;
        String[] receta = new String[4];
        File temp = new File(nombre + "aux");
        File or = new File(nombre);
        Receta out = null;
        Date d = null;
        int id;
        try {
            bw = new BufferedWriter(new FileWriter(nombre + "aux"));
            br = new BufferedReader(new FileReader(nombre));
            while ((linea = br.readLine()) != null) {
                receta = linea.split(";");
                id = Integer.parseInt(receta[0]);
                if (e.getIdReceta()!= id) {
                    bw.write(linea);
                    bw.newLine();
                } else {
                    out = new Receta(Integer.parseInt(receta[0]), receta[1]);
                        if (!receta[3].equalsIgnoreCase("null") && !receta[3].equalsIgnoreCase("")) {
                            d = sdf.parse(receta[3]);
                            out.setFechInvención(d);
                        } else {
                            out.setFechInvención(null);
                        }
                    
                    out.setIdLibro(Integer.parseInt(receta[2]));
                    if (receta[4].equalsIgnoreCase("si")) {
                        out.setVegana(true);
                    } else {
                        out.setVegana(false);
                    }
                }
            }
            bw.close();
            br.close();
            or.delete();
            temp.renameTo(new File(nombre));
            
        } catch (IOException | ParseException io) {
            out=null;
        }
        return out;
    }

    @Override
    public boolean modificar(Receta oldT, Receta newT) {
        boolean flag=true;
        String linea;
        String[] receta = new String[4];
        File temp = new File(nombre + "aux");
        File or = new File(nombre);
        String lr = newT.getIdReceta() + ";" + newT.getNombre() + ";" + newT.getIdLibro() + ";" + newT.getFechInvención() + ";" + newT.getVegana();
        int id;
        try {
            bw = new BufferedWriter(new FileWriter(nombre + "aux"));
            br = new BufferedReader(new FileReader(nombre));
            while ((linea = br.readLine()) != null) {
                receta = linea.split(";");
                id = Integer.parseInt(receta[0]);
                if (id == oldT.getIdReceta()) {
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
        } catch (IOException | NumberFormatException e) {
            flag=false;
        }
        return flag;
    }

    @Override
    public Receta consultaId(int id) {
        String[] receta = new String[4];
        String linea;
        Receta out = null;
        Date d;
        try {
            br = new BufferedReader(new FileReader(nombre));
            while ((linea = br.readLine()) != null) {
                receta = linea.split(";");
                if (id == Integer.parseInt(receta[0])) {
                    out = new Receta(Integer.parseInt(receta[0]), receta[1]);
                    out.setIdLibro(Integer.parseInt(receta[2]));
                    if (!receta[3].equalsIgnoreCase("null") && !receta[3].equalsIgnoreCase("")) {
                        d = sdf.parse(receta[3]);
                        out.setFechInvención(d);
                    } else {
                        out.setFechInvención(null);
                    }
                    if (receta[4].equalsIgnoreCase("si")) {
                        out.setVegana(true);
                    } else {
                        out.setVegana(false);
                    }
                    break;
                }
            }
        } catch (IOException | NumberFormatException | ParseException ex) {
            out=null;
        }
        try {
            br.close();
        } catch (IOException ex) {
            out=null;
        }
        return out;
    }

    @Override
    public ArrayList<Receta> consultaAll() {
        ArrayList<Receta> lista = new ArrayList<>();
        Receta lr;
        String[] receta = new String[4];
        String linea;
        Date d = null;
        try {
            br = new BufferedReader(new FileReader(nombre));
            while ((linea = br.readLine()) != null) {
                receta = linea.split(";");
                lr = new Receta(Integer.parseInt(receta[0]), receta[1]);
                if (!receta[3].equalsIgnoreCase("null") && !receta[3].equalsIgnoreCase("")) {
                    d = sdf.parse(receta[3]);
                    lr.setFechInvención(d);
                } else {
                    lr.setFechInvención(null);
                }

                lr.setIdLibro(Integer.parseInt(receta[2]));
                if (receta[4].equalsIgnoreCase("si")) {
                    lr.setVegana(true);
                } else {
                    lr.setVegana(false);
                }
                lista.add(lr);
            }
        } catch (IOException | NumberFormatException | ParseException ex) {
            lista=null;
        }
        try {
            br.close();
        } catch (IOException ex) {
            lista=null;
        }
        
        return lista;

    }

}
