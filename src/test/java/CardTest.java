import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

class CardTest {
    
    //object to test 
    private Card card;

    //creates the card before each test 
    @BeforeEach
    void setUp() throws IOException {
        card = new Card(5);
    }

    //tests getValue()
    @Test
    void testGetValue() {
        assertEquals(5, card.getValue());
    }

    //tests toString()
    @Test
    void testToString() {
        assertEquals("5", card.toString());
    }

}
