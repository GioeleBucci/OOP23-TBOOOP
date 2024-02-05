package tbooop.view.enemy;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tbooop.model.core.api.GameObjectUnmodifiable;
import tbooop.model.enemy.api.Enemy;

/**
 * An enemy animator stores the logic that allows the view
 * to display a graphical animation of enemies.
 */
@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Due to design decisions"
        + "this class should be able to access to the view's map of gameObjects in order to"
        + "easily update the animation frames of only the enemies that may be present.")
public class EnemyAnimatorImpl {

    private static final int MELEE_UPDATE_FREQUENCY = 1000 / 12;
    private static final int SHOOTER_UPDATE_FREQUENCY = 1000 / 8;
    private static final int BOUNCER_UPDATE_FREQUENCY = 1000 / 4;


    private final EnemyFrameUpdaterImpl meleeUpdater = new EnemyFrameUpdaterImpl(List.of(
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
    private final EnemyFrameUpdaterImpl shooterUpdater = new EnemyFrameUpdaterImpl(List.of(
        new Image("enemy/shooter/shooter1.png"),
        new Image("enemy/shooter/shooter2.png"),
        new Image("enemy/shooter/shooter3.png"),
        new Image("enemy/shooter/shooter4.png"),
        new Image("enemy/shooter/shooter5.png")), SHOOTER_UPDATE_FREQUENCY);
    private final EnemyFrameUpdaterImpl bouncerUpdater = new EnemyFrameUpdaterImpl(List.of(
        new Image("enemy/bouncer/bouncer1.png"),
        new Image("enemy/bouncer/bouncer2.png"),
        new Image("enemy/bouncer/bouncer3.png")), BOUNCER_UPDATE_FREQUENCY);

    private final Map<GameObjectUnmodifiable, ImageView> gameObjMap;

    /**
     * creates an instance of an EnemyAnimator.
     * @param gameObjMap the map of gameObjects that may contain enemies
     * @throws NullPointerException if gameObjMap is null
     */
    public EnemyAnimatorImpl(final Map<GameObjectUnmodifiable, ImageView> gameObjMap) {
        this.gameObjMap = Objects.requireNonNull(gameObjMap);
    }

    /**
     * This method updates the animation frames of enemies stored in gameObjMap.
     */
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
                    default -> { }
                }
            }
        }
        meleeUpdater.resetIfUpdated(currentTime);
        shooterUpdater.resetIfUpdated(currentTime);
        bouncerUpdater.resetIfUpdated(currentTime);
    }

}
