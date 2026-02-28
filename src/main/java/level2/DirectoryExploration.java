package level2;

import level1.exceptions.NotDirectoryException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

public class DirectoryExploration {
    private String fileOutput;
    private boolean first;

    public DirectoryExploration(String fileOutput)
    {
        this.first = true;
        this.fileOutput = fileOutput;
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
        String fullDescription="";
        if (directory.exists() && directory.isDirectory())
        {
            File[] files = directory.listFiles();
            if(files!=null)
            {
                File[] orderedFiles = orderFilesByName(files);
                String spacesIdent = spaces(ident);
                for(File file:orderedFiles)
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    description = spacesIdent + file.getName() + " " + sdf.format(file.lastModified());
                    if(file.isFile())
                    {
                        fullDescription = description + " Arxiu";
                    }
                    else if(file.isDirectory())
                    {
                        fullDescription = description + " Directori";
                    }

                    if(first)
                    {
                        FileWriter fw = new FileWriter(fileOutput,false);
                        first = false;
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(fullDescription);
                        bw.close();
                        fw.close();
                    }
                    else
                    {
                        FileWriter fw = new FileWriter(fileOutput,true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.newLine();
                        bw.write(fullDescription);
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
