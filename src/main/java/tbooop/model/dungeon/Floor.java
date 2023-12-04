package tbooop.model.dungeon;

import java.util.Queue;
import java.util.Random;

import tbooop.commons.Points2d;
import tbooop.commons.Point2d;
import tbooop.model.dungeon.rooms.impl.RegularRoom;
import tbooop.model.dungeon.rooms.api.Room;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A Floor is a series of rooms organized in a square grid layout.
 */
public class Floor {
    private final Map<Point2d, Room> roomsMap = new LinkedHashMap<>();
    // dead ends are used for placing special rooms, such as item rooms, shops and
    // boss rooms
    private List<Point2d> deadEnds;
    private final Point2d bossRoomPos;
    private final Point2d itemRoomPos;
    // maximum distance in each axis a room can be from the starting room
    private static final int MAX_DIST_FROM_START = 3;
    private static final int SPECIAL_ROOMS_AMOUNT = 2;
    private static final int MINIMUM_ROOMS_AMOUNT = SPECIAL_ROOMS_AMOUNT + 1;

    private final int roomsAmount;
    private int generatedRooms;
    private static final Random RAND = new Random(); // create a single istance and re-use it

    /**
     * Creates a new floor with the desired amount of rooms (>=3).
     * 
     * @param rooms the amount of rooms to generate
     * @throws NullPointerException     if null is passed
     * @throws IllegalArgumentException if a number < 3 is passed
     */
    protected Floor(final int rooms) {
        this.roomsAmount = Objects.requireNonNull(rooms);
        if (roomsAmount < MINIMUM_ROOMS_AMOUNT) {
            throw new IllegalArgumentException("You must pass a number greater than " + MINIMUM_ROOMS_AMOUNT);
        }
        // generate until desired dead ends and rooms amount is reached
        // number of dead ends needs to be >= number of special rooms
        do {
            generate();
            deadEnds = getDeadEnds();
        } while (generatedRooms != rooms || deadEnds.size() < SPECIAL_ROOMS_AMOUNT);
        bossRoomPos = pickBossRoom();
        itemRoomPos = pickSpecialRoom();
        placeDoors();
    }

    /**
     * Returns the rooms map.
     * 
     * @return a Map where the key is a room's position and the value the associated
     *         room object
     */
    public Map<Point2d, Room> getRoomsMap() {
        return Collections.unmodifiableMap(this.roomsMap);
    }

    private void placeDoors() {
        for (final Map.Entry<Point2d, Room> entry : roomsMap.entrySet()) {
            final List<Points2d> doorsList = new ArrayList<>();
            for (final Points2d offset : Points2d.getAll()) {
                final Point2d newPoint = entry.getKey().add(offset.toP2d());
                if (roomsMap.containsKey(newPoint)) {
                    doorsList.add(offset);
                }
            }
            entry.getValue().setDoorSet(doorsList);
        }
    }

    private Point2d pickSpecialRoom() {
        final Point2d picked = deadEnds.get(RAND.nextInt(deadEnds.size()));
        deadEnds.remove(picked);
        return picked;
    }

    // The boss room is the furthest dead end from the starting room.
    private Point2d pickBossRoom() {
        final Iterator<Point2d> iterator = roomsMap.keySet().iterator();
        Point2d lastElement = null;
        while (iterator.hasNext()) {
            lastElement = iterator.next();
        }
        // remove bossRoom from potential special rooms list
        deadEnds.remove(lastElement);
        return lastElement;
    }

    // A dead end is a room with only one neighbor (except the starting room).
    private List<Point2d> getDeadEnds() {
        final List<Point2d> out = new ArrayList<>();
        for (final Point2d roomPos : roomsMap.keySet()) {
            if (neighboursAmount(roomPos) == 1 && !roomPos.equals(Point2d.ZERO)) {
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
        final Point2d startingRoomPos = Point2d.ZERO;
        roomsMap.put(startingRoomPos, new RegularRoom()); // placeholder: change for true starting room
        queue.add(startingRoomPos);
        generatedRooms++;
        while (!queue.isEmpty()) {
            final Point2d current = queue.poll();
            final List<Points2d> directions = new ArrayList<>(Points2d.getAll());
            Collections.shuffle(directions);
            // check all neighbouring rooms
            for (final Points2d dir : directions) {
                final Point2d newSpot = current.add(dir.toP2d());
                // if neighbouring room is already occupied, has more than two neighbours or no
                // more rooms can be generated, give up
                if (!roomsMap.containsKey(newSpot) && generatedRooms < roomsAmount && neighboursAmount(newSpot) < 2
                        && Math.random() > 0.5 && Math.abs(newSpot.getX()) <= MAX_DIST_FROM_START
                        && Math.abs(newSpot.getY()) <= MAX_DIST_FROM_START) {
                    roomsMap.put(newSpot, new RegularRoom());
                    queue.add(newSpot);
                    generatedRooms++;
                }
            }
        }
    }

    private int neighboursAmount(final Point2d pos) {
        int out = 0;
        for (final Points2d offset : Points2d.getAll()) {
            final Point2d newPos = pos.add(offset.toP2d());
            if (roomsMap.containsKey(newPos)) {
                out++;
            }
        }
        return out;
    }

    /**
     * A graphical representation of the Floor.
     * <p>
     * <i>S=Start Room ?=Item Room !=Boss Room</i>
     * 
     * @return A representation of the floor's layout
     */
    @Override
    public String toString() {
        // the lenght of a single side of the square map
        final int mapEdgeLenght = 2 * MAX_DIST_FROM_START + 1;
        String[][] matrix = new String[mapEdgeLenght][mapEdgeLenght];

        final Map<Point2d, List<String>> symbols = new HashMap<>();
        symbols.put(Point2d.ZERO, List.of("S", "Starting Room"));
        symbols.put(itemRoomPos, List.of("?", "Item Room"));
        symbols.put(bossRoomPos, List.of("!", "Boss Room"));

        for (final Point2d room : roomsMap.keySet()) {
            final int x = (int) room.getX() + MAX_DIST_FROM_START;
            final int y = (int) room.getY() + MAX_DIST_FROM_START;

            String symbol = "O"; // Default room symbol
            for (final Map.Entry<Point2d, List<String>> entry : symbols.entrySet()) {
                if (room.equals(entry.getKey())) {
                    symbol = entry.getValue().get(0);
                    break;
                }
            }
            matrix[y][x] = symbol;
        }
        final StringBuilder output = new StringBuilder();
        for (int i = 0; i < mapEdgeLenght; i++) {
            for (int j = 0; j < mapEdgeLenght; j++) {
                output.append('[').append(matrix[i][j] == null ? ' ' : matrix[i][j]).append(']');
            }
            output.append('\n');
        }
        return output.toString();
    }
}
