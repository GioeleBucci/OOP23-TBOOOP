package tbooop.commons.api;

import tbooop.commons.impl.Vector2dImpl;
import java.util.Random;

public class Point2dUtils {

    public static Point2d random() {
        var rand = new Random();
        return new Vector2dImpl(
                rand.nextDouble(-1, 1),
                rand.nextDouble(-1, 1))
                .normalize().toP2d();
    }
}
