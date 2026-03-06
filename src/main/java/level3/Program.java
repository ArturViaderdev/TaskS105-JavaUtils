package level3;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;

public class Program {
    private String directory;
    public Program(String directory)
    {
        this.directory=directory;
    }

    private void di() {
    }

    /**
     * Encode directory files
     */
    public void encode()
    {
        try {
            CryptFiles cryptFiles = new CryptFiles();
            cryptFiles.generateAESKey();
            String skey = cryptFiles.getKey();
            System.out.println("Clau de encriptació: " + skey);
            DirectoryExploration directoryExploration = new DirectoryExploration(true,cryptFiles);
            directoryExploration.ListRecursive(directory,0);
            System.out.println("Operació completada.");
        } catch (NotDirectoryException | IOException | InvalidAlgorithmParameterException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);

        }
    }

    /**
     * Decode directory files
     * @param key
     */
    public void decode(String key)
    {
        try {
            CryptFiles cryptFiles = new CryptFiles();
            cryptFiles.setKey(key);
            DirectoryExploration directoryExploration = new DirectoryExploration(false, cryptFiles);
            directoryExploration.ListRecursive(directory, 0);
            System.out.println("Operació completada.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
