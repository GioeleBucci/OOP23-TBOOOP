package tbooop.view.player;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tbooop.model.player.api.UnmodifiablePlayer;
import tbooop.view.api.ViewComponent;
import tbooop.view.api.ViewElements;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a view for displaying the health of a player.
 * This class is responsible for rendering the health bar and updating it based
 * on the player's health.
 */
public class HealthView extends ViewComponent {

    private final HBox root = new HBox();
    private final List<ImageView> heartList = new ArrayList<>();
    private final HealthRender healthPoint;
    private final UnmodifiablePlayer player;
    private int currentHealth;
    private int maxHealth;

    /**
     * Represents a view for displaying the health of a player.
     * This class is responsible for rendering the health bar and updating it based
     * on the player's health.
     *
     * @param view   The ViewElements object used for rendering the view.
     * @param player The UnmodifiablePlayer object representing the player.
     */
    public HealthView(final ViewElements view, final UnmodifiablePlayer player) {
        super(view);
        this.player = player;
        view.getRoot().getChildren().add(root);
        healthPoint = new HealthRender(this.player.getMaxHealth(), this.root, this.heartList);
        this.currentHealth = this.player.getHealth();
        this.maxHealth = this.player.getMaxHealth();
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void update() {
        if (this.currentHealth != this.player.getHealth()) {
            if (this.currentHealth > this.player.getHealth()) {
                toggledHealth(this.currentHealth);
            } else {
                addHealth(player.getHealth());
            }
            this.currentHealth = this.player.getHealth();
        }

        if (this.maxHealth != player.getMaxHealth()) {
            addMaxHealth();
            this.maxHealth = player.getMaxHealth();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    /**
     * Change the empty heart with the full one.
     * 
     * @param currentHealth the heart to change
     */
    public synchronized void toggledHealth(final int currentHealth) {
        this.heartList.get(currentHealth).setImage(new Image("test/empty_hearth.png"));
    }

    /**
     * Change the full heart with the empty one.
     * 
     * @param currentHealth the heart to change
     */
    public synchronized void addHealth(final int currentHealth) {
        this.heartList.get(currentHealth).setImage(new Image("test/full_hearth.png"));
    }

    /**
     * Add a new Heart.
     */
    public synchronized void addMaxHealth() {
        final ImageView heartView = new ImageView("test/empty_hearth.png");
        healthPoint.addMaxHealth(heartView, this.root);
        heartList.add(heartView);
    }
}
