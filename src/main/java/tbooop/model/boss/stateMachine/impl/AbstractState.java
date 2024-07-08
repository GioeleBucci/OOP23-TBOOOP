package tbooop.model.boss.stateMachine.impl;

import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.boss.stateMachine.api.State;

/**
 * Abstract class for boss states.
 *
 * @param <EState> the enum type of the states, specified by the state machine
 *                 where this state is used
 */
public abstract class AbstractState<EState extends Enum<EState>> implements State<EState> {

    private AbstractBoss<EState> boss;

    protected AbstractState(AbstractBoss<EState> boss) {
        this.boss = boss;
    }

    @Override
    public void onStateEnter() {
    }

    @Override
    public abstract void updateState(long deltaTime);

    @Override
    public abstract EState getNextStateKey();

    protected AbstractBoss<EState> getBoss() {
        return boss;
    }
}
