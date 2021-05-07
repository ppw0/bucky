import java.awt.Image;

public class Sprite {

    private final Animation a;
    private float x, y, vx, vy;

    public Sprite(Animation a) { this.a = a;}

    public float getX() { return x; }

    public float getY() { return y; }

    public float getVx() { return vx; }

    public float getVy() { return vy; }

    public void setVx(float vx) { this.vx = vx; }

    public void setVy(float vy) { this.vy = vy; }

    public int getWidth() { return a.getImage().getWidth(null); }

    public int getHeight() { return a.getImage().getHeight(null); }

    public Image getImage() { return a.getImage(); }

    public void update(long timePassed) {
        x += vx * timePassed;
        y += vy * timePassed;
        a.update(timePassed);
    }
}