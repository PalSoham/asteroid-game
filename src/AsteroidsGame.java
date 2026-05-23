import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 * Responsible for updating the game for every game step.
 * Takes care of creating the bursts, asteroids and ship objects.
 * Exposes isGameOver(), getScore(), and drawBackground() so
 * GameDriver can manage screen transitions cleanly.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */

public class AsteroidsGame
{
    private Ship ship;
    private ArrayList<Burst> bursts;
    private ArrayList<Star> starfield;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<PowerUp> powerups;

    public AsteroidsGame()
    {
        ship = new Ship();
        starfield = new ArrayList<Star>();
        bursts = new ArrayList<Burst>();
        asteroids = new ArrayList<Asteroid>();
        powerups = new ArrayList<PowerUp>();
        for (int i = 0; i < 300; i++)
        {
            starfield.add(new Star());
        }
    }

    /**
     * Returns true when the player has no lives remaining.
     *
     * @return true if the game is over
     */
    public boolean isGameOver()
    {
        return ship.getLives() <= 0;
    }

    /**
     * Returns the player's current score.
     *
     * @return the current score
     */
    public int getScore()
    {
        return ship.getScore();
    }

    /**
     * Draws only the background (starfield + asteroids) without the HUD.
     * Used by GameDriver when rendering the game-over overlay on top of a
     * frozen snapshot of the world.
     */
    public void drawBackground()
    {
        for (int i = 0; i < starfield.size(); i++)
        {
            starfield.get(i).draw();
        }
        for (int i = 0; i < asteroids.size(); i++)
        {
            asteroids.get(i).draw();
        }
    }

    public void draw()
    {
        for (int i = 0; i < starfield.size(); i++)
        {
            starfield.get(i).draw();
        }
        if (ship.getLives() > 0)
        {
            ship.draw();
        }
        for (int i = 0; i < bursts.size(); i++)
        {
            bursts.get(i).draw();
        }
        for (int i = 0; i < asteroids.size(); i++)
        {
            asteroids.get(i).draw();
        }
        for (int i = 0; i < powerups.size(); i++)
        {
            powerups.get(i).draw();
        }
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(50, 50, "Score: " + ship.getScore());
        StdDraw.text(50, 20, "Lives: " + ship.getLives());
    }

    public void update()
    {
        for (int i = 0; i < starfield.size(); i++)
        {
            starfield.get(i).update();
        }
        if (asteroids.size() < 15)
        {
            int rand = (int)(Math.random() * 50 + 1);
            if (rand == 1)
            {
                asteroids.add(new Asteroid());
            }
        }
        for (int i = 0; i < asteroids.size(); i++)
        {
            asteroids.get(i).update();
            asteroids.get(i).changeHeading();
        }
        if (Math.random() < 0.002) {
            powerups.add(new PowerUp());
        }
        for (int i = 0; i < powerups.size(); i++)
        {
            powerups.get(i).update();
        }
        if (ship.getLives() > 0)
        {
            for (int i = 0; i < bursts.size(); i++)
            {
                bursts.get(i).update();
                if (bursts.get(i).getLifeValue() == 0)
                {
                    bursts.remove(i);
                    i--;
                }
            }

            if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
            {
                ship.turn(0.04);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
            {
                ship.turn(-0.04);
            }
            if (StdDraw.isKeyPressed(KeyEvent.VK_UP))
            {
                ship.thrust(0.1);
            }
            if (StdDraw.hasNextKeyTyped() && StdDraw.nextKeyTyped() == ' ')
            {
                bursts.add(new Burst(ship.getPosition()));
                SoundEffect.playShoot();
            }
            ship.update();

            for (int i = 0; i < bursts.size(); i++)
            {
                for (int k = 0; k < asteroids.size(); k++)
                {
                    if (bursts.get(i).collision(asteroids.get(k)))
                    {
                        bursts.remove(i);
                        Asteroid hit = asteroids.get(k);
                        asteroids.remove(k);
                        SoundEffect.playExplosion();
                        if (hit.getSize() > 1) {
                            asteroids.add(new Asteroid(new Position(hit.getPosition().getX(), hit.getPosition().getY(), Math.random() * 2 * Math.PI), new VelocityVector(Math.random() * 2 * Math.PI, Math.random() * 4), hit.getSize() - 1));
                            asteroids.add(new Asteroid(new Position(hit.getPosition().getX(), hit.getPosition().getY(), Math.random() * 2 * Math.PI), new VelocityVector(Math.random() * 2 * Math.PI, Math.random() * 4), hit.getSize() - 1));
                        }
                        ship.increaseScore();
                        k--;
                        i--;
                        break;
                    }
                }
            }
            for (int i = 0; i < powerups.size(); i++)
            {
                if (ship.collision(powerups.get(i)))
                {
                    ship.activateShield();
                    powerups.remove(i);
                    SoundEffect.playPowerUp();
                    i--;
                }
            }
            for (int i = 0; i < asteroids.size(); i++)
            {
                if (ship.collision(asteroids.get(i)))
                {
                    if (ship.hasShield()) {
                        ship.deactivateShield();
                        Asteroid hit = asteroids.get(i);
                        asteroids.remove(i);
                        SoundEffect.playExplosion();
                        if (hit.getSize() > 1) {
                            // Spawn child asteroids safely outside of the player's immediate collision radius
                            double safeDist = 35.0; // Slightly larger than ship's collision radius
                            double ang1 = Math.random() * 2 * Math.PI;
                            double ang2 = Math.random() * 2 * Math.PI;
                            asteroids.add(new Asteroid(new Position(hit.getPosition().getX() + Math.cos(ang1) * safeDist, hit.getPosition().getY() + Math.sin(ang1) * safeDist, Math.random() * 2 * Math.PI), new VelocityVector(Math.random() * 2 * Math.PI, Math.random() * 4), hit.getSize() - 1));
                            asteroids.add(new Asteroid(new Position(hit.getPosition().getX() + Math.cos(ang2) * safeDist, hit.getPosition().getY() + Math.sin(ang2) * safeDist, Math.random() * 2 * Math.PI), new VelocityVector(Math.random() * 2 * Math.PI, Math.random() * 4), hit.getSize() - 1));
                        }
                    } else {
                        asteroids.remove(i);
                        SoundEffect.playExplosion();
                        ship.reduceLives();
                    }
                    i--;
                }
            }
        }
    }
}