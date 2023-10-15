package javaBeans;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class DepartamentosHandler extends DefaultHandler {

    private ArrayList<Departamento> departamentos = new ArrayList<>();
    private Departamento depAux;

    //uso esta variable para parsear el ID desde el XML
    private Integer id;

    //para almacenar el texto contenido en un nodo texto
    private StringBuilder buffer = new StringBuilder();

    //para que nos devuelva un array del objeto Departamento
    public ArrayList<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName){
            case "departamento":
                depAux = new Departamento();
                //obtengo el ID del XML
                id = Integer.parseInt(attributes.getValue("id"));
                //se establece el ID en el Departamento
                depAux.setId(id);
                break;
            case "nombre", "localidad":
                //cuando se llega al cierre de la etiqueta se vacia el buffer
                buffer.delete(0, buffer.length());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName){
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