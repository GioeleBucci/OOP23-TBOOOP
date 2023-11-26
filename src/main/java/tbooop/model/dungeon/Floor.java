package tbooop.model.dungeon;

import java.util.Queue;
import java.util.Random;

import javafx.geometry.Point2D;
import tbooop.commons.Directions;

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
    private final Map<Point2D, Room> roomsMap = new LinkedHashMap<>();
    // dead ends are used for placing special rooms, such as item rooms, shops and
    // boss rooms
    private List<Point2D> deadEnds;
    private final Point2D bossRoomPos;
    private final Point2D itemRoomPos;
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

    /** Returns the rooms map.
     * 
     * @return a Map where the key is a room's position and the value the associated
     * room object
    */
    public Map<Point2D, Room> getRoomsMap() {
        return Collections.unmodifiableMap(this.roomsMap);
    }

    private void placeDoors() {
        for (Map.Entry<Point2D, Room> entry : roomsMap.entrySet()) {
            Room room = entry.getValue();
            Point2D roomPos = entry.getKey();
            List<Point2D> doorsList = new ArrayList<>();
            for (Point2D offset : Directions.ALL_DIRECTIONS) {
                Point2D newPoint = roomPos.add(offset);
                if (roomsMap.containsKey(newPoint)) {
                    doorsList.add(newPoint);
                }
            }
            room.setDoors(doorsList);
        }
    }

    private Point2D pickSpecialRoom() {
        Point2D picked = deadEnds.get(RAND.nextInt(deadEnds.size()));
        deadEnds.remove(picked);
        return picked;
    }

    // The boss room is the furthest dead end from the starting room.
    private Point2D pickBossRoom() {
        Iterator<Point2D> iterator = roomsMap.keySet().iterator();
        Point2D lastElement = null;
        while (iterator.hasNext()) {
            lastElement = iterator.next();
        }
        // remove bossRoom from potential special rooms list
        deadEnds.remove(lastElement);
        return lastElement;
    }

    // A dead end is a room with only one neighbor (except the starting room).
    private List<Point2D> getDeadEnds() {
        List<Point2D> out = new ArrayList<>();
        for (Point2D roomPos : roomsMap.keySet()) {
            if (neighboursAmount(roomPos) == 1 && roomPos != Point2D.ZERO) {
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
        Queue<Point2D> queue = new LinkedList<>();
        Point2D startingRoomPos = Point2D.ZERO;
        roomsMap.put(startingRoomPos, new Room()); // placeholder: change for true starting room
        queue.add(startingRoomPos);
        generatedRooms++;
        while (!queue.isEmpty()) {
            Point2D current = queue.poll();
            List<Point2D> directions = new ArrayList<>(Directions.ALL_DIRECTIONS);
            Collections.shuffle(directions);
            // check all neighbouring rooms
            for (Point2D dir : directions) {
                Point2D newSpot = current.add(dir);
                // if neighbouring room is already occupied, has more than two neighbours or no
                // more rooms can be generated, give up
                if (!roomsMap.containsKey(newSpot) && generatedRooms < roomsAmount && neighboursAmount(newSpot) < 2) {
                    // Random 50% chance to give up
                    if (Math.random() > 0.5 && Math.abs(newSpot.getX()) <= MAX_DIST_FROM_START
                            && Math.abs(newSpot.getY()) <= MAX_DIST_FROM_START) {
                        roomsMap.put(newSpot, new Room());
                        queue.add(newSpot);
                        generatedRooms++;
                    }
                }
            }
        }
    }

    private int neighboursAmount(final Point2D pos) {
        int out = 0;
        for (Point2D offset : Directions.ALL_DIRECTIONS) {
            Point2D newPos = pos.add(offset);
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
        String output = "";
        // the lenght of a single side of the square map
        final int mapEdgeLenght = 2 * MAX_DIST_FROM_START + 1;
        String[][] matrix = new String[mapEdgeLenght][mapEdgeLenght];

        Map<Point2D, List<String>> symbols = new HashMap<>();
        symbols.put(Point2D.ZERO, List.of("S", "Starting Room"));
        symbols.put(itemRoomPos, List.of("?", "Item Room"));
        symbols.put(bossRoomPos, List.of("!", "Boss Room"));

        for (Point2D room : roomsMap.keySet()) {
            int x = (int) room.getX() + MAX_DIST_FROM_START;
            int y = (int) room.getY() + MAX_DIST_FROM_START;

            String symbol = "O"; // Default room symbol
            for (Map.Entry<Point2D, List<String>> entry : symbols.entrySet()) {
                if (room.equals(entry.getKey())) {
                    symbol = entry.getValue().get(0);
                    break;
                }
            }
            matrix[y][x] = symbol;
        }
        for (int i = 0; i < mapEdgeLenght; i++) {
            for (int j = 0; j < mapEdgeLenght; j++) {
                output += ("[" + (matrix[i][j] == null ? " " : matrix[i][j]) + "]");
            }
            output += "\n";
        }
        return output;
    }
}
