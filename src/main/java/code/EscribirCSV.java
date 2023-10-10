package code;

import javaBeans.Empleado;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EscribirCSV {

    public static void pedirEmpleados(Path p) {

        Path filePath = Path.of("target/empleados.csv");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String respuesta;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(p.toFile(), true))) {

            Scanner sc = new Scanner(System.in);
            do {
                System.out.print("Nombre del empleado: ");
                String nombre = sc.nextLine();

                System.out.print("Sueldo del empleado: ");
                double sueldo = Double.parseDouble(sc.nextLine());

                System.out.print("Año de nacimiento del empleado: ");
                Date añoNacimiento = dateFormat.parse(sc.nextLine());

                System.out.print("Antigüedad del empleado: ");
                Date antiguedad = dateFormat.parse(sc.nextLine());

                Empleado empleado = new Empleado(nombre, sueldo, añoNacimiento, antiguedad);

                // Escribir los datos del empleado en el archivo CSV
                writer.write(empleado.getNombre() + "," +
                        empleado.getSueldo() + "," +
                        dateFormat.format(empleado.getAñoNacimiento()) + "," +
                        empleado.getAntiguedad() + "\n");

                System.out.print("¿Quieres añadir otro empleado? (Si/No): ");
                respuesta = sc.nextLine().toLowerCase();
            } while (respuesta.equals("si"));

            System.out.println("Datos de empleados guardados en empleados.csv.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo CSV: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Error al parsear una de las fechas: " + e.getMessage());
        }
    }
}
