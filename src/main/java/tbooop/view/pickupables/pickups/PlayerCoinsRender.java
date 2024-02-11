package tbooop.view.pickupables.pickups;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tbooop.view.pickupables.PlayerPickupsRenderAbs;
/**
 * This class is responsible for updating 
 * the view of the number of coins owned by the player.
 */
public class PlayerCoinsRender extends PlayerPickupsRenderAbs {
    private static final double COIN_IMAGE_LAYOUT = 60.0;
    private final ImageView coinView;
    private final Label coinLabel;
     /**
     * This class is responsible for rendering player's coins number.
     *
     * @param playerCoinsNumber the number of coins owned by the player.
     * @param root The HBox object representing the root of the key bar.
     */
    public PlayerCoinsRender(final int playerCoinsNumber, final HBox root) {
        this.coinView = new ImageView("pickupables/pickups/coin.png");
        this.coinLabel = new Label(Integer.toString(playerCoinsNumber));
        init(root);
    }

    private void init(final HBox root) {
        super.bindPickup(coinView, root, COIN_IMAGE_LAYOUT);
        super.bindLabel(coinLabel, root);
    }

    /** {@inheritDoc} */
    @Override
    public void updateLabelText(final int amountToUpdate) {
        this.coinLabel.setText(Integer.toString(amountToUpdate));
    }
}
