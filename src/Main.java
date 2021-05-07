import java.awt.*;

public class Main {

    public static void main(String[] args) {

        // run tests

        new Movie().run(new DisplayMode(1366,768,32,DisplayMode.REFRESH_RATE_UNKNOWN));

        new MouseLook().run(null);

        new MouseInput().run(null);
    }
}
