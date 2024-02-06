package tbooop.model.dungeon.rooms.api;

/** A special room. */
public abstract class SpecialRoom extends RoomAbstract {

    /** {@inheritDoc} */
    @Override
    public boolean isSpecial() {
        return true;
    }

}
