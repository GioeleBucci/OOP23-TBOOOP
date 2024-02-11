package tbooop.view.enemy;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tbooop.model.core.api.GameObjectUnmodifiable;
import tbooop.model.enemy.api.Enemy;
import tbooop.view.FrameUpdaterImpl;
import tbooop.view.api.FrameUpdater;
import tbooop.view.api.enemy.EnemyAnimator;

/**
 * Implementation of EnemyAnimator, this class directly access the view's gameObjmap to animate
 * the enemies contained in it.
 * @see EnemyAnimator
 */
@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Due to design decisions"
        + "this class should be able to access to the view's map of gameObjects in order to"
        + "easily update the animation frames of only the enemies that may be present.")
public class EnemyAnimatorImpl implements EnemyAnimator {

    private static final int MELEE_UPDATE_FREQUENCY = 1000 / 14;
    private static final int SHOOTER_UPDATE_FREQUENCY = 1000 / 8;
    private static final int BOUNCER_UPDATE_FREQUENCY = 1000 / 4;
    private static final int CRAZY_UPDATE_FREQUENCY = 1000 / 5;
    private final Map<GameObjectUnmodifiable, ImageView> gameObjMap;

    private final FrameUpdater meleeUpdater = new FrameUpdaterImpl(List.of(
        new Image("enemy/melee/melee1.png"),
        new Image("enemy/melee/melee2.png"),
        new Image("enemy/melee/melee3.png"),
        new Image("enemy/melee/melee4.png"),
        new Image("enemy/melee/melee5.png"),
        new Image("enemy/melee/melee6.png"),
        new Image("enemy/melee/melee7.png"),
        new Image("enemy/melee/melee8.png"),
        new Image("enemy/melee/melee9.png"),
        new Image("enemy/melee/melee10.png"),
        new Image("enemy/melee/melee11.png")), MELEE_UPDATE_FREQUENCY);

    private final FrameUpdater shooterUpdater = new FrameUpdaterImpl(List.of(
        new Image("enemy/shooter/shooter1.png"),
        new Image("enemy/shooter/shooter2.png"),
        new Image("enemy/shooter/shooter3.png"),
        new Image("enemy/shooter/shooter4.png"),
        new Image("enemy/shooter/shooter5.png")), SHOOTER_UPDATE_FREQUENCY);

    private final FrameUpdater bouncerUpdater = new FrameUpdaterImpl(List.of(
        new Image("enemy/bouncer/bouncer1.png"),
        new Image("enemy/bouncer/bouncer2.png"),
        new Image("enemy/bouncer/bouncer3.png")), BOUNCER_UPDATE_FREQUENCY);

    private final FrameUpdater crazyUpdater = new FrameUpdaterImpl(List.of(
        new Image("enemy/crazy/crazy1.png"),
        new Image("enemy/crazy/crazy2.png"),
        new Image("enemy/crazy/crazy3.png"),
        new Image("enemy/crazy/crazy4.png")), CRAZY_UPDATE_FREQUENCY);

    /**
     * creates an instance of an EnemyAnimator.
     * @param gameObjMap the map of gameObjects that may contain enemies
     * @throws NullPointerException if gameObjMap is null
     */
    public EnemyAnimatorImpl(final Map<GameObjectUnmodifiable, ImageView> gameObjMap) {
        this.gameObjMap = Objects.requireNonNull(gameObjMap);
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        final long currentTime = System.currentTimeMillis();
        for (final var en : gameObjMap.entrySet()) {
            if (en.getKey() instanceof Enemy) {
                switch (((Enemy) en.getKey()).getEnemyType()) {
                    case MELEE -> {
                        en.getValue().setImage(meleeUpdater.getNextFrame(currentTime));
                    }
                    case SHOOTER -> {
                        en.getValue().setImage(shooterUpdater.getNextFrame(currentTime));
                    }
                    case BOUNCER -> {
                        en.getValue().setImage(bouncerUpdater.getNextFrame(currentTime));
                    }
                    case CRAZY -> {
                        en.getValue().setImage(crazyUpdater.getNextFrame(currentTime));
                    }
                    default -> { }
                }
            }
        }
        meleeUpdater.resetIfUpdated(currentTime);
        shooterUpdater.resetIfUpdated(currentTime);
        bouncerUpdater.resetIfUpdated(currentTime);
        crazyUpdater.resetIfUpdated(currentTime);
    }

}
