import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class ScreenManager {

    private final GraphicsDevice vc;

    public ScreenManager() {
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        vc = e.getDefaultScreenDevice();
    }

    public DisplayMode getCurrentDisplayMode() { return vc.getDisplayMode(); }

    public Window getFullScreenWindow() { return vc.getFullScreenWindow(); }

    public Graphics2D getGraphics() {
        Window w = vc.getFullScreenWindow();
        if (w != null) {
            BufferStrategy s = w.getBufferStrategy();
            return (Graphics2D)s.getDrawGraphics();
        } else return null;
    }

    public int getHeight() { return java.util.Optional.of(vc.getFullScreenWindow().getHeight()).orElse(0); }

    public int getWidth() { return java.util.Optional.of(vc.getFullScreenWindow().getWidth()).orElse(0); }

    public void restoreScreen() {
        Window w = vc.getFullScreenWindow();
        if (w != null)
            w.dispose();
        vc.setFullScreenWindow(null);
    }

    public void setFullScreen(DisplayMode dm) {
        JFrame f = new JFrame();
        f.setUndecorated(true);
        f.setIgnoreRepaint(true);
        f.setResizable(false);
        vc.setFullScreenWindow(f);

        if (dm != null && vc.isDisplayChangeSupported())
            try {
                vc.setDisplayMode(dm);
            } catch (Exception ignored) {}
        f.createBufferStrategy(2);
    }

    public void update() {
        Window w = vc.getFullScreenWindow();
        if (w != null) {
            BufferStrategy s = w.getBufferStrategy();
            if (!s.contentsLost())
                s.show();
        }
    }
}
