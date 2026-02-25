package Level1;

import java.util.Scanner;

public class ConsoleUI {
    public ConsoleUI()
    {

    }

    public int showMenu()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1-Llistar fitxers de un directori.");
        System.out.println("2-Llistar recursivament arbre de directoris.");
        System.out.println("3-Llistar recursivament arbre de directoris i guarda en txt.");
        System.out.println("0-Sortir.");
        int option = scanner.nextInt();
        return option;

    }
    public void showIncorrectOption()
    {
        System.out.println("Opció incorrecta.");
    }

    public void goodbyeMessage()
    {
        System.out.println("Adeu, fi del programa.");
    }

    public String getString()
    {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showMessage(String message)
    {
        System.out.println(message);
    }
}
