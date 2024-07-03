package tbooop.model.boss.impl.DukeOfEyes;

import tbooop.commons.api.Vector2d;
import tbooop.commons.api.Vector2dUtils;
import tbooop.model.boss.attacks.Attack;
import tbooop.model.boss.impl.DukeOfEyes.DoESM.State;
import tbooop.model.boss.stateMachine.api.AbstractState;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.enemy.impl.ai.BouncingAi;
import tbooop.model.player.api.Player;

public class DoERoam extends AbstractState<DoESM.State> {

    private MovementAi ai;
    private DukeOfEyes doe;
    private Player p;
    private static final long TIME_BETWEEN_ATTACKS = 200;
    private static final double PROJECTILE_SPEED = 0.08;
    private long timeSinceLastShoot;

    public DoERoam(DukeOfEyes doe, Player p) {
        super(doe);
        this.doe = doe;
        this.p = p;
        ai = new BouncingAi(Vector2dUtils.randomNorm().toP2d(), doe.getCollider().getRadius());
    }

    private Vector2d dir = Vector2dUtils.randomNorm();

    @Override
    public void updateState(long deltaTime) {
        var nextPos = ai.newPosition(doe.getPosition(), deltaTime, doe.getVelocity());
        // doe.setPosition(nextPos); TODO uncomment
        this.timeSinceLastShoot += deltaTime;
        if (this.timeSinceLastShoot >= TIME_BETWEEN_ATTACKS) {
            this.timeSinceLastShoot = 0;
            dir = Attack.multiSpiral(doe, PROJECTILE_SPEED, dir, 20, 4);
            // Attack.snipe(doe, p.getPosition(), PROJECTILE_SPEED);
        }

    }

    @Override
    public State getNextStateKey() {
        return State.ROAMING;
    }

}
