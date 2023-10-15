package code;

import com.google.gson.Gson;
import javaBeans.Empleado;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CargarEmpleadosJSON {

    public static void cargar() {
        Path p = Path.of("src/main/resources/nuevosEmpleados.json");

        Empleado[] nuevosEmpleados;

        try {
            //leemos el JSON "nuevosEmpleados.json"
            String txtJson = Files.readString(p, StandardCharsets.UTF_8);

            //creo el objeto GSON para pasar de JSON a objeto
            Gson gson = new Gson();
            //guardamos la informacion leida del JSON en el array
            nuevosEmpleados = gson.fromJson(txtJson, Empleado[].class);

            //itero sobre los empleados cargados del JSON
            for (Empleado nuevoEmpleado : nuevosEmpleados) {
                String añoNacimiento = String.valueOf(nuevoEmpleado.getAñoNacimiento());
                Date fechaNacimiento = null;

                //parseamos la fecha de nacimiento
                if (añoNacimiento != null && !añoNacimiento.equals("null")) {
                    try {
                        fechaNacimiento = new SimpleDateFormat("yyyy").parse(añoNacimiento);
                    } catch (ParseException e) {
                        System.err.println("Error al parsear la fecha de nacimiento: " + e.getMessage());
                        return;
                    }
                }

                //guardo en el objeto Empleado los datos del JSON
                Empleado empleado = new Empleado(
                        nuevoEmpleado.getNombre(),
                        nuevoEmpleado.getSueldo(),
                        fechaNacimiento,
                        new Date(),
                        nuevoEmpleado.getIdDepartamento()
                );
                //guardamos la informacion en el array "listaEmpleados"
                EscribirCSV.listaEmpleados.add(empleado);
                System.out.println("Datos de nuevosEmpleados.json guardados");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }
}