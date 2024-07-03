package tbooop.model.boss.api;

import tbooop.commons.api.Health;
import tbooop.commons.api.RoomBounds;
import tbooop.model.boss.stateMachine.api.AbstractStateMachine;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.AbstractEntity;
import tbooop.model.enemy.api.EnemyType;
import tbooop.model.player.api.Player;

public abstract class AbstractBoss<EState extends Enum<EState>> extends AbstractEntity implements Boss {

    private AbstractStateMachine<EState> stateMachine;

    public AbstractBoss(Health health, double velocity, double colliderRadius) {
        super(RoomBounds.CENTER, health, velocity, GameTag.ENEMY, colliderRadius);
    }

    protected void setStateMachine(AbstractStateMachine<EState> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void updateState(long deltaTime) {
        super.updateState(deltaTime);
        stateMachine.update(deltaTime);
    }

    /** all bosses should deal contact damage. */
    @Override
    public void onPlayerCollision(final Player player) {
        super.onPlayerCollision(player);
        player.takeDamage(1);
    }

    @Override
    public EnemyType getEnemyType() {
        return EnemyType.DUKE_OF_EYES;
    }

}
