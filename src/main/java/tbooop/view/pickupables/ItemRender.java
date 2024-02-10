package tbooop.view.pickupables;

import javafx.scene.image.Image;
import tbooop.model.pickupables.Pickupable;
/**
 * Renders items and pickups
 * sprites according to their
 * own label.
 */
public class ItemRender {
    /**Set the correct sprite for every
     * pickupable.
     * 
     * @param gameObject
     * @return ImageView
     */
    public Image getPickupableSprite(final Pickupable gameObject) {
        switch (gameObject.getObjectName()) {
            case GLASS_HEART: return new Image("pickupables/items/glassHeart.png");
            case LOCKED_RING: return new Image("pickupables/items/LockedRings.png");
            case SPICY_SAUCE: return new Image("pickupables/items/spicysauce.png");
            case ZAP: return new Image("pickupables/items/zap.png");
            case GOLDEN_APPLE: return new Image("pickupables/items/forbiddenfruit.png");
            case BILL: return new Image("pickupables/pickups/foreignbill.png");
            case COIN: return new Image("pickupables/pickups/coin.png");
            case KEY: return new Image("pickupables/pickups/key.png");
            case HEART: return new Image("pickupables/pickups/pickup_heart.png");
            default: return new Image("pickupables/pickups/pickup_heart.png");
        }
    }
}
