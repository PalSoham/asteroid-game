import org.junit.Test;
import static org.junit.Assert.*;

public class ShipTest {

    @Test
    public void testInitialScore() {
        Ship ship = new Ship();
        assertEquals("Initial score should be 0", 0, ship.getScore());
    }

    @Test
    public void testIncreaseScore() {
        Ship ship = new Ship();
        int initialScore = ship.getScore();

        ship.increaseScore();

        assertEquals("Score should increase by 20 after one call", initialScore + 20, ship.getScore());
    }

    @Test
    public void testMultipleIncreaseScore() {
        Ship ship = new Ship();
        int initialScore = ship.getScore();

        ship.increaseScore();
        ship.increaseScore();
        ship.increaseScore();

        assertEquals("Score should increase by 60 after three calls", initialScore + 60, ship.getScore());
    }
}
