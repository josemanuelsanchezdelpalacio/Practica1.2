package code;

import javaBeans.Departamento;
import javaBeans.Empleado;

import java.util.Scanner;

/**Asigne todos los empleados creados a un departamento preguntando al usuario a qué departamento
 * pertenece y dándole a elegir entre los nombres de departamentos cargados.**/

public class AsignarEmpleados {

    private static Scanner scanner = new Scanner(System.in);

    public static void asignar() {
        //pido el nombre del empleado
        System.out.print("Escribe el nombre del empleado al que quieres asignar un departamento: ");
        String nombreEmpleado = scanner.nextLine();

        boolean empleadoEncontrado = false;
        //y compruebo que ese nombre del empleado coincida con el nombre del empleado guardado previamente en el array "listaEmpleados"
        for (Empleado empleado : EscribirCSV.listaEmpleados) {
            if (empleado.getNombre().equalsIgnoreCase(nombreEmpleado)) {
                System.out.println("Asignar departamento para el empleado: " + empleado.getNombre());

                //muestro los departamentos
                mostrarDepartamentos();
                //y pido un ID
                System.out.print("Elige un departamento por su ID: ");
                int id;
                try {
                    id = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println("ID debe ser un número válido.");
                    return;
                }

                //si el ID existe se lo agregamos al empleado
                boolean departamentoEncontrado = false;
                for (Departamento departamento : LeerDepartamentos.departamentos) {
                    if (departamento.getId() == id) {
                        departamento.agregarEmpleado(empleado);
                        departamentoEncontrado = true;
                        empleadoEncontrado = true;
                        System.out.println("Departamento agreado al empleado correctamente");
                        break;
                    }
                }

                if (!departamentoEncontrado) {
                    System.out.println("No se ha encontrado el departamento con el ID especificado.");
                }
            }
        }

        if (!empleadoEncontrado) {
            System.out.println("No se ha encontrado un empleado con el nombre especificado.");
        }
    }

    //metodo para mostrar por pantalla los departamentos y su ID
    private static void mostrarDepartamentos() {
        System.out.println("Departamentos disponibles:");
        for (Departamento departamento : LeerDepartamentos.departamentos) {
            System.out.println(departamento.getId() + " - " + departamento.getNombre());
        }
    }
}