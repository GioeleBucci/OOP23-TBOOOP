package tbooop.view.api;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * View elements that can be used by the various view components.
 */
public interface ViewElements {
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
     * Returns the root node of the view's scene.
     * 
     * @return the root node of the view's scene
     */
    Group getRoot();

    /**
     * Returns the aspect ratio of the view's stage.
     * 
     * @return the aspect ratio of the view's stage ({@code Width/Height})
     */
    double getStageAspectRatio();
}
