import org.junit.jupiter.api.*;

//import src.main.java.CardGame;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class CardGameTest {

    
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;

    //allows the program to read the outputs from the terminal
    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    //resets the test environment
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        CardGame.playerList.clear();
        CardGame.deckList.clear();
    }

    
    //tests FileSearch()
    @Test
    void testFileSearch_existingFile() throws IOException {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();
        assertTrue(CardGame.FileSearch(tempFile.getAbsolutePath()));
    }

    //tests FileSearch() with an invalid input 
    @Test
    void testFileSearch_nonExistingFile() {
        assertFalse(CardGame.FileSearch("non_existing_file.txt"));
    }

    //tests the methods at the end of the game
    @Test
    void testGameOverAndWinner() {
   
        assertFalse(CardGame.isGameOver());
        CardGame.endGame(1);
        assertTrue(CardGame.isGameOver());
        assertEquals(1, new CardGame().getWinner());
    }


    @Test
    void testMainAndInputs() throws IOException {
        //creates a temporary pack file to test with
        File packFile = File.createTempFile("pack", ".txt");
        //removes file when test is complete
        packFile.deleteOnExit();
        //fills pack with data
        try (PrintWriter temp = new PrintWriter(packFile)) {
            for (int i = 0; i < 40; i++) {
              
                temp.println(i);
                
            }
        }

        
        //string to pass into the command line, navigates through the terminal prompts
        String simulatedInput = "x\n" + "-5\n"+ "5\n" + "invalid.txt\n" + packFile.getAbsolutePath() + "\n" + "y\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        
       //starts the main method
        assertDoesNotThrow(() -> CardGame.main(new String[]{}));

        //reads the outputs and sees if they're what is expected 
        String output = outContent.toString();
        assertTrue(output.contains("Please enter the number of players"));
        assertTrue(output.contains("Invalid input! Wrong number format"));
        assertTrue(output.contains("Invalid input! Illegal number of players"));
        assertTrue(output.contains("Invalid input! Pack not found"));
        assertTrue(output.contains("file chosen = " + packFile.getAbsolutePath()));
        assertTrue(output.contains("Playing with 5 players"));
        
    }

    
}

    

