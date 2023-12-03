package tbooop.model.core.api.movable;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import javafx.geometry.Point2D;
import tbooop.model.core.impl.GameObject;

public abstract class Entity extends GameObject implements IDamageable {
    
    private int maxHealth;
    private int currentHeath;
    private Vector2D velocity;

    protected Entity(Point2D position, int maxHealth, int currentHeath, Vector2D velocity) {
        super(position);
        this.currentHeath = currentHeath;
        this.maxHealth = maxHealth;
        this.velocity = velocity;
    }

    public void update(long deltaTime) {
        //TO DO to implements
    }

    public abstract void onCollision(GameObject gameObj);

    public void takeDamage(int damage) {
        this.currentHeath = this.currentHeath - damage;        
    }

    public abstract void die();

    public int getHealth() {
        return this.currentHeath;
    }

    public int getMaxHealth() {
        return this.maxHealth;    
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public Vector2D getVelocity() {
        return this.velocity;
    }
}
