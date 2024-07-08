package tbooop.model.boss.impl.float_bloat;

import tbooop.commons.api.MovementUtils;
import tbooop.commons.api.RoomBounds;
import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.boss.impl.float_bloat.FBSM.State;
import tbooop.model.boss.stateMachine.impl.AbstractState;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.enemy.attacks.Attack;
import tbooop.model.enemy.impl.ai.BouncingAi;

public class FBBounce extends AbstractState<FBSM.State> {

    private static final long BOUNCE_TIME = 4000;
    private static final double SPEED_MULTIPLIER = 3;
    private static final long RING_SPAWN_INTERVAL = 300;
    private static final double PROJ_SPEED = .1;
    private static final int PROJ_AMOUNT = 10;
    private MovementAi ai;
    private AbstractBoss<FBSM.State> boss;
    private long bounceTimer;
    private long ringTimer;

    public FBBounce(AbstractBoss<State> boss) {
        super(boss);
        this.boss = boss;
        changeAI();
    }

    @Override
    public void onStateEnter() {
        super.onStateEnter();
        bounceTimer = 0;
        ringTimer = RING_SPAWN_INTERVAL;
        changeAI();
    }

    private void changeAI() {
        this.ai = new BouncingAi(MovementUtils.randomNorm().toP2d(),
                boss.getCollider().getRadius());
    }

    @Override
    public void updateState(long deltaTime) {
        bounceTimer += deltaTime;
        getBoss().setPosition(ai.newPosition(boss.getPosition(),
                deltaTime,
                boss.getVelocity() * SPEED_MULTIPLIER));
        checkWallCollision(deltaTime);
    }

    private void checkWallCollision(long deltaTime) {
        ringTimer += deltaTime;
        if (ringTimer < RING_SPAWN_INTERVAL) {
            return;
        }
        if (!RoomBounds.nearOutOfBounds(boss.getPosition(), boss.getCollider().getRadius() * 1.2)) {
            return;
        }
        ringTimer = 0;
        Attack.ring(boss, PROJ_SPEED, PROJ_AMOUNT);
    }

    @Override
    public State getNextStateKey() {
        if (bounceTimer >= BOUNCE_TIME) {
            return State.IDLE;
        }
        return State.BOUNCING;
    }

}
