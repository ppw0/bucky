import java.awt.*;
import javax.swing.*;

public abstract class Core {

    public abstract void draw(Graphics2D g);
    public abstract void update(long timePassed);

    private boolean running;
    protected ScreenManager s;

    public void init() {
        Window w = s.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN, 20));
        w.setBackground(Color.GREEN);
        w.setForeground(Color.WHITE);
        running = true;
    }

    public Image loadImage(String s) {
        return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource(s))).getImage();
    }

    public void loop() {
        long cumulativeTime = System.currentTimeMillis();
        while (running) {
            long timePassed = System.currentTimeMillis() - cumulativeTime;
            cumulativeTime += timePassed;
            update(timePassed);
            Graphics2D g = s.getGraphics();
            draw(g);
            g.dispose();
            s.update();
        }
    }

    public void run(DisplayMode dm) {
        try {
            s = new ScreenManager();
            if (dm == null)
                dm = s.getCurrentDisplayMode();
            s.setFullScreen(dm);
            init();
            loop();
        } finally {
            s.restoreScreen();
        }
    }

    public void stop() {
        running = false;
    }
}
