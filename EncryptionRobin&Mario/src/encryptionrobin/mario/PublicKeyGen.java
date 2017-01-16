package encryptionrobin.mario;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

/**
 *
 * @author Mastermouse
 * http://www.java2s.com/Tutorial/Java/0490__Security/RSASignatureGeneration.htm
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

    public static PublicKey getPublicKey() {
        PublicKey key = null;

        try {
            key = (PublicKey) new ObjectInputStream(new FileInputStream("publicKey_rsa.pub")).readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return key;
    }

    public static PrivateKey getPrivateKey() {
        PrivateKey key = null;

        try {
            key = (PrivateKey) new ObjectInputStream(new FileInputStream("private_rsa")).readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return key;
    }
}
