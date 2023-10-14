package code;

import javaBeans.Departamento;
import javaBeans.Empleado;

import java.util.Scanner;

import static code.EscribirCSV.listaEmpleados;
import static code.LeerDepartamentos.departamentos;

public class AsignarEmpleados {

    private static Scanner scanner = new Scanner(System.in);

    public static void asignar() {
        System.out.print("Escribe el nombre del empleado al que quieres asignar un departamento: ");
        String nombreEmpleado = scanner.nextLine();

        boolean empleadoEncontrado = false;
        for (Empleado empleado : EscribirCSV.listaEmpleados) {
            if (empleado.getNombre().equalsIgnoreCase(nombreEmpleado)) {
                System.out.println("Asignar departamento para el empleado: " + empleado.getNombre());

                mostrarDepartamentos();
                System.out.print("Elige un departamento por su ID: ");
                int id;
                try {
                    id = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println("ID debe ser un número válido.");
                    return;
                }

                boolean departamentoEncontrado = false;
                for (Departamento departamento : LeerDepartamentos.departamentos) {
                    if (departamento.getId() == id) {
                        departamento.agregarEmpleado(empleado);
                        departamentoEncontrado = true;
                        empleadoEncontrado = true;
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

    private static void mostrarDepartamentos() {
        System.out.println("Departamentos disponibles:");
        for (Departamento departamento : LeerDepartamentos.departamentos) {
            System.out.println(departamento.getId() + " - " + departamento.getNombre());
        }
    }
}
