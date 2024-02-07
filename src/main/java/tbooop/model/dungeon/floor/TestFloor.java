package tbooop.model.dungeon.floor;

import tbooop.model.enemy.api.EnemyFactory;

/** Debug only. */
public class TestFloor extends BaseFloor {
    // TODO REMOVE THIS CLASS

    /**
     * Constructs a new TestFloor with the specified enemy factory.
     * 
     * @param enemyFactory the enemy factory that this floor will use
     */
    public TestFloor(final EnemyFactory enemyFactory) {
        super(4, enemyFactory);
    }

    /** {@inheritDoc} */
    @Override
    public int getLevel() {
        return 1;
    }

}
