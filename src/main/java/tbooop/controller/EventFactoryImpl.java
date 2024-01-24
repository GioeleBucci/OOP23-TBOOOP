package tbooop.controller;

import tbooop.commons.api.Point2d;
import tbooop.commons.Point2ds;
import tbooop.controller.api.Event;
import tbooop.controller.api.EventFactory;
import tbooop.model.dungeon.floor.LevelFloor;
import java.util.NoSuchElementException;

/**
 * The EventFactoryImpl class is responsible for creating events.
 */
public final class EventFactoryImpl implements EventFactory {

    @Override
    public Event changeFloor() {
        return world -> world.setFloor(new LevelFloor(world.getFloor().getLevel() + 1));
    }

    @Override
    public Event changeRoom(final Point2ds dir) {
        return world -> {
            final Point2d newRoomPos = dir.toP2d().add(world.getCurrentRoom());
            if (world.getFloor().getRoomsMap().containsKey(newRoomPos)) {
                throw new NoSuchElementException("Trying to access non existing room of position " + newRoomPos);
            }
            world.setCurrentRoom(newRoomPos);
        };
    }

}
