package tbooop.model.boss.impl.float_bloat;

import tbooop.commons.api.Point2d;
import tbooop.commons.api.Vector2d;
import tbooop.commons.api.MovementUtils;
import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.boss.impl.float_bloat.FBSM.State;
import tbooop.model.boss.stateMachine.api.AbstractState;
import tbooop.model.enemy.attacks.Attack;
import tbooop.model.player.api.Player;

public class FBCharge extends AbstractState<FBSM.State> {

    private static final float SPEED_MULTIPLIER = 9;
    private static final long CHARGE_TIME = 400;
    private static final int ATKS_WHILE_CHARGING = 3;
    private static final long TIME_BETWEEN_ATTACKS = CHARGE_TIME / (ATKS_WHILE_CHARGING + 1);
    private static final int PROJ_AMOUNT = 5;
    private static final double PROJ_SPEED = .08;
    private AbstractBoss<FBSM.State> boss;
    private Player p;
    private Vector2d chargeDirection;
    private long chargeTimer;
    private long attackTimer;
    private boolean invertShot;
    private double velocity;

    public FBCharge(AbstractBoss<State> boss, Player p) {
        super(boss);
        this.boss = boss;
        this.p = p;
        this.velocity = SPEED_MULTIPLIER * boss.getVelocity();
    }

    @Override
    public void onStateEnter() {
        super.onStateEnter();
        chargeTimer = 0;
        attackTimer = 0;
        chargeDirection = MovementUtils.directionTowards(boss.getPosition(), p.getPosition());
    }

    @Override
    public void updateState(long deltaTime) {
        chargeTimer += deltaTime;
        attackTimer += deltaTime;
        if (attackTimer >= TIME_BETWEEN_ATTACKS) {
            attackTimer = 0;
            attack();
        }
        boss.setPosition(MovementUtils.move(boss.getPosition(),
                chargeDirection, velocity, deltaTime));
    }

    private void attack() {
        if (invertShot) {
            Attack.closeIn(boss, boss.getPosition(), 20, PROJ_AMOUNT, PROJ_SPEED);
        } else {
            Attack.ring(boss, PROJ_SPEED, PROJ_AMOUNT);
        }
        invertShot = !invertShot;
    }

    @Override
    public State getNextStateKey() {
        if (chargeTimer > CHARGE_TIME) {
            return State.IDLE;
        }
        return State.CHARGING;
    }
}
