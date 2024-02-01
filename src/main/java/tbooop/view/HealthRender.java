package tbooop.view;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HealthRender {
    
    private int scale;
    private static final int SCALE_PERCENTAGE = 25;
    private final Group root = new Group();
    private List<ImageView> heartList = new ArrayList<>();

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

    public void toggledHealth(final int currentHealth) {
        this.heartList.get(currentHealth).setImage(new Image("empty_hearth.png"));
    }

    public void addHealth(final int currentHealth) {
        this.heartList.get(currentHealth - 1).setImage(new Image("full_hearth.png")); 
    }

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
