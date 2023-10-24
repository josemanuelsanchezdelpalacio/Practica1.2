package code;

import javaBeans.Empleado;
import libs.Leer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Date;

import static libs.FicheroEscribible.ficheroEscribible;
import static libs.Leer.*;

/**Pida al usuario los datos de una serie de empleados y que los guarde en un fichero empleados.csv.
 * El usuario introducirá datos de empleados hasta que indique que no quiere añadir ninguno más.
 * Los empleados se representarán en el código como objetos de una clase Empleado que deberá contener
 * nombre, sueldo, año de nacimiento y antigüedad.**/

public class EscribirCSV {

    public static ArrayList<Empleado> listaEmpleados = new ArrayList<>();

    //metodo para añadir los empleados al CSV y al arrayList "listaEmpleados"
    public static void pedirEmpleados() {
        String entrada = "";
        Empleado empAux;

        String nombre;
        Double sueldo;
        Date añoNacimiento, antiguedad;

        do {
            empAux = new Empleado();

            nombre = pedirCadena("Introduce el nombre del nuevo empleado: ");
            empAux.setNombre(nombre);

            sueldo = pedirDouble("Introduce el sueldo: ");
            empAux.setSueldo(sueldo);

            añoNacimiento = pedirFecha("Introduce el año de nacimiento (yyyy): ", "yyyy");
            empAux.setAñoNacimiento(añoNacimiento);

            antiguedad = pedirFecha("Introduce la fecha en que comenzó a trabajar en la empresa (dd-MM-yyyy): ", "dd-MM-yyyy");
            empAux.setAntiguedad(antiguedad);

            listaEmpleados.add(empAux);
            entrada = Leer.pedirCadena("¿Quieres añadir otro empleado? (si/no): ").toLowerCase();
        } while (entrada.equals("si"));
        guardarEmpleadoEnArchivo(listaEmpleados);
        System.out.println("Datos de los empleados añadidos al .csv");
    }

    //metodo para guardar los datos del empleado en el CSV
    private static void guardarEmpleadoEnArchivo(ArrayList<Empleado> empleados) {
        Path p = Path.of("target/empleados.csv");

        String linea = "";
        StringBuilder textoCSV = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); // Formato de fecha

        for (Empleado e : empleados) {
            String fechaNacimiento = dateFormat.format(e.getAñoNacimiento());
            String fechaAntiguedad = dateFormat.format(e.getAntiguedad());

            linea = e.getNombre() + "," + e.getSueldo() + "," + fechaNacimiento + "," + fechaAntiguedad;
            textoCSV.append(linea).append('\n');
        }

        if (ficheroEscribible(p)) {
            try (FileWriter writer = new FileWriter(p.toString(), true); BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                bufferedWriter.write(textoCSV.toString());
            } catch (IOException e) {
                System.out.println("Ha habido un error durante la escritura");
            }
        } else {
            System.out.println("El fichero no se puede crear");
        }
    }
}
