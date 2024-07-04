package tbooop.model.boss.impl.meaty;

import tbooop.model.boss.api.AbstractBoss;
import tbooop.model.boss.impl.meaty.MeatySM.State;
import tbooop.model.boss.stateMachine.api.AbstractState;
import tbooop.model.enemy.attacks.Attack;
import tbooop.model.player.api.Player;
import java.util.Random;

public class MeatyBurst extends AbstractState<MeatySM.State> {

    private static final long ATK_FREQUENCY = 800;
    private static final long BURST_FREQUENCY = 2125;
    private static final double PROJECTILE_SPEED = 0.14;
    private static final double EXTRA_BURST_ATK_CHANCE = .5;
    private AbstractBoss<State> boss;
    private Player p;
    private long timeSinceLastAttack;
    private long timeSinceLastBurst;
    Random rand = new Random();

    public MeatyBurst(AbstractBoss<State> boss, Player p) {
        super(boss);
        this.boss = boss;
        this.p = p;
    }

    @Override
    public void updateState(long deltaTime) {
        timeSinceLastAttack += deltaTime;
        timeSinceLastBurst += deltaTime;
        if (timeSinceLastAttack >= ATK_FREQUENCY) {
            timeSinceLastAttack = 0;
            Attack.ring(boss, PROJECTILE_SPEED, 12);
        }
        if (timeSinceLastBurst >= BURST_FREQUENCY) {
            timeSinceLastBurst = 0;
            burst();
        }
    }

    private void burst() {
        Attack.ring(boss, PROJECTILE_SPEED * .65, 7);
        if (rand.nextDouble() > (1 - EXTRA_BURST_ATK_CHANCE)) {
            Attack.circle(boss, p.getPosition(), 6. + rand.nextInt(4), 5, PROJECTILE_SPEED * 1.3);
        }
    }

    @Override
    public State getNextStateKey() {
        return State.BURST;
    }

}
