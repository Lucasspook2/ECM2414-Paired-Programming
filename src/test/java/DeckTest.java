import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import src.main.java.Deck;

import java.beans.Transient;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class DeckTest {

    private Deck deck;
    

    @BeforeEach
    void setUp() throws IOException {
        FileWriter fw = new FileWriter("test_output.txt");
        deck = new Deck(1, fw);
    }


    @Test
    void testGetName() {
        assertEquals(1, deck.getName());
    }

    @Test 
    void testAddCardAndContents(){
        deck.addCard(new Card(5));
        assertEquals(5, deck.getContents().get(0).getValue());
    }

    @Test 
    void testDiscardCard(){
        deck.addCard(new Card(1));
        deck.addCard(new Card(2));
        deck.addCard(new Card(3));
        deck.discard(new Card(4));
        assertEquals(4, deck.getContents().get(deck.getContents().size()-1).getValue());
    }

    @Test 
    void testDrawCard(){
        deck.addCard(new Card(1));
        assertEquals(1, deck.draw().getValue());
    }


}
