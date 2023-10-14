import code.*;
import libs.Leer;

public class Main {
    public static void main(String[] args) {
        boolean salir = false;
        int opcion;
        do {
            System.out.println("0. Salir");
            System.out.println("1. Guardar empleados en CSV");
            System.out.println("2. Leer XML departamento");
            System.out.println("3. Asignar empleado a departamento");
            System.out.println("4. Cargar empleados JSON");
            System.out.println("5. Guardar datos empresa en XML y JSON");

            opcion = Leer.pedirEntero("Introduce una opción: ");

            switch (opcion) {
                case 0 -> {salir = true;}
                case 1 -> {EscribirCSV.pedirEmpleados();}
                case 2 -> {LeerDepartamentos.leer();}
                case 3 -> {AsignarEmpleados.asignar();}
                case 4 -> {CargarEmpleadosJSON.cargar();}
                case 5 -> {GenerarXMLyJSON.generarArchivoXML(); GenerarXMLyJSON.generarArchivoJSON();}
                default -> {System.out.println("La opcion seleccionada no existe");}
            }
        } while (!salir);
    }
}