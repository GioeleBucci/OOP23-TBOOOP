package tbooop.model.boss.impl.duke_of_eyes;

import tbooop.commons.api.Vector2d;
import tbooop.commons.api.MovementUtils;
import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.boss.impl.duke_of_eyes.DoESM.State;
import tbooop.model.boss.stateMachine.impl.AbstractState;
import tbooop.model.enemy.api.ai.MovementAi;
import tbooop.model.enemy.attacks.Attack;
import tbooop.model.enemy.impl.ai.ChasingAi;
import tbooop.model.player.api.Player;
import java.util.Random;

public class DoEAngered extends AbstractState<DoESM.State> {

    private static final double VELOCITY_MULTIPLIER = 2;
    private static final long TIME_BETWEEN_ATTACKS = 2800;
    private static final long ATK_DURATION = 1000;
    private static final long ATK_FREQUENCY = 20;
    private static final double PROJECTILE_SPEED = 0.16;
    private AbstractBoss<State> boss;
    private Player p;
    private MovementAi ai;
    private double velocity;
    private long timeSinceLastAttack;
    private boolean isAttacking;
    private AttackType attackType;
    Random rand = new Random();

    public DoEAngered(AbstractBoss<State> boss, Player p) {
        super(boss);
        this.boss = boss;
        this.p = p;
        ai = new ChasingAi(p);
        velocity = boss.getVelocity() * VELOCITY_MULTIPLIER;
    }

    @Override
    public void onStateEnter() {
        timeSinceLastAttack = TIME_BETWEEN_ATTACKS - 1;
    }

    private long atkWindowTime;
    private long atkFreqTime;

    @Override
    public void updateState(long deltaTime) {
        if (!(isAttacking && attackType == AttackType.SPIRAL)) {
            var nextPos = ai.newPosition(boss.getPosition(), deltaTime, velocity);
            boss.setPosition(nextPos);
        }
        this.timeSinceLastAttack += deltaTime;
        if (isAttacking) {
            attack(deltaTime);
        }
        if (this.timeSinceLastAttack >= TIME_BETWEEN_ATTACKS) {
            this.timeSinceLastAttack = 0;
            setAttackMode(true);
        }
    }

    private void attack(long deltaTime) {
        atkWindowTime += deltaTime;
        atkFreqTime += deltaTime;
        if (atkWindowTime >= ATK_DURATION) {
            setAttackMode(false);
            return;
        }
        if (atkFreqTime >= ATK_FREQUENCY) {
            atkFreqTime = 0;
            performAttack();
        }
    }

    private Vector2d dir = MovementUtils.randomNorm();

    private void performAttack() {
        switch (attackType) {
            case SPIRAL:
                dir = Attack.spiral(boss, PROJECTILE_SPEED / 2, dir, 35);
                break;
            case VOMIT:
                Attack.vomit(boss, p.getPosition(), 10, PROJECTILE_SPEED, 30);
                break;
        }
    }

    private enum AttackType {
        VOMIT,
        SPIRAL;
    }

    private void setAttackMode(boolean isAttacking) {
        this.isAttacking = isAttacking;
        atkWindowTime = 0;
        atkFreqTime = 0;
        if (isAttacking) {
            setRandomAttackType();
        }
    }

    private void setRandomAttackType() {
        AttackType[] attackTypes = AttackType.values();
        int index = rand.nextInt(attackTypes.length);
        attackType = attackTypes[index];
    }

    @Override
    public State getNextStateKey() {
        return State.ANGERED;
    }

}
