package tbooop.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import tbooop.view.api.ViewComponent;
import tbooop.view.api.ViewElements;
import java.util.logging.Logger;

/**
 * This class represents a demo component that extends ViewComponent.
 * It provides functionality to draw a square on the view.
 */
public class DemoComponent extends ViewComponent {

    private final Logger logger = Logger.getLogger(DemoComponent.class.getName());

    /**
     * Constructs a DemoComponent with the specified ViewElements.
     * 
     * @param view the ViewElements to associate with this component
     */
    public DemoComponent(final ViewElements view) {
        super(view);
    }

    /**
     * Draws a square on the view.
     * The square is represented by a Rectangle object with a size of 100x100 and a
     * red color.
     * The square is added to the root of the view's children.
     * This method also logs a message indicating that the square has been drawn.
     */
    public void drawSquare() {
        final Rectangle rect = new Rectangle(100, 100, Color.RED);
        addToRoot(rect);
        logger.info("View Component demo: square drawn");
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
