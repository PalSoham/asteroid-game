import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class VelocityVectorTest {

    @Test
    public void testSetMagnitude_ZeroInitialMagnitude() {
        VelocityVector v = new VelocityVector(0.0, 0.0);
        v.setX(0.0);
        v.setY(0.0);
        assertEquals("Initial x should be 0", 0.0, v.getX(), 0.0001);
        assertEquals("Initial y should be 0", 0.0, v.getY(), 0.0001);

        v.setMagnitude(5.0);

        assertEquals("x should be set to new magnitude when old magnitude is 0", 5.0, v.getX(), 0.0001);
        assertEquals("y should remain 0 when old magnitude is 0", 0.0, v.getY(), 0.0001);
        assertEquals("Magnitude should be 5.0", 5.0, v.getMagnitude(), 0.0001);
    }

    @Test
    public void testSetMagnitude_VerySmallInitialMagnitude() {
        VelocityVector v = new VelocityVector(0.0, 0.0);
        v.setX(0.00000005);
        v.setY(0.0);

        assertEquals(0.00000005, v.getX(), 1e-10);
        assertEquals(0.0, v.getY(), 1e-10);

        v.setMagnitude(10.0);

        assertEquals("x should be set to new magnitude", 10.0, v.getX(), 0.0001);
        assertEquals("y should remain unchanged", 0.0, v.getY(), 0.0001);
        assertEquals("Magnitude should be 10.0", 10.0, v.getMagnitude(), 0.0001);
    }

    @Test
    public void testSetMagnitude_NormalInitialMagnitude() {
        VelocityVector v = new VelocityVector(0.0, 0.0);
        v.setX(3.0);
        v.setY(4.0);
        assertEquals("Initial magnitude should be 5.0", 5.0, v.getMagnitude(), 0.0001);

        v.setMagnitude(10.0);

        assertEquals("x should be scaled correctly", 6.0, v.getX(), 0.0001);
        assertEquals("y should be scaled correctly", 8.0, v.getY(), 0.0001);
        assertEquals("New magnitude should be 10.0", 10.0, v.getMagnitude(), 0.0001);
    }
}
