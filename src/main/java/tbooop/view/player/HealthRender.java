package tbooop.view.player;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/** Renders a Hearth. */
public class HealthRender {

    private static final double HEART_SIZE = 2.2;
    private int scale;
    private static final int SCALE_PERCENTAGE = 15;
    private final Group root = new Group();
    private final List<ImageView> heartList = new ArrayList<>();

    /** 
     * @param initialHealth the initial Player health
     */
    public HealthRender(final int initialHealth, Rectangle walkableArea) {
        init(initialHealth, walkableArea);
    }

    private void init(final int healtPoint, Rectangle walkableArea) {
        for (int i = 0; i < healtPoint; i++) {
            final ImageView heartView = new ImageView("full_hearth.png");
            root.getChildren().add(heartView);

            heartView.fitWidthProperty()
            .bind(walkableArea
            .widthProperty()
            .multiply(heartView.getImage().getWidth() / walkableArea.widthProperty().get()));
    
            heartView.fitHeightProperty()
            .bind(walkableArea
            .heightProperty()
            .multiply(heartView.getImage().getHeight() / walkableArea.heightProperty().get()));

            this.scale = this.scale + 1;
            heartView.setLayoutX(scale * SCALE_PERCENTAGE);

            heartList.add(heartView);
        }
    }

    /**
     * Change the empty heart with the full one.
     * @param currentHealth the heart to change
     */
    public void toggledHealth(final int currentHealth) {
        this.heartList.get(currentHealth).setImage(new Image("empty_hearth.png"));
    }

    /**
     * Change the full heart with the empty one.
     * @param currentHealth the heart to change
     */
    public void addHealth(final int currentHealth) {
        this.heartList.get(currentHealth - 1).setImage(new Image("full_hearth.png")); 
    }

    /**
     * Add a new Heart.
     */
    public void addMaxHealth() {
        final ImageView heartView = new ImageView("full_hearth.png");
        root.getChildren().add(heartView);
        heartView.setScaleX(HEART_SIZE);
        heartView.setScaleY(HEART_SIZE);
        this.scale = this.scale + 1;
        heartView.setLayoutX(scale * SCALE_PERCENTAGE);
        heartList.add(heartView);
    }

    public ImageView toNode(int index) {
        return heartList.get(index);
    }
}
