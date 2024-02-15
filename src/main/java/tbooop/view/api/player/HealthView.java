package tbooop.view.api.player;

import tbooop.view.api.ViewComponent;

public interface HealthView extends ViewComponent {

    /**
     * Change the empty heart with the full one.
     * 
     * @param currentHealth the heart to change
     */
    void toggledHealth(int currentHealth);

    /**
     * Change the full heart with the empty one.
     * 
     * @param currentHealth the heart to change
     */
    void addHealth(int currentHealth);

    /**
     * Add a new Heart.
     */
    void addMaxHealth();

}
