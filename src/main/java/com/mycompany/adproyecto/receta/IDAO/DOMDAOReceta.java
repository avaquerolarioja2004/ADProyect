/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.adproyecto.receta.IDAO;

/**
 *
 * @author mrpox
 */
import com.mycompany.adproyecto.receta.Receta;
import com.mycompany.adproyecto.IDAO.IDAO;
import org.w3c.dom.*;
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
import org.xml.sax.SAXException;

public class DOMDAOReceta implements IDAO<Receta> {
    private final String nombre;
    private final SimpleDateFormat sdf;

    public DOMDAOReceta(String nombre) {
        this.nombre = nombre;
        this.sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        this.sdf.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
    }

    @Override
    public boolean alta(Receta e) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc;

            File file = new File(nombre);

            if (file.exists()) {
                doc = docBuilder.parse(file);
            } else {
                doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("Recetas");
                doc.appendChild(rootElement);
            }

            Element recetaElement = doc.createElement("Receta");
            doc.getDocumentElement().appendChild(recetaElement);

            Element idRecetaElement = doc.createElement("IdReceta");
            idRecetaElement.appendChild(doc.createTextNode(String.valueOf(e.getIdReceta())));
            recetaElement.appendChild(idRecetaElement);

            Element nombreElement = doc.createElement("Nombre");
            nombreElement.appendChild(doc.createTextNode(e.getNombre()));
            recetaElement.appendChild(nombreElement);

            Element idLibroElement = doc.createElement("IdLibro");
            idLibroElement.appendChild(doc.createTextNode(String.valueOf(e.getIdLibro())));
            recetaElement.appendChild(idLibroElement);

            Element fechaElement = doc.createElement("FechaInvencion");
            if (e.getFechInvención() != null) {
                fechaElement.appendChild(doc.createTextNode(sdf.format(e.getFechInvención())));
            } else {
                fechaElement.appendChild(doc.createTextNode("null"));
            }
            recetaElement.appendChild(fechaElement);

            Element veganaElement = doc.createElement("Vegana");
            veganaElement.appendChild(doc.createTextNode(e.isVegana() ? "si" : "no"));
            recetaElement.appendChild(veganaElement);

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
    public Receta baja(Receta e) {
        try {
            File file = new File(nombre);

            if (!file.exists()) {
                return null;
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList recetas = doc.getElementsByTagName("Receta");

            for (int i = 0; i < recetas.getLength(); i++) {
                Element receta = (Element) recetas.item(i);
                int idReceta = Integer.parseInt(getElementValue(receta, "IdReceta"));

                if (idReceta == e.getIdReceta()) {
                    doc.getDocumentElement().removeChild(receta);
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
    public boolean modificar(Receta oldT, Receta newT) {
        try {
            File file = new File(nombre);

            if (!file.exists()) {
                return false;
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList recetas = doc.getElementsByTagName("Receta");

            for (int i = 0; i < recetas.getLength(); i++) {
                Element receta = (Element) recetas.item(i);
                int idReceta = Integer.parseInt(getElementValue(receta, "IdReceta"));

                if (idReceta == oldT.getIdReceta()) {
                    setElementValue(receta, "Nombre", newT.getNombre());
                    setElementValue(receta, "IdLibro", String.valueOf(newT.getIdLibro()));
                    if (newT.getFechInvención() != null) {
                        setElementValue(receta, "FechaInvencion", sdf.format(newT.getFechInvención()));
                    } else {
                        setElementValue(receta, "FechaInvencion", "null");
                    }
                    setElementValue(receta, "Vegana", newT.isVegana() ? "si" : "no");

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
    public Receta consultaId(int idReceta) {
        try {
            File file = new File(nombre);

            if (!file.exists()) {
                return null;
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList recetas = doc.getElementsByTagName("Receta");

            for (int i = 0; i < recetas.getLength(); i++) {
                Element receta = (Element) recetas.item(i);
                int recetaId = Integer.parseInt(getElementValue(receta, "IdReceta"));

                if (recetaId == idReceta) {
                    Receta r = new Receta(recetaId, getElementValue(receta, "Nombre"));
                    r.setIdLibro(Integer.parseInt(getElementValue(receta, "IdLibro")));
                    String fechaStr = getElementValue(receta, "FechaInvencion");
                    if (!fechaStr.equals("null")) {
                        Date fecha = sdf.parse(fechaStr);
                        r.setFechInvención(fecha);
                    } else {
                        r.setFechInvención(null);
                    }
                    r.setVegana(getElementValue(receta, "Vegana").equalsIgnoreCase("si"));
                    return r;
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | ParseException ex) {
            return null;
        }

        return null;
    }

    @Override
    public ArrayList<Receta> consultaAll() {
        ArrayList<Receta> lista = new ArrayList<>();

        try {
            File file = new File(nombre);

            if (!file.exists()) {
                return lista;
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(file);

            NodeList recetas = doc.getElementsByTagName("Receta");

            for (int i = 0; i < recetas.getLength(); i++) {
                Element receta = (Element) recetas.item(i);
                int idReceta = Integer.parseInt(getElementValue(receta, "IdReceta"));
                String nombre = getElementValue(receta, "Nombre");
                int idLibro = Integer.parseInt(getElementValue(receta, "IdLibro"));
                String fechaStr = getElementValue(receta, "FechaInvencion");
                Date fecha = fechaStr.equals("null") ? null : sdf.parse(fechaStr);
                boolean vegana = getElementValue(receta, "Vegana").equalsIgnoreCase("si");

                Receta r = new Receta(idReceta, nombre);
                r.setIdLibro(idLibro);
                r.setFechInvención(fecha);
                r.setVegana(vegana);
                lista.add(r);
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

