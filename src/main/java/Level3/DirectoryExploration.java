package Level3;

import javax.crypto.SecretKey;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;


public class DirectoryExploration {
    private boolean encrypt;
    private SecretKey secretKey;
    private CryptFiles cryptFiles;
    public DirectoryExploration(boolean encrypt, CryptFiles cryptFiles)
    {
        this.encrypt=encrypt;
        this.cryptFiles = cryptFiles;
    }

    private String spaces(int ident)
    {
        String space = "";
        for(int cont=0;cont<=ident;cont++)
        {
            space = space + " ";
        }
        return space;
    }

    public void ListRecursive(String path,int ident) throws Exception{
        File directory = new File(path);
        String description;
        String fulldescription="";

        if (directory.exists() && directory.isDirectory())
        {
            File[] files = directory.listFiles();
            if(files!=null)
            {
                File[] orderedfiles = orderFilesByName(files);
                String spacesident = spaces(ident);
                for(File file:orderedfiles)
                {

                    if(file.isFile())
                    {
                        if (encrypt) {
                            cryptFiles.encrypt(file);

                        }
                        else
                        {
                            cryptFiles.decrypt(file);
                        }

                    }
                    else if(file.isDirectory())
                    {
                        ListRecursive(file.getPath(),ident+1);
                    }
                }
            }
        }
        else
        {
            throw new NotDirectoryException();
        }
    }

    public File[] orderFilesByName(File[] files)
    {
        Arrays.sort(files, new Comparator<File>(){
            @Override
            public int compare(File t, File t1)
            {
                return t.getName().compareTo(t1.getName());
            }
        });
        return files;
    }
}
