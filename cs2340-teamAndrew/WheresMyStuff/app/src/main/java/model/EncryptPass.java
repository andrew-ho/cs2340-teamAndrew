package model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Andrew on 6/15/2017.
 */

public class EncryptPass {
    public static String  encryptPW(String password) {
        for (int i = 0; i < 10000; i++) {
        }
        return null;
    }

    public static String encryptMessage(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {

        }
        return null;
    }
}
