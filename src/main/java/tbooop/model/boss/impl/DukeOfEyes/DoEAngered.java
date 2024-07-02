package tbooop.model.boss.impl.DukeOfEyes;

import tbooop.model.boss.impl.DukeOfEyes.DoESM.State;
import tbooop.model.boss.stateMachine.api.AbstractState;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.enemy.impl.ai.ChasingAi;
import tbooop.model.player.api.Player;

public class DoEAngered extends AbstractState<DoESM.State> {

    private DukeOfEyes doe;
    private MovementAi ai;

    public DoEAngered(DukeOfEyes doe, Player p) {
        super(doe);
        this.doe = doe;
        ai = new ChasingAi(p);
    }

    @Override
    public void updateState(long deltaTime) {
        var nextPos = ai.newPosition(doe.getPosition(), deltaTime, doe.getVelocity());
        doe.setPosition(nextPos);
    }

    @Override
    public State getNextStateKey() {
        return State.ANGERED;
    }

}
