package code;

import javaBeans.Departamento;
import javaBeans.DepartamentosHandler;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import static libs.FicheroEscribible.ficheroEscribible;

public class LeerDepartamentos {

    static ArrayList<Departamento> departamentos = new ArrayList<>();

    public static void leer() {
        Path p = Path.of("src/main/resources/departamentos.xml");

        if (ficheroEscribible(p)) {
            SAXParserFactory saxPF = SAXParserFactory.newInstance();
            DepartamentosHandler depHandler = new DepartamentosHandler();
            try {
                SAXParser parser = saxPF.newSAXParser();
                parser.parse(p.toFile(), depHandler);
                departamentos = depHandler.getDepartamentos();
                for (Departamento departamento : departamentos) {
                    System.out.println(departamento.toString());
                }
            } catch (SAXException e) {
                System.err.println("La clase no está modificada correctamente para aplicar el contexto: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            } catch (ParserConfigurationException e) {
                System.err.println("Error al parsear el archivo: " + e.getMessage());
            }
        }
    }
}
