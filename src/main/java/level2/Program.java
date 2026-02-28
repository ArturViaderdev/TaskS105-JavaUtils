package level2;

import java.io.File;
import java.io.FileInputStream;
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
            directoryExploration.ListRecursive(properties.getProperty("Directori"),0);
            System.out.println("Operació completada");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
