package javaBeans;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class DepartamentosHandler extends DefaultHandler {

    private ArrayList<Departamento> departamentos = new ArrayList<>();
    private Departamento depAux;

    private Integer id;

    //para almacenar el texto contenido en un nodo texto
    private StringBuilder buffer = new StringBuilder();

    //para que nos devuelva un array de coches
    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    //qName = nombre etiqueta

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName){
            case "departamentos":
                break;
            case "departamento":
                depAux = new Departamento();
                id = Integer.parseInt(attributes.getValue("id"));  // Obtenemos el ID del XML
                depAux.setId(id);  // Establecemos el ID en el Departamento
                break;
            case "nombre", "localidad":
                //cuando se llega al cierre de la etiqueta se vacia el buffer
                buffer.delete(0, buffer.length());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName){
            case "departamentos":
                break;
            case "departamento":
                departamentos.add(depAux);
                break;
            case "nombre":
                //almaceno el contenido del buffer
                depAux.setNombre(buffer.toString());
                break;
            case "localidad":
                depAux.setLocalidad(buffer.toString());
                break;
        }
    }

    //se lee el texto de una etiqueta
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
    }

}