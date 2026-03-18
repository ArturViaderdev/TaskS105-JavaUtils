package level3;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class CryptFiles {
    private SecretKey secretKey;

    public CryptFiles() throws NoSuchAlgorithmException {

    }

    /**
     * Converts a String key to SecretKey to decode
     * @param sKey the key in a string
     */
    public void setKey(String sKey)
    {
        byte[] decodedKey = Base64.getDecoder().decode(sKey);
        secretKey = new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");
    }

    public String getKey()
    {
        String sKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        return sKey;
    }

    /**
     * Generates a random aes key
     * @throws NoSuchAlgorithmException This error never happens because is AES
     */
    public void generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(256);
        secretKey = keygen.generateKey();
    }

    /**
     * Encrypts a file and deletes the original
     * @param file
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public void encrypt(File file) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String encodedName = addSymbol(file.getPath());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // I generate an IV for more security
        byte[] iv = new byte[16];
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE,secretKey,ivSpec);
        try(FileInputStream inputStream = new FileInputStream(file);
            FileOutputStream outputStream = new FileOutputStream(new File(encodedName));) {

            outputStream.write(iv);
            byte[] buffer = new byte[64];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    outputStream.write(output);
                }
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                outputStream.write(outputBytes);
            }
        }
        file.delete();
    }

    /**
     * Decrypts a file and deletes the encrypted file
     * @param file
     * @throws IOException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidAlgorithmParameterException
     */
    public void decrypt(File file) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        String originalName = removeSymbol(file.getPath());
        byte[] ibuf = new byte[64];
        int len;
        byte[] iv = new byte[16];
        try(FileInputStream in = new FileInputStream((file)); FileOutputStream out = new FileOutputStream(new File(originalName));) {
            if (in.read(iv) != 16) {
                throw new IllegalStateException("No es pot llegir el iv del fitxer.");
            }
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            while ((len = in.read(ibuf)) != -1) {
                byte[] obuf = cipher.update(ibuf, 0, len);
                if (obuf != null) {
                    out.write(obuf);
                }
            }
            byte[] obuf = cipher.doFinal();
            if (obuf != null) {
                out.write(obuf);
            }
        }
        file.delete();
    }

    /**
     * Remove the first character of the filename
     * @param path
     * @return
     */
    private String removeSymbol(String path)
    {
        boolean found = false;
        String newPath="error";
        int count;
        for(count = path.length()-1;count>0;count--)
        {
            if(path.charAt(count)==File.separator.charAt(0))
            {
                found = true;
                break;
            }
        }
        if(found)
        {
            newPath = path.substring(0,count+1) + path.substring(count+2,path.length());
        }
        return newPath;
    }

    /**
     * Add a _ character at the start of filename
     * @param path
     * @return
     */
    private String addSymbol(String path)
    {
        int count;
        boolean found = false;
        String newPath="error";
        for(count = path.length()-1;count>0;count--)
        {
            if(path.charAt(count)==File.separator.charAt(0))
            {
                found = true;
                break;
            }
        }
        if(found)
        {
            newPath = path.substring(0,count+1) + "_" + path.substring(count+1,path.length());
        }
        return newPath;
    }
}
