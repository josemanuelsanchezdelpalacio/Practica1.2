import code.EscribirCSV;
import code.LeerDepartamentos;
import libs.Leer;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        int opcion;
        do {
            System.out.println("0. Salir");
            System.out.println("1. Guardar empleados en CSV");
            System.out.println("2. Leer XML departamento");

            opcion = Leer.pedirEntero("Introduce una opción: ");

            switch (opcion) {
                case 0 -> {salir = true;}
                case 1 -> {EscribirCSV.pedirEmpleados();}
                case 2 -> {LeerDepartamentos.leer();}
                default -> {System.out.println("La opcion seleccionada no existe");}
            }
        } while (!salir);
    }
}