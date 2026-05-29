import java.awt.Color;

/**
 * Represents an asteroid in space that can collide with either the ship
 * or bursts shot from the ship. It extends a SpaceObject.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */

public class Asteroid extends SpaceObject
{
    private int size;

    /**
     * No-args constructor for Asteroid.
     */
    public Asteroid()
    {
        super(new Position(Math.random() * GameConstants.SCREEN_WIDTH,
                        Math.random() * GameConstants.SCREEN_HEIGHT, Math.random() * (2 * Math.PI)),
                new VelocityVector(Math.random() * (2 * Math.PI), Math.random() * 4));
        this.size = 3;
    }

    public Asteroid(Position p, VelocityVector v, int size)
    {
        super(p, v);
        this.size = size;
    }

    public int getSize()
    {
        return size;
    }

    /**
     * Changes the heading every game step to give the asteroids the spinning effect
     * while moving through space.
     */
    public void changeHeading()
    {
        getPosition().setHeading(getPosition().getHeading() + (Math.PI / 200 % (2 * Math.PI)));
    }

    /**
     * Draws the asteroid.
     */
    public void draw()
    {
        // Deep dangerous red solid body
        StdDraw.setPenColor(new Color(190, 30, 30));
        GameUtils.drawPositionAsFilledPolygon(getPosition(), 9 * size);

        // Brighter glowing coral-red border
        StdDraw.setPenColor(new Color(255, 75, 75));
        GameUtils.drawPositionAsPolygon(getPosition(), 9 * size);
    }

    /**
     * Calls the update method for SpaceObject.
     */
    public void update()
    {
        super.update();
    }
}