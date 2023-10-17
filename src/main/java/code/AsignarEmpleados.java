package code;

import javaBeans.Departamento;
import javaBeans.Empleado;
import libs.Leer;

/**Asigne todos los empleados creados a un departamento preguntando al usuario a qué departamento
 * pertenece y dándole a elegir entre los nombres de departamentos cargados.**/

public class AsignarEmpleados {

    public static void asignar() {
        String nombreEmpleado = Leer.pedirCadena("Escribe el nombre del empleado al que quieres asignar un departamento: ");

        boolean empleadoEncontrado = false;
        //itero para comprobar que el nombre del empleado existe en el CSV
        for (Empleado empleado : EscribirCSV.listaEmpleados) {
            if (empleado.getNombre().equalsIgnoreCase(nombreEmpleado)) {
                empleadoEncontrado = true;
                break;
            }
        }
        if (!empleadoEncontrado) {
            System.out.println("No existe un empleado con ese nombre");
            return;
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