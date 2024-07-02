package tbooop.model.boss.impl.DukeOfEyes;

import tbooop.commons.api.CardinalDirection;
import tbooop.model.boss.impl.DukeOfEyes.DoESM.State;
import tbooop.model.boss.stateMachine.api.AbstractState;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.enemy.impl.ai.BouncingAi;

public class DoERoam extends AbstractState<DoESM.State> {

    private MovementAi ai;
    private DukeOfEyes doe;

    public DoERoam(DukeOfEyes doe) {
        super(doe);
        this.doe = doe;
        ai = new BouncingAi(CardinalDirection.UP.toP2d(), 1000);
    }

    @Override
    public void updateState(long deltaTime) {
        var nextPos = ai.newPosition(doe.getPosition(), deltaTime, doe.getVelocity());
        doe.setPosition(nextPos);
    }

    @Override
    public State getNextStateKey() {
        return State.ROAMING;
    }

}
