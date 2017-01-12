package encryptionrobin.mario;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author Mastermouse
 */
public class PublicKeyGen {

    private static SecureRandom secureRandom;
    private static KeyPairGenerator keyPairGenerator;

    public static void main(String[] args) {
        System.out.println("Bliep bloop het aanmaken van de keys");
        secureRandom = new SecureRandom();

        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024, secureRandom);
            
        } catch (NoSuchAlgorithmException e) {
            //TODO
        }
        
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        try {
            new ObjectOutputStream(new FileOutputStream("publicKey_rsa.pub")).writeObject(keyPair.getPublic());
            new ObjectOutputStream(new FileOutputStream("private_rsa")).writeObject(keyPair.getPrivate());
        } catch (IOException e) {
            //TODO
        }

        System.out.println("Public Key:" + (keyPair.getPublic()));
        System.out.println("Private Key:" + (keyPair.getPrivate()));
        System.out.println("Keys zijn aangemaakt");

    }
}
