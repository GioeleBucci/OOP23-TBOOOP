package tbooop.view.pickupables.pickups;

import javafx.scene.layout.HBox;
import tbooop.model.player.api.UnmodifiablePlayer;
import tbooop.view.api.ViewComponentImpl;
import tbooop.view.api.ViewElements;

/**
 * Represents view of the number of keys
 * owned by the player.
 */
public class PlayerKeysView extends ViewComponentImpl {

    private final HBox root = new HBox();
    private final PlayerKeysRender keyRender;
    private final UnmodifiablePlayer player;
    private int currentKeys;
    /**
     * Represents a view for displaying player's keys.
     *
     * @param view The ViewElements object used for rendering the view.
     * @param player The UnmodifiablePlayer object representing the player.
     */
    public PlayerKeysView(final ViewElements view, final UnmodifiablePlayer player) {
        super(view);
        this.player = player; 
        view.getRoot().getChildren().add(root);
        keyRender = new PlayerKeysRender(this.player.getKey(), this.root);
        this.currentKeys = this.player.getKey();
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void update() {
        if (this.currentKeys != this.player.getKey()) {
            updateLabel(this.player.getKey());
        }
        this.currentKeys = this.player.getKey();
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    /**
     * Update key label content according
     * to the correct number of keys.
     * 
     * @param keysNumber number of keys to update.
     */
    public synchronized void updateLabel(final int keysNumber) {
        keyRender.updateLabelText(keysNumber);
    }
}
