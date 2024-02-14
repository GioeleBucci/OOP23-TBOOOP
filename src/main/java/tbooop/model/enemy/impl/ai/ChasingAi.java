package tbooop.model.enemy.impl.ai;

import java.util.Objects;

import tbooop.commons.api.Point2d;
import tbooop.commons.impl.Vector2dImpl;
import tbooop.model.core.api.movable.Movable;
import tbooop.model.enemy.api.ai.AbstractAi;

/**
 * A movement ai that chases the player.
 * It calculates the position an enemy should move to in a way that tends to
 * get closer to the player's current position.
 */
public class ChasingAi extends AbstractAi {

    private final Movable player;

    /**
     * Creates an istance of a ChasingAi.
     * 
     * @param player the player that will be chased
     * @throws NullPointerException if player is null
     */
    public ChasingAi(final Movable player) {
        this.player = Objects.requireNonNull(player);
    }

    /** {@inheritDoc} */
    @Override
    public Point2d newPosition(final Point2d initialPosition, final long deltaTime, final double velocity) {
        super.checkParameters(initialPosition, deltaTime);
        if (initialPosition.equals(player.getPosition())) {
            return initialPosition;
        }
        super.setDirection(new Vector2dImpl(this.player.getPosition()
            .subtract(initialPosition).toV2d()).normalize().toP2d());
        return super.nextPos(initialPosition, deltaTime, velocity);
    }

}
