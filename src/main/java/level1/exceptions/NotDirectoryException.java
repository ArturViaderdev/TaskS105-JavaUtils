package level1.exceptions;

public class NotDirectoryException extends Exception{
    public NotDirectoryException()
    {
        super("El directori no existeix o no és un directori.");
    }
}
