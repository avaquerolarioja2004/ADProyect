/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.libroReceta.IDAO;

/**
 *
 * @author mrpox
 */


import com.mycompany.adproyecto.IDAO.IDAO;
import com.mycompany.adproyecto.libroReceta.LibroRecetas;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DOMDAOLibroRecetas implements IDAO<LibroRecetas> {
    private final String nombre;
    private final SimpleDateFormat sdf;

    public DOMDAOLibroRecetas(String nombre) {
        this.nombre = nombre;
        this.sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        this.sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
    }

    @Override
    public boolean alta(LibroRecetas e) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc;

            File file = new File(nombre);

            if (file.exists()) {
                doc = docBuilder.parse(file);
            } else {
                doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("LibrosRecetas");
                doc.appendChild(rootElement);
            }

            Element libroElement = doc.createElement("LibroReceta");
            doc.getDocumentElement().appendChild(libroElement);

            Element isbnElement = doc.createElement("ISBN");
            isbnElement.appendChild(doc.createTextNode(String.valueOf(e.getIsbn())));
            libroElement.appendChild(isbnElement);

            Element nombreElement = doc.createElement("Nombre");
            nombreElement.appendChild(doc.createTextNode(e.getNombre()));
            libroElement.appendChild(nombreElement);

            Element numPagsElement = doc.createElement("NumPags");
            numPagsElement.appendChild(doc.createTextNode(String.valueOf(e.getNumPags())));
            libroElement.appendChild(numPagsElement);

            Element fechaElement = doc.createElement("FechaPublicacion");
            if (e.getFechaPublicacion() != null) {
                fechaElement.appendChild(doc.createTextNode(sdf.format(e.getFechaPublicacion())));
            } else {
                fechaElement.appendChild(doc.createTextNode("null"));
            }
            libroElement.appendChild(fechaElement);

            Element digitalElement = doc.createElement("Digital");
            digitalElement.appendChild(doc.createTextNode(e.isDigital() ? "si" : "no"));
            libroElement.appendChild(digitalElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(nombre));
            transformer.transform(source, result);

            return true;
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            return false;
        }
    }

    @Override
    public LibroRecetas baja(LibroRecetas e) {
        try {
            File file = new File(nombre);

            if (!file.exists()) {
                return null;
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList libros = doc.getElementsByTagName("LibroReceta");

            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);
                int isbn = Integer.parseInt(getElementValue(libro, "ISBN"));

                if (isbn == e.getIsbn()) {
                    doc.getDocumentElement().removeChild(libro);
                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File(nombre));
                    transformer.transform(source, result);
                    return e;
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            return null;
        }

        return null;
    }

    @Override
    public boolean modificar(LibroRecetas oldT, LibroRecetas newT) {
        try {
            File file = new File(nombre);

            if (!file.exists()) {
                return false;
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList libros = doc.getElementsByTagName("LibroReceta");

            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);
                int isbn = Integer.parseInt(getElementValue(libro, "ISBN"));

                if (isbn == oldT.getIsbn()) {
                    setElementValue(libro, "Nombre", newT.getNombre());
                    setElementValue(libro, "NumPags", String.valueOf(newT.getNumPags()));
                    if (newT.getFechaPublicacion() != null) {
                        setElementValue(libro, "FechaPublicacion", sdf.format(newT.getFechaPublicacion()));
                    } else {
                        setElementValue(libro, "FechaPublicacion", "null");
                    }
                    setElementValue(libro, "Digital", newT.isDigital() ? "si" : "no");

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File(nombre));
                    transformer.transform(source, result);
                    return true;
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            return false;
        }

        return false;
    }

    @Override
    public LibroRecetas consultaId(int isbn) {
        try {
            File file = new File(nombre);

            if (!file.exists()) {
                return null;
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList libros = doc.getElementsByTagName("LibroReceta");

            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);
                int libroIsbn = Integer.parseInt(getElementValue(libro, "ISBN"));

                if (libroIsbn == isbn) {
                    LibroRecetas lr = new LibroRecetas(libroIsbn, getElementValue(libro, "Nombre"));
                    lr.setNumPags(Integer.parseInt(getElementValue(libro, "NumPags")));
                    String fechaStr = getElementValue(libro, "FechaPublicacion");
                    if (!fechaStr.equals("null")) {
                        Date fecha = sdf.parse(fechaStr);
                        lr.setFechaPublicacion(fecha);
                    } else {
                        lr.setFechaPublicacion(null);
                    }
                    lr.setDigital(getElementValue(libro, "Digital").equalsIgnoreCase("si"));
                    return lr;
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | ParseException ex) {
            return null;
        }

        return null;
    }

    @Override
    public ArrayList<LibroRecetas> consultaAll() {
        ArrayList<LibroRecetas> lista = new ArrayList<>();

        try {
            File file = new File(nombre);

            if (!file.exists()) {
                return lista;
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList libros = doc.getElementsByTagName("LibroReceta");

            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);
                int isbn = Integer.parseInt(getElementValue(libro, "ISBN"));
                String nombre = getElementValue(libro, "Nombre");
                int numPags = Integer.parseInt(getElementValue(libro, "NumPags"));
                String fechaStr = getElementValue(libro, "FechaPublicacion");
                Date fecha = fechaStr.equals("null") ? null : sdf.parse(fechaStr);
                boolean digital = getElementValue(libro, "Digital").equalsIgnoreCase("si");

                LibroRecetas lr = new LibroRecetas(isbn, nombre);
                lr.setNumPags(numPags);
                lr.setFechaPublicacion(fecha);
                lr.setDigital(digital);
                lista.add(lr);
            }
        } catch (ParserConfigurationException | SAXException | IOException | ParseException ex) {
            return null;
        }

        return lista;
    }

    private String getElementValue(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }

    private void setElementValue(Element element, String elementName, String value) {
        NodeList nodeList = element.getElementsByTagName(elementName).item(0).getChildNodes();
        Node node = nodeList.item(0);
        node.setNodeValue(value);
    }
}
