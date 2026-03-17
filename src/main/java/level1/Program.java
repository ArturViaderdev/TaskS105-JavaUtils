package level1;

import java.io.IOException;
import java.util.InputMismatchException;

public class Program {
    ConsoleUI consoleui;
    public Program()
    {
        consoleui = new ConsoleUI();
    }

    public void start()
    {
        int option=-1;
        do {
            try
            {
                option = consoleui.showMenu();

                executeOption(option);

            }
            catch(InputMismatchException ex)
            {
                consoleui.consumeNextLine();
                consoleui.showIncorrectOption();
            }
        }while(option!=0);
        consoleui.goodbyeMessage();
    }

    private void scanDirectory(DirectoryExploration directoryExploration)
    {
        consoleui.showMessage("Introdueix la ruta de un directori.");
        consoleui.consumeNextLine();
        String path = consoleui.getString();
        try
        {
            directoryExploration.unsetWriteFile();
            directoryExploration.listDirectory(path);
        }
        catch(NotDirectoryException ex)
        {
            consoleui.showMessage(ex.getMessage());
        }
    }

    private void scanDirectoryRecursive(DirectoryExploration directoryExploration)
    {
        consoleui.showMessage("Introdueix la ruta de un directori.");
        consoleui.consumeNextLine();
        String path = consoleui.getString();
        try
        {
            directoryExploration.unsetWriteFile();
            directoryExploration.listRecursive(path,0);
        }
        catch(NotDirectoryException | IOException ex)
        {
            consoleui.showMessage(ex.getMessage());
        }
    }

    private void scanDirectoryRecursiveAndWriteToFile(DirectoryExploration directoryExploration)
    {
        consoleui.showMessage("Introdueix la ruta de un directori.");
        consoleui.consumeNextLine();
        String path = consoleui.getString();
        try
        {
            directoryExploration.setWriteFile();
            directoryExploration.listRecursive(path,0);
            consoleui.showMessage("Procés completat.");
        }
        catch(NotDirectoryException | IOException ex)
        {
            consoleui.showMessage(ex.getMessage());
        }
    }

    private void showFileInTheConsole(DirectoryExploration directoryExploration)
    {
        consoleui.showMessage("Introdueix la ruta d'un fitxer.");
        consoleui.consumeNextLine();
        String path = consoleui.getString();
        try
        {
            directoryExploration.showFile(path);
        } catch(IOException e){
            consoleui.showMessage(e.getMessage());
        }
    }

    private void serializeAndDeserializeObject(DirectoryExploration directoryExploration)
    {
        String name = "Artur";
        int age = 43;
        Example example = new Example(name,age);
        consoleui.showMessage("Serialitzant objecte example");
        consoleui.showExample(example);
        try {
            directoryExploration.serializeObject(example);
            Example exampleb = directoryExploration.deserializeObject();
            consoleui.showMessage("Objecte deserialitzat des de arxiu.");
            consoleui.showExample(exampleb);
        } catch (IOException | ClassNotFoundException e) {
            consoleui.showMessage("Error d'entrada sortida.");
        }
    }

    private void executeOption(int option)
    {
        DirectoryExploration directoryExploration = new DirectoryExploration();
        switch(option)
        {
            case 1:
                scanDirectory(directoryExploration);
                break;
            case 2:
                scanDirectoryRecursive(directoryExploration);
                break;
            case 3:
                scanDirectoryRecursiveAndWriteToFile(directoryExploration);
                break;
            case 4:
                showFileInTheConsole(directoryExploration);
                break;
            case 5:
               serializeAndDeserializeObject(directoryExploration);
                break;
            default:
                consoleui.showIncorrectOption();
        }
    }
}
