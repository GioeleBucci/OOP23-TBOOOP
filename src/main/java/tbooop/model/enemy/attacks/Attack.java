package tbooop.model.enemy.attacks;

import tbooop.commons.api.Direction;
import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;
import tbooop.commons.api.MovementUtils;
import tbooop.commons.impl.Point2dImpl;
import tbooop.commons.impl.Vector2dImpl;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.enemy.impl.EnemyProjectile;
import java.util.Random;

import java.util.List;
import java.util.stream.IntStream;
import java.util.ArrayList;

/**
 * The Attack class contains static methods for performing various enemy
 * attacks.
 */
public class Attack {

    private Attack() {
    }

    private static final Random r = new Random();

    /**
     * Istantiates a single projectile that goes straight for the target.
     *
     * @param source    the entity performing the attack
     * @param target    the target position
     * @param projSpeed the projectile speed
     */
    public static void snipe(Entity source, Point2d target, double projSpeed) {
        shotgun(source, target, projSpeed, 1, 0);
    }

    /**
     * Istantiates multiple projectiles with random scatter angles.
     * This attack gets instantiated with a single call.
     *
     * @param source      the entity performing the attack
     * @param target      the target position
     * @param projSpeed   the projectile speed
     * @param projAmount  the number of projectiles to create
     * @param spreadAngle the maximum scatter angle
     */
    public static void shotgun(Entity source, Point2d target, double projSpeed, int projAmount, double spreadAngle) {
        Vector2d dir = MovementUtils.directionTowards(source.getPosition(), target);
        double angleOffset = spreadAngle / projAmount;
        for (int i = 0; i < projAmount; i++) {
            Vector2d newDir = dir.rotate((i * angleOffset) % 360).normalize();
            source.addProjectile(new EnemyProjectile(newDir, source.getPosition(), projSpeed));
        }
    }

    /**
     * Istantiates a single projectile with random scatter angle and velocity.
     * Needs to be called multiple times in different frames to create the effect.
     *
     * @param source                the entity performing the attack
     * @param target                the target position
     * @param maxScatterAngle       the maximum scatter angle
     * @param maxprojSpeed          the maximum projectile speed
     * @param maxProjSpeedVariation the variation in projectile speed
     */
    public static void vomit(Entity source, Point2d target,
            double maxScatterAngle, double maxprojSpeed, double maxProjSpeedVariation) {
        double scatterAngle = r.nextDouble(-maxScatterAngle, maxScatterAngle);
        Vector2d vec = new Vector2dImpl(target
                .subtract(source.getPosition()).toV2d()).normalize();
        double speed = r.nextDouble(maxprojSpeed - (maxProjSpeedVariation / 100 * maxprojSpeed), maxprojSpeed);
        EnemyProjectile proj = new EnemyProjectile(vec.rotate(scatterAngle), source.getPosition(), speed);
        source.addProjectile(proj);
    }

    /**
     * Performs a spiral attack by creating a projectile that moves in a spiral
     * pattern.
     * This instantiates a single projectile: to create the effect this method needs
     * to be called multiple times in different frames.
     * 
     * @param source    the entity performing the attack
     * @param projSpeed the projectile speed
     * @param direction the direction (feed the result of this method back
     *                  to the next call to create the spiral effect)
     * @param angle     the rotation angle
     * @return the next direction, to use for the next method call
     */
    public static Vector2d spiral(Entity source, double projSpeed, Vector2d direction, double angle) {
        Vector2d newDir = direction.rotate(angle).normalize();
        EnemyProjectile proj = new EnemyProjectile(newDir, source.getPosition(), projSpeed);
        source.addProjectile(proj);
        return newDir;
    }

    /**
     * Performs a multi-spiral attack by creating multiple spirals of projectiles.
     * To create the effect this method needs to be called multiple times in
     * different frames.
     *
     * @param source        the entity performing the attack
     * @param projSpeed     the projectile speed
     * @param direction     the direction
     * @param angle         the angle of rotation
     * @param spiralsAmount the number of spirals to create
     * @return the next direction, to use for the next method call
     * 
     * @see #spiral(Entity, Double, Vector2d, Double) spiral
     */
    public static Vector2d multiSpiral(Entity source, double projSpeed, Vector2d direction, double angle,
            int spiralsAmount) {
        double angleBetweenSpirals = 360. / spiralsAmount;
        for (int i = 0; i < spiralsAmount; i++) {
            spiral(source, projSpeed, direction.rotate(angleBetweenSpirals * i), angle);
        }
        return direction.rotate(angle);
    }

