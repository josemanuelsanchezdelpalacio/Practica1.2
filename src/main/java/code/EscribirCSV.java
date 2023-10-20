package code;

import javaBeans.Empleado;
import libs.Leer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Date;

import static libs.Leer.pedirFecha;

/**Pida al usuario los datos de una serie de empleados y que los guarde en un fichero empleados.csv.
 * El usuario introducirá datos de empleados hasta que indique que no quiere añadir ninguno más.
 * Los empleados se representarán en el código como objetos de una clase Empleado que deberá contener
 * nombre, sueldo, año de nacimiento y antigüedad.**/

public class EscribirCSV {

    public static ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    //metodo para añadir los empleados al CSV y al arrayList "listaEmpleados"
    public static void pedirEmpleados() {

        Path p = Path.of("target/empleados.csv");

        String respuesta = "";
        do {
            String nombre = Leer.pedirCadena("Nombre del empleado: ");

            double sueldo = 0;
            try {
                sueldo = Leer.pedirDouble("Sueldo del empleado: ");
            } catch (NumberFormatException e) {
                System.err.println("Sueldo debe ser un número válido.");
            }

            //pedirFecha es un metodo creado en libs.Leer para parsear cualquier tipo de formato de fecha
            Date añoNacimiento = null;
            añoNacimiento = pedirFecha("Fecha de nacimiento del empledo (dd-MM-yyyy): ", "dd-MM-yyyy");

            Date antiguedad = new Date(); //fecha actual

            Empleado empleado = new Empleado (nombre, sueldo, añoNacimiento, antiguedad);
            if (empleado != null) {
                guardarEmpleadoEnArchivo(empleado, p);
                listaEmpleados.add(empleado);
            } else {
                System.out.println("Ninguno de los datos puede estar vacío.");
            }
            respuesta = Leer.pedirCadena("¿Quieres añadir otro empleado? (si/no): ").toLowerCase();
        } while (respuesta.equals("si"));

        System.out.println("Datos de empleados guardados en empleados.csv");
    }

    //metodo para guardar los datos del empleado en el CSV
    public static void guardarEmpleadoEnArchivo(Empleado empleado, Path p) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(p.toFile(), true))) {
            if (empleado != null) {
                writer.write(empleado.getNombre() + "," +
                        empleado.getSueldo() + "," +
                        dateFormat.format(empleado.getAñoNacimiento()) + "," +
                        dateFormat.format(empleado.getAntiguedad()) + "\n");
            } else {
                System.err.println("No se pudo guardar el empleado debido a errores en los datos.");
            }
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }
}
