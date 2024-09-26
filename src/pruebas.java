import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class pruebas
{
    public static void main(String[] args) throws IOException
    {
        copiarFicheroBinario();
        convertirFicheroABinario();
        validarCorreo();
        buscador();
    }

    public static void copiarFicheroBinario()
    {
        try (FileInputStream fis = new FileInputStream("input.jpg");
             FileOutputStream fos = new FileOutputStream("output.jpg")) // Cambié el nombre a "output.jpg"
        {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1)
            {
                fos.write(buffer, 0, bytesRead);
            }
            System.out.println("El archivo se copió exitosamente.");
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void convertirFicheroABinario() throws IOException
    {
        String fichero = "fichero.txt";
        String ficheroSalida = "salida.bin";

        try (BufferedWriter crearFichero = new BufferedWriter(new FileWriter(fichero))) {
            crearFichero.write("He creado este archivo para pasarlo a binario");
            System.out.println("Se ha creado el archivo correctamente");
        }

        try (FileInputStream fis = new FileInputStream(fichero);
             FileOutputStream fos = new FileOutputStream(ficheroSalida)) {
            int byteDato;
            while ((byteDato = fis.read()) != -1) {
                fos.write(byteDato);
            }
            System.out.println("Se ha escrito el contenido en formato binario en " + ficheroSalida);
        }
    }

    public static void validarCorreo()
    {
        String[] listaCorreos =
                {"miguel@gmail.com",
                 "castro@gmail.com",
                 "andres@gmail.es",
                 "wan@gmail.moc"};

        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|es)$"; // Permitir .com y .es
        Pattern pattern = Pattern.compile(regex);

        for (String email : listaCorreos) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                System.out.println(email + " es un email válido.");
            } else {
                System.out.println(email + " no es un email válido.");
            }
        }
    }

    public static void buscador()
    {
        String nombreArchivo = "nombres.txt"; // Cambia esto por la ruta de tu archivo
        String palabraBuscada = ";";

        String regex = "\\b" + Pattern.quote(palabraBuscada) + "\\b"; // Palabra completa
        Pattern pattern = Pattern.compile(regex);

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo)))
        {
            String linea;
            while ((linea = br.readLine()) != null)
            {
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find())
                {
                    System.out.println(linea); // Imprime la línea si contiene la palabra buscada
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
