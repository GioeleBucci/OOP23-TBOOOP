package tbooop.model.player.api;

import tbooop.commons.Point2ds;

/**
 * Unmodifiable Player.
 */
public interface UnmodifiablePlayer {
    
    /**
     * Get one of four direction (UP,DOWN,LEFT,RIGHT).
     * @return
     */
    Point2ds getPoint2ds();
}
