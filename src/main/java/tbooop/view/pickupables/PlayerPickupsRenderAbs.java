package tbooop.view.pickupables;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * Class for rendering pickup icon and label
 * to display number of a certain pickup owned
 * by the player.
 * In particular keys and coins.
 */
public abstract class PlayerPickupsRenderAbs {
    private static final double PICKUP_SIZE = 1.5;
    private static final double LABEL_SIZE = 1.0;
    private final Font pickupFont = new Font("Serif", 18);
    /**
     * Binds pickup icon to the correct position
     * of the view.
     * 
     * @param pickupView the image to bind
     * @param root root to be added to
     * @param pickupIconLayout layout to be binded
     */
    protected void bindPickup(final ImageView pickupView, final HBox root, final double pickupIconLayout) {
        root.getChildren().add(pickupView);
        root.setLayoutY(pickupIconLayout);

        pickupView.fitWidthProperty()
        .bind(root.getScene()
        .widthProperty()
        .multiply(pickupView.getImage().getWidth() / root.getScene().widthProperty().get()));

        pickupView.fitHeightProperty()
        .bind(root.getScene()
        .heightProperty()
        .multiply(pickupView.getImage().getHeight() / root.getScene().heightProperty().get()));

        pickupView.setScaleX(LABEL_SIZE);
        pickupView.setScaleY(LABEL_SIZE);
    }

    /**
     * Binds the label containing the number of
     * pickups in the correct position of
     * the view.
     * 
     * @param label a JavaFX label
     * @param root  root to be added to
     * 
     */
    protected void bindLabel(final Label label, final HBox root) {
        label.setFont(pickupFont);
        label.setTextFill(Color.GREY);
        label.setScaleX(PICKUP_SIZE);
        label.setScaleY(PICKUP_SIZE);
        root.getChildren().add(label);
    }

    /**
     * Updates the content of label
     * with the correct number of
     * pickups.
     * 
     * @param amountToUpdate the amount to update
     */
    public abstract void updateLabelText(int amountToUpdate);
}
