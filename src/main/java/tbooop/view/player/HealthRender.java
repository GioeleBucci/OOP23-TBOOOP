package tbooop.view.player;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.List;

/**
 * Represents a view for displaying the health of a player.
 * This class is responsible for rendering the health bar and updating it based on the player's health.
 */
public class HealthRender {

    private static final double HEART_SIZE = 1.5;

    /**
     * Represents a view for displaying the health of a player.
     * This class is responsible for rendering the health bar and updating it based on the player's health.
     *
     * @param initialHealth The initial health of the player.
     * @param root The HBox object representing the root of the health bar.
     * @param heartList The List of ImageView objects representing the hearts of the health bar.
     */
    public HealthRender(final int initialHealth, final HBox root, final List<ImageView> heartList) {
        init(initialHealth, root, heartList);
    }

    private void init(final int healtPoint, final HBox root, final List<ImageView> heartList) {
        for (int i = 0; i < healtPoint; i++) {
            final ImageView heartView = new ImageView("ui/full_hearth.png");
            bindHeart(heartView, root);
            heartList.add(heartView);
        }
    }

    /**
     * Adds the maximum health to the player's view.
     * 
     * @param heartView The ImageView representing the heart.
     * @param root The HBox container for the heartView.
     */
    public void addMaxHealth(final ImageView heartView, final HBox root) {
        bindHeart(heartView, root);
    }

    private void bindHeart(final ImageView heartView, final HBox root) {
        root.getChildren().add(heartView);

        heartView.fitWidthProperty()
        .bind(root.getScene()
        .widthProperty()
        .multiply(heartView.getImage().getWidth() / root.getScene().widthProperty().get()));

        heartView.fitHeightProperty()
        .bind(root.getScene()
        .heightProperty()
        .multiply(heartView.getImage().getHeight() / root.getScene().heightProperty().get()));
        
        heartView.setScaleX(HEART_SIZE);
        heartView.setScaleY(HEART_SIZE);
    }
}
