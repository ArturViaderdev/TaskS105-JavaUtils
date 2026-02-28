package level3;
import java.security.NoSuchAlgorithmException;

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
        } catch (Exception e) {
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
