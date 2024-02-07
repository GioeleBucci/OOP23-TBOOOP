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
            case LOCKED_RING: return new Image("pickupables/items/lockedRings.png");
            case SPICY_SAUCE: return new Image("pickupables/items/spicySauce.png");
            case ZAP: return new Image("pickupables/items/zap.png");
            case GOLDEN_APPLE: return new Image("pickupables/items/forbiddenFruit.png");
            case BILL: return new Image("pickupables/items/foreignBill.png");
            default: return new Image("down2.png");
        }
    }
}
