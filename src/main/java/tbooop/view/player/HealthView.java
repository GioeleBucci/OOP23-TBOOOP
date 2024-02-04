package tbooop.view.player;

import javafx.scene.shape.Rectangle;
import tbooop.view.api.ViewComponent;
import tbooop.view.api.ViewElements;

/**
 * Heart component.
 */
public class HealthView extends ViewComponent {

    private static final int INITIAL_HEART = 6;

    /**
     * To instantiate Health View.
     * @param view
     */
    public HealthView(final ViewElements view) {
        super(view);
    }

    /**
     * add an Heart to View.
     * @param walkableArea it's the game field.
     */
    public void drawHeart(final Rectangle walkableArea) {
        final HealthRender healthPoint = new HealthRender(INITIAL_HEART, walkableArea);

        for (int i = 0; i < INITIAL_HEART; i++) {
            addToRoot(healthPoint.toNode(i));
        }
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}
