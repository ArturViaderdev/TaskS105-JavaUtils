package level1;

import level1.exceptions.NotDirectoryException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;

public class DirectoryExploration {
    private final String fileOutput = "sortida.txt";
    private final String fileSerialize = "objecte.ser";
    private boolean writeFile;
    private boolean first;

    public DirectoryExploration()
    {
        this.writeFile = false;
        this.first = true;
    }

    public void setWriteFile() {
        writeFile = true;
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
                    if(writeFile)
                    {
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
                    }
                    else
                    {
                        System.out.println(fullDescription);
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
                File[] orderedFiles = orderFilesByName(files);
                for(File file:orderedFiles)
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

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileSerialize));
        os.writeObject(example);
    }

    public Example deserializeObject() throws IOException, ClassNotFoundException {
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(fileSerialize));
        Example example = (Example) oi.readObject();
        return example;
    }
}
