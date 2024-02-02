package tbooop.view.player;

import javafx.scene.shape.Rectangle;
import tbooop.view.api.ViewComponent;
import tbooop.view.api.ViewElements;

public class DemoHealth extends ViewComponent{
    

    public DemoHealth(final ViewElements view){
        super(view);
    }

    public void drawHeart(Rectangle walkableArea) {
        HealthRender healthPoint = new HealthRender(6, walkableArea);
        addToRoot(healthPoint.toNode(0));
    }
}
