import java.awt.*;
import java.util.Objects;
import javax.swing.*;

public class Images extends JFrame {

    private Image bg;
    private Image pic;
    private boolean loaded;

    public void run(DisplayMode dm) {
        getContentPane().setBackground(Color.PINK);
        setForeground(Color.WHITE);
        setFont(new Font("Arial",Font.PLAIN,24));
        loaded = false;

        var s = new Screen();
        try {
            s.setFullScreen(dm,this);
            loadPictures();
            try {
                Thread.sleep(5000);
            } catch (Exception ignored) {}
        } finally {
            s.restoreScreen();
        }
    }

    private Image loadPic(String s) {
        return new ImageIcon(Objects.requireNonNull(getClass().getResource(s))).getImage();
    }

    public void loadPictures() {
        bg = loadPic("bg.png");
        pic = loadPic("pic.png");
        loaded = true;
        repaint();
    }

    public void paint(Graphics g) {
        if (g instanceof Graphics2D g2)
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (loaded) {
            g.drawImage(bg,0,0,null);
            g.drawImage(pic,170,180,null);
        }
        super.paint(g);
        g.drawString("This is gonna be awesome",200,200);
    }

    public static void main(String[] args) {
        DisplayMode dm = new DisplayMode(1366,768,32,DisplayMode.REFRESH_RATE_UNKNOWN);
        Images i = new Images();
        i.run(dm);
    }
}