import java.awt.*;
import java.awt.event.*;

public class MouseInput extends Core implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

    private String message;

    public synchronized void draw(Graphics2D g) {
        Window w = s.getFullScreenWindow();
        g.setColor(w.getBackground());
        g.fillRect(0,0,w.getWidth(),w.getHeight());
        g.setColor(w.getForeground());
        g.drawString(message,40,50);
    }
    
    public void init() {
        super.init();
        Window w = s.getFullScreenWindow();
        w.addMouseListener(this);
        w.addMouseMotionListener(this);
        w.addMouseWheelListener(this);
        w.setFocusTraversalKeysEnabled(false);
        w.addKeyListener(this);
        message = "Press Esc to exit";
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode != KeyEvent.VK_ESCAPE) {
            message = "Pressed: "+KeyEvent.getKeyText(keyCode);
            e.consume();
        } else stop();
    }

    public void keyReleased(KeyEvent e) {
        message = "Released: "+KeyEvent.getKeyText(e.getKeyCode());
        e.consume();
    }
    
    public void keyTyped(KeyEvent e) { e.consume(); }

    public void mouseClicked(MouseEvent e) {}

    public void mouseDragged(MouseEvent e) { message = "You are dragging the mouse"; }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseMoved(MouseEvent e) { message = "You are moving the mouse"; }

    public void mousePressed(MouseEvent e) { message = "You pressed down the mouse"; }

    public void mouseReleased(MouseEvent e) { message = "You released the mouse"; }

    public void mouseWheelMoved(MouseWheelEvent e) { message = "Mouse wheel moving"; }

    public void update(long timePassed) {}
}
