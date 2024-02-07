package tbooop.model.dungeon.floor;

import tbooop.model.enemy.api.EnemyFactory;

/** A floor that gets more rooms the higher its level is. */
public class LevelFloor extends BaseFloor {

    private static final int MINIMUM_ROOMS_AMOUNT = 5;
    private static final double MULTIPLIER = 2.6;
    private final int floorLevel;

    /**
     * Builds the floor according to the following formula:
     * {@code Math.random() * 2 + 5 + level * 2.6}.
     * 
     * @param floorLevel   floor number, used to determine the amount of rooms
     *                     spawned
     * @param enemyFactory the factory that will be used to create enemies inside
     *                     enemy rooms
     * @see BaseFloor
     */
    public LevelFloor(final int floorLevel, final EnemyFactory enemyFactory) {
        super((int) (Math.random() * 2 + MINIMUM_ROOMS_AMOUNT + floorLevel * MULTIPLIER), enemyFactory);
        this.floorLevel = floorLevel;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Floor " + floorLevel + ":\n" + super.toString();
    }

    /** {@inheritDoc} */
    @Override
    public int getLevel() {
        return this.floorLevel;
    }
}
