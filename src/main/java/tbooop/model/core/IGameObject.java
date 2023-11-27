package tbooop.model.core;

import javafx.geometry.Point2D;

/**
 * Interface for game objects.
 * <p>
 * It represents the methods that everything in the game must have.
 */
public interface IGameObject {

    /**
     * The position of this GameObject, expressed as a Point2D.
     * 
     * @return the position of this object.
     * @see {@link javafx.geometry.Point2D Point2D}
     */
    Point2D getPosition();

    /**
     * Sets the position of this GameObject, expressed as a Point2D.
     * 
     * @param newPos the new position of this object.
     * @see {@link javafx.geometry.Point2D Point2D}
     */
    void setPosition(Point2D newPos);
}
