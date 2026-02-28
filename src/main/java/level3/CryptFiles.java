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
        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(new File(encodedName));
        outputStream.write(iv);
        byte[] buffer = new byte[64];
        int bytesRead;
        while((bytesRead = inputStream.read(buffer))!=-1){
            byte[] output = cipher.update(buffer,0,bytesRead);
            if(output!=null)
            {
                outputStream.write(output);
            }
        }
        byte[] outputBytes = cipher.doFinal();
        if(outputBytes!=null){
            outputStream.write(outputBytes);
        }
        inputStream.close();
        outputStream.close();

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
        FileInputStream in = new FileInputStream((file));
        FileOutputStream out = new FileOutputStream(new File(originalName));
        byte[] ibuf = new byte[64];
        int len;
        byte[] iv = new byte[16];
        if(in.read(iv) !=16)
        {
            throw new IllegalStateException("No es pot llegir el iv del fitxer.");
        }
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE,secretKey,ivSpec);

        while((len = in.read(ibuf))!=-1){
            byte[] obuf = cipher.update(ibuf,0,len);
            if(obuf!=null)
            {
                out.write(obuf);
            }
        }
        byte[] obuf = cipher.doFinal();
        if(obuf!=null)
        {
            out.write(obuf);
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
        int cont=path.length()-1;
        boolean exit = false;
        boolean found = false;
        String newPath="error";
        while(!exit)
        {
            if(cont>0)
            {
                if(path.charAt(cont)==File.separator.charAt(0))
                {
                    found = true;
                    exit = true;
                }
                else
                {
                    cont--;
                }
            }
            else
            {
                exit = true;
            }
        }
        if(found)
        {
            newPath = path.substring(0,cont+1) + path.substring(cont+2,path.length());
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
        int cont=path.length()-1;
        boolean exit = false;
        boolean found = false;
        String newPath="error";
        while(!exit)
        {
            if(cont>0)
            {
                if(path.charAt(cont)==File.separator.charAt(0))
                {
                    found = true;
                    exit = true;
                }
                else
                {
                    cont--;
                }
            }
            else
            {
                exit = true;
            }
        }
        if(found)
        {
            newPath = path.substring(0,cont+1) + "_" + path.substring(cont+1,path.length());
        }
        return newPath;
    }
}
