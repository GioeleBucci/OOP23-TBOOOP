package tbooop.commons.api;

import tbooop.commons.impl.Vector2dImpl;
import java.util.Random;

public class Vector2dUtils {

    public static Vector2d randomNorm() {
        var rand = new Random();
        return new Vector2dImpl(
                rand.nextDouble(-1, 1),
                rand.nextDouble(-1, 1))
                .normalize();
    }

    public static Vector2d directionTowards(Point2d from, Point2d to) {
        return to.subtract(from).toV2d().normalize();
    }
}
