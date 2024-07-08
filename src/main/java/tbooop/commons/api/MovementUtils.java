package tbooop.commons.api;

import tbooop.commons.impl.Vector2dImpl;
import java.util.Random;

/**
 * Utility class for movement operations.
 */
public class MovementUtils {

    private static Random rand = new Random();

    private MovementUtils() {
    }

    /**
     * Generates a random normalized vector.
     *
     * @return a random normalized vector
     */
    public static Vector2d randomNorm() {
        return new Vector2dImpl(
                rand.nextDouble(-1, 1),
                rand.nextDouble(-1, 1))
                .normalize();
    }

    /**
     * Calculates the direction vector from one point to another (normalized).
     *
     * @param from the starting point
     * @param to   the target point
     * @return the normalized direction vector
     */
    public static Vector2d directionTowards(Point2d from, Point2d to) {
        return to.subtract(from).toV2d().normalize();
    }

    /**
     * Calculates where a moving point will be after a certain time interval, given
     * its direction and velocity.
     *
     * @param from      the starting position
     * @param direction the direction vector
     * @param velocity  the velocity
     * @param deltaTime the time interval (in ms)
     * @return the new position after the movement
     */
    public static Point2d move(Point2d from, Vector2d direction, double velocity, long deltaTime) {
        return direction
                .scalarMultiply(velocity * deltaTime)
                .toP2d()
                .add(from);
    }

    /**
     * Calculates where a moving point will be after a certain time interval, given
     * the target destination and the velocity.
     *
     * @param from      the starting position
     * @param to        the target position
     * @param velocity  the velocity
     * @param deltaTime the time interval (in ms)
     * @return the new position after the movement towards the target
     */
    public static Point2d moveTowards(Point2d from, Point2d to, double velocity, long deltaTime) {
        return move(from, directionTowards(from, to), velocity, deltaTime);
    }
}
