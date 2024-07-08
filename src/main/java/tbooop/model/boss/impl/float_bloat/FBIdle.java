package tbooop.model.boss.impl.float_bloat;

import tbooop.commons.api.Point2d;
import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.boss.impl.float_bloat.FBSM.State;
import tbooop.model.boss.stateMachine.impl.AbstractState;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.enemy.impl.ai.ChasingAi;
import tbooop.model.player.api.Player;

public class FBIdle extends AbstractState<FBSM.State> {

    private static final long TIME_BETWEEN_CHARGES = 2500;
    AbstractBoss<State> boss;
    Player p;
    MovementAi ai;
    private long timeSinceLastCharge;

    public FBIdle(AbstractBoss<State> boss, Player p) {
        super(boss);
        this.boss = boss;
        this.p = p;
        ai = new ChasingAi(p);
    }

    @Override
    public void onStateEnter() {
        super.onStateEnter();
        timeSinceLastCharge = 0;
    }

    @Override
    public void updateState(long deltaTime) {
        timeSinceLastCharge += deltaTime;
        Point2d nextPos = ai.newPosition(boss.getPosition(), deltaTime, boss.getVelocity());
        boss.setPosition(nextPos);
    }

    @Override
    public State getNextStateKey() {
        if (timeSinceLastCharge >= TIME_BETWEEN_CHARGES) {
            return State.CHARGING_UP;
        }
        return State.IDLE;
    }

}
