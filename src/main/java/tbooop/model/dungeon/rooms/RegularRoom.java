package tbooop.model.dungeon.rooms;

/** A regular room is a non-special room with enemies inside it. */
public class RegularRoom extends BaseRoom {

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "BaseRoom [doorSet=" + getDoorSet() + ", isExplored=" + isExplored() + "]";
    }
}
