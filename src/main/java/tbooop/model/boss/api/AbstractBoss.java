package tbooop.model.boss.api;

import tbooop.commons.api.Health;
import tbooop.commons.api.RoomBounds;
import tbooop.model.boss.stateMachine.impl.AbstractStateMachine;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.AbstractEntity;
import tbooop.model.player.api.Player;
import tbooop.view.sound_manager.Sound;
import tbooop.view.sound_manager.SoundManager;

/**
 * Abstract base class for all bosses.
 */
public abstract class AbstractBoss<EState extends Enum<EState>> extends AbstractEntity implements Boss {

    private AbstractStateMachine<EState> stateMachine;

    /**
     * Constructs a new AbstractBoss object with the specified health, velocity, and
     * collider radius.
     *
     * @param health         the health of the boss
     * @param velocity       the velocity of the boss
     * @param colliderRadius the collider radius of the boss
     */
    protected AbstractBoss(Health health, double velocity, double colliderRadius) {
        super(RoomBounds.CENTER, health, velocity, GameTag.ENEMY, colliderRadius);
    }

    /**
     * Sets the state machine for the boss.
     *
     * @param stateMachine the state machine to set
     */
    protected void setStateMachine(AbstractStateMachine<EState> stateMachine) {
        this.stateMachine = stateMachine;
    }

    private boolean deathSoundPlayed = false;

    @Override
    public void updateState(long deltaTime) {
        super.updateState(deltaTime);
        stateMachine.update(deltaTime);
        if (isDestroyed() && !deathSoundPlayed) {
            SoundManager.getInstance().playSound(Sound.BOSS_DEATH);
            deathSoundPlayed = true;
        }
    }

    /** All bosses should deal contact damage to the player. */
    @Override
    public void onPlayerCollision(final Player player) {
        super.onPlayerCollision(player);
        player.takeDamage(1);
    }
}
