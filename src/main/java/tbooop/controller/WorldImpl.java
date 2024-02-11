package tbooop.controller;

import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.application.Platform;

import tbooop.commons.api.Projectile;
import tbooop.controller.api.FloorManager;
import tbooop.controller.api.World;
import tbooop.commons.RoomBounds;
import tbooop.model.core.api.GameObject;
import tbooop.model.core.api.movable.Entity;
import tbooop.model.dungeon.rooms.api.DoorUnmodifiable;
import tbooop.model.player.api.Player;
import tbooop.model.player.impl.PlayerImpl;

import tbooop.view.api.View;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * The World class represents the game world and contains information about the
 * game objects,
 * the player, the current floor, and the current room.
 */
@SuppressFBWarnings(value = {
        "EI_EXPOSE_REP" // may expose internal representation by returning reference to mutable object
}, justification = "This class is a container of game objects, that the controller needs check and modify"
        + "and for that reason exposing the internal representation is necessary.")
public final class WorldImpl implements World {

    private static final int INITIAL_KEYS = 2; // let the player start with some keys
    private final View view;
    private final FloorManager floorManager;

    private volatile Set<GameObject> gameObjects = new CopyOnWriteArraySet<>();
    private volatile Set<Projectile> projectiles = new CopyOnWriteArraySet<>();
    private volatile Player player = new PlayerImpl(RoomBounds.CENTER);

    /**
     * Constructs a new World with the specified view.
     *
     * @param view the view that this world will update
     */
    public WorldImpl(final View view) {
        this.view = view;
        this.floorManager = new FloorManagerImpl(this, view);
    }

    /** {@inheritDoc} */
    @Override
    public void init() {
        for (int i = 0; i < INITIAL_KEYS; i++) {
            player.pickupKeys();
        }
        floorManager.init();
    }

    /** {@inheritDoc} */
    @Override
    public void update() {
        gameObjects.removeIf(GameObject::isDestroyed);
        projectiles.removeIf(Projectile::isDestroyed);
        floorManager.update();
    }

    /** {@inheritDoc} */
    @Override
    public Set<GameObject> getGameObjects() {
        return gameObjects;
    }

    /** {@inheritDoc} */
    @Override
    public Set<Projectile> getProjectiles() {
        return projectiles;
    }

    /** {@inheritDoc} */
    @Override
    public Player getPlayer() {
        return player;
    }

    /** {@inheritDoc} */
    @Override
    public void onDoorCollision(final DoorUnmodifiable door) {
        floorManager.onDoorCollision(door);
    }

    /** {@inheritDoc} */
    @Override
    public void changeFloor() {
        floorManager.changeFloor();
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void collectProjectiles(final Entity entity) {
        final Set<Projectile> projectiles = entity.getShotProjectiles();
        for (final Projectile projectile : projectiles) {
            getProjectiles().add(projectile);
            Platform.runLater(() -> {
                view.addGameObject(projectile);
            });
        }
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void clearAll() {
        clearSet(gameObjects);
        clearSet(projectiles);
    }

    private synchronized void clearSet(final Set<? extends GameObject> set) {
        set.forEach(g -> {
            Platform.runLater(() -> {
                view.removeGameObject(g);
            });
        });
        set.clear();
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void addGameObject(final GameObject gameObject) {
        gameObjects.add(gameObject);
        Platform.runLater(() -> {
            view.addGameObject(gameObject);
        });
    }
}
