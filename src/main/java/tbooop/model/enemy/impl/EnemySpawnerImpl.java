package tbooop.model.enemy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import tbooop.commons.Point2dImpl;
import tbooop.commons.Point2ds;
import tbooop.commons.RoomBounds;
import tbooop.commons.Vector2dImpl;
import tbooop.commons.api.Point2d;
import tbooop.model.enemy.api.Enemy;
import tbooop.model.enemy.api.EnemyFactory;
import tbooop.model.enemy.api.EnemySpawner;

/**
 * An EnemySpawnerImpl is an EnemySpawner that uses an EnemyFactory to create enemies.
 * The enemy type and the initial position are set randomly.
 */
public class EnemySpawnerImpl implements EnemySpawner {

    private static final double UPPER_BOUND = RoomBounds.HEIGHT * 0.1;
    private static final double LOWER_BOUND = RoomBounds.HEIGHT * 0.9;
    private static final double LEFT_BOUND = RoomBounds.WIDTH * 0.1;
    private static final double RIGHT_BOUND = RoomBounds.WIDTH * 0.9;
    private static final double MIN_VALUE = -10.0;
    private static final double MAX_VALUE = 10.0;
    private static final int CARDINAL_DIRECTIONS = 4;
    private static final int ENEMY_TYPES = 2;
    private static final int SPAWN_MELEE = 0;
    private static final int SPAWN_SHOOTER = 1;
    private static final int SPAWN_BOWNCER = 2;

    private final Random rand = new Random();
    private final EnemyFactory factory;

    /**
     * Creates an instance of an EnemySpawnerImpl.
     * @param factory the factory used to create enemy objects
     * @throws NullPointerException if factory is null
     */
    public EnemySpawnerImpl(final EnemyFactory factory) {
        this.factory = Objects.requireNonNull(factory);
    }

    /** {@inheritDoc} */
    @Override
    public List<Enemy> getRandomEnemies(final int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }
        final List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            switch (rand.nextInt(0, ENEMY_TYPES)) {
                case SPAWN_MELEE -> enemies.add(factory.melee());
                case SPAWN_SHOOTER -> enemies.add(factory.shooter(this.randomCardinalDirection()));
                case SPAWN_BOWNCER -> enemies.add(factory.bouncer(this.randomDirection()));
                default -> { }
            }
        }
        enemies.forEach(e -> e.setPosition(this.randomPosition()));
        return enemies;
    }

    private Point2d randomDirection() {
        return new Vector2dImpl(
            rand.nextDouble(MIN_VALUE, MAX_VALUE),
            rand.nextDouble(MIN_VALUE, MAX_VALUE))
            .normalize().toP2d();
    }

    private Point2d randomPosition() {
        return new Point2dImpl(
            rand.nextDouble(RIGHT_BOUND, LEFT_BOUND),
            rand.nextDouble(UPPER_BOUND, LOWER_BOUND));
    }

    private Point2ds randomCardinalDirection() {
        final int pick = rand.nextInt(0, CARDINAL_DIRECTIONS + 1);
        return pick == 0 ? Point2ds.UP
            : pick == 1 ? Point2ds.DOWN
            : pick == 2 ? Point2ds.LEFT
            : Point2ds.RIGHT;
    }

}