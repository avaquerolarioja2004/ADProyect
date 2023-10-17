/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.receta.IDAO;

import com.mycompany.adproyecto.IDAO.IDAO;
import com.mycompany.adproyecto.receta.Receta;
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
public class BinaryDAOReceta implements IDAO<Receta> {

    private final String nombre;
    private DataOutputStream salida;
    private DataInputStream entrada;
    private final SimpleDateFormat sdf;

    public BinaryDAOReceta(String nombre) {
        this.sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        this.sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        this.nombre = nombre;
    }

    @Override
    public boolean alta(Receta e) {
        boolean flag = true;
        salida = null;
        try {
            salida = new DataOutputStream(new FileOutputStream(nombre, true));
            salida.writeInt(e.getIdReceta());
            salida.writeUTF(e.getNombre());
            salida.writeInt(e.getIdLibro());
            if (e.getFechInvención() != null) {
                salida.writeUTF(e.getFechInvención().toString());
            } else {
                salida.writeUTF("");
            }
            salida.writeUTF(e.getVegana());
        } catch (FileNotFoundException exc) {
            return false;
        } catch (IOException ex) {
            flag = false;
        }
        try {
            salida.close();
        } catch (IOException ex) {
            flag = false;
        }
        return flag;

    }

    @Override
    public Receta baja(Receta e) {
        int id;
        String nombreR;
        String fechaCreacion;
        int idLibro;
        String vegana;
        File fOld = new File(nombre);
        File fNew = new File(nombre + "aux");
        Receta out = null;
        try {
            entrada = new DataInputStream(new FileInputStream(nombre));
            salida = new DataOutputStream(new FileOutputStream(nombre + "aux", true));
            while (true) {
                id = entrada.readInt();
                nombreR = entrada.readUTF();
                idLibro = entrada.readInt();
                fechaCreacion = entrada.readUTF();
                vegana = entrada.readUTF();
                if (id != e.getIdReceta()) {
                    salida.writeInt(id);
                    salida.writeUTF(nombreR);
                    salida.writeInt(idLibro);
                    salida.writeUTF(fechaCreacion);
                    salida.writeUTF(vegana);
                } else {
                    out = new Receta(id, nombreR);
                    out.setIdLibro(idLibro);
                    if (fechaCreacion != null && !fechaCreacion.isBlank()) {
                        out.setFechInvención(sdf.parse(fechaCreacion));
                    } else {
                        out.setFechInvención(null);
                    }
                    if (vegana.equalsIgnoreCase("si")) {
                        out.setVegana(true);
                    } else {
                        out.setVegana(false);
                    }
                    break;
                }
            }
        } catch (EOFException eof) {
        } catch (IOException | ParseException ioe) {
            out = null;
        }
        try {
            entrada.close();
            salida.close();
            fOld.delete();
            fNew.renameTo(fOld);
        } catch (IOException exc) {
            out = null;
        }
        return out;

    }

    @Override
    public boolean modificar(Receta oldT, Receta newT) {
        boolean flag = true;
        int id;
        String nombreR;
        String fechaCreacion;
        int idLibro;
        String vegana;
        File fOld = new File(nombre);
        File fNew = new File(nombre + "aux");
        try {
            entrada = new DataInputStream(new FileInputStream(nombre));
            salida = new DataOutputStream(new FileOutputStream(nombre + "aux", true));
            while (true) {
                id = entrada.readInt();
                nombreR = entrada.readUTF();
                idLibro = entrada.readInt();
                fechaCreacion = entrada.readUTF();
                vegana = entrada.readUTF();
                if (id == oldT.getIdReceta()) {
                    salida.writeInt(newT.getIdReceta());
                    salida.writeUTF(newT.getNombre());
                    salida.writeInt(newT.getIdLibro());
                    if (newT.getFechInvención() != null && !newT.getFechInvención().toString().isBlank()) {
                        salida.writeUTF(newT.getFechInvención().toString());
                    } else {
                        salida.writeUTF("");
                    }
                    salida.writeUTF(newT.getVegana());
                } else {
                    salida.writeInt(id);
                    salida.writeUTF(nombreR);
                    salida.writeInt(idLibro);
                    salida.writeUTF(fechaCreacion);
                    salida.writeUTF(vegana);
                }
            }
        } catch (EOFException eof) {
        } catch (IOException ioe) {
            flag = false;
        } catch (Exception ex) {
            flag = false;
        }
        try {
            entrada.close();
            salida.close();
            fOld.delete();
            fNew.renameTo(fOld);
        } catch (IOException exc) {
            flag = false;
        }
        return flag;

    }

    @Override
    public Receta consultaId(int id) {
        int idR;
        String nombreR;
        String fechaCreacion;
        int idLibro;
        String vegana;
        Receta rec = null;
        Date d = null;
        try {
            entrada = new DataInputStream(new FileInputStream(nombre));
            while (true) {
                idR = entrada.readInt();
                nombreR = entrada.readUTF();
                idLibro = entrada.readInt();
                fechaCreacion = entrada.readUTF();
                vegana = entrada.readUTF();
                if (idR == id) {
                    rec = new Receta(idR, nombreR);
                    rec.setIdLibro(idLibro);
                    if (fechaCreacion != null && !fechaCreacion.isEmpty()) {
                        d = sdf.parse(fechaCreacion);
                        rec.setFechInvención(d);
                    } else {
                        rec.setFechInvención(null);
                    }
                    if (vegana.equalsIgnoreCase("si")) {
                        rec.setVegana(true);
                    } else {
                        rec.setVegana(false);
                    }
                    break;
                }
            }
        } catch (EOFException eof) {
        } catch (IOException | ParseException ioe) {
            rec = null;
        }
        try {
            entrada.close();
        } catch (IOException exc) {
            rec = null;
        }
        return rec;

    }

    @Override
    public ArrayList<Receta> consultaAll() {
        int idR;
        String nombreR;
        String fechaCreacion;
        int idLibro;
        String vegana;
        Receta rec = null;
        Date d = null;
        ArrayList<Receta> lista = new ArrayList<>();
        try {
            entrada = new DataInputStream(new FileInputStream(nombre));
            while (true) {
                idR = entrada.readInt();
                nombreR = entrada.readUTF();
                idLibro = entrada.readInt();
                fechaCreacion = entrada.readUTF();
                vegana = entrada.readUTF();
                rec = new Receta(idR, nombreR);
                rec.setIdLibro(idLibro);
                if (fechaCreacion != null && !fechaCreacion.isEmpty()) {
                    d = sdf.parse(fechaCreacion);
                    rec.setFechInvención(d);
                } else {
                    rec.setFechInvención(null);
                }
                if (vegana.equalsIgnoreCase("si")) {
                    rec.setVegana(true);
                } else {
                    rec.setVegana(false);
                }
                lista.add(rec);
            }

        } catch (EOFException eof) {
        } catch (IOException | ParseException ioe) {
            lista = null;
        }
        try {
            entrada.close();
        } catch (IOException exc) {
            lista = null;
        }
        return lista;

    }

}
