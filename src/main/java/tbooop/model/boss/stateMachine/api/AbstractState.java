package tbooop.model.boss.stateMachine.api;

import tbooop.model.boss.api.AbstractBoss;

public abstract class AbstractState<EState extends Enum<EState>> {

    private AbstractBoss<EState> boss;

    public AbstractState(AbstractBoss<EState> boss) {
        this.boss = boss;
    }

    public void onStateEnter() { }

    public abstract void updateState(long deltaTime);

    public abstract EState getNextStateKey();

    protected AbstractBoss<EState> getBoss() {
        return boss;
    }
}
