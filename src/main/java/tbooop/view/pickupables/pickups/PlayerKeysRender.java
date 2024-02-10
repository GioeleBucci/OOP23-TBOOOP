package tbooop.view.pickupables.pickups;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * This class is responsible for updating 
 * the view of the number of keys owned by the player.
 */
public class PlayerKeysRender {
    private static final double KEY_SIZE = 1.5;
    private static final double LABEL_SIZE = 1.0;
    private static final double KEY_IMAGE_LAYOUT = 30.0;
    private final Font keyFont = new Font("Serif", 18);
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
        bindKey(keyView, root);
        bindLabel(keyLabel, root);
    }

    /**
     * Update the label containing the number
     * of keys.
     * 
     * @param amountToUpdate key number to update.
     */
    public void updateLabelText(final int amountToUpdate) {
        this.keyLabel.setText(Integer.toString(amountToUpdate));
    }

    private void bindKey(final ImageView keyView, final HBox root) {
        root.getChildren().add(keyView);
        root.setLayoutY(KEY_IMAGE_LAYOUT);

        keyView.fitWidthProperty()
        .bind(root.getScene()
        .widthProperty()
        .multiply(keyView.getImage().getWidth() / root.getScene().widthProperty().get()));

        keyView.fitHeightProperty()
        .bind(root.getScene()
        .heightProperty()
        .multiply(keyView.getImage().getHeight() / root.getScene().heightProperty().get()));

        keyView.setScaleX(KEY_SIZE);
        keyView.setScaleY(KEY_SIZE);

    }

    private void bindLabel(final Label label, final HBox root) {
        label.setFont(keyFont);
        label.setTextFill(Color.GREY);
        label.setScaleX(LABEL_SIZE);
        label.setScaleY(LABEL_SIZE);
        root.getChildren().add(label);
    }
}
