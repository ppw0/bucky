import java.awt.*;
import javax.swing.JFrame;

public class ScreenTest extends JFrame {

    public static void main(String[] args) {
        DisplayMode dm = new DisplayMode(1920,1080,32, DisplayMode.REFRESH_RATE_UNKNOWN);
        ScreenTest st = new ScreenTest();
        st.run(dm);
    }

    public void run(DisplayMode dm) {
        setBackground(Color.PINK);
        setForeground(Color.WHITE);
        setFont(new Font("Arial",Font.PLAIN,24));

        Screen s = new Screen();
        try {
            s.setFullScreen(dm,this);
            try {
                Thread.sleep(5000);
            } catch (Exception ignored) {}
        } finally {
            s.restoreScreen();
        }
    }

    public void paint(Graphics g) {
        if (g instanceof Graphics2D g2)
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawString("This is gonna be awesome",200,200);
    }

}