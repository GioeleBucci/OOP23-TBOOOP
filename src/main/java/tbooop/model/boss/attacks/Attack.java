package tbooop.model.boss.attacks;

import tbooop.commons.api.Direction;
import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;
import tbooop.commons.api.Vector2dUtils;
import tbooop.commons.impl.Vector2dImpl;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.enemy.impl.EnemyProjectile;
import java.util.Random;

import java.util.List;
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

    public static void radius(Entity source, double projSpeed, int projectileAmount) {
        radiusWithGap(source, projSpeed, projectileAmount, 0);
    }

    public static void radiusWithGap(Entity source, double projSpeed, int projectileAmount, int gapSize) {
        double angle = 360 / projectileAmount;
        List<Double> angles = new ArrayList<>();
        for (int i = 0; i < projectileAmount; i++) {
            angles.add(i * angle);
        }
        removeUpToNElements(angles, gapSize).forEach(a -> source.addProjectile(
                new EnemyProjectile(Direction.RIGHT.toP2d().toV2d().rotate(a).normalize(),
                        source.getPosition(), projSpeed)));
    }

    private static <T> List<T> removeUpToNElements(List<T> list, int n) {
        if (list == null || list.isEmpty() || n <= 0) {
            return list;
        }
        int size = list.size();
        Random random = new Random();
        int startIndex = random.nextInt(size);
        List<T> result = new ArrayList<>();
        for (int i = 0; i < size - n; i++) {
            result.add(list.get((startIndex + i) % size));
        }
        return result;
    }
}
