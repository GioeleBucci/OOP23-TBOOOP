package tbooop.model.boss.impl.float_bloat;

import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.boss.stateMachine.api.AbstractStateMachine;
import tbooop.model.player.api.Player;

import java.util.Map;

public class FBSM extends AbstractStateMachine<FBSM.State> {

    public enum State {
        IDLE,
        CHARGING_UP,
        CHARGING,
        BOUNCING;
    }

    public FBSM(AbstractBoss<FBSM.State> boss, Player p) {
        super(State.IDLE);
        setStates(Map.of(State.IDLE, new FBIdle(boss, p),
                State.CHARGING_UP, new FBChargeUp(boss),
                State.CHARGING, new FBCharge(boss, p),
                State.BOUNCING, new FBBounce(boss)));
        init();
    }
}
