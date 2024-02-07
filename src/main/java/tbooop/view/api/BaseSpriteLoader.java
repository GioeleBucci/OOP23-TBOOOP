package tbooop.view.api;

import javafx.scene.image.Image;
import tbooop.model.core.api.GameObject;

/**
 * A base sprite loader provides the image associated with every game object.
 */
public interface BaseSpriteLoader {

    /**
     * Returns the base sprite image associated with the given GameObject.
     * @param gameObj
     * @return the game object's base sprite image
     * @throws NullPointerException if gameObj is null
     */
    Image getGameObjectSprite(GameObject gameObj);
}
