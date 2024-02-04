package tbooop.model.enemy.impl;

import java.util.Objects;

import tbooop.commons.HealthImpl;
import tbooop.commons.Point2dImpl;
import tbooop.commons.Point2ds;
import tbooop.model.core.api.movable.Damageable;
import tbooop.model.enemy.api.Enemy;
import tbooop.model.enemy.api.EnemyFactory;
import tbooop.model.enemy.api.EnemyType;
import tbooop.model.enemy.impl.ai.ChasingAi;
import tbooop.model.enemy.impl.ai.LinearAi;

/**
 * Factory of enemies, its method create and return a particular
 * configuration of an enemy object.
 */
public class EnemyFactoryImpl implements EnemyFactory {

    private static final int MELEE_HP = 3;
    private static final double MELEE_SPEED = 0.01;
    private static final int SHOOTER_HP = 3;
    private static final double SHOOTER_SPEED = 0.1;
    private final Damageable player;

    /**
     * Creates an instance of a factory of enemies.
     * 
     * @param player the player that will be attacked by the enemies
     */
    public EnemyFactoryImpl(final Damageable player) {
        this.player = player;
    }

    /** {@inheritDoc} */
    @Override
    public Enemy melee() {
        return new Melee(new BaseEnemy(new Point2dImpl(0, 0),
            new HealthImpl(MELEE_HP), MELEE_SPEED, new ChasingAi(this.player),
            EnemyType.MELEE));
    }

    /** {@inheritDoc} */
    @Override
    public Enemy shooter(final Point2ds initialDirection) {
        return new Shooter(new BaseEnemy(new Point2dImpl(0, 0),
            new HealthImpl(SHOOTER_HP), SHOOTER_SPEED,
            new LinearAi(Objects.requireNonNull(initialDirection)),
            EnemyType.SHOOTER), this.player);
    }

}
