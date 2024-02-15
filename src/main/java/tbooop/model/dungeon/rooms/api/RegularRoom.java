package tbooop.model.dungeon.rooms.api;

/** A regular room is a non-special room. */
public abstract class RegularRoom extends AbstractRoom {

    /** {@inheritDoc} */
    @Override
    public boolean isSpecial() {
        return false;
    }

}
