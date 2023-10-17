package code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import javaBeans.Empresa;

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

            // Crear el contexto JAXB para la clase Empresa
            JAXBContext contexto = JAXBContext.newInstance(Empresa.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Crear una instancia de Empresa con datos existentes
            Empresa empresa = new Empresa();
            // Asignar los empleados y departamentos existentes
            empresa.setEmpleados(listaEmpleados);
            empresa.setDepartamentos(departamentos);

            // Guardar en el archivo "empresa.xml"
            marshaller.marshal(empresa, p.toFile());
            System.out.println("Archivo XML creado correctamente: " + p);
        } catch (Exception e) {
            System.err.println("Error al generar el archivo XML: " + e.getMessage());
        }
    }

    public static void generarArchivoJSON() {
        Path p = Path.of("target/empresa.json");

        // Crear una instancia de Empresa con datos existentes
        Empresa empresa = new Empresa();
        // Asignar los empleados y departamentos existentes
        empresa.setEmpleados(listaEmpleados);
        empresa.setDepartamentos(departamentos);

        // Crear el objeto Gson para pasar de objeto a JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Guardar la información en el JSON
        String jsonInfo = gson.toJson(empresa);

        try {
            // Escribir la información en el JSON
            Files.writeString(p, jsonInfo);
            System.out.println("Archivo JSON creado correctamente: " + p);
        } catch (IOException e) {
            System.err.println("Error al generar el archivo JSON: " + e.getMessage());
        }
    }
}
