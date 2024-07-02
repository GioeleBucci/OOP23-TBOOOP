package tbooop.model.dungeon.floor.api;

import java.util.Queue;
import java.util.Random;
import java.util.function.Supplier;

import tbooop.commons.api.CardinalDirection;
import tbooop.commons.api.Point2d;
import tbooop.commons.impl.Point2dImpl;
import tbooop.model.enemy.api.EnemyFactory;
import tbooop.model.dungeon.rooms.api.Room;
import tbooop.model.dungeon.rooms.api.RoomFactory;
import tbooop.model.dungeon.rooms.impl.RoomFactoryImpl;
import tbooop.model.dungeon.doors.api.DoorPositions;
import tbooop.model.dungeon.doors.api.DoorUnmodifiable;
import tbooop.model.dungeon.doors.impl.RegularDoor;
import tbooop.model.dungeon.doors.impl.SpecialDoor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A Floor is a series of rooms organized in a square grid layout.
 */
public abstract class BaseFloor implements Floor {
    /** maximum distance in each axis a room can be from the starting room (0;0). */
    public static final int MAX_DIST_FROM_START = 3;
    private static final int SPECIAL_ROOMS_AMOUNT = 3;
    private static final int MINIMUM_ROOMS_AMOUNT = SPECIAL_ROOMS_AMOUNT + 1;
    private static final Random RAND = new Random(); // create a single istance and re-use it
    private final EnemyFactory enemyFactory;
    private final Supplier<Integer> enemyAmountSupplier;
    private final RoomFactory roomFactory;
    private final Map<Point2d, Room> roomsMap = new LinkedHashMap<>();
    private final int roomsAmount;
    private int generatedRooms;
    /*
     * dead ends are used for placing special rooms, such as item rooms, shops and
     * trapdoor rooms.
     */
    private List<Point2d> deadEnds;

    /**
     * Creates a new floor with the desired amount of rooms (>=4).
     * 
     * @param rooms               the amount of rooms to generate
     * @param enemyFactory        the factory used for creating enemies inside enemy
     *                            rooms
     * @param enemyAmountSupplier the function used to get the amount of enemies to
     *                            spawn
     * @throws NullPointerException     if null is passed
     * @throws IllegalArgumentException if a number < 4 is passed
     */
    protected BaseFloor(final int rooms, final EnemyFactory enemyFactory, final Supplier<Integer> enemyAmountSupplier) {
        if (rooms < MINIMUM_ROOMS_AMOUNT) {
            throw new IllegalArgumentException("You must pass a number greater than " + MINIMUM_ROOMS_AMOUNT);
        }
        this.enemyFactory = Objects.requireNonNull(enemyFactory);
        this.enemyAmountSupplier = Objects.requireNonNull(enemyAmountSupplier);
        this.roomsAmount = rooms;
        this.roomFactory = new RoomFactoryImpl();
        /*
         * generate until desired dead ends and rooms amount is reached
         * number of dead ends needs to be >= number of special rooms
         */
        do {
            generate();
            deadEnds = getDeadEnds();
        } while (generatedRooms != rooms || deadEnds.size() < SPECIAL_ROOMS_AMOUNT);
        roomsMap.put(pickTrapdoorRoomPos(), roomFactory.trapdoorRoom());
        roomsMap.put(pickSpecialRoomPos(), roomFactory.shopRoom());
        roomsMap.put(pickSpecialRoomPos(), roomFactory.itemRoom());
        roomsMap.values().forEach(Room::init);
        placeDoors();
    }

    /** {@inheritDoc} */
    @Override
    public Room getStaringRoom() {
        return roomsMap.get(Point2dImpl.ZERO);
    }

    private void placeDoors() {
        roomsMap.entrySet().forEach(entry -> {
            CardinalDirection.getAll().forEach(offset -> {
                final Point2d newPoint = entry.getKey().add(offset.toP2d());
                if (roomsMap.containsKey(newPoint)) {
                    final Room neighbour = roomsMap.get(newPoint);
                    final Point2d doorPos = DoorPositions.getDoorPosition(offset);
                    // check if neighbour room needs a key to enter
                    final DoorUnmodifiable door = neighbour.isSpecial() || entry.getValue().isSpecial()
                            ? new SpecialDoor(doorPos, neighbour)
                            : new RegularDoor(doorPos, neighbour);
                    entry.getValue().addDoor(offset, door);
                }
            });
        });
    }

    private Point2d pickSpecialRoomPos() {
        final Point2d picked = deadEnds.get(RAND.nextInt(deadEnds.size()));
        deadEnds.remove(picked);
        return picked;
    }

    /*
     * The room that takes to the next level is the furthest dead end from
     * the starting room.
     */
    private Point2d pickTrapdoorRoomPos() {
        final Iterator<Point2d> iterator = roomsMap.keySet().iterator();
        Point2d lastElement = null;
        while (iterator.hasNext()) {
            lastElement = iterator.next();
        }
        deadEnds.remove(lastElement);
        return lastElement;
    }

    /*
     * A dead end is a room with only one neighbor (except the starting room).
     */
    private List<Point2d> getDeadEnds() {
        final List<Point2d> out = new ArrayList<>();
        for (final Point2d roomPos : roomsMap.keySet()) {
            if (neighboursAmount(roomPos) == 1 && !roomPos.equals(Point2dImpl.ZERO)) {
                out.add(roomPos);
            }
        }
        return out;
    }

    /**
     * This algorithm works similarly to a BFS, starting from the starting room and
     * generating outwards.
     * <p>
     * This emulates the dungeon generation of the original
     * game, more on that<a href=
     * "https://www.boristhebrave.com/2020/09/12/dungeon-generation-in-binding-of-isaac/">
     * here</a>
     */
    private void generate() {
        roomsMap.clear();
        generatedRooms = 0;
        final Queue<Point2d> queue = new LinkedList<>();
        final Point2d startingRoomPos = Point2dImpl.ZERO;
        roomsMap.put(startingRoomPos, roomFactory.startingRoom());
        queue.add(startingRoomPos);
        generatedRooms++;
        while (!queue.isEmpty()) {
            final Point2d current = queue.poll();
            final List<CardinalDirection> directions = new ArrayList<>(CardinalDirection.getAll());
            Collections.shuffle(directions);
            // check all neighbouring rooms
            for (final CardinalDirection dir : directions) {
                final Point2d newSpot = current.add(dir.toP2d());
                // if neighbouring room is already occupied, has more than two neighbours or no
                // more rooms can be generated, give up
                if (!roomsMap.containsKey(newSpot) && generatedRooms < roomsAmount && neighboursAmount(newSpot) < 2
                        && Math.random() > 0.5 && Math.abs(newSpot.getX()) <= MAX_DIST_FROM_START
                        && Math.abs(newSpot.getY()) <= MAX_DIST_FROM_START) {
                    roomsMap.put(newSpot, roomFactory.enemyRoom(enemyFactory, enemyAmountSupplier.get()));
                    queue.add(newSpot);
                    generatedRooms++;
                }
            }
        }
    }

    private int neighboursAmount(final Point2d pos) {
        int out = 0;
        for (final CardinalDirection offset : CardinalDirection.getAll()) {
            final Point2d newPos = pos.add(offset.toP2d());
            if (roomsMap.containsKey(newPos)) {
                out++;
            }
        }
        return out;
    }
}
