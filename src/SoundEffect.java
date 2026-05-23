import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class SoundEffect {
    public static void playShoot() {
        playSound(800, 100);
    }

    public static void playExplosion() {
        playNoise(200);
    }

    public static void playPowerUp() {
        playSound(1200, 150);
    }

    private static void playSound(int hz, int msecs) {
        new Thread(() -> {
            try {
                byte[] buf = new byte[1];
                AudioFormat af = new AudioFormat((float) 44100, 8, 1, true, false);
                SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
                sdl.open(af);
                sdl.start();
                for (int i = 0; i < msecs * 44.1; i++) {
                    double angle = i / (44100.0 / hz) * 2.0 * Math.PI;
                    buf[0] = (byte) (Math.sin(angle) * 127.0);
                    sdl.write(buf, 0, 1);
                }
                sdl.drain();
                sdl.stop();
                sdl.close();
            } catch (Exception e) {
                // ignore
            }
        }).start();
    }

    private static void playNoise(int msecs) {
        new Thread(() -> {
            try {
                byte[] buf = new byte[1];
                AudioFormat af = new AudioFormat((float) 44100, 8, 1, true, false);
                SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
                sdl.open(af);
                sdl.start();
                for (int i = 0; i < msecs * 44.1; i++) {
                    buf[0] = (byte) (Math.random() * 255.0 - 128.0);
                    sdl.write(buf, 0, 1);
                }
                sdl.drain();
                sdl.stop();
                sdl.close();
            } catch (Exception e) {
                // ignore
            }
        }).start();
    }
}
