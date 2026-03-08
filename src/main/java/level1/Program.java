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

    private void executeOption(int option)
    {
        DirectoryExploration directoryExploration = new DirectoryExploration();
        String path;
        switch(option)
        {
            case 1:

                consoleui.showMessage("Introdueix la ruta de un directori.");
                consoleui.consumeNextLine();
                path = consoleui.getString();
                try
                {
                    directoryExploration.ListDirectory(path);
                }
                catch(NotDirectoryException ex)
                {
                    consoleui.showMessage(ex.getMessage());
                }
                break;
            case 2:
                consoleui.showMessage("Introdueix la ruta de un directori.");
                consoleui.consumeNextLine();
                path = consoleui.getString();
                try
                {

                    directoryExploration.ListRecursive(path,0);
                }
                catch(NotDirectoryException | IOException ex)
                {
                    consoleui.showMessage(ex.getMessage());
                }
                break;
            case 3:
                consoleui.showMessage("Introdueix la ruta de un directori.");
                consoleui.consumeNextLine();
                path = consoleui.getString();
                try
                {
                    directoryExploration.setWriteFile();
                    directoryExploration.ListRecursive(path,0);
                    consoleui.showMessage("Procés completat.");
                }
                catch(NotDirectoryException | IOException ex)
                {
                    consoleui.showMessage(ex.getMessage());
                }
                break;
            case 4:
                consoleui.showMessage("Introdueix la ruta d'un fitxer.");
                consoleui.consumeNextLine();
                path = consoleui.getString();
                try
                {
                    directoryExploration.showFile(path);
                } catch(IOException e){
                    consoleui.showMessage(e.getMessage());
                }
                break;
            case 5:
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
                break;
            default:
                consoleui.showIncorrectOption();
        }
    }
}
