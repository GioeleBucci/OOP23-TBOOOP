package tbooop.model.enemy.impl;

import java.util.Objects;
import java.util.Random;

import tbooop.commons.api.CardinalDirection;
import tbooop.commons.api.Point2d;
import tbooop.commons.impl.HealthImpl;
import tbooop.commons.impl.Point2dImpl;
import tbooop.model.boss.api.Boss;
import tbooop.model.boss.impl.duke_of_eyes.DukeOfEyes;
import tbooop.model.boss.impl.float_bloat.FloatBloat;
import tbooop.model.boss.impl.meaty.Meaty;
import tbooop.model.enemy.api.Enemy;
import tbooop.model.enemy.api.EnemyFactory;
import tbooop.model.enemy.api.EnemyType;
import tbooop.model.enemy.impl.ai.BouncingAi;
import tbooop.model.enemy.impl.ai.ChasingAi;
import tbooop.model.enemy.impl.ai.LinearAi;
import tbooop.model.player.api.Player;
import java.util.List;

/**
 * Factory of enemies, its method create and return a particular
 * configuration of an enemy object.
 */
public class EnemyFactoryImpl implements EnemyFactory {

    private static final int BOUNCER_HP = 3;
    private static final double BOUNCER_SPEED = 0.05;
    private static final double BOUNCER_RADIUS = 15;
    private static final int BOUNCER_PROJ_AMOUNT = 8;
    private static final int MELEE_HP = 3;
    private static final double MELEE_SPEED = 0.055;
    private static final double MELEE_RADIUS = 15;
    private static final int SHOOTER_HP = 3;
    private static final double SHOOTER_SPEED = 0.06;
    private static final double SHOOTER_RADIUS = 15;
    private static final int CRAZY_HP = 3;
    private static final double CRAZY_SPEED = 0.04;
    private static final double CRAZY_RADIUS = 15;
    private static final int CRAZY_PROJ_AMOUNT = 5;
    private final Player player;

    private static final Random rand = new Random();

    /**
     * Creates an instance of a factory of enemies.
     * 
     * @param player the player that will be attacked by the enemies
     * @throws NullPointerException if player is null
     */
    public EnemyFactoryImpl(final Player player) {
        this.player = Objects.requireNonNull(player);
    }

    /** {@inheritDoc} */
    @Override
    public Enemy melee() {
        return new Melee(new BaseEnemy(new Point2dImpl(0, 0),
                new HealthImpl(MELEE_HP), MELEE_SPEED, new ChasingAi(this.player),
                EnemyType.MELEE, MELEE_RADIUS));
    }

    /** {@inheritDoc} */
    @Override
    public Enemy shooter(final CardinalDirection initialDirection) {
        return new Shooter(new BaseEnemy(new Point2dImpl(0, 0),
                new HealthImpl(SHOOTER_HP), SHOOTER_SPEED,
                new LinearAi(Objects.requireNonNull(initialDirection), SHOOTER_RADIUS),
                EnemyType.SHOOTER, SHOOTER_RADIUS), this.player);
    }

    /** {@inheritDoc} */
    @Override
    public Enemy bouncer(final Point2d initialDirection) {
        return new Explosive(new Melee(new BaseEnemy(new Point2dImpl(0, 0),
                new HealthImpl(BOUNCER_HP), BOUNCER_SPEED,
                new BouncingAi(Objects.requireNonNull(initialDirection), BOUNCER_RADIUS),
                EnemyType.BOUNCER, BOUNCER_RADIUS)), BOUNCER_PROJ_AMOUNT);
    }

    /** {@inheritDoc} */
    @Override
    public Enemy crazy() {
        return new Explosive(new Melee(new Shooter(new BaseEnemy(
                new Point2dImpl(0, 0), new HealthImpl(CRAZY_HP), CRAZY_SPEED,
                new ChasingAi(player), EnemyType.CRAZY, CRAZY_RADIUS), player)), CRAZY_PROJ_AMOUNT);
    }

    List<Class<? extends Boss>> bossClasses = List.of(DukeOfEyes.class, Meaty.class, FloatBloat.class);
    private int bossIndex = rand.nextInt(bossClasses.size());

    @Override
    public Boss boss() {
        bossIndex = (bossIndex + 1) % bossClasses.size();
        try {
            return bossClasses.get(bossIndex).getConstructor(Player.class).newInstance(player);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
