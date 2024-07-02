package tbooop.model.boss.impl.DukeOfEyes;

import tbooop.model.boss.stateMachine.api.AbstractStateMachine;
import tbooop.model.player.api.Player;

import java.util.Map;

public class DoESM extends AbstractStateMachine<DoESM.State> {

    public enum State {
        ROAMING,
        ANGERED;
    }

    public DoESM(DukeOfEyes doe, Player p) {
        super(State.ROAMING);
        setStates(Map.of(State.ROAMING, new DoERoam(doe),
                State.ANGERED, new DoEAngered(doe, p)));
        init();
    }
}
