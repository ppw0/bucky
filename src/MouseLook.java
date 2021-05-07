import java.awt.*;
import java.awt.event.*;

public class MouseLook extends Core implements MouseMotionListener, KeyListener {

    private Image bg;
    private Robot robot;
    private Point mouse, center, image;
    private boolean centering;

    public synchronized void draw(Graphics2D g) {
        int w = s.getWidth(),
            h = s.getHeight(),
            x = Math.floorMod(image.x,w),
            y = Math.floorMod(image.y,h);

        g.drawImage(bg,x,y,null);
        g.drawImage(bg,x-w,y,null);
        g.drawImage(bg,x,y-h,null);
        g.drawImage(bg,x-w,y-h,null);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            stop();
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public void init() {
        super.init();
        mouse = new Point();
        center = new Point();
        image = new Point();
        centering = false;

        try {
            robot = new Robot();
            recenterMouse();
            mouse.x = center.x;
            mouse.y = center.y;
        } catch (Exception ignored) {}

        Window w = s.getFullScreenWindow();
        w.addMouseMotionListener(this);
        w.addKeyListener(this);
        bg = loadImage("bg.png");
    }

    public void mouseDragged(MouseEvent e) { mouseMoved(e); }

    public synchronized void mouseMoved(MouseEvent e) {
        if (centering && center.x == e.getX() && center.y == e.getY())
            centering = false;
        else {
            image.x += (e.getX() - mouse.x);
            image.y += (e.getY() - mouse.y);
            recenterMouse();
        }
        mouse.x = e.getX();
        mouse.y = e.getY();
    }

    private synchronized void recenterMouse() {
        Window w = s.getFullScreenWindow();
        if (robot != null && w.isShowing()) {
            center.x = w.getWidth()/2;
            center.y = w.getHeight()/2;
            javax.swing.SwingUtilities.convertPointToScreen(center,w);
            centering = true;
            robot.mouseMove(center.x, center.y);
        }
    }

    public void update(long x) {}
}
