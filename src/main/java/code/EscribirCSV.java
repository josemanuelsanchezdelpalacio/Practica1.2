package code;

import javaBeans.Empleado;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Date;
import java.util.Scanner;

public class EscribirCSV {

    private static Scanner sc = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
    public static ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    public static void pedirEmpleados() {
        String respuesta;
        do {
            Empleado empleado = obtenerDatosEmpleado();
            if (empleado != null) {
                guardarEmpleadoEnArchivo(empleado, Path.of("target/empleados.csv"));
                listaEmpleados.add(empleado);
            } else {
                System.out.println("Ninguno de los datos pueden estar vacíos.");
            }
            System.out.print("¿Quieres añadir otro empleado? (si/no): ");
            respuesta = sc.nextLine().toLowerCase();
        } while (respuesta.equals("si"));

        System.out.println("Datos de empleados guardados en empleados.csv");
    }

    public static Empleado obtenerDatosEmpleado() {
        System.out.print("Nombre del empleado: ");
        String nombre = sc.nextLine();

        System.out.print("Sueldo del empleado: ");
        double sueldo;
        try {
            sueldo = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Sueldo debe ser un número válido.");
            return null;
        }

        System.out.print("Año de nacimiento del empleado (yyyy): ");
        Date añoNacimiento;
        try {
            añoNacimiento = dateFormat.parse(sc.nextLine());
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha de nacimiento: " + e.getMessage());
            return null;
        }

        System.out.print("Antigüedad del empleado (yyyy): ");
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
