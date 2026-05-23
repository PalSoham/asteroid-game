/**
 * Extends a point to include an associated heading.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */
public class Position extends Point
{
    /**  The heading, or direction, of this position      */
    private double heading;

    /**
     * Construct a Position.
     *
     * @param x            the position on the X-axis
     * @param y            the position on the Y-axis
     * @param direction    the heading, or direction (in radians)
     */
    public Position(double x, double y, double direction)
    {
        super(x, y);
        heading = direction;
    }

    /**
     * Copy constructor.
     *
     * @param other        the Position to copy
     */
    public Position(Position other)
    {
        this(other.getX(), other.getY(), other.heading);
    }

    /**
     * @return the heading in radians
     */
    public double getHeading()
    {
        return heading;
    }

    /**
     * Set the heading.
     *
     * @param newHeading     the new heading in radians.
     */
    public void setHeading(double newHeading)
    {
        heading = newHeading;
    }

    /**
     * @return String representation of this Position as
     *         "Position[xPosition=x, yPosition=y, heading=h]"
     */
    public String toString()
    {
        return "Position[xPosition=" + getX() + ", yPosition="
                + getY() + ", heading=" + heading + "]";
    }
}