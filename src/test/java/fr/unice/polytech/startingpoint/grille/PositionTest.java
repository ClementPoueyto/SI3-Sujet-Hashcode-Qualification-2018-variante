package fr.unice.polytech.startingpoint.grille;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author Clement
 */


public class PositionTest {


    @Test
    void equalsTest(){
        Position p0 = new Position(3,5);
        Vehicule p1=null;
        String test = "test";
        assertFalse(p0.equals(p1));
        assertFalse(p0.equals(test));

        assertTrue(p0.equals(p0));

        Position p2 = new Position(4,3);
        assertFalse(p0.equals(p2));
        Position p3 = new Position(4,5);
        assertFalse(p3.equals(p2));
        Position p4 = new Position(4,5);
        assertTrue(p3.equals(p4));


    }

}
