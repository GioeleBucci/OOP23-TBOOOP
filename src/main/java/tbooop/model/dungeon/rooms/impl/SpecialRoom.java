package tbooop.model.dungeon.rooms.impl;

/** A special room. */
public class SpecialRoom extends BaseRoom {

    /** {@inheritDoc} */
    @Override
    public boolean isSpecial() {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "SpecialRoom [doorSet=" + getDoorMap() + ", isExplored=" + isExplored() + "]\n";
    }

}
