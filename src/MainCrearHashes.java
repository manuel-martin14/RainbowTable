import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class MainCrearHashes {
    public static void main(String[] args) {
        try (BufferedReader bffr = new BufferedReader(new FileReader("passwd/passwd.txt"))) {
            try (BufferedWriter bffw = new BufferedWriter(new FileWriter("rainbow_table/rainbow.txt",true))) {
                String linea;
                while ((linea = bffr.readLine()) != null) {
                    MessageDigest md = MessageDigest.getInstance("SHA-1");
                    md.update(linea.getBytes());
                    byte[] resumen = md.digest();
                    bffw.write(HexFormat.of().formatHex(resumen) + "\n");
                }
                System.out.println("Todo fue cifrado con éxito, compruebe el contenido de 'rainbow.txt'");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}