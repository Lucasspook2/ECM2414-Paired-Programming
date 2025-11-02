
//imports

import static org.junit.jupiter.api.Assertions.*;

//allows for creation of mock objects for testing 
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

class PlayerTest {

    //creating objects to test with 
    private Player player;
    private Deck mockDrawDeck;
    private Deck mockDiscardDeck;

    //this done before each test to reset the environment 
    @BeforeEach
    void setUp() throws IOException {
        FileWriter fw = new FileWriter("test_output.txt");
        CyclicBarrier barrier = new CyclicBarrier(2);

        player = new Player(1, 5, barrier, fw);
        mockDrawDeck = mock(Deck.class);
        mockDiscardDeck = mock(Deck.class);

        player.setDrawDeck(mockDrawDeck);
        player.setDiscardDeck(mockDiscardDeck);
    }

    //tests getName()
    @Test 
    void testGetName(){
        assertEquals(1, player.getPlayerName());
    }

    //tests addCard()
    @Test
    void testAddCard() {
        Card card = new Card(3);
        player.addCard(card);
        assertEquals(1, player.getHand().size());
        assertEquals(card, player.getHand().get(0));
    }

    //tests whether a player is declared to have won if they've met the conditions 
    @Test
    void testHasWonTrue() {
        player.addCard(new Card(5));
        player.addCard(new Card(5));
        player.addCard(new Card(5));
        player.addCard(new Card(5));

        assertTrue(player.hasWon());
    }

    //tests the inverse: makes sure a player hasnt won with a non winning hand 
    @Test
    void testHasWonFalse() {
        player.addCard(new Card(5));
        player.addCard(new Card(3));
        player.addCard(new Card(5));
        player.addCard(new Card(5));

        assertFalse(player.hasWon());
    }

    //tests that a player discards the card that is not the preffered value 
    @Test
    void testDiscardCard() {
        player.addCard(new Card(3)); // non-preferred
        player.addCard(new Card(5));
        player.addCard(new Card(5));
        player.addCard(new Card(5));

        Card discarded = player.discardCard();
        assertEquals(3, discarded.getValue());
        assertEquals(3, player.getHand().size());
    }


    //tests  getDrawDeck() and getDiscardDeck()
    @Test
    void testSetAndGetDecks() {
        assertEquals(mockDrawDeck, player.getDrawDeck());
        assertEquals(mockDiscardDeck, player.getDiscardDeck());
    }
}
