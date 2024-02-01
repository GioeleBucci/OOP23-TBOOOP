package tbooop.view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class HealthRender {
    
    private final Group root = new Group();
    private int healtPoint;

    public HealthRender(final Group parentRoot, final int initialHealth) {
        parentRoot.getChildren().add(this.root);
        this.healtPoint = initialHealth;
        init();
    }

    private void init() {
        Image heart = new Image();
    }

    public void toggledHealth() {
        this.healtPoint = this.healtPoint - 1;
    }

    public void addHealth() {
        this.healtPoint = this.healtPoint + 1; 
    }
}
