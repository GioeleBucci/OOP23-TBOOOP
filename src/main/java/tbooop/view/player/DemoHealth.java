package tbooop.view.player;

import tbooop.view.api.ViewComponent;
import tbooop.view.api.ViewElements;

public class DemoHealth extends ViewComponent{
    
    HealthRender healthPoint;
    public DemoHealth(final ViewElements view){
        super(view);
        this.healthPoint = new HealthRender(view.getRoot(), 6);
    }
}
