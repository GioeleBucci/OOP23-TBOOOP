package tbooop.controller;

import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import tbooop.commons.Point2dImpl;
import tbooop.commons.api.Point2d;
import tbooop.controller.api.FloorManager;
import tbooop.controller.api.World;
import tbooop.model.core.api.GameTag;
import tbooop.model.dungeon.floor.LevelFloor;
import tbooop.model.dungeon.floor.api.Floor;
import tbooop.model.dungeon.rooms.api.Door;
import tbooop.model.dungeon.rooms.api.DoorLockable;
import tbooop.model.dungeon.rooms.api.DoorPositions;
import tbooop.model.dungeon.rooms.api.DoorUnmodifiable;
import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.dungeon.rooms.api.RoomClosable;
import tbooop.model.enemy.impl.EnemyFactoryImpl;
import tbooop.model.pickupables.pickups.api.Pickup;
import tbooop.model.pickupables.pickups.impl.PickupLogic;
import tbooop.view.api.View;

/**
 * The FloorManagerImpl class is responsible for managing the floors and rooms
 * of the game.
 */
public class FloorManagerImpl implements FloorManager {

    private final World world;
    private final View view;
    private Floor floor;
    private int currentFloorLevel = 1;
    private Room currentRoom;
    private final EnemyFactoryImpl enemyFactory;
    private final PickupLogic pickupSpawner = new PickupLogic();
    private final Logger logger = Logger.getLogger(FloorManagerImpl.class.getName());

    /**
     * If the room is locked, the player cannot leave it.
     * This flag that reduces the amount of checks for locked rooms.
     */
    private boolean isRoomLocked = true;

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
        this.enemyFactory = new EnemyFactoryImpl(world.getPlayer());
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void init() {
        changeFloor();
    }

    /** If there are no more alive enemies in this room opens the doors. */
    @Override
    public synchronized void update() {
        if (isRoomLocked && currentRoom instanceof RoomClosable && !roomHasEnemies()) {
            ((RoomClosable) currentRoom).openDoors();
            onRoomClear();
        }
    }

    private void onRoomClear() {
        final Pickup pickup = pickupSpawner.getRandomPickup();
        world.addGameObject(pickup);
        currentRoom.getGameObjects().add(pickup); // save the pickup in the room so that it doesn't despawn
        view.refreshRoom(currentRoom);
        isRoomLocked = false;
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void onDoorCollision(final DoorUnmodifiable door) {
        if (door instanceof DoorLockable
                && ((DoorLockable) door).isLocked()
                && !roomHasEnemies()
                && world.getPlayer().hasKey()) {
            world.getPlayer().useKey();
            ((DoorLockable) door).unlock();
        }
        if (!door.isOpen()) {
            return;
        }
        synchronized (this) {
            changeRoom((Room) door.getRoom());
            world.getPlayer().setPosition(newPlayerPosition(door));
        }
    }

    /** {@inheritDoc} */
    @Override
    public void changeFloor() {
        this.floor = new LevelFloor(currentFloorLevel++, enemyFactory);
        logger.info("New floor: " + floor.toString());
        this.currentRoom = floor.getStaringRoom();
        view.changeFloor();
        changeRoom(currentRoom);
    }

    private synchronized boolean roomHasEnemies() {
        return currentRoom.getGameObjects().stream()
                .anyMatch(g -> g.getTag().equals(GameTag.ENEMY) && !g.isDestroyed());
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
        world.clearAll();
        newRoom.getGameObjects().forEach(gobj -> {
            if (gobj instanceof Door) {
                world.getGameObjects().add(gobj);
            } else {
                world.addGameObject(gobj);
            }
        });
        currentRoom = newRoom;
        if (currentRoom instanceof RoomClosable && roomHasEnemies()) {
            ((RoomClosable) currentRoom).closeDoors();
            isRoomLocked = true;
        }
        currentRoom.setExplored();
        view.refreshRoom(currentRoom);
    }
}
