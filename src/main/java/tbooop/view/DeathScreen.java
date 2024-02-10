package tbooop.view;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * The death screen.
 */
public class DeathScreen {

    /**
     * Returns the death scene.
     * 
     * @return the death scene
     */
    public Scene getDeathScene() {
        final Group root = new Group();
        final ImageView deathScreen = new ImageView(new Image("ui/end_screen.png"));
        final Scene scene = new Scene(root);

        deathScreen.fitWidthProperty().bind(scene.widthProperty());
        deathScreen.fitHeightProperty().bind(scene.heightProperty());

        root.getChildren().add(deathScreen);

        scene.setOnKeyPressed((KeyEvent event) -> {
            Platform.exit();
        });

        return scene;
    }
}
