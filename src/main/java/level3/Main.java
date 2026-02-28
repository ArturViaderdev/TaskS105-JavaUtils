package level3;

public class Main {
    public static void main(String[] args)
    {
        if(args.length==0)
        {
            System.out.println("Falten paràmetres.");
        }
        else if(args.length==1)
        {
            if(args[0].equals("help") || args[0].equals("-h")||args[0].equals("-help"))
            {
                System.out.println("Instruccions.");
                System.out.println("Paràmetre 1. Pot esser encode o decode.");
                System.out.println("Paràmetre 2. Directori a codificar o descodificar.");
                System.out.println("En el cas de encode es generarà una clau i s'informarà al usuari. Guardar aquesta clau per a poder recuperar els arxius.");
                System.out.println("En el cas de decode hem de introduïr un quart paràmetre.");
                System.out.println("Paràmetre 4. Key. Hem de posar la clau que hem obtingut al fer el encode.");
            }
            else {
                System.out.println("Paràmetres incorrectes");
            }
        }
        else if(args.length==2)
        {
            if(args[0].equals("encode"))
            {
                Program program = new Program(args[1]);
                program.encode();
            }
            else
            {
                System.out.println("Paràmetres incorrectes.");
            }
        }
        else if(args.length==3)
        {
            if(args[0].equals("decode"))
            {
                Program program = new Program(args[1]);
                program.decode(args[2]);
            }
            else
            {
                System.out.println("Paràmetres incorrectes.");
            }
        }
        else
        {
            System.out.println("Paràmetres incorrectes.");
        }
    }
}
