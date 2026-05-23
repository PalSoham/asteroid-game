import org.junit.Test;
import static org.junit.Assert.*;

public class ShipTest {

    @Test
    public void testReduceLives() {
        Ship ship = new Ship();

        // Initial lives should be 3
        assertEquals(3, ship.getLives());

        // Reduce lives once
        ship.reduceLives();
        assertEquals(2, ship.getLives());

        // Reduce lives again
        ship.reduceLives();
        assertEquals(1, ship.getLives());

        // Reduce lives one more time
        ship.reduceLives();
        assertEquals(0, ship.getLives());
    }
}
