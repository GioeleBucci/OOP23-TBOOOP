package tbooop.model.boss.stateMachine.api;

import java.util.Map;

/**
 * AbstractStateMachine
 */
public abstract class AbstractStateMachine<EState extends Enum<EState>> {
    private Map<EState, AbstractState<EState>> states;
    private EState currentStateKey;
    private AbstractState<EState> currentState;

    public AbstractStateMachine(EState initialState) {
        this.currentStateKey = initialState;
    }

    public void update(long deltaTime) {
        EState next = currentState.getNextStateKey();
        if (next != currentStateKey) {
            changeState(next);
        }
        currentState.updateState(deltaTime);
    }

    private void changeState(EState newStateKey) {
        currentStateKey = newStateKey;
        currentState = states.get(currentStateKey);
        currentState.onStateEnter();
    }

    protected void setStates(Map<EState, AbstractState<EState>> states) {
        this.states = states;
    }

    protected void init(){
        changeState(currentStateKey);
    }
}
