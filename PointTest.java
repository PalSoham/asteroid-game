import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PointTest {

    private static final double DELTA = 1e-6;

    @Test
    public void testMoveWithPositiveVelocity() {
        Point p = new Point(1.0, 2.0);
        VelocityVector v = new VelocityVector(0, 0); // Temporary dummy values
        v.setX(3.0);
        v.setY(4.0);

        p.move(v);

        assertEquals(4.0, p.getX(), DELTA);
        assertEquals(6.0, p.getY(), DELTA);
    }

    @Test
    public void testMoveWithNegativeVelocity() {
        Point p = new Point(5.0, 5.0);
        VelocityVector v = new VelocityVector(0, 0);
        v.setX(-2.0);
        v.setY(-3.0);

        p.move(v);

        assertEquals(3.0, p.getX(), DELTA);
        assertEquals(2.0, p.getY(), DELTA);
    }

    @Test
    public void testMoveWithZeroVelocity() {
        Point p = new Point(10.0, -10.0);
        VelocityVector v = new VelocityVector(0, 0);
        v.setX(0.0);
        v.setY(0.0);

        p.move(v);

        assertEquals(10.0, p.getX(), DELTA);
        assertEquals(-10.0, p.getY(), DELTA);
    }

    @Test
    public void testMoveWithMixedVelocity() {
        Point p = new Point(0.0, 0.0);
        VelocityVector v = new VelocityVector(0, 0);
        v.setX(5.5);
        v.setY(-2.5);

        p.move(v);

        assertEquals(5.5, p.getX(), DELTA);
        assertEquals(-2.5, p.getY(), DELTA);
    }
}
