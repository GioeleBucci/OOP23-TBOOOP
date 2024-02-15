package tbooop.model.dungeon.rooms.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import tbooop.commons.api.CardinalDirection;
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

    private final Map<CardinalDirection, DoorUnmodifiable> doorMap = new HashMap<>();
    private final Set<GameObject> gameObjects = new HashSet<>();

    /** {@inheritDoc} */
    @Override
    public void addDoor(final CardinalDirection direction, final DoorUnmodifiable door) {
        doorMap.put(direction, door);
        addGameObject(door);
    }

    /** {@inheritDoc} */
    @Override
    public Map<CardinalDirection, DoorUnmodifiable> getDoorMap() {
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
    public String toString() {
        return this.getClass().getSimpleName() + "[doorSet=" + getDoorMap() + "]\n";
    }

    /** {@inheritDoc} */
    @Override
    public boolean isFirstRoom() {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void closeDoors() {
        getDoorMap().values().forEach(door -> {
            ((Door) door).close();
        });
    }

    /** {@inheritDoc} */
    @Override
    public void openDoors() {
        getDoorMap().values().forEach(door -> {
            ((Door) door).open();
        });
    }

}
