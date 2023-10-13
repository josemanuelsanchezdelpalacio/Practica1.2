package code;

import com.google.gson.*;
import javaBeans.Empleado;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CargarEmpleadosJSON {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

    public static void cargar() {
        ArrayList<Empleado> nuevosEmpleados = new ArrayList<>();
        Path p = Path.of("src/main/resources/nuevosEmpleados.json");  // Update with the correct file path

        try (FileReader reader = new FileReader(p.toFile())) {
            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(reader).getAsJsonArray();

            for (JsonElement jsonElement : jsonArray) {
                JsonObject empleadoJson = jsonElement.getAsJsonObject();
                String nombre = empleadoJson.get("nombre").getAsString();
                double sueldo = empleadoJson.get("sueldo").getAsDouble();
                int año = empleadoJson.get("año").getAsInt();
                int idDep = empleadoJson.get("idDep").getAsInt();

                // Convierte el año de representación de cadena a un objeto Date
                Date fechaAño = dateFormat.parse(String.valueOf(año));

                // Assign the current date as the start date (antigüedad)
                Date currentDate = new Date();

                Empleado empleado = new Empleado(nombre, sueldo, fechaAño, currentDate, idDep);
                nuevosEmpleados.add(empleado);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de nuevos empleados: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Imprime los nuevos empleados cargados
        for (Empleado empleado : nuevosEmpleados) {
            System.out.println(empleado);
        }
    }
}
