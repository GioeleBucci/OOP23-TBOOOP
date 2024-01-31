package tbooop.view.api;

import javafx.scene.Scene;
import javafx.stage.Stage;
import tbooop.model.core.api.GameObject;

/**
 * Interface for the main game view.
 */
public interface View {

    /**
     * Adds a game object to the view.
     * 
     * @param gameObject the game object to add
     */
    void addGameObject(GameObject gameObject);

    /**
     * Updates the view.
     * <br></br>
     * This method should be called each frame.
     */
    void update();

    public double getBarsize();

    public Stage getStage();

    public Scene getScene();

    public double getStageAspectRatio();

}
