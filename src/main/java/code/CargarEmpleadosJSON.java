package code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import javaBeans.Empleado;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static code.EscribirCSV.listaEmpleados;
import static libs.FicheroEscribible.ficheroEscribible;

public class CargarEmpleadosJSON {

    public static void cargar() {
        Path pJson = Path.of("src/main/resources/nuevosEmpleados.json");

        Empleado[] nuevosEmpleados;

        try {
            String txtJson = Files.readString(pJson, StandardCharsets.UTF_8);

            Gson gson = new Gson();
            nuevosEmpleados = gson.fromJson(txtJson, Empleado[].class);

            for (Empleado nuevoEmpleado : nuevosEmpleados) {
                String strAñoNacimiento = String.valueOf(nuevoEmpleado.getAñoNacimiento());
                Date fechaNacimiento = null;

                if (strAñoNacimiento != null && !strAñoNacimiento.equals("null")) {
                    try {
                        fechaNacimiento = new SimpleDateFormat("yyyy").parse(strAñoNacimiento);
                    } catch (ParseException e) {
                        System.err.println("Error al parsear la fecha de nacimiento: " + e.getMessage());
                        return;
                    }
                }

                Empleado empleado = new Empleado(
                        nuevoEmpleado.getNombre(),
                        nuevoEmpleado.getSueldo(),
                        fechaNacimiento,
                        new Date(),
                        nuevoEmpleado.getIdDepartamento()
                );
                EscribirCSV.listaEmpleados.add(empleado);
            }
        } catch (FileNotFoundException | NoSuchFileException e) {
            System.err.println("El archivo no existe: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }
}