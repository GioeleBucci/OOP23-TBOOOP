package tbooop.view.pickupables.pickups;

import javafx.scene.layout.HBox;
import tbooop.model.player.api.UnmodifiablePlayer;
import tbooop.view.api.ViewComponentImpl;
import tbooop.view.api.ViewElements;
/**
 * Represents view of the number of coins
 * owned by the player.
 */
public class PlayerCoinsView extends ViewComponentImpl {

    private final HBox root = new HBox();
    private final PlayerCoinsRender coinRender;
    private final UnmodifiablePlayer player;
    private int currentCoins;
    /**
     * Represents a view for displaying player's coins.
     *
     * @param view The ViewElements object used for rendering the view.
     * @param player The UnmodifiablePlayer object representing the player.
     */
    public PlayerCoinsView(final ViewElements view, final UnmodifiablePlayer player) {
        super(view);
        this.player = player; 
        view.getRoot().getChildren().add(root);
        coinRender = new PlayerCoinsRender(this.player.getCoins(), this.root);
        this.currentCoins = this.player.getCoins();
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void update() {
        if (this.currentCoins != this.player.getCoins()) {
            updateLabel(this.player.getCoins());
        }
        this.currentCoins = this.player.getCoins();
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    /**
     * Update coins label content according
     * to the correct number of coins.
     * 
     * @param coinsAmount number of coins to modify
     */
    public synchronized void updateLabel(final int coinsAmount) {
        coinRender.updateLabelText(coinsAmount);
    }
}
