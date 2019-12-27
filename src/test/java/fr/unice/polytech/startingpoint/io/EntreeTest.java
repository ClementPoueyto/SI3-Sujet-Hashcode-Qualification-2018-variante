package fr.unice.polytech.startingpoint.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.unice.polytech.startingpoint.grille.Course;
import fr.unice.polytech.startingpoint.grille.Position;

/**
 * @author Loic
 */


public class EntreeTest {

    Entree entree;

    @BeforeEach
    void setup(){
        String ligne1 = "3 4 2 3 2 0 1 10";
        String ligne2 = "0 0 1 3 2 9";
        String ligne3 = "1 2 1 0 5 9";
        String ligne4 = "2 0 2 2 0 9";
        AnalyseEntree aE = mock(AnalyseEntree.class);
        when(aE.nextLine()).thenReturn(ligne1,ligne2,ligne3,ligne4);
        entree = new Entree(aE);
    }

    @Test
    void bonusTest(){
        assertEquals(2, entree.getBonus());
        assertEquals(0,entree.getPrixConstant());
        assertEquals(1,entree.getPrixDistance());
    }

    @Test
    void nombreDeVehiculeTest(){
        assertEquals(2,entree.getCarte().getListeVehicules().size());
    }

    @Test
    void nombreDeCourseTest(){
        assertEquals(3, entree.getCarte().getListeCourses().size());
    }

    @Test
    void coursesCreeTest(){
        Course course = new Course(new Position(0,0), new Position(1,3), 2, 9);
        assertTrue(entree.getCarte().getListeCourses().get(0).equals(course));
    }
}