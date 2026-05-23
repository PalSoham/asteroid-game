import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ShipTest {

    private static final double DELTA = 1e-6;

    @Test
    public void testThrustPositive() {
        Ship ship = new Ship();

        // Initial velocity should be 0
        assertEquals(0.0, ship.getVelocity().getX(), DELTA);
        assertEquals(0.0, ship.getVelocity().getY(), DELTA);

        double heading = ship.getPosition().getHeading();
        double thrustValue = 5.0;

        ship.thrust(thrustValue);

        // New velocity should be updated based on heading and thrust
        double expectedX = Math.cos(heading) * thrustValue;
        double expectedY = Math.sin(heading) * thrustValue;

        assertEquals(expectedX, ship.getVelocity().getX(), DELTA);
        assertEquals(expectedY, ship.getVelocity().getY(), DELTA);
    }

    @Test
    public void testThrustZero() {
        Ship ship = new Ship();

        double heading = ship.getPosition().getHeading();
        double thrustValue = 0.0;

        ship.thrust(thrustValue);

        assertEquals(0.0, ship.getVelocity().getX(), DELTA);
        assertEquals(0.0, ship.getVelocity().getY(), DELTA);
    }

    @Test
    public void testThrustMultiple() {
        Ship ship = new Ship();

        double heading = ship.getPosition().getHeading();

        ship.thrust(3.0);
        ship.thrust(4.0);

        double expectedX = Math.cos(heading) * 7.0;
        double expectedY = Math.sin(heading) * 7.0;

        assertEquals(expectedX, ship.getVelocity().getX(), DELTA);
        assertEquals(expectedY, ship.getVelocity().getY(), DELTA);
    }

    @Test
    public void testThrustWithDifferentHeading() {
        Ship ship = new Ship();

        // Change heading
        ship.turn(Math.PI / 4.0);
        double heading = ship.getPosition().getHeading();

        ship.thrust(10.0);

        double expectedX = Math.cos(heading) * 10.0;
        double expectedY = Math.sin(heading) * 10.0;

        assertEquals(expectedX, ship.getVelocity().getX(), DELTA);
        assertEquals(expectedY, ship.getVelocity().getY(), DELTA);
    }
}
