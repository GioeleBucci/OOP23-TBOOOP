package tbooop.view.api;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Interface for the main game view.
 */
public interface ViewUnm {
     /**
     * Returns the view's stage.
     * 
     * @return the view's stage
     */
    Stage getStage();

    /**
     * Returns the view's scene.
     * 
     * @return the view's scene
     */
    Scene getScene();

    /**
     * Returns the aspect ratio of the view's stage.
     * 
     * @return the aspect ratio of the view's stage ({@code Width/Height})
     */
    double getStageAspectRatio();
}
