package fr.unice.polytech.startingpoint.io;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.unice.polytech.startingpoint.grille.Carte;
import fr.unice.polytech.startingpoint.grille.Course;
import fr.unice.polytech.startingpoint.grille.Vehicule;
import fr.unice.polytech.startingpoint.main.Score;

/**
 * @author Nathan
 * @author Vincent
 * @author Loic
 * @author Clement
 */


public class SortieTest {
    Score score;
    ArrayList<Vehicule> tabVehicule;
    Vehicule peugeot;
    Vehicule fiat;
    Carte carte;
    Course course1;
    Course course2;
    Course course3;




    @BeforeEach
    void setup(){
        score = mock(Score.class);
        when(score.getScore()).thenReturn(999999);

        tabVehicule = new ArrayList<>();
        peugeot = new Vehicule();
        fiat = new Vehicule();
        tabVehicule.add(peugeot);
        tabVehicule.add(fiat);

        carte = mock(Carte.class);
        when(carte.getListeVehicules()).thenReturn(tabVehicule);
        course1 = mock(Course.class);
        when(course1.getId()).thenReturn(1);
        course2 = mock(Course.class);
        when(course2.getId()).thenReturn(2);
        course3 = mock(Course.class);
        when(course3.getId()).thenReturn(3);

    }

    /**
     * Test de la sortie si aucune course n'est attribuée
     */
    @Test
    void afficherSortieTest() {
        Sortie sortie = new Sortie(score, carte);
        assertEquals(sortie.creerSortie(), "999999\n0\n0");
    }

    /**
     * test avec une attribution
     */
    @Test
    void afficherSortieTest2() {
        peugeot.attribuerCourse(course1);

        Sortie sortie = new Sortie(score, carte);

        assertEquals(sortie.creerSortie(), "999999\n1 1\n0");

    }

    /**
     * test avec plusieurs attributions
     */
    @Test
    void afficherSortieTest3() {
        peugeot.attribuerCourse(course1);
        peugeot.attribuerCourse(course2);
        fiat.attribuerCourse(course3);

        Sortie sortie = new Sortie(score, carte);

        assertEquals(sortie.creerSortie(), "999999\n2 1 2\n1 3");
    }
}


