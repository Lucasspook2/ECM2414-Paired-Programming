import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

class PlayerTest {

    private Player player;
    private Deck mockDrawDeck;
    private Deck mockDiscardDeck;

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

    @Test
    void testAddCard() {
        Card card = new Card(3);
        player.addCard(card);
        assertEquals(1, player.getHand().size());
        assertEquals(card, player.getHand().get(0));
    }

    @Test
    void testHasWonTrue() {
        player.addCard(new Card(5));
        player.addCard(new Card(5));
        player.addCard(new Card(5));
        player.addCard(new Card(5));

        assertTrue(player.hasWon());
    }

    @Test
    void testHasWonFalse() {
        player.addCard(new Card(5));
        player.addCard(new Card(3));
        player.addCard(new Card(5));
        player.addCard(new Card(5));

        assertFalse(player.hasWon());
    }

    @Test
    void testDiscardCardRemovesNonPreferred() {
        player.addCard(new Card(3)); // non-preferred
        player.addCard(new Card(5));
        player.addCard(new Card(5));
        player.addCard(new Card(5));

        Card discarded = player.discardCard();
        assertEquals(3, discarded.getValue());
        assertEquals(3, player.getHand().size());
    }

    @Test
    void testDiscardCardWhenOnlyPreferred() {
        player.addCard(new Card(5));
        player.addCard(new Card(5));
        player.addCard(new Card(5));
        player.addCard(new Card(5));

        Card discarded = player.discardCard();
        assertEquals(5, discarded.getValue());
        assertEquals(3, player.getHand().size());
    }

    @Test
    void testSetAndGetDecks() {
        assertEquals(mockDrawDeck, player.getDrawDeck());
        assertEquals(mockDiscardDeck, player.getDiscardDeck());
    }
}
