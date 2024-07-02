package tbooop.model.boss.api;

import tbooop.commons.api.Health;
import tbooop.commons.api.RoomBounds;
import tbooop.model.boss.stateMachine.api.AbstractStateMachine;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.api.movable.AbstractEntity;

public abstract class AbstractBoss<EState extends Enum<EState>> extends AbstractEntity {

    private AbstractStateMachine<EState> stateMachine;

    public AbstractBoss(Health health, double velocity, double colliderRadius) {
        super(RoomBounds.CENTER, health, velocity, GameTag.BOSS, colliderRadius);
    }

    public void setStateMachine(AbstractStateMachine<EState> stateMachine) {
        this.stateMachine = stateMachine;
    }

    @Override
    public void updateState(long deltaTime) {
        stateMachine.update(deltaTime);
    }

}
