package tbooop.model.enemy.attacks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class TestAttack {

    @Test
    void testClosestAngleIndex() {
        List<Double> angles = List.of(10.0, 20.0, 30.0, 40.0, 50.0);
        double angle = 42.0;
        int expectedIndex = 3;
        int actualIndex = Attack.findClosestAngleIndex(angles, angle);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void closestAngleIndexRoundsDown() {
        List<Double> angles = List.of(10.0, 20.0, 30.0, 40.0, 50.0);
        double angle = 15.0;
        int expectedIndex = 0;
        int actualIndex = Attack.findClosestAngleIndex(angles, angle);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void removeMoreThanNElementsGivesEmptyList() {
        List<Double> list = List.of(10.0, 20.0, 30.0, 40.0, 50.0);
        assertEquals(List.of(), Attack.removeUpToNElements(list, 10, 20));
        assertEquals(List.of(), Attack.removeUpToNElements(list, 5, 20));
        assertEquals(list, Attack.removeUpToNElements(list, 0, 20));
        assertEquals(list, Attack.removeUpToNElements(list, -1, 20));
    }

    @Test
    void removeOddNumberOfElements() {
        List<Double> list = List.of(10.0, 20.0, 30.0, 40.0, 50.0, 60.0, 70.0);
        int n = 3;
        double angle = 42.0;
        List<Double> expected = List.of(10.0, 20.0, 60.0, 70.0);
        List<Double> result = Attack.removeUpToNElements(list, n, angle);
        assertEquals(expected, result);
    }

    @Test
    void removeEvenNumberOfElements() {
        List<Double> list = List.of(10.0, 20.0, 30.0, 40.0, 50.0, 60.0);
        int n = 2;
        double angle = 42.0;
        List<Double> expected = List.of(10.0, 20.0, 50.0, 60.0);
        List<Double> result = Attack.removeUpToNElements(list, n, angle);
        assertEquals(expected, result);
    }

    @Test
    void checkWraparound() {
        List<Double> list = List.of(10.0, 20.0, 30.0, 40.0, 50.0, 60.0);
        int n = 3;
        double angle = 10.0;
        List<Double> expected = List.of(30.0, 40.0, 50.0);
        List<Double> result = Attack.removeUpToNElements(list, n, angle);
        assertEquals(expected, result);
    }
}
