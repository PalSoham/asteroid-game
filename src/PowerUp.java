import java.awt.Color;

public class PowerUp extends SpaceObject {
    public PowerUp() {
        super(new Position(Math.random() * GameConstants.SCREEN_WIDTH,
                        Math.random() * GameConstants.SCREEN_HEIGHT, 0),
                new VelocityVector(Math.random() * (2 * Math.PI), Math.random() * 2));
    }

    public void draw() {
        StdDraw.setPenColor(StdDraw.CYAN);
        StdDraw.filledCircle(getPosition().getX(), getPosition().getY(), 10);
    }

    public void update() {
        super.update();
    }
}
