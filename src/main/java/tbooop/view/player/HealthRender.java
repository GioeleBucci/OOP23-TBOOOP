package tbooop.view.player;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import java.util.List;

/** Renders a Hearth. */
public class HealthRender{

    private static final double HEART_SIZE = 1.5;

    /** 
     * @param initialHealth the initial Player health.
     * @param walkableArea it's the game field.
     */
    public HealthRender(final int initialHealth, HBox root, List<ImageView> heartList) {
        init(initialHealth, root, heartList);
    }

    private void init(final int healtPoint, HBox root, List<ImageView> heartList) {
        for (int i = 0; i <= healtPoint; i++) {
            final ImageView heartView = new ImageView("full_hearth.png");
            bindHeart(heartView, root);
            heartList.add(heartView);
        }
    }

    /**
     * Add a new Heart.
     */
    public void addMaxHealth(final ImageView heartView, HBox root) {
        bindHeart(heartView, root);
    }

    private void bindHeart(final ImageView heartView, HBox root) {
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
