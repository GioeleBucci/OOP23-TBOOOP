package tbooop.model.enemy.impl.ai;

import java.util.Objects;

import tbooop.commons.Point2d;
import tbooop.commons.Vector2d;
import tbooop.commons.api.SimpleVector;
import tbooop.model.core.api.movable.Movable;
import tbooop.model.enemy.api.ai.MovementAi;

/**
 * A movement ai that chases the player.
 * It calculates the position an enemy should move to in a way that tends to
 * get closer to the player's current position.
 */
public class ChasingAi implements MovementAi {

    private final Movable player;

    /**
     * Creates an istance of a ChasingAi.
     * 
     * @param player the player that will be chased
     */
    public ChasingAi(final Movable player) {
        this.player = Objects.requireNonNull(player);
    }

    /** {@inheritDoc} */
    @Override
    public Point2d newPosition(final Point2d initialPosition, final long deltaTime, final double velocity) {
        if (initialPosition.equals(player.getPosition())) {
            return initialPosition;
        }
        final Point2d distance = this.player.getPosition().subtract(initialPosition);
        final SimpleVector direction = new Vector2d(
            distance.getX(), distance.getY())
            .normalize();
        return new Point2d(direction.getX(), direction.getY())
            .mul(deltaTime)
            .mul(velocity)
            .add(initialPosition);
    }

}
