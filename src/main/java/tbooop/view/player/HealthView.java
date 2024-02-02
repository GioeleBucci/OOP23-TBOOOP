package tbooop.view.player;

import javafx.scene.shape.Rectangle;
import tbooop.view.api.ViewComponent;
import tbooop.view.api.ViewElements;

/**
 * 
 */
public class HealthView extends ViewComponent {

    private static final int INITIAL_HEART = 6;

    public HealthView(final ViewElements view){
        super(view);
    }

    public void drawHeart(Rectangle walkableArea) {
        HealthRender healthPoint = new HealthRender(INITIAL_HEART, walkableArea);

        for (int i = 0; i < INITIAL_HEART; i++) {
            addToRoot(healthPoint.toNode(i));
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
