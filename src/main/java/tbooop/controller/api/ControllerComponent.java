package tbooop.controller.api;

/**
 * A component of the main controller.
 */
public interface ControllerComponent {

    /**
     * Initializes the component.
     */
    void init();

    /**
     * Updates the component (this method is called each game frame).
     */
    void update();
}
