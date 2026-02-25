package Level1;

import Level1.Exceptions.NotDirectoryException;

import java.io.File;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

public class DirectoryExploration {
    public DirectoryExploration()
    {

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
        if (directory.exists() && directory.isDirectory())
        {
            File[] files = directory.listFiles();
            if(files!=null)
            {
                File[] orderedfiles = orderFilesByName(files);
                String spacesident = spaces(ident);
                for(File file:orderedfiles)
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    description = spacesident + file.getName() + " " + sdf.format(file.lastModified());
                    if(file.isFile())
                    {
                        System.out.println(description);
                    }
                    else if(file.isDirectory())
                    {
                        System.out.println(description + " Directori");
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

    public void ListDirectory(String path) throws Exception {
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory())
        {
            File[] files = directory.listFiles();
            if(files!=null)
            {
                File[] orderedfiles = orderFilesByName(files);
                for(File file:orderedfiles)
                {
                    if(file.isFile())
                    {
                        System.out.println(file.getName());
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
