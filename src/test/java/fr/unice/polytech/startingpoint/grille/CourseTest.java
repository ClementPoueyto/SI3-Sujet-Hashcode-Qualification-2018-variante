package fr.unice.polytech.startingpoint.grille;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Vincent
 */


class CourseTest {

    @Test
    void distanceTestUn() {
        Position depart = new Position(0,0);
        Position arrivee = new Position(0,1);
        Course course = new Course(depart, arrivee, 0, 100);

        assertEquals(course.distance(depart, arrivee),1);
    }

    @Test
    void distanceTestDeux() {
        Position depart = new Position(5,3);
        Position arrivee = new Position(4,1);

        Course course = new Course(depart, arrivee, 0, 100);
        assertEquals(course.distance(depart, arrivee),3);
    }

    @Test
    void equalsTest(){
        Course c0 = new Course(new Position(2,2),new Position(3,5),2,3);
        Course c1=null;
        String test = "test";
        assertFalse(c0.equals(c1));
        assertFalse(c0.equals(test));

        assertTrue(c0.equals(c0));

        Course c2 = new Course(new Position(3,9),new Position(1,1),3,9);
        assertFalse(c0.equals(c2));

        Course c3 = new Course(new Position(3,9),new Position(1,1),3,9);
        assertTrue(c2.equals(c3));

        Course c4 = new Course(new Position(3,8),new Position(1,1),3,9);
        assertFalse(c2.equals(c4));
        Course c5 = new Course(new Position(3,8),new Position(1,1),3,8);
        assertFalse(c2.equals(c5));

    }
}