package tbooop.model.boss.stateMachine.api;

import java.util.Map;

/**
 * AbstractStateMachine
 */
public abstract class AbstractStateMachine<EState extends Enum<EState>> {
    private Map<EState, AbstractState<EState>> states;
    private EState currentStateKey;
    private AbstractState<EState> currentState;

    public void update(long deltaTime) {
        EState next = currentState.getNextStateKey();
        if (next != currentStateKey) {
            currentStateKey = next;
            currentState = states.get(currentStateKey);
        }
        currentState.updateState(deltaTime);
    }

    protected void setStates(Map<EState, AbstractState<EState>> states) {
        this.states = states;
    }
}
