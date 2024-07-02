package tbooop.model.boss.stateMachine.api;

public abstract class AbstractState<EState extends Enum<EState>> {
    public abstract void updateState(long deltaTime);

    public abstract void enterState();

    public abstract void exitState();

    public abstract EState getNextStateKey();
}
