package libs;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FicheroEscribible {

    public static boolean ficheroEscribible(Path p){
        boolean ficheroOK = false;
        if (Files.exists(p)) {
            if (p.toString().equals(".xml") || p.toString().equals(".csv") || p.toString().equals(".json") || p.toString().equals(".txt")) {
                ficheroOK = true;
            } else {
                System.out.println("No es un archivo XML, CSV, JSON o TXT");
            }
        } else {
            try {
                Files.createFile(p);
                ficheroOK = true;
                System.out.println("Archivo creado en la ruta: " + p);
            } catch (FileAlreadyExistsException e) {
                System.err.println("El archivo ya existe en la ruta: " + p);
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
            } catch (SecurityException e) {
                System.err.println("No tiene permiso para crear el archivo: " + e.getMessage());
            }
        }
        return ficheroOK;
    }
}