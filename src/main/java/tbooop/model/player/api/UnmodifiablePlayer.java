package tbooop.model.player.api;

import tbooop.commons.Point2ds;
import tbooop.model.core.api.GameObjectUnmodifiable;
import java.util.Optional;

/**
 * Unmodifiable Player.
 */
public interface UnmodifiablePlayer extends GameObjectUnmodifiable {

    /**
     * Get one of four direction (UP,DOWN,LEFT,RIGHT).
     * @return Optional<Point2ds> one of four diection or Optional.empty()
     */
    Optional<Point2ds> getPoint2ds();
}
