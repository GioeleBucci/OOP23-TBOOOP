package tbooop.model.enemy.attacks;

import tbooop.commons.api.Direction;
import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;
import tbooop.commons.api.Vector2dUtils;
import tbooop.commons.impl.Point2dImpl;
import tbooop.commons.impl.Vector2dImpl;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.enemy.impl.EnemyProjectile;
import java.util.Random;

import java.util.List;
import java.util.stream.IntStream;
import java.util.ArrayList;

/** scatter angle is max rotation degrees */
public class Attack {

    public static void snipe(Entity source, Point2d target, double projSpeed) {
        Vector2d dir = Vector2dUtils.directionTowards(source.getPosition(), target);
        source.addProjectile(new EnemyProjectile(dir, source.getPosition(), projSpeed));
    }

    public static void vomit(Entity source, Point2d target,
            double maxScatterAngle, double maxprojSpeed, double projSpeedVariation) {
        Random r = new Random();
        double scatterAngle = r.nextDouble(-maxScatterAngle, maxScatterAngle);
        Vector2d vec = new Vector2dImpl(target
                .subtract(source.getPosition()).toV2d()).normalize();
        double speed = r.nextDouble(maxprojSpeed - (projSpeedVariation / 100 * maxprojSpeed), maxprojSpeed);
        EnemyProjectile proj = new EnemyProjectile(vec.rotate(scatterAngle), source.getPosition(), speed);
        source.addProjectile(proj);
    }

    /** Returns the next direction */
    public static Vector2d spiral(Entity source, double projSpeed, Vector2d direction, double angle) {
        Vector2d newDir = direction.rotate(angle).normalize();
        EnemyProjectile proj = new EnemyProjectile(newDir, source.getPosition(), projSpeed);
        source.addProjectile(proj);
        return newDir;
    }

    public static Vector2d multiSpiral(Entity source, double projSpeed, Vector2d direction, double angle,
            int spiralsAmount) {
        double angleBetweenSpirals = 360 / spiralsAmount;
        for (int i = 0; i < spiralsAmount; i++) {
            spiral(source, projSpeed, direction.rotate(angleBetweenSpirals * i), angle);
        }
        return direction.rotate(angle);
    }

    public static void ring(Entity source, double maxScatterAngle, double projSpeed, int projectileAmount) {
        ringWithGap(source, Point2dImpl.ZERO, maxScatterAngle, projSpeed, projectileAmount, 0);
    }

    public static void ringWithGap(Entity source, Point2d target, double maxScatterAngle,
            double projSpeed, int projectileAmount, int gapSize) {
        double angle = 360 / projectileAmount;
        Random r = new Random();
        double randomAngleOffset = r.nextDouble(angle);
        List<Double> angles = new ArrayList<>();
        for (int i = 0; i < projectileAmount; i++) {
            angles.add((i * angle + randomAngleOffset) % 360);
        }
        Vector2d targetVec = Vector2dUtils.directionTowards(source.getPosition(), target);
        double scatterAngle = maxScatterAngle == 0 ? 0
                : (targetVec.getAngle()
                        + r.nextDouble(-maxScatterAngle, maxScatterAngle)) % 360;
        removeUpToNElements(angles, gapSize, scatterAngle).forEach(a -> source.addProjectile(
                new EnemyProjectile(Direction.RIGHT.toP2d().toV2d().rotate(a).normalize(),
                        source.getPosition(), projSpeed)));
    }

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

    static int findClosestAngleIndex(List<Double> list, double angle) {
        return IntStream.range(0, list.size())
                .boxed()
                .min((i, j) -> Double.compare(Math.abs(list.get(i) - angle), Math.abs(list.get(j) - angle)))
                .get();
    }

    public static void circle(Entity source, Point2d target, double radius, int projAmount, double projSpeed) {
        Vector2d dir = Vector2dUtils.directionTowards(source.getPosition(), target);
        Point2d center = source.getPosition().add(dir.toP2d().mul(radius));
        double angle = 360 / projAmount;
        for (int i = 0; i < projAmount; i++) {
            Point2d spawnPoint = center.add(new Vector2dImpl(0, radius).rotate(angle * i).toP2d());
            source.addProjectile(new EnemyProjectile(dir, spawnPoint, projSpeed));
        }
    }
}
