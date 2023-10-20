package code;

import javaBeans.Departamento;
import javaBeans.Empleado;
import libs.Leer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**Asigne todos los empleados creados a un departamento preguntando al usuario a qué departamento
 * pertenece y dándole a elegir entre los nombres de departamentos cargados.**/

public class AsignarEmpleados {

    public static void asignar() {
        String nombreEmpleado = Leer.pedirCadena("Escribe el nombre del empleado al que quieres asignar un departamento: ");

        BufferedReader bufferLectura = null;
        try {
            // Abrir el .csv en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("target/empleados.csv"));

            // Leer una linea del archivo
            String linea = bufferLectura.readLine();

            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] campos = linea.split(",");

                System.out.println(Arrays.toString(campos));

                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //si el empleado fue encontrado en el CSV muestro los departamentos y pido el ID del departamento
        System.out.println("Asignar departamento para el empleado: " + nombreEmpleado);
        LeerDepartamentos.leer();

        System.out.print("Elige un departamento por su ID: ");
        int id;
        try {
            id = Integer.parseInt(String.valueOf(Leer.pedirEntero("Introduce el ID de uno de los departamentos: ")));
        } catch (NumberFormatException e) {
            System.err.println("El ID debe ser un número valido.");
            return;
        }

        //busco el departamento para asignarselo al empleado
        boolean departamentoEncontrado = false;
        for (Departamento departamento : LeerDepartamentos.departamentos) {
            if (departamento.getId() == id) {
                departamento.agregarEmpleado(new Empleado());
                departamentoEncontrado = true;
                System.out.println("Departamento agregado al empleado correctamente");
                break;
            }
        }
        if (!departamentoEncontrado) {
            System.out.println("No se ha encontrado el departamento con el ID especificado.");
        }
    }
}