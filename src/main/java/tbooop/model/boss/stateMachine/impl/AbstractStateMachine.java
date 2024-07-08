package tbooop.model.boss.stateMachine.impl;

import java.util.Map;

import tbooop.model.boss.stateMachine.api.State;

/**
 * The StateMachine interface represents a state machine that can be used to
 * manage different states.
 * It provides methods to update the state machine, set the states, and
 * initialize the state machine.
 *
 * @param <EState> the type of the states in the state machine
 */
public abstract class AbstractStateMachine<EState extends Enum<EState>> {
    private Map<EState, AbstractState<EState>> states;
    private EState currentStateKey;
    private State<EState> currentState;

    /**
     * Creates a new state machine with the given initial state.
     *
     * @param initialState the initial state of the state machine
     */
    protected AbstractStateMachine(EState initialState) {
        this.currentStateKey = initialState;
    }

    /**
     * Updates the state machine based on the elapsed time since the last update.
     * <br>
     * </b>
     * <b>NOTE</b>: This method should be called only after the states have
     * been set and the state machine has been initialized.
     *
     * @param deltaTime the elapsed time since the last update in milliseconds
     */
    public void update(long deltaTime) {
        EState next = currentState.getNextStateKey();
        if (next != currentStateKey) {
            changeState(next);
        }
        currentState.updateState(deltaTime);
    }

    /**
     * Sets the states of the state machine.
     * <br>
     * </br>
     * <b>NOTE</b>: This method should be called <b>before</b> the init
     * method.
     *
     * @param states a map of states where the key is the state enum and the value
     *               is the corresponding state object
     * 
     * @see #init()
     */
    protected void setStates(Map<EState, AbstractState<EState>> states) {
        this.states = states;
    }

    /**
     * Initializes the state machine.
     * <br>
     * </br>
     * <b>NOTE</b>: This method should be called only <b>after</b> the setStates
     * method.
     * 
     * @see #setStates(Map)
     */
    protected void init() {
        changeState(currentStateKey);
    }

    private void changeState(EState newStateKey) {
        currentStateKey = newStateKey;
        currentState = states.get(currentStateKey);
        currentState.onStateEnter();
    }
}
