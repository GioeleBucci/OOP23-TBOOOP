package tbooop.model.dungeon;

/** A floor that gets more rooms the higher its level is. */
public class LevelFloor extends Floor {

    private static final int MINIMUM_ROOMS_AMOUNT = 5;
    private static final double MULTIPLIER = 2.6;
    private final int floorLevel;

    /**
     * Builds the floor according to the following formula:
     * {@code Math.random() * 2 + 5 + level * 2.6}.
     * 
     * @param floorLevel floor number, used to determine the amount of rooms spawned
     * @see Floor
     */
    public LevelFloor(final int floorLevel) {
        super((int) (Math.random() * 2 + MINIMUM_ROOMS_AMOUNT + floorLevel * MULTIPLIER));
        this.floorLevel = floorLevel;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Floor " + floorLevel + ":\n" + super.toString();
    }
}
