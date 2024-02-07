package tbooop.view.pickupables;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public ImageView getPickupableSprite(final Pickupable gameObject) {
        final ImageView imgView = new ImageView();
        switch (gameObject.getObjectName()) {
            case GLASS_HEART -> imgView.setImage(new Image("pickupables/items/glassHeart.png"));
            case LOCKED_RING -> imgView.setImage(new Image("pickupables/items/lockedRings.png"));
            case SPICY_SOUCE -> imgView.setImage(new Image("pickupables/items/spicySauce.png"));
            case ZAP -> imgView.setImage(new Image("pickupables/items/zap.png"));
            case BILL -> imgView.setImage(new Image("pickupables/items/foreignBill.png"));
            default -> imgView.setImage(new Image("down2.png"));
        }
        return imgView;
    }
}
