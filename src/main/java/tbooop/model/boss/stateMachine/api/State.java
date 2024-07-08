package tbooop.model.boss.stateMachine.api;

/**
 * Represents a single state of a state machine.
 *
 * @param <EState> the type of the state enum
 */
public interface State<EState extends Enum<EState>> {

    /**
     * Called when the state is entered.
     */
    void onStateEnter();

    /**
     * Updates the state based on the elapsed time.
     *
     * @param deltaTime the time elapsed since the last update
     */
    void updateState(long deltaTime);

    /**
     * Gets the key of the next state to transition to.
     *
     * @return the key of the next state
     */
    EState getNextStateKey();

}
