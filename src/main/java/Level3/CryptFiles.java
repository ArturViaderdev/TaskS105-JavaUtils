package Level3;

import javax.crypto.*;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CryptFiles {
    private SecretKey secretKey;

    public CryptFiles() throws NoSuchAlgorithmException {

    }

    public void setKey(String skey)
    {
        byte[] decodedKey = Base64.getDecoder().decode(skey);
        secretKey = new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");
    }

    public String getKey()
    {
        String skey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        return skey;
    }

    public void generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        keygen.init(256);
        SecretKey secretKey = keygen.generateKey();
    }

    public void encrypt(File file) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        String encodedname = addsymbol(file.getPath());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(new File(encodedname));
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

    public void decrypt(File file) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String originalname = removesymbol(file.getPath());
        FileInputStream in = new FileInputStream((file));
        FileOutputStream out = new FileOutputStream(new File(originalname));
        byte[] ibuf = new byte[64];
        int len;
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);

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

    private String removesymbol(String path)
    {
        int cont=path.length()-1;
        boolean exit = false;
        boolean found = false;
        String newpath="error";
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
            newpath = path.substring(0,cont+1) + path.substring(cont+2,path.length());
        }
        return newpath;
    }

    private String addsymbol(String path)
    {
        int cont=path.length()-1;
        boolean exit = false;
        boolean found = false;
        String newpath="error";
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
            newpath = path.substring(0,cont+1) + "_" + path.substring(cont+1,path.length());
        }
        return newpath;
    }
}
