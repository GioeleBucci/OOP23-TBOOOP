package tbooop.model.boss.impl.meaty;

import tbooop.commons.api.Vector2d;
import tbooop.commons.api.Vector2dUtils;
import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.boss.impl.meaty.MeatySM.State;
import tbooop.model.boss.stateMachine.api.AbstractState;
import tbooop.model.enemy.attacks.Attack;
import tbooop.model.player.api.Player;
import java.util.Random;

public class MeatySpiral extends AbstractState<MeatySM.State> {

    private static final long GRACE_TIME = 500; // time before starting phase
    private static final long ATK_FREQUENCY = 360;
    private static final long SWITCH_DIRECTION_TIME = 5500;
    private static final long SWITCH_DIRECTION_VARIATION = 3000;
    private static final double PROJECTILE_SPEED = 0.12;
    private static final double SPIRAL_ANGLE_BASE = 10;
    private static final double ANGLE_SPEED_INCREMENT = .8;
    private AbstractBoss<State> boss;
    private Player p;
    private long timeSinceLastAttack;
    private long timeSinceLastSwitch;
    private long graceTimer;
    private Random rand = new Random();

    public MeatySpiral(AbstractBoss<State> boss, Player p) {
        super(boss);
        this.boss = boss;
        this.p = p;
    }

    private double angle;
    private boolean isSwitched; // whether the spiral is switching or not
    private Vector2d spiralDir = Vector2dUtils.randomNorm();
    private double windUp = SPIRAL_ANGLE_BASE;
    private long switchDirTime = rand.nextLong(SWITCH_DIRECTION_VARIATION) + SWITCH_DIRECTION_TIME;

    @Override
    public void updateState(long deltaTime) {
        if (graceTimer < GRACE_TIME) {
            graceTimer += deltaTime;
            return;
        }
        timeSinceLastSwitch += deltaTime;
        timeSinceLastAttack += deltaTime;
        if (timeSinceLastSwitch >= switchDirTime) {
            timeSinceLastSwitch = 0;
            isSwitched = !isSwitched;
            resetWindup();
            shootRing();
        }
        if (timeSinceLastAttack >= ATK_FREQUENCY) {
            timeSinceLastAttack = 0;
            Attack.multiSpiral(boss, PROJECTILE_SPEED, spiralDir, angle, 4);
            angle += windUp * (isSwitched ? 1 : -1);
            windUp += ANGLE_SPEED_INCREMENT;
        }
    }

    private void shootRing() {
        Attack.ringWithGap(boss, p.getPosition(), 10, PROJECTILE_SPEED * .7, 20, 4);
    }

    private void resetWindup() {
        switchDirTime = rand.nextLong(SWITCH_DIRECTION_VARIATION) + SWITCH_DIRECTION_TIME;
        windUp = SPIRAL_ANGLE_BASE;
    }

    @Override
    public State getNextStateKey() {
        return boss.getHealth() > boss.getMaxHealth() * .4
                ? State.SPIRAL
                : State.BURST;
    }

}
