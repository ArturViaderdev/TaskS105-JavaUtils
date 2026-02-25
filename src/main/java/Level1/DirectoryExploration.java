package Level1;

import Level1.Exceptions.NotDirectoryException;

import java.io.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

public class DirectoryExploration {
    private final String fileoutput = "sortida.txt";
    private final String fileserialize = "objecte.ser";
    private boolean writefile;
    private boolean first;

    public DirectoryExploration()
    {
        this.writefile = false;
        this.first = true;
    }

    public void setWritefile() {
        writefile = true;
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
                    if(writefile)
                    {
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
                    }
                    else
                    {
                        System.out.println(fulldescription);
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

    public void showFile(String path) throws Exception {
        File file= new File(path);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String readed;
        do {
            readed=br.readLine();
            if(readed!=null)
            {
                System.out.println(readed);
            }
        }while(readed != null);

        br.close();
        reader.close();
    }

    public void serializeObject(Example example) throws IOException {

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileserialize));
        os.writeObject(example);
    }

    public Example deserializeobject() throws IOException, ClassNotFoundException {
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(fileserialize));
        Example example = (Example) oi.readObject();
        return example;
    }
}
