package tbooop.model.boss.impl.Meaty;

import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.boss.impl.Meaty.MeatySM.State;
import tbooop.model.boss.stateMachine.api.AbstractState;

public class MeatyBase extends AbstractState<MeatySM.State> {

    public MeatyBase(AbstractBoss<State> boss) {
        super(boss);
    }

    @Override
    public void updateState(long deltaTime) {
    }

    @Override
    public State getNextStateKey() {
        return State.BASE;
    }

}
