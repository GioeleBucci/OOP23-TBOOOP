package tbooop.model.enemy.impl;

import tbooop.model.enemy.api.Enemy;
import tbooop.model.enemy.api.EnemyDecorator;
import tbooop.model.player.api.Player;

/**
 * a Melee decorator makes the enemy deal more damage when
 * a physical collision with the player happens.
 */
public class Melee extends EnemyDecorator {

    private static final int DAMAGE_AMOUNT = 1;
    private static final long TIME_BETWEEN_HITS = 1000;
    private long timeSinceLastHit;

    /**
     * Creates an instance of a Melee decoration.
     * 
     * @param concreteEnemy the enemy that gets decorated.
     */
    protected Melee(final Enemy concreteEnemy) {
        super(concreteEnemy);
    }

    /** {@inheritDoc} */
    @Override
    public void updateState(final long deltaTime) {
        super.updateState(deltaTime);
        this.timeSinceLastHit += deltaTime;
    }

    /** {@inheritDoc} */
    @Override
    public void onPlayerCollision(final Player player) {
        super.onPlayerCollision(player);
        if (this.timeSinceLastHit >= TIME_BETWEEN_HITS) {
            this.timeSinceLastHit = 0;
            player.takeDamage(DAMAGE_AMOUNT);
        }
    }

}
