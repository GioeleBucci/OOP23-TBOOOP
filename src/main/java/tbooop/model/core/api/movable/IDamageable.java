package tbooop.model.core.api.movable;

/**
 * Interface for Damageable GameObject.
 * <p>
 * It represents the methods that everything can take damage in the game must have.
 */

public interface IDamageable extends IMovable {

    /**
     * This method it's used to reduce the health of a Damageable GameObject.
     * <p>
     * 
     * @param damage is the damage a GameObject takes.
     */
    void takeDamage(int damage);

    /**
     * This method it is used to communicate the death of an Damageable GameObject.
     * <p>
     */
    void die();

    /**
     *  This method it is used to get the current health of an Damageable GameObject.
     * <p>
     * 
     * @return the current health.
     */
    int getHealth();

    /**
     * This method it is used to get the max health of an Damageable GameObject.
     * <p>
     * 
     * @return the max health
     */
    int getMaxHealth();
}
