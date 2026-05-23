import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Field;

public class BurstTest {
    @Test
    public void testUpdateLifecycle() throws Exception {
        // Arrange
        Position p = new Position(100, 100, 0);
        Burst burst = new Burst(p);

        int initialLife = burst.getLifeValue();
        assertEquals("Initial life should be 30", 30, initialLife);

        // Use reflection to test radius since there's no getter
        double initialRadius = 7.5;

        // Act
        burst.update();

        // Assert
        assertEquals("Life should decrease by 1", initialLife - 1, burst.getLifeValue());

        Field radiusField = Burst.class.getDeclaredField("radius");
        radiusField.setAccessible(true);
        double newRadius = (double) radiusField.get(burst);
        assertEquals("Radius should be multiplied by 0.97", initialRadius * 0.97, newRadius, 0.001);
    }
}
