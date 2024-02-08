package tbooop.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tbooop.model.core.api.GameObjectUnmodifiable;
import tbooop.model.pickupables.Pickupable;
import tbooop.model.player.api.PlayerProjectile;
import tbooop.view.api.BaseSpriteProvider;
import tbooop.view.pickupables.ItemRender;

/**
 * Basic implementation of BaseSpriteLoader.
 */
public class BaseSpriteProviderImpl implements BaseSpriteProvider {

    private final ItemRender itemRender = new ItemRender();

    /** {@inheritDoc} */
    @Override
    public ImageView getGameObjectSprite(final GameObjectUnmodifiable gameObj) {
        final ImageView imgView = new ImageView();
         switch (gameObj.getTag()) {
            case PROJECTILE -> {
                if (gameObj instanceof PlayerProjectile) {
                    imgView.setImage(new Image("projectile/playerproj.png"));
                } else {
                    imgView.setImage(new Image("projectile/enemyproj.png"));
                }
            }
            case PICKUP -> {
                if (gameObj instanceof Pickupable) {
                    imgView.setImage(itemRender.getPickupableSprite((Pickupable) gameObj));
                }
            }
            case TRAPDOOR -> imgView.setImage(new Image("tileset/trapdoor.png"));
            default -> imgView.setImage(new Image("down2.png"));
        }
        return imgView;
    }

}
