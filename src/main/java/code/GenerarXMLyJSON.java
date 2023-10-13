package code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import javaBeans.Departamento;
import javaBeans.Empleado;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GenerarXMLyJSON {

    public static void generarXML() {
        // Crear departamentos y empleados
        Departamento departamento1 = new Departamento();
        departamento1.setId(1);
        departamento1.setNombre("Departamento A");
        departamento1.setLocalidad("Ciudad A");

        Empleado empleado1 = new Empleado("Empleado1", 3000.0, new Date(), new Date(), 1);
        Empleado empleado2 = new Empleado("Empleado2", 3500.0, new Date(), new Date(), 1);

        departamento1.getEmpleados().add(empleado1);
        departamento1.getEmpleados().add(empleado2);

        ArrayList<Departamento> departamentos = new ArrayList<>();
        departamentos.add(departamento1);

        Path p = Path.of("target/departamentos.xml");

        // Crear un contexto JAXB
        JAXBContext contexto;
        try {
            contexto = JAXBContext.newInstance(Departamento.class, Empleado.class);

            // Crear un marshaller
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Escribir el XML
            marshaller.marshal(departamentos, p.toFile());

            System.out.println("Archivo XML generado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generarJSON() {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        // Create some departments and employees
        Departamento departamento1 = new Departamento();
        departamento1.setId(1);
        departamento1.setNombre("Departamento A");
        departamento1.setLocalidad("Ciudad A");

        Empleado empleado1 = new Empleado("Empleado1", 3000.0, new Date(), new Date(), 1);
        Empleado empleado2 = new Empleado("Empleado2", 3500.0, new Date(), new Date(), 1);

        departamento1.getEmpleados().add(empleado1);
        departamento1.getEmpleados().add(empleado2);

        departamentos.add(departamento1);

        Path p = Path.of("target/empleados.json");

        // Create a Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Convert to JSON
        JsonArray jsonArray = new JsonArray();
        for (Departamento departamento : departamentos) {
            JsonObject departmentJson = new JsonObject();
            departmentJson.addProperty("id", departamento.getId());
            departmentJson.addProperty("nombre", departamento.getNombre());
            departmentJson.addProperty("localidad", departamento.getLocalidad());

            JsonArray employeesJson = new JsonArray();
            for (Empleado empleado : departamento.getEmpleados()) {
                JsonObject employeeJson = new JsonObject();
                employeeJson.addProperty("nombre", empleado.getNombre());
                employeeJson.addProperty("sueldo", empleado.getSueldo());
                employeeJson.addProperty("añoNacimiento", empleado.getAñoNacimiento().getTime());
                employeeJson.addProperty("antiguedad", empleado.getAntiguedad().getTime());
                employeeJson.addProperty("idDepartamento", empleado.getIdDepartamento());

                employeesJson.add(employeeJson);
            }

            departmentJson.add("empleados", employeesJson);
            jsonArray.add(departmentJson);
        }

        try {
            // Write the JSON to a file
            FileWriter writer = new FileWriter(p.toString());
            gson.toJson(jsonArray, writer);
            writer.close();

            System.out.println("JSON file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}