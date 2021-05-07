import java.awt.*;
import java.awt.event.*;

public class KeyTest extends Core implements KeyListener {

    private String message;

    public synchronized void draw(Graphics2D g) {
        Window w = s.getFullScreenWindow();
        g.setColor(w.getBackground());
        g.fillRect(0,0,w.getWidth(),w.getHeight());
        g.setColor(w.getForeground());
        g.drawString(message,30,30);
    }

    public void init() {
        super.init();
        Window w = s.getFullScreenWindow();
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
        int keyCode = e.getKeyCode();
        message = "Released: "+KeyEvent.getKeyText(keyCode);
        e.consume();
    }

    public void keyTyped(KeyEvent e) { e.consume(); }

    public static void main(String[] args) { new KeyTest().run(); }
}
