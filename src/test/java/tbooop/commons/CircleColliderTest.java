package tbooop.commons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tbooop.commons.api.CircleCollider;

class CircleColliderTest {
    @Test
    void twoPointIntersection() {
        final CircleCollider a = new CircleColliderImpl(new Point2dImpl(0, 0), 2);
        assertTrue(a.isColliding(a));
        final CircleCollider b = new CircleColliderImpl(new Point2dImpl(3, 3), 3);
        assertTrue(a.isColliding(b));
        final CircleCollider c = new CircleColliderImpl(new Point2dImpl(3, 3), 1);
        assertFalse(c.isColliding(a));
        assertTrue(c.isColliding(b));
    }

    @Test
    void onePointIntersection() {
        final CircleCollider a = new CircleColliderImpl(new Point2dImpl(0, 0), 1);
        final CircleCollider b = new CircleColliderImpl(new Point2dImpl(2, 0), 1);
        assertTrue(a.isColliding(b));
    }
}
