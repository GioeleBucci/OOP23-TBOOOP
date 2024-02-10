package tbooop.view;

import java.util.logging.Logger;

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

    private static final int MIN_DURATION = 2000;
    private final Logger logger = Logger.getLogger(DeathScreen.class.getName());

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
            try {
                Thread.sleep(MIN_DURATION);
            } catch (InterruptedException e) {
                logger.fine("InterruptedException occurred while waiting.");
            }
            Platform.exit();
        });

        return scene;
    }
}
