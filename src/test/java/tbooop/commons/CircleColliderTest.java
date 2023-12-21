package tbooop.commons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tbooop.commons.api.CircleCollider;

class CircleColliderTest {
    @Test
    void twoPointIntersection() {
        CircleCollider a = new CircleColliderImpl(new Point2d(0, 0), 2);
        assertTrue(a.isColliding(a));
        CircleCollider b = new CircleColliderImpl(new Point2d(3, 3), 3);
        assertTrue(a.isColliding(b));
        CircleCollider c = new CircleColliderImpl(new Point2d(3, 3), 1);
        assertFalse(c.isColliding(a));
        assertTrue(c.isColliding(b));
    }

    @Test 
    void onePointIntersection(){
       CircleCollider a = new CircleColliderImpl(new Point2d(0,0),1);
       CircleCollider b = new CircleColliderImpl(new Point2d(2,0),1);
       assertTrue(a.isColliding(b)); 
    }
}
