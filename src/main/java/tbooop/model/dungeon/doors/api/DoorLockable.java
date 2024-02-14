package tbooop.model.dungeon.doors.api;

/**
 * A lockable door representation used in special rooms.
 */
public interface DoorLockable {

    /** @return Whether this door is locked or not. */
    boolean isLocked();

    /** Unlocks the door. */
    void unlock();

}
