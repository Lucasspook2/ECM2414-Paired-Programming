import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

class CardTest {

    private Card card;

    @BeforeEach
    void setUp() throws IOException {
        card = new Card(5);
    }

    @Test
    void testGetValue() {
        assertEquals(5, card.getValue());
    }

    @Test
    void testToString() {
        assertEquals("5", card.toString());
    }

}
