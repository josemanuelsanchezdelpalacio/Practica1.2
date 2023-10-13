package code;

import javaBeans.Departamento;
import javaBeans.Departamentos;
import javaBeans.Empleado;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AsignarEmpleados {

    public static void asignar() {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Departamento> departamentos = LeerDepartamentos.leer();

        boolean agregarEmpleado = true;
        while (agregarEmpleado) {
            System.out.println("Departamentos disponibles:");
            for (Departamento departamento : departamentos) {
                System.out.println("ID: " + departamento.getId() + ", Nombre: " + departamento.getNombre());
            }

            System.out.print("Seleccione el ID del departamento al que pertenece el empleado: ");
            int idDepartamento = Integer.parseInt(scanner.nextLine());

            Departamento departamentoSeleccionado = null;
            for (Departamento departamento : departamentos) {
                if (departamento.getId() != null && departamento.getId() == idDepartamento) {
                    departamentoSeleccionado = departamento;
                    break;
                }
            }

            if (departamentoSeleccionado != null) {
                Empleado empleado = EscribirCSV.obtenerDatosEmpleado();
                empleado.setIdDepartamento(idDepartamento);
                departamentoSeleccionado.agregarEmpleado(empleado);
                System.out.println("Empleado asignado al departamento: " + departamentoSeleccionado.getNombre());
            } else {
                System.out.println("No se encontró un departamento con el ID proporcionado.");
            }

            System.out.print("¿Desea agregar otro empleado? (Sí/No): ");
            String respuesta = scanner.nextLine().toLowerCase();
            agregarEmpleado = respuesta.equals("s") || respuesta.equals("si");
        }

        scanner.close();
    }
}