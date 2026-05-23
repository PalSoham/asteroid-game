import org.junit.Test;
import static org.junit.Assert.*;

public class GameUtilsTest {

    @Test
    public void testApplyThrustZeroDegrees() {
        VelocityVector velocity = new VelocityVector(0, 0);

        GameUtils.applyThrust(velocity, 0, 10.0);

        assertEquals(10.0, velocity.getX(), 0.0001);
        assertEquals(0.0, velocity.getY(), 0.0001);
    }

    @Test
    public void testApplyThrustNinetyDegrees() {
        VelocityVector velocity = new VelocityVector(0, 0);

        GameUtils.applyThrust(velocity, Math.PI / 2, 10.0);

        assertEquals(0.0, velocity.getX(), 0.0001);
        assertEquals(10.0, velocity.getY(), 0.0001);
    }

    @Test
    public void testApplyThrustArbitraryAngle() {
        VelocityVector velocity = new VelocityVector(0, 10.0); // x=10, y=0

        GameUtils.applyThrust(velocity, Math.PI / 4, 10.0);

        assertEquals(10.0 + 10.0 * Math.cos(Math.PI / 4), velocity.getX(), 0.0001);
        assertEquals(10.0 * Math.sin(Math.PI / 4), velocity.getY(), 0.0001);
    }

    @Test
    public void testApplyThrustZeroThrust() {
        VelocityVector velocity = new VelocityVector(0, 5.0); // x=5, y=0

        GameUtils.applyThrust(velocity, Math.PI, 0.0);

        assertEquals(5.0, velocity.getX(), 0.0001);
        assertEquals(0.0, velocity.getY(), 0.0001);
    }
}
