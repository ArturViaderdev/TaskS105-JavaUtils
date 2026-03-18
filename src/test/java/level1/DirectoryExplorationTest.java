package level1;

import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class DirectoryExplorationTest {
    @Test
    public void incorrectDirectory()
    {
        DirectoryExploration directoryExploration = new DirectoryExploration();
        Assertions.assertThrows(NotDirectoryException.class , () -> directoryExploration.listDirectory("abcde9876"));
    }

    @Test
    public void incorrectDirectoryRecursive()
    {
        DirectoryExploration directoryExploration = new DirectoryExploration();
        Assertions.assertThrows(NotDirectoryException.class , () -> directoryExploration.listRecursive("abcde9876",0));
    }

    @Test
    public void showFileIncorrectPath()
    {
        DirectoryExploration directoryExploration = new DirectoryExploration();
        Assertions.assertThrows(IOException.class , () -> directoryExploration.showFile("abcde9876"));
    }
}
