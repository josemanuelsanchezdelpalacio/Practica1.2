package code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import javaBeans.Empresa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static code.EscribirCSV.listaEmpleados;
import static code.LeerDepartamentos.departamentos;
import static libs.FicheroEscribible.ficheroEscribible;

/**Genere un archivo xml y otro json con toda la información de la empresa.**/
public class GenerarXMLyJSON {

    public static void generarArchivoXML() {
        try {
            Path p = Path.of("target/empresa.xml");

            //creo instancia de Empresa con datos existentes
            Empresa empresa = new Empresa();

            //obtengo la lista de empleados y departamentos
            empresa.setEmpleados(listaEmpleados);
            empresa.setDepartamentos(departamentos);

            //creo contexto JAXB para la clase Empresa
            if (ficheroEscribible(p)) {
                JAXBContext contexto = JAXBContext.newInstance(Empresa.class);
                Marshaller marshaller = contexto.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                //guardo Guardo en el archivo empresa.xml
                marshaller.marshal(empresa, p.toFile());
                System.out.println("Archivo XML creado correctamente: " + p);
            } else {
                System.out.println("El archivo no se puede escribir.");
            }
        } catch (Exception e) {
            System.err.println("Error al generar el archivo XML: " + e.getMessage());
        }
    }

    public static void generarArchivoJSON() {
        Path p = Path.of("target/empresa.json");

        //creo una instancia de Empresa con datos existentes
        Empresa empresa = new Empresa();
        //asigno los empleados y departamentos existentes
        empresa.setDepartamentos(departamentos);

        //creo el objeto Gson para pasar de objeto a JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //guardo la información en el JSON
        String jsonInfo = gson.toJson(empresa);

        if (ficheroEscribible(p)) {
            try {
                //escribo la información en el JSON
                Files.writeString(p, jsonInfo);
                System.out.println("Archivo JSON creado correctamente: " + p);
            } catch (IOException e) {
                System.err.println("Error al generar el archivo JSON: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no se puede escribir.");
        }
    }
}

