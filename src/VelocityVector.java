/**
 * Represents a two-dimensional vector, for
 * use as a velocity.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */
public class VelocityVector
{
    /**  The x component of the velocity vector      */
    private double xComponent;

    /**  The y component of the velocity vector      */
    private double yComponent;

    /**
     * Construct a new vector from the provided heading and magnitude.
     *
     * A heading of 0 represents a vector pointing directly to the right, the
     * heading increases as vectors are rotated in a counter-clockwise
     * direction, and the heading is measured in radians.
     *
     * @param heading           initial heading
     * @param magnitude         initial magnitude (length of the vector)
     */
    public VelocityVector(double heading, double magnitude)
    {
        setMagnitude(magnitude);
        setHeading(heading);
    }

    /**
     * Copy constructor.
     *
     * @param other             the vector to copy
     */
    public VelocityVector(VelocityVector other)
    {
        this(other.getHeading(), other.getMagnitude());
    }

    /**
     * @return      the X component of the vector
     */
    public double getX()
    {
        return xComponent;
    }

    /**
     * Set the X component of the vector.
     *
     * @param xComponent       the new X component
     */
    public void setX(double xComponent)
    {
        this.xComponent = xComponent;
    }

    /**
     * @return      the Y component of the vector
     */
    public double getY()
    {
        return yComponent;
    }

    /**
     * Set the Y component of the vector.
     *
     * @param yComponent       the new Y component
     */
    public void setY(double yComponent)
    {
        this.yComponent = yComponent;
    }

    /**
     * @return      the magnitude (length) of this vector
     */
    public double getMagnitude()
    {
        return Math.sqrt(xComponent * xComponent + yComponent * yComponent);
    }

    /**
     * @return      the heading of this vector in radians
     */
    public double getHeading()
    {
        return Math.atan2(getY(), getX());
    }

    /**
     * Set the heading of this vector. Note that this operation will have no
     * effect if the magnitude of the vector is zero.
     *
     * @param heading       the new heading in radians
     */
    public void setHeading(double heading)
    {
        double magnitude = getMagnitude();
        setX(Math.cos(heading) * magnitude);
        setY(Math.sin(heading) * magnitude);
    }

    /**
     * Set the magnitude (length) of the vector.
     *
     * @param magnitude     the new magnitude
     */
    public void setMagnitude(double magnitude)
    {
        double oldMagnitude = getMagnitude();

        // Prevent divide by zero if we attempt to set the magnitude of a vector
        // with no heading.
        if (oldMagnitude < .0000001)
        {
            xComponent = magnitude;
        }
        else
        {
            xComponent *= magnitude / oldMagnitude;
            yComponent *= magnitude / oldMagnitude;
        }
    }

    /**
     * @return String representation of this point as
     *         "VelocityVector[xComponent=x, yComponent=y]"
     */
    public String toString()
    {
        return "VelocityVector[xComponent=" + xComponent + ", yComponent=" + yComponent + "]";
    }
}