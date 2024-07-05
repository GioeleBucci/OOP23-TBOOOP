package tbooop.model.enemy.api;

/**
 * enum to classify different types of enemies. 
 */
public enum EnemyType {
    /** an enemy that deals damage on physical contact. */
    MELEE,
    /** an enemy that shoots projectiles. */
    SHOOTER,
    /** an enemy that bounces on walls. */
    BOUNCER,
    /** an enemy that uses all the attack systems available. */
    CRAZY,
    // spooky bosses ahead !
    DUKE_OF_EYES,
    FLOATBLOAT,
    MEATY;
}
