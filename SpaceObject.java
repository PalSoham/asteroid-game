/**
 * Your description here.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */

public abstract class SpaceObject
{
    private Position position;
    private VelocityVector velocity;

    public SpaceObject(Position p, VelocityVector v)
    {
        position = p;
        velocity = v;
    }

    public void setPosition(Position p)
    {
        position = p;
    }

    public Position getPosition ( )
    {
        return position;
    }

    public void setVelocity(VelocityVector v)
    {
        velocity = v;
    }

    public VelocityVector getVelocity ( )
    {
        return velocity;
    }

    public abstract void draw();

    public void update ( )
    {
        getPosition().move(getVelocity());
        getPosition().setX((getPosition().getX() + GameConstants.SCREEN_WIDTH) % GameConstants.SCREEN_WIDTH);
        getPosition().setY((getPosition().getY() + GameConstants.SCREEN_HEIGHT) % GameConstants.SCREEN_HEIGHT);
    }
}







