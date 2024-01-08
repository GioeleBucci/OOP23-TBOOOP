package tbooop.model.core.api.unmovable;

import tbooop.commons.Point2d;
import tbooop.model.core.api.GameTag;
import tbooop.model.core.impl.GameObjectImpl;

public abstract class UnmovableAbs extends GameObjectImpl implements Unmovable {

    protected UnmovableAbs(final Point2d position, final double colliderRadius, final GameTag tag) {
        super(position, colliderRadius, tag);
    }

    @Override
    /** {@inheritDoc} */
    public void setPosition(Point2d newPos) {
        throw new UnsupportedOperationException("Cannot change the position of an unmovable GameObject.");
    }

}
