import java.awt.Color;

/**
 * represents a burst shot from the ship when the space bar is
 * pressed. It can destroy an asteroid.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */

public class Burst extends SpaceObject
{
    /**	Represents the number of game steps a burst lasts */
    private int lifeValue;
    /**	Represents the radius of the burst over time */
    private double radius;

    /**
     * One-arg constructor for Burst.
     * @param p 	The position of the ship.
     */
    public Burst(Position p)
    {
        super(new Position(p), new VelocityVector(p.getHeading(), 20));
        lifeValue = 30;
        radius = 7.5;
    }

    /**
     * Returns the number of game steps left for the burst
     * to survive.
     * @return Number of game steps left for the burst.
     */
    public int getLifeValue()
    {
        return lifeValue;
    }

    /**
     * Draws the burst.
     */
    public void draw()
    {
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(getPosition().getX(), getPosition().getY(), radius);
    }

    /**
     * Updates the game steps related to the burst.
     */
    public void update()
    {
        super.update();
        lifeValue--;
        radius *= 0.97;
    }

    /**
     * Checks for collision between a burst and an asteroid.
     * Returns true if a collision happened, false otherwise.
     * @param a 	Reference to an Asteroid.
     * @return True if a collision occured, false otherwise.
     */
    public boolean collision(Asteroid a)
    {
        double dx = getPosition().getX() - a.getPosition().getX();
        double dy = getPosition().getY() - a.getPosition().getY();
        double distSq = dx * dx + dy * dy;
        if(distSq <= 625)
        {
            return true;
        }
        return false;
    }
}