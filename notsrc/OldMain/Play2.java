import java.awt.*;
import java.util.Objects;
import javax.swing.*;

public class Play2 {

    private Animation a;
    private ScreenManager s;
    private Image bg;

    private static final DisplayMode[] modes1 = {
        new DisplayMode(800,600,24,0),
        new DisplayMode(800,600,16,0),
        new DisplayMode(640,480,32,0),
        new DisplayMode(640,480,24,0),
        new DisplayMode(800,600,32,0),
        new DisplayMode(640,480,16,0),
    };

    private Image loadPic(String s) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(s))).getImage();
    }

    public void loadImages() {
        bg = loadPic("bg.png");
        a = new Animation();
        a.addScene(loadPic("pic.png"),250);
        a.addScene(loadPic("pic2.png"),250);
    }

    public void run () {
        s = new ScreenManager();
        try {
            DisplayMode dm = s.findFirstCompatibleMode(modes1);
            s.setFullScreen(dm);
            loadImages();
            movieLoop();
        } finally {
            s.restoreScreen();
        }
    }

    public void movieLoop() {
        long startTime = System.currentTimeMillis();
        long cumulativeTime = startTime;
        while (cumulativeTime - startTime < 6000) {
            long timePassed = System.currentTimeMillis() - cumulativeTime;
            cumulativeTime += timePassed;
            a.update(timePassed);

            Graphics2D g = s.getGraphics();
            draw(g);
            g.dispose();
            s.update();

            try {
                Thread.sleep(20);
            } catch (Exception ignored) {}
        }
    }

    public void draw(Graphics g) {
        g.drawImage(bg, 0, 0, null);
        g.drawImage(a.getImage(),0,0,null);
    }

    public static void main(String[] args) {
        Play2 p = new Play2();
        p.run();
    }
}
