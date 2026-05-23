import java.awt.Color;

/**
 * Represents a star in space. Blinks in the background.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */

public class Star extends SpaceObject
{
    private double size;

    public Star ( )
    {
        super(new Position(Math.random() * GameConstants.SCREEN_WIDTH,
                        Math.random() * GameConstants.SCREEN_HEIGHT, 7 * Math.PI / 4),
                new VelocityVector(7 * Math.PI / 4, 0.5));
        size = 0.5 + Math.random();
    }

    public void draw ( )
    {
        int value = 255 - (int)(180 * Math.random());
        Color color = new Color(value, value, value);
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(getPosition().getX(), getPosition().getY(), size);
    }
}










