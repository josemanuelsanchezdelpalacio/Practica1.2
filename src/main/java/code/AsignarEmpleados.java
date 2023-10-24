package code;

import javaBeans.Departamento;
import javaBeans.Empleado;
import libs.Leer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static code.EscribirCSV.listaEmpleados;

/**Asigne todos los empleados creados a un departamento preguntando al usuario a qué departamento
 * pertenece y dándole a elegir entre los nombres de departamentos cargados.**/

public class AsignarEmpleados {

    public static void asignar() {
        // Pido el nombre del empleado y compruebo que exista en el CSV
        String nombreEmpleado = Leer.pedirCadena("Escribe el nombre del empleado al que quieres asignar un departamento: ");
        Empleado empleadoEncontrado = buscarEmpleadoPorNombre(nombreEmpleado);

        if (empleadoEncontrado == null) {
            System.err.println("Empleado no encontrado en el CSV");
            return;
        }

        // Si el empleado fue encontrado en el CSV, muestro los departamentos y pido el ID del departamento
        System.out.println("Asignar departamento para el empleado: " + nombreEmpleado);
        LeerDepartamentos.leer();

        System.out.print("Elige un departamento por su ID: ");
        int id;
        try {
            //pido el numero de ID y lo asigno al empleado
            id = Leer.pedirEntero("Introduce el ID de uno de los departamentos: ");
            empleadoEncontrado.setIdDepartamento(id);
        } catch (NumberFormatException e) {
            System.err.println("El ID debe ser valido.");
            return;
        }

        //busco el departamento para asignarselo al empleado
        Departamento departamentoEncontrado = null;

        for (Departamento departamento : LeerDepartamentos.departamentos) {
            if (departamento.getId() == id) {
                //asigno el departamento al empleado
                departamento.agregarEmpleado(empleadoEncontrado);
                departamentoEncontrado = departamento;
                System.out.println("Departamento agregado al empleado correctamente");
                break;
            }
        }
        if (departamentoEncontrado == null) {
            System.err.println("No se ha encontrado el departamento con el ID especificado.");
            return;
        }

        //agrego el empleado de nuevo a la lista de empleados
        listaEmpleados.add(empleadoEncontrado);
    }

    //metodo para comprobar si existe el empleado dentro del CSV
    public static Empleado buscarEmpleadoPorNombre(String nombreEmpleado) {
        try (BufferedReader bufferLectura = new BufferedReader(new FileReader("target/empleados.csv"))) {
            String linea;
            while ((linea = bufferLectura.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length >= 1 && campos[0].equalsIgnoreCase(nombreEmpleado)) {
                    double sueldo = Double.parseDouble(campos[1]);
                    Date añoNacimiento = new SimpleDateFormat("yyyy").parse(campos[2]);
                    Date antiguedad = new SimpleDateFormat("dd-MM-yyyy").parse(campos[3]);
                    return new Empleado(nombreEmpleado, sueldo, añoNacimiento, antiguedad);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        } catch (ParseException e) {
            System.err.println("Error al parsear una fecha: " + e.getMessage());
        }
        return null;
    }
}