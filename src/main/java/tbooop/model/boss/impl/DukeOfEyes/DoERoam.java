package tbooop.model.boss.impl.DukeOfEyes;

import tbooop.commons.api.Vector2dUtils;
import tbooop.model.boss.attacks.Attack;
import tbooop.model.boss.impl.DukeOfEyes.DoESM.State;
import tbooop.model.boss.stateMachine.api.AbstractState;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.enemy.impl.ai.BouncingAi;
import tbooop.model.player.api.Player;
import java.util.Random;

public class DoERoam extends AbstractState<DoESM.State> {

    private static final long TIME_BETWEEN_ATTACKS = 2500;
    private static final double PROJECTILE_SPEED = 0.08;
    private DukeOfEyes doe;
    private Player p;
    private MovementAi ai;
    private long timeSinceLastAttack;
    Random rand = new Random();

    public DoERoam(DukeOfEyes doe, Player p) {
        super(doe);
        this.doe = doe;
        this.p = p;
        ai = new BouncingAi(Vector2dUtils.randomNorm().toP2d(), doe.getCollider().getRadius());
    }

    @Override
    public void updateState(long deltaTime) {
        var nextPos = ai.newPosition(doe.getPosition(), deltaTime, doe.getVelocity());
        doe.setPosition(nextPos);
        this.timeSinceLastAttack += deltaTime;
        if (this.timeSinceLastAttack >= TIME_BETWEEN_ATTACKS) {
            this.timeSinceLastAttack = 0;
            performRandomAttack();
        }
    }

    private void performRandomAttack() {
        switch (rand.nextInt(2)) {
            case 0:
                Attack.radiusWithGap(doe, PROJECTILE_SPEED, 20, 4);
                break;
            default:
                Attack.snipe(doe, p.getPosition(), PROJECTILE_SPEED * 2.5);
        }
    }

    @Override
    public State getNextStateKey() {
        if (doe.getHealth() <= doe.getMaxHealth() / 2) {
            return State.ANGERED;
        }
        return State.ROAMING;
    }

}
