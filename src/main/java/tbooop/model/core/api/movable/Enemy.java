package tbooop.model.core.api.movable;

/**
 * An Enemy is a game object that can move on a 2D space, it automatically
 * interacts with the player by trying to kill it with attacks.
 * Vice versa, An Enemy can also be attacked and killed by the player.
 */
public interface Enemy extends Damageable {
}
