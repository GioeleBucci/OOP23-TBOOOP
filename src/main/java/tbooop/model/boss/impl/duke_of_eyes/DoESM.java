package tbooop.model.boss.impl.duke_of_eyes;

import tbooop.model.boss.stateMachine.impl.AbstractStateMachine;
import tbooop.model.player.api.Player;

import java.util.Map;

public class DoESM extends AbstractStateMachine<DoESM.State> {

    public enum State {
        ROAMING,
        ANGERED;
    }

    public DoESM(DukeOfEyes doe, Player p) {
        super(State.ROAMING);
        setStates(Map.of(State.ROAMING, new DoERoam(doe, p),
                State.ANGERED, new DoEAngered(doe, p)));
        init();
    }
}
