package tbooop.model.dungeon.rooms.impl;

/** A regular room is a non-special room with enemies inside it. */
public class RegularRoom extends BaseRoom {

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "BaseRoom [doorSet=" + getDoorMap() + ", isExplored=" + isExplored() + "]\n";
    }

    /** {@inheritDoc} */
    @Override
    public boolean isSpecial() {
        return false;
    }
}
