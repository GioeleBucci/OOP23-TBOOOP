package tbooop.model.boss.impl.DukeOfEyes;

import tbooop.model.boss.stateMachine.api.AbstractStateMachine;
import java.util.Map;

public class DoESM extends AbstractStateMachine<DoESM.State> {

    public enum State {
        ROAMING,
        ANGERED;
    }

    public DoESM(DukeOfEyes dukeOfEyes) {
        setStates(Map.of(State.ROAMING, new DoERoam(),
                State.ANGERED, new DoEAngered(dukeOfEyes)));
    }
}
