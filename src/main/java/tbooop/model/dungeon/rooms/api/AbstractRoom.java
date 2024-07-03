package tbooop.model.dungeon.rooms.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import tbooop.commons.api.Direction;
import tbooop.model.core.api.GameObject;
import tbooop.model.dungeon.doors.api.Door;
import tbooop.model.dungeon.doors.api.DoorUnmodifiable;

import java.util.Collection;

/**
 * Base for all Rooms implementations
 * A room can have up to 4 doors and a series of rooms
 * create a floor.
 * 
 * @see {@link tbooop.model.dungeon.floor.api.BaseFloor Floor}
 */
public abstract class AbstractRoom implements Room {

    private final Map<Direction, Door> doorMap = new HashMap<>();
    private final Set<GameObject> gameObjects = new HashSet<>();

    /** {@inheritDoc} */
    @Override
    public void addDoor(final Direction direction, final DoorUnmodifiable door) {
        if (!doorMap.containsKey(direction)) {
            doorMap.put(direction, (Door) door);
            addGameObject(door);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Map<Direction, DoorUnmodifiable> getDoorMap() {
        return Collections.unmodifiableMap(doorMap);
    }

    /** {@inheritDoc} */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "Passing modifiable set is required"
            + "becouse otherwise the controller wouldn't be able to modify these game objects")
    public final Set<GameObject> getGameObjects() {
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

    /**
     * Adds a collection of game objects to this room.
     * 
     * @param gameObjects the elements to add
     */
    protected void addGameObjects(final Collection<? extends GameObject> gameObjects) {
        gameObjects.forEach(this::addGameObject);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFirstRoom() {
        return false;
    }

    @Override
    public boolean isBossRoom() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void closeDoors() {
        doorMap.values().forEach(door -> {
            door.close();
        });
    }

    /** {@inheritDoc} */
    @Override
    public void openDoors() {
        doorMap.values().forEach(door -> {
            door.open();
        });
    }

}
