import java.awt.*;
import java.util.Objects;
import javax.swing.*;

public class Play1 {

    private Animation a;
    private Image bg;
    private Screen s;

    private Image loadPic(String s) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(s))).getImage();
    }

    public void loadPictures() {
        bg = loadPic("bg.png");
        a = new Animation();
        a.addScene(loadPic("pic.png"),50);
        a.addScene(loadPic("pic2.png"),50);
    }

    public void run(DisplayMode dm) {
        s = new Screen();
        try {
            s.setFullScreen(dm, new JFrame());
            loadPictures();
            movieLoop();
        } finally {
            s.restoreScreen();
        }
    }

    public void movieLoop() {
        long startingTime = System.currentTimeMillis();
        long cumulativeTime = startingTime;

        while (cumulativeTime - startingTime < 5000) {
            long timePassed = System.currentTimeMillis() - cumulativeTime;
            cumulativeTime += timePassed;
            a.update(timePassed);

            Graphics g = s.getFullScreenWindow().getGraphics();
            draw(g);
            g.dispose();

            try {
                Thread.sleep(20);
            } catch (Exception ignored) {}
        }
    }

    public void draw(Graphics g) {
        Image temp = a.getImage();
        try {
            Thread.sleep(20);
        } catch (Exception ignored) {}
        g.drawImage(bg,0,0,null);
        g.drawImage(temp,0,0,null);
    }

    public static void main(String[] args) {
        DisplayMode dm = new DisplayMode(1366,768,32,DisplayMode.REFRESH_RATE_UNKNOWN);
        Play1 p = new Play1();
        p.run(dm);
    }
}
