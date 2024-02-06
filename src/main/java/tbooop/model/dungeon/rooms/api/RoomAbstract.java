package tbooop.model.dungeon.rooms.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import tbooop.commons.Point2ds;
import tbooop.model.core.api.GameObject;

/**
 * Base for all Rooms implementations
 * A room can have up to 4 doors and a series of rooms
 * create a floor.
 * 
 * @see {@link tbooop.model.dungeon.floor.BaseFloor Floor}
 */
public abstract class RoomAbstract implements Room {

    private final Map<Point2ds, DoorUnmodifiable> doorMap = new HashMap<>();
    private boolean isExplored;
    private final Set<GameObject> gameObjects = new HashSet<>();

    /** {@inheritDoc} */
    @Override
    public void addDoor(final Point2ds direction, final DoorUnmodifiable door) {
        doorMap.put(direction, door);
        addGameObject(door);
    }

    /** {@inheritDoc} */
    @Override
    public Map<Point2ds, DoorUnmodifiable> getDoorMap() {
        return Collections.unmodifiableMap(doorMap);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExplored() {
        return isExplored;
    }

    /** {@inheritDoc} */
    @Override
    public void setExplored() {
        this.isExplored = true;
    }

    /** {@inheritDoc} */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Passing modifiable set is required"
            + "becouse otherwise the controller wouldn't be able to modify these game objects")
    public Set<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Adds a game object to this room.
     * 
     * @param gameObject the game object to add
     */
    protected void addGameObject(final GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[doorSet=" + getDoorMap() + ", isExplored=" + isExplored() + "]\n";
    }
}
