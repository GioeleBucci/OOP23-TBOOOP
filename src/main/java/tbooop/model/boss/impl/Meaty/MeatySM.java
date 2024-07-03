package tbooop.model.boss.impl.Meaty;

import tbooop.model.boss.stateMachine.api.AbstractStateMachine;
import tbooop.model.player.api.Player;
import java.util.Map;

public class MeatySM extends AbstractStateMachine<MeatySM.State> {

    public enum State {
        BASE;
    }

    public MeatySM(Meaty meaty, Player p) {
        super(State.BASE);
        setStates(Map.of(State.BASE, new MeatyBase(meaty)));
        init();
    }
}
