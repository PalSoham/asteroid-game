import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VelocityVectorTest {

    private static final double DELTA = 1e-6; // Precision for float comparisons

    @Test
    public void testGetMagnitudePositiveComponents() {
        VelocityVector v = new VelocityVector(0, 0);
        v.setX(3.0);
        v.setY(4.0);
        assertEquals("Magnitude with positive components should be 5", 5.0, v.getMagnitude(), DELTA);
    }

    @Test
    public void testGetMagnitudeNegativeComponents() {
        VelocityVector v = new VelocityVector(0, 0);
        v.setX(-3.0);
        v.setY(-4.0);
        assertEquals("Magnitude with negative components should be 5", 5.0, v.getMagnitude(), DELTA);
    }

    @Test
    public void testGetMagnitudeZero() {
        VelocityVector v = new VelocityVector(0, 0);
        v.setX(0.0);
        v.setY(0.0);
        assertEquals("Magnitude of zero vector should be 0", 0.0, v.getMagnitude(), DELTA);
    }

    @Test
    public void testGetMagnitudeOneZeroComponent() {
        VelocityVector v1 = new VelocityVector(0, 0);
        v1.setX(0.0);
        v1.setY(5.0);
        assertEquals("Magnitude with zero X should be absolute value of Y", 5.0, v1.getMagnitude(), DELTA);

        VelocityVector v2 = new VelocityVector(0, 0);
        v2.setX(-7.0);
        v2.setY(0.0);
        assertEquals("Magnitude with zero Y should be absolute value of X", 7.0, v2.getMagnitude(), DELTA);
    }
}
