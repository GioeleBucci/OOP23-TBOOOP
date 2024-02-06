package tbooop.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import tbooop.commons.Point2dImpl;
import tbooop.commons.api.Point2d;
import tbooop.controller.api.FloorManager;
import tbooop.controller.api.World;
import tbooop.model.dungeon.floor.LevelFloor;
import tbooop.model.dungeon.floor.api.Floor;
import tbooop.model.dungeon.rooms.api.DoorLockable;
import tbooop.model.dungeon.rooms.api.DoorPositions;
import tbooop.model.dungeon.rooms.api.DoorUnmodifiable;
import tbooop.model.dungeon.rooms.api.Room;
import tbooop.view.api.View;

/**
 * The FloorManagerImpl class is responsible for managing the floors and rooms
 * of the game.
 */
public class FloorManagerImpl implements FloorManager {

    private final World world;
    private final View view;
    private int currentFloorLevel = 1;
    private Floor floor = new LevelFloor(currentFloorLevel);
    private Room currentRoom = floor.getStaringRoom();

    /**
     * Constructs a new FloorManager with the specified world and view.
     * 
     * @param world the world that this floor manager will update
     * @param view  the view that this floor manager will use
     */
    @SuppressFBWarnings(value = {
            "EI_EXPOSE_REP" // may expose internal representation by returning reference to mutable object
    }, justification = "This class is a helper class for the World component"
            + "that needs to access and directly modify its fields.")
    public FloorManagerImpl(final World world, final View view) {
        this.world = world;
        this.view = view;
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void init() {
        world.getGameObjects().addAll(currentRoom.getDoorMap().values());
        view.changeRoom(currentRoom);
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        // nothing to do here!
    }

    /** {@inheritDoc} */
    @Override
    public void onDoorCollision(final DoorUnmodifiable door) {
        if (!door.isOpen()) {
            if (!world.getPlayer().hasKey()) {
                return;
            }
            if (door instanceof DoorLockable) {
                world.getPlayer().useKey();
                ((DoorLockable) door).unlock();
            }
        }
        synchronized (this) {
            world.getPlayer().setPosition(newPlayerPosition(door));
            world.clearAll();
            changeRoom((Room) door.getRoom());
        }
    }

    /** {@inheritDoc} */
    @Override
    public void changeFloor() {
        this.floor = new LevelFloor(++currentFloorLevel);
    }

    private Point2d newPlayerPosition(final DoorUnmodifiable door) {
        final double offset = door.getCollider().getRadius() + world.getPlayer().getCollider().getRadius() + 10;
        if (door.getPosition().equals(DoorPositions.TOP.getPosition())) {
            return new Point2dImpl(DoorPositions.BOTTOM.getPosition().add(new Point2dImpl(0, -offset)));
        }
        if (door.getPosition().equals(DoorPositions.BOTTOM.getPosition())) {
            return new Point2dImpl(DoorPositions.TOP.getPosition().add(new Point2dImpl(0, offset)));
        }
        if (door.getPosition().equals(DoorPositions.LEFT.getPosition())) {
            return new Point2dImpl(DoorPositions.RIGHT.getPosition().add(new Point2dImpl(-offset, 0)));
        }
        if (door.getPosition().equals(DoorPositions.RIGHT.getPosition())) {
            return new Point2dImpl(DoorPositions.LEFT.getPosition().add(new Point2dImpl(offset, 0)));
        }
        throw new IllegalArgumentException("Invalid door position " + door.getPosition());
    }

    private synchronized void changeRoom(final Room newRoom) {
        world.getGameObjects().addAll(newRoom.getDoorMap().values());
        currentRoom = newRoom;
        currentRoom.setExplored();
        view.changeRoom(currentRoom);
    }

}
