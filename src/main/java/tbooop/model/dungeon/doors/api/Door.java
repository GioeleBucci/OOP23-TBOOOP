package tbooop.model.dungeon.doors.api;

/** A door representation. */
public interface Door extends DoorUnmodifiable {

    /** Opens the door. */
    void open();

    /** Closes the door. */
    void close();

}
