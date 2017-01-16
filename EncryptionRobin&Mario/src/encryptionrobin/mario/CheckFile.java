package encryptionrobin.mario;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;

public class CheckFile {

    public static void main(String[] args) {
        boolean fileIsValid = false;
        try {
            String name = "";

            while (name.isEmpty()) {
                System.out.println("Naam van de persoon?");
                name = new BufferedReader(new InputStreamReader(System.in)).readLine();
            }
            
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(String.format("INPUT(SignedBy%s)", name.replace(" ", ""))));
            int singnatureSize = objectInputStream.readInt();
            byte[] signatureBytes = (byte[]) objectInputStream.readObject();

            String text = (String) objectInputStream.readObject();

            Signature signature = Signature.getInstance("SHA1withRSA");
            signature.initVerify(PublicKeyGen.getPublicKey());
            fileIsValid = signature.verify(signatureBytes);

        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            //TODO
        }

        if (fileIsValid) {
            System.out.println("Perfect de file mag gelezen worden");
        } else {
            System.out.println("Niejt goed, verkeerd bezig mannetje");
        }
    }
}
