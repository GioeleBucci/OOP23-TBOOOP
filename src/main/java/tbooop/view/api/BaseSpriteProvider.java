package tbooop.view.api;

import javafx.scene.image.ImageView;
import tbooop.model.core.api.GameObjectUnmodifiable;

/**
 * A base sprite provider provides the image associated with every game object.
 */
public interface BaseSpriteProvider {

    /**
     * Returns the base sprite image associated with the given GameObject.
     * @param gameObj the game object
     * @return the game object's base sprite image contained in an ImageView
     * @throws NullPointerException if gameObj is null
     */
    ImageView getGameObjectSprite(GameObjectUnmodifiable gameObj);
}
