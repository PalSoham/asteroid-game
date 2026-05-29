import java.awt.Color;

public class PowerUp extends SpaceObject {
    private double rotation = 0.0;

    public PowerUp() {
        super(new Position(Math.random() * GameConstants.SCREEN_WIDTH,
                        Math.random() * GameConstants.SCREEN_HEIGHT, 0),
                new VelocityVector(Math.random() * (2 * Math.PI), Math.random() * 2));
    }

    public void draw() {
        double cx = getPosition().getX();
        double cy = getPosition().getY();
        double[] xs = new double[10];
        double[] ys = new double[10];
        double rOuter = 13;
        double rInner = 6;
        for (int i = 0; i < 10; i++) {
            double angle = i * Math.PI / 5.0 - Math.PI / 2.0 + rotation;
            double r = (i % 2 == 0) ? rOuter : rInner;
            xs[i] = cx + r * Math.cos(angle);
            ys[i] = cy + r * Math.sin(angle);
        }
        
        // Outer bright neon-green border/glow
        StdDraw.setPenColor(new Color(50, 255, 120));
        StdDraw.filledPolygon(xs, ys);

        // Slightly smaller inner star for depth
        double[] innerXs = new double[10];
        double[] innerYs = new double[10];
        for (int i = 0; i < 10; i++) {
            double angle = i * Math.PI / 5.0 - Math.PI / 2.0 + rotation;
            double r = (i % 2 == 0) ? 10 : 4;
            innerXs[i] = cx + r * Math.cos(angle);
            innerYs[i] = cy + r * Math.sin(angle);
        }
        StdDraw.setPenColor(new Color(0, 180, 60));
        StdDraw.filledPolygon(innerXs, innerYs);
    }

    public void update() {
        super.update();
        rotation += 0.05;
    }
}
