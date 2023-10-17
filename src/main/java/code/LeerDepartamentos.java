package code;

import javaBeans.Departamento;
import javaBeans.DepartamentosHandler;
import javaBeans.Empleado;
import libs.Leer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import static libs.FicheroEscribible.ficheroEscribible;

/** A partir del archivo "departamentos.xml" que se adjunta, lea el fichero y cree los objetos correspondientes.**/

public class LeerDepartamentos {

    //para guardar los departamentos leidos
    static ArrayList<Departamento> departamentos = new ArrayList<>();

    public static void leer() {
        Path p = Path.of("src/main/resources/departamentos.xml");

        if (ficheroEscribible(p)) {
            SAXParserFactory saxPF = SAXParserFactory.newInstance();
            try {
                //creo el objeto SAXParser para leer el XML de departamentos a través del Handler
                SAXParser parser = saxPF.newSAXParser();
                DepartamentosHandler depHandler = new DepartamentosHandler();
                parser.parse(p.toFile(), depHandler);
                //obtengo el ArrayList de Departamento
                departamentos = depHandler.getDepartamentos();

                //itero para mostrar los departamentos
                System.out.println("Departamentos disponibles:");
                for (Departamento departamento : departamentos) {
                    System.out.println(departamento.getId() + " - " + departamento.getNombre());
                }
            } catch (SAXException e) {
                System.err.println("La clase no está modificada correctamente para aplicar el contexto: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error al leer el archivo: " + e.getMessage());
            } catch (ParserConfigurationException e) {
                System.err.println("Error al parsear el archivo: " + e.getMessage());
            }
        }
    }
}