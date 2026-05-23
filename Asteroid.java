import java.awt.Color;
import java.security.SecureRandom;

/**
 * Represents an asteroid in space that can collide with either the ship
 * or bursts shot from the ship. It extends a SpaceObject.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */

public class Asteroid extends SpaceObject
{
    private static final SecureRandom random = new SecureRandom();

    /**
     * No-args constructor for Asteroid.
     */
    public Asteroid()
    {
        super(new Position(random.nextDouble() * GameConstants.SCREEN_WIDTH,
                        random.nextDouble() * GameConstants.SCREEN_HEIGHT, random.nextDouble() * (2 * Math.PI)),
                new VelocityVector(random.nextDouble() * (2 * Math.PI), random.nextDouble() * 4));
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
        StdDraw.setPenColor(StdDraw.WHITE);
        GameUtils.drawPositionAsPolygon(getPosition(), 26);
    }

    /**
     * Calls the update method for SpaceObject.
     */
    public void update()
    {
        super.update();
    }
}