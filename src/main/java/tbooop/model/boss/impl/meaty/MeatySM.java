package tbooop.model.boss.impl.meaty;

import tbooop.model.boss.stateMachine.impl.AbstractStateMachine;
import tbooop.model.player.api.Player;
import java.util.Map;

public class MeatySM extends AbstractStateMachine<MeatySM.State> {

    public enum State {
        SPIRAL,
        BURST;
    }

    public MeatySM(Meaty meaty, Player p) {
        super(State.SPIRAL);
        setStates(Map.of(State.BURST, new MeatyBurst(meaty, p),
                State.SPIRAL, new MeatySpiral(meaty, p)));
        init();
    }
}
