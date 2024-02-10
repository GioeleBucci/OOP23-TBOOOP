package tbooop.view.pickupables.pickups;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * This class is responsible for updating 
 * the view of the number of coins owned by the player.
 */
public class PlayerCoinsRender {
    private static final double COIN_SIZE = 1.5;
    private static final double LABEL_SIZE = 1.0;
    private static final double COIN_IMAGE_LAYOUT = 60.0;
    private final Font coinFont = new Font("Serif", 18);
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
        bindKey(coinView, root);
        bindLabel(coinLabel, root);
    }

    /**
     * Update the label containing the number
     * of coins.
     * 
     * @param amountToUpdate number of coins to update.
     */
    public void updateLabelText(final int amountToUpdate) {
        this.coinLabel.setText(Integer.toString(amountToUpdate));
    }

    private void bindKey(final ImageView coinView, final HBox root) {
        root.getChildren().add(coinView);
        root.setLayoutY(COIN_IMAGE_LAYOUT);

        coinView.fitWidthProperty()
        .bind(root.getScene()
        .widthProperty()
        .multiply(coinView.getImage().getWidth() / root.getScene().widthProperty().get()));

        coinView.fitHeightProperty()
        .bind(root.getScene()
        .heightProperty()
        .multiply(coinView.getImage().getHeight() / root.getScene().heightProperty().get()));

        coinView.setScaleX(COIN_SIZE);
        coinView.setScaleY(COIN_SIZE);

    }

    private void bindLabel(final Label label, final HBox root) {
        label.setFont(coinFont);
        label.setTextFill(Color.GREY);
        label.setScaleX(LABEL_SIZE);
        label.setScaleY(LABEL_SIZE);
        root.getChildren().add(label);
    }
}
