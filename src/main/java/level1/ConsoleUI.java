package level1;

import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    public ConsoleUI()
    {

    }

    public void showExample(Example example)
    {
        System.out.println(example.toString());
    }

    public int showMenu()
    {
        System.out.println("1-Llistar fitxers de un directori.");
        System.out.println("2-Llistar recursivament arbre de directoris.");
        System.out.println("3-Llistar recursivament arbre de directoris i guarda en txt.");
        System.out.println("4-Llegir un fitxer txt.");
        System.out.println("5-Serialitza un objecte a un fitxer i després deserialitza.");
        System.out.println("0-Sortir.");
        return scanner.nextInt();
    }

    public void consumeNextLine()
    {
        scanner.nextLine();
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
