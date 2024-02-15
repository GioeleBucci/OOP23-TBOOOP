package tbooop.view.api;

import javafx.scene.Node;

public interface ViewComponent {

    /**
     * Adds a node to the root of this ViewComponent.
     * 
     * @param node the node to attach to this component's root
     */
    void addToRoot(Node node);

    /**
     * Returns the main view associated with this ViewComponent.
     * 
     * @return the main view
     */
    ViewElements getView();

    /**
     * To update ViewComponent.
     */
    void update();

}
