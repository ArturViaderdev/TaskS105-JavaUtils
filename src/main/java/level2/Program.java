package level2;

import level2.DirectoryExploration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Program {
    public Program()
    {

    }

    public void start()
    {
        Properties properties = new Properties();
        try{
            ClassLoader classLoader = getClass().getClassLoader();
            String path = classLoader.getResource("config.properties").getPath();
            properties.load(new FileInputStream(new File(path)));
            DirectoryExploration directoryExploration = new DirectoryExploration((String) properties.getProperty("Resultat"));
            directoryExploration.listRecursive(properties.getProperty("Directori"),0);
            System.out.println("Operació completada");
        } catch (IOException | NotDirectoryException e) {
            System.out.println(e.getMessage());
        }
    }
}
