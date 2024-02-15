package tbooop.view.api;

import javafx.scene.Node;

/**
 * The ViewComponent interface represents a component in the view layer of an application.
 * It provides methods for adding nodes to the root of the component, retrieving the main view,
 * and updating the component.
 */
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
