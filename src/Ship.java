import java.awt.Color;

/**
 * Represents the ship in space. It can collide with an asteroid
 * in which case the lives decreases. 0 lives and the game is over.
 * It can also shoot bursts, if they collid with an asteroid then 
 * score is increased by 20.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */

public class Ship extends SpaceObject
{
    /** The number of lives left */
    private int lives;
    /** The score for the game */
    private int score;
    private boolean shieldActive;

    public Ship()
    {
        super(new Position(GameConstants.SCREEN_WIDTH / 2.0,
                        GameConstants.SCREEN_HEIGHT / 2.0, Math.PI / 2.0),
                new VelocityVector(Math.PI / 2.0, 0));
        lives = 3;
        score = 0;
        shieldActive = false;
    }

    public void activateShield()
    {
        shieldActive = true;
    }

    public void deactivateShield()
    {
        shieldActive = false;
    }

    public boolean hasShield()
    {
        return shieldActive;
    }

    public void turn(double value)
    {
        getPosition().setHeading(getPosition().getHeading() + value);
    }

    public void thrust(double value)
    {
        GameUtils.applyThrust(getVelocity(), getPosition().getHeading(), value);
    }

    public int getScore()
    {
        return score;
    }

    public int getLives()
    {
        return lives;
    }

    public void increaseScore()
    {
        score += 20;
    }

    public void reduceLives()
    {
        lives--;
    }

    public void draw()
    {
        StdDraw.setPenColor(StdDraw.WHITE);
        GameUtils.drawPositionAsTriangle(getPosition(), 20, 40);
        if (shieldActive) {
            StdDraw.setPenColor(StdDraw.CYAN);
            StdDraw.circle(getPosition().getX(), getPosition().getY(), 30);
        }
    }

    public void update()
    {
        super.update();
        getVelocity().setX(getVelocity().getX() * 0.99);
        getVelocity().setY(getVelocity().getY() * 0.99);
    }

    public boolean collision(Asteroid a)
    {
        double dist = Math.sqrt(Math.pow(getPosition().getX() - a.getPosition().getX(), 2) + Math.pow(getPosition().getY() - a.getPosition().getY(), 2));
        if(dist <= 30)
        {
            return true;
        }
        return false;
    }

    public boolean collision(PowerUp p)
    {
        double dist = Math.sqrt(Math.pow(getPosition().getX() - p.getPosition().getX(), 2) + Math.pow(getPosition().getY() - p.getPosition().getY(), 2));
        if(dist <= 30)
        {
            return true;
        }
        return false;
    }
}