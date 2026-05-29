import java.awt.event.KeyEvent;
import java.awt.Font;
import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;

/**
 * Creates an instance of the Asteroids game and runs it.
 * Manages the overall game state: START, PLAYING, and GAME_OVER screens.
 *
 * @author Soham Pal
 * @version 5/26/2023
 */
public class GameDriver
{
    /** Represents the possible states of the game loop */
    private enum GameState { START, PLAYING, GAME_OVER }

    /**
     * Create a game object and a display screen and execute the main update loop.
     * Handles transitions between the start screen, gameplay, and game-over screen.
     *
     * @param args      command line arguments (ignored)
     */
    private static int highScore = 0;
    private static File highScoreFile = null;

    public static void main(String[] args)
    {
        boolean inSrcDir = new File("GameDriver.class").exists() || new File("Ship.class").exists();
        if (inSrcDir) {
            highScoreFile = new File("../highscore.txt");
        } else {
            highScoreFile = new File("highscore.txt");
        }

        try {
            if (highScoreFile.exists()) {
                Scanner scanner = new Scanner(highScoreFile);
                if (scanner.hasNextInt()) {
                    highScore = scanner.nextInt();
                }
                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        StdDraw.setCanvasSize(GameConstants.SCREEN_WIDTH, GameConstants.SCREEN_HEIGHT);
        StdDraw.setXscale(0, GameConstants.SCREEN_WIDTH);
        StdDraw.setYscale(0, GameConstants.SCREEN_HEIGHT);
        StdDraw.enableDoubleBuffering();

        GameState state = GameState.START;
        AsteroidsGame game = null;

        // Track space key state to prevent firing immediately on transition
        boolean spaceWasPressed = false;

        while (true)
        {
            StdDraw.clear(GameConstants.SCREEN_COLOR);

            switch (state)
            {
                case START:
                    drawStartScreen();
                    StdDraw.show();
                    StdDraw.pause(GameConstants.DRAW_DELAY);

                    // Consume any buffered key events so they don't bleed into gameplay
                    while (StdDraw.hasNextKeyTyped()) StdDraw.nextKeyTyped();

                    if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE))
                    {
                        if (!spaceWasPressed)
                        {
                            game = new AsteroidsGame();
                            state = GameState.PLAYING;
                            spaceWasPressed = true;
                        }
                    }
                    else
                    {
                        spaceWasPressed = false;
                    }
                    break;

                case PLAYING:
                    game.update();
                    game.draw();
                    StdDraw.show();
                    StdDraw.pause(GameConstants.DRAW_DELAY);

                    if (game.isGameOver())
                    {
                        state = GameState.GAME_OVER;
                        spaceWasPressed = true; // prevent immediate restart
                        while (StdDraw.hasNextKeyTyped()) StdDraw.nextKeyTyped();
                    }
                    break;

                case GAME_OVER:
                    // Keep drawing the frozen world behind the overlay
                    game.drawBackground();
                    drawGameOverScreen(game.getScore());
                    StdDraw.show();
                    StdDraw.pause(GameConstants.DRAW_DELAY);

                    if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE))
                    {
                        if (!spaceWasPressed)
                        {
                            game = new AsteroidsGame();
                            state = GameState.PLAYING;
                            spaceWasPressed = true;
                            while (StdDraw.hasNextKeyTyped()) StdDraw.nextKeyTyped();
                        }
                    }
                    else if (StdDraw.isKeyPressed(KeyEvent.VK_ESCAPE))
                    {
                        state = GameState.START;
                        spaceWasPressed = false;
                        while (StdDraw.hasNextKeyTyped()) StdDraw.nextKeyTyped();
                    }
                    else
                    {
                        spaceWasPressed = false;
                    }
                    break;
            }
        }
    }

    /**
     * Draws the start / title screen with controls and a prompt to begin.
     */
    private static void drawStartScreen()
    {
        int cx = GameConstants.SCREEN_WIDTH / 2;
        int cy = GameConstants.SCREEN_HEIGHT / 2;

        // Title
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 64));
        StdDraw.text(cx, cy + 180, "ASTEROIDS");

        // Decorative line
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.line(cx - 200, cy + 145, cx + 200, cy + 145);

        // Controls heading
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 22));
        StdDraw.text(cx, cy + 110, "HOW TO PLAY");

        // Controls list
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 16));
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        String[] lines = {
                "LEFT / RIGHT ARROW  -  Rotate ship",
                "UP ARROW            -  Thrust forward",
                "SPACE               -  Fire burst",
        };
        int lineY = cy + 75;
        for (String line : lines)
        {
            StdDraw.text(cx, lineY, line);
            lineY -= 28;
        }

        // Scoring
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 16));
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(cx, lineY - 10, "+20 points for each asteroid destroyed");

        // Decorative line
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.line(cx - 200, lineY - 35, cx + 200, lineY - 35);

        // Blinking prompt (blink every ~600 ms at 10 ms delay = 60 frames)
        long frame = System.currentTimeMillis() / 600;
        if (frame % 2 == 0)
        {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, 22));
            StdDraw.text(cx, cy - 160, "PRESS SPACE TO START");
        }

        // Footer
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 13));
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.text(cx, 30, "by Soham Pal");
    }

    /**
     * Draws the game-over overlay with the final score and restart options.
     *
     * @param score   the player's final score
     */
    private static void drawGameOverScreen(int score)
    {
        if (score > highScore) {
            highScore = score;
            try {
                if (highScoreFile == null) {
                    File localFile = new File("highscore.txt");
                    File parentFile = new File("../highscore.txt");
                    highScoreFile = parentFile.exists() ? parentFile : localFile;
                }
                PrintWriter writer = new PrintWriter(highScoreFile);
                writer.println(highScore);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        int cx = GameConstants.SCREEN_WIDTH / 2;
        int cy = GameConstants.SCREEN_HEIGHT / 2;

        // Semi-transparent dark panel (drawn as a filled rectangle via multiple overlapping rects)
        StdDraw.setPenColor(new java.awt.Color(0, 0, 0, 180));
        StdDraw.filledRectangle(cx, cy, 240, 150);

        // Game Over heading
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 52));
        StdDraw.text(cx, cy + 90, "GAME OVER");

        // Final score
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 26));
        StdDraw.text(cx, cy + 45, "Final Score: " + score);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 22));
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.text(cx, cy + 15, "High Score: " + highScore);

        // Blinking restart prompt
        long frame = System.currentTimeMillis() / 600;
        if (frame % 2 == 0)
        {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, 18));
            StdDraw.text(cx, cy - 30, "SPACE to Play Again");
        }

        // ESC option
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.setFont(new Font("SansSerif", Font.BOLD, 15));
        StdDraw.text(cx, cy - 65, "ESC for Title Screen");
    }
}