package code;

import javaBeans.Departamento;
import javaBeans.Empleado;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EscribirCSV {
    private static Scanner sc = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");

    public static void pedirEmpleados() {
        Path p = Path.of("empleados.csv");

        String respuesta;
        do {
            Empleado empleado = obtenerDatosEmpleado();
            guardarEmpleadoEnArchivo(empleado, p);

            System.out.print("¿Quieres añadir otro empleado? (si/no): ");
            respuesta = sc.nextLine().toLowerCase();
        } while (respuesta.equals("si"));

        System.out.println("Datos de empleados guardados en empleados.csv.");
    }

    public static Empleado obtenerDatosEmpleado() {
        System.out.print("Nombre del empleado: ");
        String nombre = sc.nextLine();

        System.out.print("Sueldo del empleado: ");
        double sueldo = Double.parseDouble(sc.nextLine());

        System.out.print("Año de nacimiento del empleado (dd-MM-yy): ");
        Date añoNacimiento;
        try {
            añoNacimiento = dateFormat.parse(sc.nextLine());
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha de nacimiento: " + e.getMessage());
            return null;
        }

        System.out.print("Antigüedad del empleado (dd-MM-yy): ");
        Date antiguedad;
        try {
            antiguedad = dateFormat.parse(sc.nextLine());
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha de antigüedad: " + e.getMessage());
            return null;
        }

        return new Empleado(nombre, sueldo, añoNacimiento, antiguedad);
    }

    public static void guardarEmpleadoEnArchivo(Empleado empleado, Path p) {
        if (empleado == null) {
            System.err.println("No se pudo guardar el empleado debido a errores en los datos.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(p.toFile(), true))) {
            writer.write(empleado.getNombre() + "," +
                    empleado.getSueldo() + "," +
                    dateFormat.format(empleado.getAñoNacimiento()) + "," +
                    dateFormat.format(empleado.getAntiguedad()) + "\n");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }
}
