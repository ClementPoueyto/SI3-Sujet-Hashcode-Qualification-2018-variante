package fr.unice.polytech.startingpoint.grille;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * @author Clement
 */

public class VehiculeTest {

    Vehicule vehicule;
    Vehicule vehicule2;
    @BeforeEach void setUp(){
        vehicule= new Vehicule();
        vehicule2= new Vehicule();
    }

    @Test
    void courseActuelleTest(){
        Course course = new Course(new Position(1,1),new Position(2,4),4,7);
        vehicule.attribuerCourse(course);
        assertEquals(course,vehicule.courseActuelle());
        Course course2 = new Course(new Position(3,2),new Position(2,7),4,10);
        vehicule.attribuerCourse(course2);
        assertEquals(course2,vehicule.courseActuelle());
    }

    @Test
    void distanceJusquaProchaineCourseTest(){
        Vehicule v= new Vehicule();
        v.setPosition(new Position(5,5));
        Course course = new Course(new Position(1,1),new Position(2,4),4,7);
        assertEquals(8,v.distanceJusquaProchaineCourse(course));

    }

    @Test
    void deplacerVersFinTest(){
        Vehicule v = new Vehicule();
        Course course = new Course(new Position(1,1),new Position(2,4),4,7);
        v.deplacerVersFin(course);
        assertEquals(v.getPosition(),course.getPositionArrivee());

    }
    @Test
    void deplacerVersDepartTest(){
        Vehicule v = new Vehicule();
        Course course = new Course(new Position(1,1),new Position(2,4),4,7);
        v.deplacerVersDepart(course);
        assertEquals(v.getPosition(),course.getPositionDepart());

    }

    @Test
    void equalsTest(){
        Vehicule v0 = new Vehicule();
        Vehicule v1=null;
        String test = "test";
        assertFalse(v0.equals(v1));
        assertFalse(v0.equals(test));

        assertTrue(v0.equals(v0));

        Vehicule v2 = new Vehicule();
        assertFalse(v0.equals(v2));

    }

}
