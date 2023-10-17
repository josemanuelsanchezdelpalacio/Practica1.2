package code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import javaBeans.Departamento;
import javaBeans.Departamentos;
import javaBeans.Empleado;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static code.EscribirCSV.listaEmpleados;
import static code.LeerDepartamentos.departamentos;

public class GenerarXMLyJSON {

    public static void generarArchivoXML() {
        try {
            Path p = Path.of("target/empresa.xml");

            //creo el objeto Marshaller para pasar de objetos a XML
            JAXBContext contexto = JAXBContext.newInstance(Departamentos.class, Departamento.class, Empleado.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            //creo un objeto Departamentos para guardar la informacion en el arraylist "departamentos"
            Departamentos departamentosObj = new Departamentos();
            departamentosObj.setDepartamentos(departamentos);

            //y lo guardamos en en el archivo "empresa.xml"
            marshaller.marshal(departamentosObj, p.toFile());
            System.out.println("Archivo XML creado correctamente: " + p);
        } catch (Exception e) {
            System.err.println("Error al generar el archivo XML: " + e.getMessage());
        }
    }

    public static void generarArchivoJSON() {
        Path p = Path.of("target/empresa.json");

        List<Object> listaObjetos = new ArrayList<>();
        listaObjetos.addAll(listaEmpleados);
        listaObjetos.addAll(departamentos);

        //creo el objeto GSON para pasar de objeto a JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //guardamos la lista de objetos
        String jsonInfo = gson.toJson(listaObjetos);

        try {
            //y escribo la informacion en el JSON
            Files.writeString(p, jsonInfo);
            System.out.println("Archivo JSON crado correctamente: " + p);
        } catch (IOException e) {
            System.err.println("Error al generar el archivo JSON: " + e.getMessage());
        }
    }
}
