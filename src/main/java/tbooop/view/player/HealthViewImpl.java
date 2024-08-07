package tbooop.view.player;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import tbooop.model.player.api.UnmodifiablePlayer;
import tbooop.view.api.ViewComponentImpl;
import tbooop.view.api.ViewElements;
import tbooop.view.api.player.HealthView;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a view for displaying the health of a player.
 * This class is responsible for rendering the health bar and updating it based
 * on the player's health.
 */
public class HealthViewImpl extends ViewComponentImpl implements HealthView {

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
    public HealthViewImpl(final ViewElements view, final UnmodifiablePlayer player) {
        super(view);
        this.player = player;
        view.getRoot().getChildren().add(root);
        healthPoint = new HealthRender((int) this.player.getMaxHealth(), this.root, this.heartList);
        this.currentHealth = (int) this.player.getHealth();
        this.maxHealth = (int) this.player.getMaxHealth();
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void update() {

        if (this.maxHealth != player.getMaxHealth()) {
            for (int i = this.maxHealth; i < player.getMaxHealth(); i++) {
                addMaxHealth();
            }
            this.maxHealth = (int) player.getMaxHealth();
        }

        if (this.currentHealth != this.player.getHealth()) {
            if (this.currentHealth > this.player.getHealth()) {
                for (int i = this.currentHealth; i > this.player.getHealth(); i--) {
                    if (i - 1 >= 0) {
                        toggledHealth(i - 1);
                    }
                }
            } else {
                for (int i = this.currentHealth; i < this.player.getHealth(); i++) {
                    addHealth(i);
                }
            }
        }

        this.currentHealth = (int) this.player.getHealth();
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
    }

    /**
     * Change the empty heart with the full one.
     * 
     * @param currentHealth the heart to change
     */
    @Override
    public synchronized void toggledHealth(final int currentHealth) {
        this.heartList.get(currentHealth).setImage(new Image("ui/empty_hearth.png"));
    }

    /**
     * Change the full heart with the empty one.
     * 
     * @param currentHealth the heart to change
     */
    @Override
    public synchronized void addHealth(final int currentHealth) {
        this.heartList.get(currentHealth).setImage(new Image("ui/full_hearth.png"));
    }

    /**
     * Add a new Heart.
     */
    @Override
    public synchronized void addMaxHealth() {
        final ImageView heartView = new ImageView("ui/empty_hearth.png");
        healthPoint.addMaxHealth(heartView, this.root);
        heartList.add(heartView);
    }
}
