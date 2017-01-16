package encryptionrobin.mario;

import java.io.BufferedReader;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mastermouse
 */
public class ReadFileAndSign {

    public static void main(String[] args) {
        createFile();

        System.out.println("Choose your file [INPUT.ext]");

        String inputText = getFile();
        
        try {
            PrivateKey privateKey = PublicKeyGen.getPrivateKey();
            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initSign(privateKey);
            byte[] signatureBytes = signature.sign();
            String name = "";

            while (name.isEmpty()) {
                System.out.println("Wat is de naam?");
                name = new BufferedReader(new InputStreamReader(System.in)).readLine();
            }
            File signatureFile = new File(String.format("INPUT(SignedBy%s)", name.replace(" ", "")));
            signatureFile.createNewFile();

            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(signatureFile));
            os.writeInt(signatureBytes.length);
            os.writeObject(signatureBytes);
            os.writeObject(inputText);
            os.close();
            

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ReadFileAndSign.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(ReadFileAndSign.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadFileAndSign.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(ReadFileAndSign.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println();
    }

    public static String getFile() {
        String input = "";
        try {
            input = new String(Files.readAllBytes(Paths.get("INPUT.ext")), UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    private static void createFile() {
        File yourFile = new File("INPUT.ext");
        try {
            yourFile.createNewFile(); // if file already exists will do nothing
            FileOutputStream oFile = new FileOutputStream(yourFile, false);
            System.out.println("File INPUT aangemaakt.");
        } catch (IOException ex) {
            Logger.getLogger(ReadFileAndSign.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
