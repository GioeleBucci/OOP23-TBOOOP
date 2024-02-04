package tbooop.controller.api;

public interface ControllerComponent {

    /**
     * Initializes the component.
     */
    public void init();

    /**
     * Updates the component (this method is called each game frame).
     */
    public void update();
}
