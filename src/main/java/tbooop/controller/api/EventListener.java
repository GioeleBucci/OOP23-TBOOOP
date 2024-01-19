package tbooop.controller.api;

/** Interface for an event listener. */
public interface EventListener {
    /**
     * This method must be called by a publisher whenever a new event is produced.
     * 
     * @param event the event this listener will be notified about.
     */
    void notifyEvent(Event event);
}
