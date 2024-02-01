package tbooop.view;

import javafx.scene.Group;

public class HealthRender {
    
    private final Group root = new Group();
    private int healtPoint;

    public HealthRender(final Group parentRoot, final int initialHealth) {
        parentRoot.getChildren().add(this.root);
        this.healtPoint = initialHealth;
        init();
    }

    private void init() {
        
    }

    public void toggledHealth() {
        this.healtPoint = this.healtPoint - 1;
    }

    public void addHealth() {
        this.healtPoint = this.healtPoint + 1; 
    }
}
