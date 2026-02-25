package Level2;

import Level1.Example;
import Level1.Exceptions.NotDirectoryException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

public class DirectoryExploration {
    private String fileoutput;
    private boolean first;

    public DirectoryExploration(String fileoutput)
    {
        this.first = true;
        this.fileoutput = fileoutput;
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
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    description = spacesident + file.getName() + " " + sdf.format(file.lastModified());
                    if(file.isFile())
                    {
                        fulldescription = description + " Arxiu";
                    }
                    else if(file.isDirectory())
                    {
                        fulldescription = description + " Directori";
                    }

                    if(first)
                    {
                        FileWriter fw = new FileWriter(fileoutput,false);
                        first = false;
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(fulldescription);
                        bw.close();
                        fw.close();
                    }
                    else
                    {
                        FileWriter fw = new FileWriter(fileoutput,true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.newLine();
                        bw.write(fulldescription);
                        bw.close();
                        fw.close();
                    }
                    if(file.isDirectory())
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
