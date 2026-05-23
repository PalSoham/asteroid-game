/**
 * A two-dimensional point, with
 * getters and setters.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */
public class Point
{
    /**  The x coordinate of the point      */
    private double xPosition;

    /**  The y coordinate of the point      */
    private double yPosition;

    /**
     * Constructs a point object.
     *
     * @param x        position on the X-axis
     * @param y        position on the Y-axis
     */
    public Point(double x, double y)
    {
        xPosition = x;
        yPosition = y;
    }

    /**
     * Copy constructor.
     *
     * @param other    the point to copy
     */
    public Point(Point other)
    {
        xPosition = other.xPosition;
        yPosition = other.yPosition;
//		this(other.xPosition,other.yPosition);
    }

    /**
     * @return the x coordinate
     */
    public double getX ( )
    {
        return xPosition;
    }

    /**
     * @return the y coordinate
     */
    public double getY ( )
    {
        return yPosition;
    }

    /**
     * Set the x position of the point.
     *
     * @param x         the new X position
     */
    public void setX(double x)
    {
        xPosition = x;
    }

    /**
     * Set the y position of the point.
     *
     * @param y         the new y position.
     */
    public void setY(double y)
    {
        yPosition = y;
    }

    /**
     * Move the point according the the provided vector.
     *
     * @param vector     the motion vector (representing a velocity)
     */
    public void move(VelocityVector vector)
    {
        setX(xPosition + vector.getX());
        setY(yPosition + vector.getY());
    }

    /**
     * @return String representation of this point as
     *         "Point[xPosition=x, yPosition=y]"
     */
    public String toString()
    {
        return "Point[xPosition=" + xPosition + ", yPosition=" + yPosition + "]";
    }
}


