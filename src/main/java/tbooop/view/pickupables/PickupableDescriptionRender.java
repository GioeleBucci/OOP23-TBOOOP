package tbooop.view.pickupables;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import tbooop.commons.api.Point2d;
import tbooop.model.pickupables.Pickupable;

public class PickupableDescriptionRender {
    
    private static final double LABEL_SIZE = 1.0;
    private Map<Pickupable, Label> map = new HashMap<>();
    private Group root;

    public void addLabel(final Group root, final Point2d objectPoint, final Pickupable gameObject) {
        final Label label = new Label();
        this.root = root;
        label.setText(gameObject.getObjectDescription());
        this.map.put(gameObject, label);
        label.setTextFill(Color.WHITE);
        label.setLayoutX(objectPoint.getX() - 35);
        label.setLayoutY(objectPoint.getY() + 20);
        label.setScaleX(LABEL_SIZE);
        label.setScaleY(LABEL_SIZE);
        root.getChildren().add(label);
    }
    
    public void deleteLabel(Pickupable gameObject) {
        this.map.get(gameObject).setText("");
    }
}

/*switch (gameObject.getObjectName()) {
    case GLASS_HEART -> label.setText(PickupablesDescriptions.GLASSHEART_DESCRIPTION.getPickupableDescription());
    case LOCKED_RING -> label.setText(PickupablesDescriptions.LOCKEDRING_DESCRIPTION.getPickupableDescription());
    case SPICY_SAUCE -> label.setText(PickupablesDescriptions.SPICYSAUCE_DESCRIPTION.getPickupableDescription());
    case ZAP -> label.setText(PickupablesDescriptions.ZAP_DESCRIPTION.getPickupableDescription());
    case GOLDEN_APPLE -> label.setText(PickupablesDescriptions.GOLDENAPPLE_DESCRIPTION.getPickupableDescription());
    case BILL -> label.setText(PickupablesDescriptions.BILL_DESCRIPTION.getPickupableDescription());
    case COIN -> label.setText(PickupablesDescriptions.COIN_DESCRIPTION.getPickupableDescription());
    case KEY -> label.setText(PickupablesDescriptions.KEY_DESCRIPTION.getPickupableDescription());
    case HEART -> label.setText(PickupablesDescriptions.HEART_DESCRIPTION.getPickupableDescription());
    default -> label.setText("NULL");
}*/