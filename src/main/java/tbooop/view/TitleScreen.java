package tbooop.view;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import tbooop.view.sound_manager.MusicPlayer;

public class TitleScreen {

    private TitleScreen() {
    }

    private static final long TIME_AMOUNT = 600;

    public static Scene getScreen(Stage stage, Scene mainScene) {
        MusicPlayer.getInstance().playTitleMusic();
        final Group root = new Group();
        final ImageView deathScreen = new ImageView(new Image("ui/title_screen.png"));
        final Scene scene = new Scene(root, mainScene.getWidth(), mainScene.getHeight());
        final long startTime = System.currentTimeMillis();
        deathScreen.fitWidthProperty().bind(scene.widthProperty());
        deathScreen.fitHeightProperty().bind(scene.heightProperty());
        root.getChildren().add(deathScreen);
        scene.setOnKeyPressed((KeyEvent event) -> {
            if (System.currentTimeMillis() - startTime >= TIME_AMOUNT) {
                final FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2),
                        mainScene.getRoot());
                fadeTransition.setFromValue(0);
                fadeTransition.setToValue(1);
                fadeTransition.setAutoReverse(true);
                fadeTransition.onFinishedProperty().set(e -> {
                    MusicPlayer.getInstance().playDefaultMusic();
                });
                fadeTransition.play();
                stage.setScene(mainScene);
            }
        });
        return scene;
    }
}
