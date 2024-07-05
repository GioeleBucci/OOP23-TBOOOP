package tbooop.commons.api;

import tbooop.commons.impl.Vector2dImpl;
import java.util.Random;

public class MovementUtils {

    private MovementUtils() {
    }

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

    public static Point2d move(Point2d from, Vector2d direction, double velocity, long deltaTime) {
        return direction
                .scalarMultiply(velocity * deltaTime)
                .toP2d()
                .add(from);
    }

    /**
     * Move towards a certain point
     * 
     * @param from      the starting position
     * @param to        the target position
     * @param velocity  the velocity
     * @param deltaTime the time (in ms)
     * @return the new position
     */
    public static Point2d moveTowards(Point2d from, Point2d to, double velocity, long deltaTime) {
        return move(from, directionTowards(from, to), velocity, deltaTime);
    }
}
