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

    private void drawShipPart(Point[] shipPoints, Color color)
    {
        double[] xs = new double[shipPoints.length];
        double[] ys = new double[shipPoints.length];
        for (int i = 0; i < shipPoints.length; i++)
        {
            Point screenPt = GameUtils.transformPositionToScreen(getPosition(), shipPoints[i]);
            xs[i] = screenPt.getX();
            ys[i] = screenPt.getY();
        }
        StdDraw.setPenColor(color);
        StdDraw.filledPolygon(xs, ys);
    }

    public void draw()
    {
        // 1. Thruster flame when moving forward
        if (StdDraw.isKeyPressed(java.awt.event.KeyEvent.VK_UP))
        {
            double flameLength = 18 + Math.random() * 14;
            Point[] outerFlame = {
                new Point(-15, -4),
                new Point(-15 - flameLength, 0),
                new Point(-15, 4)
            };
            drawShipPart(outerFlame, new Color(255, 100, 0));

            Point[] innerFlame = {
                new Point(-15, -2.5),
                new Point(-15 - flameLength * 0.6, 0),
                new Point(-15, 2.5)
            };
            drawShipPart(innerFlame, new Color(255, 230, 0));
        }

        // 2. Swept-back wings
        Point[] leftWing = {
            new Point(0, -6),
            new Point(-18, -16),
            new Point(-10, -6)
        };
        drawShipPart(leftWing, new Color(110, 130, 160));

        Point[] rightWing = {
            new Point(0, 6),
            new Point(-18, 16),
            new Point(-10, 6)
        };
        drawShipPart(rightWing, new Color(110, 130, 160));

        // 3. Central metallic fuselage
        Point[] fuselage = {
            new Point(20, 0),
            new Point(-15, -6),
            new Point(-15, 6)
        };
        drawShipPart(fuselage, new Color(235, 240, 250));

        // 4. Glowing cockpit window
        Point[] cockpit = {
            new Point(12, 0),
            new Point(2, -3),
            new Point(-3, 0),
            new Point(2, 3)
        };
        drawShipPart(cockpit, new Color(0, 240, 255));

        // 5. Shield overlay
        if (shieldActive) {
            StdDraw.setPenColor(new Color(0, 255, 255, 130)); // semi-transparent cyan
            StdDraw.circle(getPosition().getX(), getPosition().getY(), 30);
            StdDraw.setPenColor(new Color(0, 255, 255, 60));
            StdDraw.filledCircle(getPosition().getX(), getPosition().getY(), 29);
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