package tbooop.view;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Renders a Hearth. */
public class HealthRender {

    private int scale;
    private static final int SCALE_PERCENTAGE = 25;
    private final Group root = new Group();
    private List<ImageView> heartList = new ArrayList<>();

    /** 
     * @param parentRoot the root this attaches to
     * @param initialHealth the initial Player health
     */
    public HealthRender(final Group parentRoot, final int initialHealth) {
        parentRoot.getChildren().add(this.root);
        init(initialHealth);
    }

    private void init(final int healtPoint) {
        for (int i = 0; i < healtPoint; i++) {
            ImageView heartView = new ImageView("full_hearth.png");
            root.getChildren().add(heartView);
            heartView.setScaleX(2.0);
            heartView.setScaleY(2.0);
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
        ImageView heartView = new ImageView("full_hearth.png");
        root.getChildren().add(heartView);
        heartView.setScaleX(2.0);
        heartView.setScaleY(2.0);
        this.scale = this.scale + 1;
        heartView.setLayoutX(scale * SCALE_PERCENTAGE);
        heartList.add(heartView);
    }
}
