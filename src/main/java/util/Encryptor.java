package util;

import exception.EncryptorException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {


    public String hashWithSHA256(String data) throws EncryptorException {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptorException("impossible to use SHA-256");
        }
        byte[] hashBytes = digest.digest(data.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public Boolean checkPassword(String password, String passwordUser) throws EncryptorException {
        return password.equals(hashWithSHA256(passwordUser));
    }


}
