package tbooop.view;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import tbooop.view.sound_manager.MusicPlayer;

/**
 * DeathScreen contains the scene that shows a death screen, when it appears the
 * gameis over.
 * After a certain amount of seconds have passed any key pressed will close the
 * application.
 */
public class DeathScreen {

    private DeathScreen() {
    }

    private static final long TIME_AMOUNT = 800;

    public static Scene getDeathScene(Scene mainScene) {
        MusicPlayer.getInstance().playDeadMusic();
        final Group root = new Group();
        final ImageView deathScreen = new ImageView(new Image("ui/end_screen.png"));
        final Scene scene = new Scene(root, mainScene.getWidth(), mainScene.getHeight());
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
