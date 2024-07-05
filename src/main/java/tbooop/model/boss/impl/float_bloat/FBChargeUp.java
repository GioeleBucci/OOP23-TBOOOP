package tbooop.model.boss.impl.float_bloat;

import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.boss.impl.float_bloat.FBSM.State;
import tbooop.model.boss.stateMachine.api.AbstractState;

public class FBChargeUp extends AbstractState<FBSM.State> {

    private static final long CHARGE_UP_TIME = 1500;
    private long chargeTimer;

    public FBChargeUp(AbstractBoss<State> boss) {
        super(boss);
    }

    @Override
    public void onStateEnter() {
        super.onStateEnter();
        chargeTimer = 0;
    }

    @Override
    public void updateState(long deltaTime) {
        chargeTimer += deltaTime;
    }

    @Override
    public State getNextStateKey() {
        if (chargeTimer > CHARGE_UP_TIME) {
            return pickNextState();
        }
        return State.CHARGING_UP;
    }

    private State pickNextState() {
        // Pick between charge and bounce
        if (Math.random() > .5) {
            return State.CHARGING;
        }
        return State.BOUNCING;
    }

}
