import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;

public class MainDescifrarHash {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Introduce uno de los hashes generados en 'rainbow.txt':");
            String hash = sc.nextLine();

            try (BufferedReader bffr = new BufferedReader(new FileReader("passwd/passwd.txt"))) {
                String linea;
                while ((linea = bffr.readLine()) != null) {
                    MessageDigest md = MessageDigest.getInstance("SHA-1");
                    md.update(linea.getBytes());
                    byte[] resumen = md.digest();
                    if (HexFormat.of().formatHex(resumen).equals(hash)) {
                        System.out.println("La contraseña del hash " + hash + " es " + linea);
                        break;
                    }
                }
            } catch (IOException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
