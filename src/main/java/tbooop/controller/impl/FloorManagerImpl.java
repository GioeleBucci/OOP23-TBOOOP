package tbooop.controller.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import tbooop.commons.api.Point2d;
import tbooop.commons.api.RoomBounds;
import tbooop.commons.impl.Point2dImpl;
import tbooop.controller.api.FloorManager;
import tbooop.controller.api.World;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.GameTag;
import tbooop.model.dungeon.doors.api.Door;
import tbooop.model.dungeon.doors.api.DoorLockable;
import tbooop.model.dungeon.doors.api.DoorPositions;
import tbooop.model.dungeon.doors.api.DoorUnmodifiable;
import tbooop.model.dungeon.doors.impl.TrapdoorImpl;
import tbooop.model.dungeon.floor.api.Floor;
import tbooop.model.dungeon.floor.impl.LevelFloor;
import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.enemy.impl.EnemyFactoryImpl;
import tbooop.model.pickupables.items.impl.ItemRoomLogic;
import tbooop.model.pickupables.pickups.api.Pickup;
import tbooop.model.pickupables.pickups.impl.PickupLogic;
import tbooop.view.api.View;
import tbooop.view.sound_manager.Sound;
import tbooop.view.sound_manager.SoundManager;

/**
 * The FloorManagerImpl class is responsible for managing the floors and rooms
 * of the game.
 */
public class FloorManagerImpl implements FloorManager {

    private final World world;
    private final View view;
    private final EnemyFactoryImpl enemyFactory;
    private final PickupLogic pickupSpawner = new PickupLogic();
    private final ItemRoomLogic itemSpawner = new ItemRoomLogic();
    private int currentFloorLevel = 1;
    private Room currentRoom;
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
        if (isRoomLocked && !roomHasEnemies()) {
            currentRoom.openDoors();
            onRoomClear();
        }
    }

    private void onRoomClear() {
        if (currentRoom.isFirstRoom() || currentRoom.isSpecial()) {
            return;
        }
        SoundManager.getInstance().playSound(Sound.DOOR_UNLOCK);
        if (currentRoom.isBossRoom()) {
            onBossRoomClear();
            return;
        }
        final Pickup pickup = pickupSpawner.getRandomPickup();
        addGameObjToRoom(pickup);
        isRoomLocked = false;
    }

    private void onBossRoomClear() {
        spawnTrapdoor();
        GameObject item = itemSpawner.getRandomItem();
        item.setPosition(new Point2dImpl(RoomBounds.CENTER.getX(),
                RoomBounds.CENTER.getY() + RoomBounds.HEIGHT / 5));
        addGameObjToRoom(item);
        isRoomLocked = false;
    }

    /** {@inheritDoc} */
    @Override
    public void spawnTrapdoor() {
        addGameObjToRoom(new TrapdoorImpl());
    }

    private void addGameObjToRoom(GameObject gobj) {
        world.addGameObject(gobj);
        // save the gameObject in the room so that it doesn't despawn
        currentRoom.getGameObjects().add(gobj);
        view.refreshRoom(currentRoom);
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
        if (door.isOpen()) {
            changeRoom((Room) door.getRoom());
            world.getPlayer().setPosition(newPlayerPosition(door));
        }
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void changeFloor() {
        final Floor floor = new LevelFloor(currentFloorLevel++, enemyFactory);
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
            } else if (!gobj.isDestroyed()) {
                world.addGameObject(gobj);
            }
        });
        currentRoom = newRoom;
        if (roomHasEnemies()) {
            currentRoom.closeDoors();
            isRoomLocked = true;
        }
        view.refreshRoom(currentRoom);
    }
}
