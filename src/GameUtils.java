/**
 * Utility methods for working with Positions, Points, and Vector2Ds in Asteroids.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */
public class GameUtils
{
    /**
     * Update the provided velocity according to the indicated thrust value and
     * heading. The thrust will be applied so as to push the velocity value in
     * the direction of the provided heading.
     *
     * @param velocity        velocity vector to update
     * @param heading         direction to push the velocity
     * @param thrust          magnitude of the thrust
     */
    public static void applyThrust(VelocityVector velocity, double heading, double thrust)
    {
        VelocityVector thrustVector = new VelocityVector(heading, thrust);
        velocity.setX(velocity.getX() + thrustVector.getX());
        velocity.setY(velocity.getY() + thrustVector.getY());
    }

    /**
     * Draw an isosceles triangle at the location and heading of the provided
     * Position. The triangle will be centered at the x,y position of the Position
     * object.
     *
     * @param Position         location and heading for the triangle
     * @param width            the width of the base of the triangle
     * @param height           the height of the triangle
     */
    public static void drawPositionAsTriangle(Position position, double width, double height)
    {
        Point llPoint = transformPositionToScreen(position, new Point(-.5 * height, -.5 * width));
        Point lrPoint = transformPositionToScreen(position, new Point(-.5 * height, .5 * width));
        Point endPoint = transformPositionToScreen(position, new Point(.5 * height, 0));

        double[] xs = new double[3];
        double[] ys = new double[3];

        xs[0] = llPoint.getX();
        xs[1] = endPoint.getX();
        xs[2] = lrPoint.getX();
        ys[0] = llPoint.getY();
        ys[1] = endPoint.getY();
        ys[2] = lrPoint.getY();

        StdDraw.filledPolygon(xs, ys);
    }

    /**
     * A helper method, that performs a transform from the coordinate frame defined by
     * a Position object to screen coordinates.
     *
     * @param position          the coordinate frame to transform from
     * @param point             the point to transform (in Position coordinates)
     * @return                  the transformed point (in screen coordinates)
     */
    public static Point transformPositionToScreen(Position position, Point point)
    {
        double newX;
        double newY;
        newX = point.getX() * Math.cos(position.getHeading()) -
                point.getY() * Math.sin(position.getHeading()) + position.getX();
        newY = point.getX() * Math.sin(position.getHeading()) +
                point.getY() * Math.cos(position.getHeading()) + position.getY();
        return new Point(newX, newY);
    }

    /**
     * Draw a polygon at the location and heading of the provided
     * Position. The polygon will be centered at the x,y position of the Position
     * object.
     *
     * @param Position           location and heading for the polygon
     * @param radius             the radius (roughly) of the polygon
     */
    public static void drawPositionAsPolygon(Position position, double radius)
    {
        double middleX = position.getX();
        double middleY = position.getY();
        double direction = position.getHeading();
        double [] x = {middleX + (radius + 4) * Math.cos(Math.PI * 0.2 + direction), middleX + (radius  - 5) * Math.cos(Math.PI * 0.7 + direction),
                middleX + (radius - 3) * Math.cos(Math.PI * 1 + direction), middleX + (radius  - 3) * Math.cos(Math.PI * 1.2 + direction),
                middleX + (radius  + 2) * Math.cos(Math.PI * 1.5 + direction), middleX + (radius  - 0) * Math.cos(Math.PI * 1.9 + direction)};
        double [] y = {middleY + (radius + 4) * Math.sin(Math.PI * 0.2 + direction), middleY + (radius  - 5) * Math.sin(Math.PI * 0.7 + direction),
                middleY + (radius - 3) * Math.sin(Math.PI * 1 + direction), middleY + (radius  - 3) * Math.sin(Math.PI * 1.2 + direction),
                middleY + (radius  + 2) * Math.sin(Math.PI * 1.5 + direction), middleY + (radius  - 0) * Math.sin(Math.PI * 1.9 + direction)};
        StdDraw.polygon(x, y);
    }

    /**
     * Draw a filled polygon at the location and heading of the provided
     * Position. The polygon will be centered at the x,y position of the Position
     * object.
     *
     * @param position           location and heading for the polygon
     * @param radius             the radius (roughly) of the polygon
     */
    public static void drawPositionAsFilledPolygon(Position position, double radius)
    {
        double middleX = position.getX();
        double middleY = position.getY();
        double direction = position.getHeading();
        double [] x = {middleX + (radius + 4) * Math.cos(Math.PI * 0.2 + direction), middleX + (radius  - 5) * Math.cos(Math.PI * 0.7 + direction),
                middleX + (radius - 3) * Math.cos(Math.PI * 1 + direction), middleX + (radius  - 3) * Math.cos(Math.PI * 1.2 + direction),
                middleX + (radius  + 2) * Math.cos(Math.PI * 1.5 + direction), middleX + (radius  - 0) * Math.cos(Math.PI * 1.9 + direction)};
        double [] y = {middleY + (radius + 4) * Math.sin(Math.PI * 0.2 + direction), middleY + (radius  - 5) * Math.sin(Math.PI * 0.7 + direction),
                middleY + (radius - 3) * Math.sin(Math.PI * 1 + direction), middleY + (radius  - 3) * Math.sin(Math.PI * 1.2 + direction),
                middleY + (radius  + 2) * Math.sin(Math.PI * 1.5 + direction), middleY + (radius  - 0) * Math.sin(Math.PI * 1.9 + direction)};
        StdDraw.filledPolygon(x, y);
    }
}