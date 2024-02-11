package tbooop.view.pickupables.pickups;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tbooop.view.pickupables.PlayerPickupsRenderAbs;

/**
 * This class is responsible for updating 
 * the view of the number of keys owned by the player.
 */
public class PlayerKeysRender extends PlayerPickupsRenderAbs {
    private static final double KEY_IMAGE_LAYOUT = 30.0;
    private final ImageView keyView;
    private final Label keyLabel;
    /**
     * This class is responsible for rendering player's keys number.
     *
     * @param playerKeysNumber the number of keys owned by the player.
     * @param root The HBox object representing the root of the key bar.
     */
    public PlayerKeysRender(final int playerKeysNumber, final HBox root) {
        this.keyView = new ImageView("pickupables/pickups/key.png");
        this.keyLabel = new Label(Integer.toString(playerKeysNumber));
        init(root);
    }

    private void init(final HBox root) {
        super.bindPickup(keyView, root, KEY_IMAGE_LAYOUT);
        super.bindLabel(keyLabel, root);
    }

    /** {@inheritDoc} */
    @Override
    public void updateLabelText(final int amountToUpdate) {
        this.keyLabel.setText(Integer.toString(amountToUpdate));
    }
}
