import java.awt.*;
import java.awt.event.*;

public class Movie extends Core implements KeyListener {

    private Image bg;
    private Sprite sprite;

    public void draw(Graphics2D g) {
        g.drawImage(bg, 0, 0, null);
        g.drawImage(sprite.getImage(), Math.round(sprite.getX()), Math.round(sprite.getY()), null);
    }

    public void init() {
        super.init();

        s.getFullScreenWindow().addKeyListener(this);
        bg = loadImage("bg.png");

        Animation a = new Animation();
        a.addScene(loadImage("pic.png"),250);
        a.addScene(loadImage("pic2.png"),250);

        sprite = new Sprite(a);
        sprite.setVx(1.2f); // 1.2 pixels/millisecond
        sprite.setVy(0.8f);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            stop();
    }

    public void keyReleased(KeyEvent e) {}

    public void update(long timePassed) {
        if (sprite.getX() < 0)
            sprite.setVx(Math.abs(sprite.getVx()));
        else if (sprite.getX() + sprite.getWidth() >= s.getWidth())
            sprite.setVx(-Math.abs(sprite.getVx()));

        if (sprite.getY() < 0)
            sprite.setVy(Math.abs(sprite.getVy()));
        else if (sprite.getY() + sprite.getHeight() >= s.getHeight())
            sprite.setVy(-Math.abs(sprite.getVy()));

        sprite.update(timePassed);
    }
}