    /**
     * Performs a circle attack by creating projectiles in a circular pattern around
     * a target.
     * This attack gets instantiated with a single call.
     *
     * @param source     the entity performing the attack
     * @param target     the target position
     * @param radius     the radius of the circle
     * @param projAmount the number of projectiles to create
     * @param projSpeed  the projectile speed
     */
    public static void circle(Entity source, Point2d target, double radius, int projAmount, double projSpeed) {
        Vector2d dir = MovementUtils.directionTowards(source.getPosition(), target);
        Point2d center = source.getPosition().add(dir.toP2d().mul(radius));
        double angle = 360. / projAmount;
        double randomAngleOffset = r.nextDouble(angle);
        for (int i = 0; i < projAmount; i++) {
            Point2d spawnPoint = center.add(new Vector2dImpl(0, radius)
                    .rotate((angle * i + randomAngleOffset) % 360)
                    .toP2d());
            source.addProjectile(new EnemyProjectile(dir, spawnPoint, projSpeed));
        }
    }

    /**
     * Performs an attack closing in on the target by creating projectiles in a
     * circular pattern around it directed towards the center.
     * This attack gets instantiated with a single call.
     *
     * @param source     the entity performing the attack
     * @param target     the target position
     * @param radius     the radius of the circle
     * @param projAmount the number of projectiles to create
     * @param projSpeed  the projectile speed (consider putting a lower value since
     *                   the projectiles don't originate from an enemy but are put
     *                   around the target instead)
     */
    public static void closeIn(Entity source, Point2d target, double radius, int projAmount, double projSpeed) {
        Point2d center = target;
        double angle = 360. / projAmount;
        double randomAngleOffset = r.nextDouble(angle);
        for (int i = 0; i < projAmount; i++) {
            Point2d spawnPoint = center.add(new Vector2dImpl(0, radius)
                    .rotate((i * angle + randomAngleOffset) % 360)
                    .toP2d());
            source.addProjectile(new EnemyProjectile(
                    MovementUtils.directionTowards(spawnPoint, target),
                    spawnPoint,
                    projSpeed));
        }
    }

    /**
     * Performs a ring attack by creating projectiles in a circular pattern.
     * This attack gets instantiated with a single call.
     *
     * @param source           the entity performing the attack
     * @param projSpeed        the projectile speed
     * @param projectileAmount the number of projectiles to create
     */
    public static void ring(Entity source, double projSpeed, int projectileAmount) {
        ringWithGap(source, Point2dImpl.ZERO, 0, projSpeed, projectileAmount, 0);
    }

    /**
     * Performs a ring attack with a gap by creating projectiles in a circular
     * pattern, with a gap in the direction of the target (the gap can also be
     * offset, relatively to the target).
     * This attack gets instantiated with a single call.
     *
     * @param source               the entity performing the attack
     * @param target               the target position
     * @param maxGapDeviationAngle the maximum deviation angle of the gap, relative
     *                             to the target
     * @param projSpeed            the projectile speed
     * @param projectileAmount     the number of projectiles to create
     * @param gapSize              the size of the gap, as the amount of projectiles
     *                             to remove from the ring
     */
    public static void ringWithGap(Entity source, Point2d target, double maxGapDeviationAngle,
            double projSpeed, int projectileAmount, int gapSize) {
        double angle = 360. / projectileAmount;
        double randomAngleOffset = r.nextDouble(angle);
        List<Double> angles = new ArrayList<>();
        for (int i = 0; i < projectileAmount; i++) {
            angles.add((i * angle + randomAngleOffset) % 360);
        }
        Vector2d targetVec = MovementUtils.directionTowards(source.getPosition(), target);
        double scatterAngle = maxGapDeviationAngle == 0 ? 0
                : (targetVec.getAngle()
                        + r.nextDouble(-maxGapDeviationAngle, maxGapDeviationAngle)) % 360;
        removeUpToNElements(angles, gapSize, scatterAngle).forEach(a -> source.addProjectile(
                new EnemyProjectile(Direction.RIGHT.toP2d().toV2d().rotate(a).normalize(),
                        source.getPosition(), projSpeed)));
    }

    /**
     * Removes up to N elements from a list based on a given angle.
     *
     * @param list  the list of angles
     * @param n     the maximum number of elements to remove
     * @param angle the reference angle
     * @return the list with up to N elements removed
     */
    static List<Double> removeUpToNElements(List<Double> list, int n, double angle) {
        if (list == null || list.isEmpty() || n <= 0) {
            return list;
        }
        int size = list.size();
        if (n >= list.size()) {
            return List.of();
        }
        int closestIndex = findClosestAngleIndex(list, angle);
        int startIndex = (closestIndex - (n / 2) + size) % size;
        List<Double> removedElements = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double elem = list.get((startIndex + i) % size);
            removedElements.add(elem);
        }
        return list.stream().filter(e -> !removedElements.contains(e)).toList();
    }

    /**
     * Finds the index of the angle in the list that is closest to the given angle.
     *
     * @param list  the list of angles
     * @param angle the reference angle
     * @return the index of the closest angle
     */
    static int findClosestAngleIndex(List<Double> list, double angle) {
        return IntStream.range(0, list.size())
                .boxed()
                .min((i, j) -> Double.compare(Math.abs(list.get(i) - angle), Math.abs(list.get(j) - angle)))
                .get();
    }
}
