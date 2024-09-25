package actividad1;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class actividad1
{
    public static void main(String[] args)
    {
        leerDatosyAnadirUsuario();
    }

    public static void leerDatosyAnadirUsuario()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombres y correos electrónicos actuales:\n");
        // Lectura del archivo para mostrar los datos actuales
        try (BufferedReader br = new BufferedReader(new FileReader("nombres.txt.txt"))) {
            String linea;
            int numAlumno = 1;

            while ((linea = br.readLine()) != null) {
                String[] texto = linea.split(";");
                System.out.println("Alumno " + numAlumno++);
                System.out.println("Nombre: " + texto[0].toLowerCase() + " | Correo: " + texto[1].toLowerCase() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        // Solicitar nuevo nombre y correo en el formato "Nombre;Correo"
        System.out.println("Introduce el nombre y el correo en el formato: Nombre;Correo");

        String entrada = sc.nextLine();

        // Dividir la entrada en nombre y correo, y convertir a minúsculas

        String[] datos = entrada.split(";");

        if (datos.length == 2) {
            String nombre = datos[0].toLowerCase().trim();
            String correo = datos[1].toLowerCase().trim();

            try (BufferedWriter wr = new BufferedWriter(new FileWriter("nombres.txt.txt", true))) {
                wr.newLine();
                wr.write(nombre + ";" + correo);
                System.out.println("\nEl nuevo usuario ha sido añadido correctamente.");
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        } else {
            System.out.println("Formato incorrecto. Asegúrate de escribir el nombre y el correo separados por un punto y coma.");
        }
        sc.close();
    }
}