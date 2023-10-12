package code;

import javaBeans.Departamento;
import javaBeans.Departamentos;
import libs.Leer;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import static libs.FicheroEscribible.ficheroEscribible;

public class LeerDepartamentos {

    public static ArrayList<Departamento> leer() {
        Path p = Path.of(Leer.pedirCadena("Introduce ruta fichero: "));
        ArrayList<Departamento> departamentosXML = new ArrayList<>();

        if (ficheroEscribible(p)) {
            SAXParserFactory saxPF = SAXParserFactory.newInstance();
            Departamentos depHandler = new Departamentos();
            try {
                SAXParser parser = saxPF.newSAXParser();
                parser.parse(p.toFile(),depHandler);
                departamentosXML = depHandler.getDepartamentos();
                for (Departamento c : departamentosXML){
                    System.out.println(c.toString());
                }
            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        return departamentosXML;
    }
}
