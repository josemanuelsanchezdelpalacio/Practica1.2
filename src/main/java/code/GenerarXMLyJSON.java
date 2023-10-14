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

public class GenerarXMLyJSON {

    private static List<Empleado> empleados = EscribirCSV.listaEmpleados;
    private static List<Departamento> departamentos = LeerDepartamentos.departamentos;

    public static void generarArchivoXML() {
        try {
            Path p = Path.of("target/empresa.xml");

            // Configurar el marshaller
            JAXBContext contexto = JAXBContext.newInstance(Departamentos.class, Departamento.class, Empleado.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Crear objeto Departamentos y asignar los datos de los ArrayLists
            Departamentos departamentosObj = new Departamentos();
            departamentosObj.setDepartamentos(LeerDepartamentos.departamentos);

            // Marshalling a un archivo
            marshaller.marshal(departamentosObj, p.toFile());
            System.out.println("Archivo XML generado con éxito: " + p);
        } catch (Exception e) {
            System.err.println("Error al generar el archivo XML: " + e.getMessage());
        }
    }

    public static void generarArchivoJSON() {
        Path p = Path.of("target/empresa.json");

        List<Object> data = new ArrayList<>();
        data.addAll(empleados);
        data.addAll(departamentos);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonData = gson.toJson(data);

        try {
            Files.writeString(p, jsonData);
            System.out.println("Archivo JSON generado con éxito: " + p);
        } catch (IOException e) {
            System.err.println("Error al generar el archivo JSON: " + e.getMessage());
        }
    }
}
