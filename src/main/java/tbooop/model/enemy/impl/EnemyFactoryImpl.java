package tbooop.model.enemy.impl;

import tbooop.commons.HealthImpl;
import tbooop.commons.Point2d;
import tbooop.model.core.api.movable.Damageable;
import tbooop.model.enemy.api.Enemy;
import tbooop.model.enemy.api.EnemyFactory;
import tbooop.model.enemy.impl.ai.ChasingAi;

/**
 * Factory of enemies, its method create and return a particular
 * configuration of an enemy object.
 */
public class EnemyFactoryImpl implements EnemyFactory {

    private static final int MELEE_HP = 3;
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
        return new Melee(new BaseEnemy(new Point2d(0, 0),
            new HealthImpl(MELEE_HP), 1, new ChasingAi(this.player)));
    }

}
