import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AsteroidTest {

    private static final double DELTA = 1e-9;

    @Test
    public void testChangeHeading_default() {
        Asteroid asteroid = new Asteroid();
        double initialHeading = asteroid.getPosition().getHeading();

        asteroid.changeHeading();

        double expectedHeading = initialHeading + (Math.PI / 200 % (2 * Math.PI));
        assertEquals(expectedHeading, asteroid.getPosition().getHeading(), DELTA);
    }

    @Test
    public void testChangeHeading_multiple() {
        Asteroid asteroid = new Asteroid();
        double initialHeading = asteroid.getPosition().getHeading();

        int times = 5;
        for (int i = 0; i < times; i++) {
            asteroid.changeHeading();
        }

        double expectedHeading = initialHeading + times * (Math.PI / 200 % (2 * Math.PI));
        assertEquals(expectedHeading, asteroid.getPosition().getHeading(), DELTA);
    }
}