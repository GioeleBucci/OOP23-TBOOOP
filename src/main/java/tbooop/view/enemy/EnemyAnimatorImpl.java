package tbooop.view.enemy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.image.ImageView;
import tbooop.model.core.api.GameObjectUnmodifiable;
import tbooop.model.enemy.api.Enemy;
import tbooop.model.enemy.api.EnemyType;
import tbooop.view.FrameUpdaterImpl;
import tbooop.view.api.FrameUpdater;
import tbooop.view.api.enemy.BossFrames;
import tbooop.view.api.enemy.EnemyAnimator;
import tbooop.view.api.enemy.EnemyFrames;

/**
 * Implementation of EnemyAnimator, this class directly access the view's gameObjmap to animate
 * the enemies contained in it.
 * @see EnemyAnimator
 */
@SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Due to design decisions"
        + "this class should be able to directly access to the view's map of gameObjects in order to"
        + "easily update the animation frames of only the enemies that may be present in such map.")
public class EnemyAnimatorImpl implements EnemyAnimator {

    private static final double MELEE_UPDATE_FREQ = 70.0;
    private static final double SHOOTER_UPDATE_FREQ = 125.0;
    private static final double BOUNCER_UPDATE_FREQ = 250.0;
    private static final double CRAZY_UPDATE_FREQ = 200.0;
    private static final double DOE_UPDATE_FREQ = 300.0;
    private static final double MEATY_UPDATE_FREQ = 175.0;
    private final Map<GameObjectUnmodifiable, ImageView> gameObjMap;
    private final EnemyFrames enemyFrames = new EnemyFramesImpl();
    private final BossFrames bossFrames = new BossFramesImpl();
    private final Map<EnemyType, FrameUpdater> frameUpdaters = new HashMap<>();

    /**
     * creates an instance of an EnemyAnimator.
     * @param gameObjMap the map of gameObjects that may contain enemies
     * @throws NullPointerException if gameObjMap is null
     */
    public EnemyAnimatorImpl(final Map<GameObjectUnmodifiable, ImageView> gameObjMap) {
        this.gameObjMap = Objects.requireNonNull(gameObjMap);
        frameUpdaters.put(EnemyType.MELEE,
            new FrameUpdaterImpl(enemyFrames.meleeFrames(), MELEE_UPDATE_FREQ));
        frameUpdaters.put(EnemyType.SHOOTER,
            new FrameUpdaterImpl(enemyFrames.shooterFrames(), SHOOTER_UPDATE_FREQ));
        frameUpdaters.put(EnemyType.BOUNCER,
            new FrameUpdaterImpl(enemyFrames.bouncerFrames(), BOUNCER_UPDATE_FREQ));
        frameUpdaters.put(EnemyType.CRAZY,
            new FrameUpdaterImpl(enemyFrames.crazyFrames(), CRAZY_UPDATE_FREQ));
        // bosses
        frameUpdaters.put(EnemyType.DUKE_OF_EYES,
                new FrameUpdaterImpl(bossFrames.dukeOfEyesFrames(), DOE_UPDATE_FREQ));
        frameUpdaters.put(EnemyType.MEATY,
                new FrameUpdaterImpl(bossFrames.meatyFrames(), MEATY_UPDATE_FREQ));
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        final long currentTime = System.currentTimeMillis();
        gameObjMap.entrySet().stream()
            .filter(en -> en.getKey() instanceof Enemy)
            .forEach(en -> en.getValue().setImage(
                frameUpdaters.get(((Enemy) en.getKey()).getEnemyType()).getNextFrame(currentTime)));
        frameUpdaters.values().forEach(fu -> fu.resetIfUpdated(currentTime));
    }

}
