package tbooop.view;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 * DeathScreen contains the scene that shows a death screen, when it appears the
 * gameis over.
 * After a certain amount of seconds have passed any key pressed will close the
 * application.
 */
public class DeathScreen {

    private static final long TIME_AMOUNT = 1000;

    /**
     * Returns the death scene. This scene also sets a key listener that terminates
     * the application,
     * but only if a certain amount of time has passed since this method gets
     * called.
     * 
     * @param w the width of the new scene
     * @param h the height of the new scene
     * 
     * @return the death scene
     */
    public Scene getDeathScene(final double w, final double h) {
        final Group root = new Group();
        final ImageView deathScreen = new ImageView(new Image("ui/end_screen.png"));
        final Scene scene = new Scene(root, w, h);
        final long startTime = System.currentTimeMillis();
        deathScreen.fitWidthProperty().bind(scene.widthProperty());
        deathScreen.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(deathScreen);
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (System.currentTimeMillis() - startTime >= TIME_AMOUNT) {
                Platform.exit();
            }
        });
        return scene;
    }

}
