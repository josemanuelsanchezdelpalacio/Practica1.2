package code;

import com.google.gson.Gson;
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
import java.util.Date;

import static libs.FicheroEscribible.ficheroLegible;

/**Cargue los nuevos empleados a partir de los datos del fichero "nuevosEmpleados.json",
 * asignándoles como fecha de antigüedad la fecha del sistema.**/
public class CargarEmpleadosJSON {

    public static void cargar() {
        Path p = Path.of("src/main/resources/nuevosEmpleados.json");

        Empleado[] nuevosEmpleados;

        if (ficheroLegible(p)) {
            try {
                //leo el JSON "nuevosEmpleados.json"
                String txtJson = Files.readString(p, StandardCharsets.UTF_8);

                //creo un objeto Gson para convertir el JSON a objetos
                Gson gson = new Gson();
                //guardo la información leída del JSON en el array de empleados
                nuevosEmpleados = gson.fromJson(txtJson, Empleado[].class);

                //itero sobre los empleados cargados desde el JSON
                for (Empleado nuevoEmpleado : nuevosEmpleados) {
                    String añoNacimiento = String.valueOf(nuevoEmpleado.getAñoNacimiento());
                    Date fechaNacimiento = null;

                    //parseo la fecha de nacimiento
                    if (añoNacimiento != null && !añoNacimiento.equals("null")) {
                        try {
                            fechaNacimiento = new SimpleDateFormat("yyyy").parse(añoNacimiento);
                        } catch (ParseException e) {
                            System.err.println("Error al parsear la fecha de nacimiento: " + e.getMessage());
                            return;
                        }
                    }

                    //creo un objeto Empleado con los datos del JSON
                    Empleado empleado = new Empleado(
                            nuevoEmpleado.getNombre(),
                            nuevoEmpleado.getSueldo(),
                            fechaNacimiento,
                            new Date(),
                            nuevoEmpleado.getIdDepartamento()
                    );

                    //guardo la información en la lista de empleados
                    EscribirCSV.listaEmpleados.add(empleado);
                    System.out.println("Datos de nuevosEmpleados.json guardados");
                }
            } catch (FileNotFoundException e) {
                System.out.println("El archivo JSON no existe");
            } catch (MalformedInputException e) {
                System.out.println("Comprueba que la codificación del archivo sea UTF-8");
            } catch (IOException e) {
                System.err.println("Error al leer el archivo JSON: " + e.getMessage());
            }
        }
    }
}