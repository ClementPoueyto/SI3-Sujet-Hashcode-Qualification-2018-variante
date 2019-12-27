package fr.unice.polytech.startingpoint.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.unice.polytech.startingpoint.grille.Course;

/**
 * @author Nathan
 */

class ScoreTest {


    Score score;

    @BeforeEach
    void setup() {
        score = new Score(1,1,1);
    }

    /**
     * Le vehicule part en retard et n'arrive pas a l'heure
     */
    @Test
    void ajoutScoreCourseSansBonusTest() {

        Course courseMock = mock(Course.class);

        when(courseMock.getDistance()).thenReturn(1);
        when(courseMock.getPonctuel()).thenReturn(false);

        score.ajoutScoreCourse(courseMock);

        assertEquals(score.getScore(),2);
    }


    /**
     * Le vehicule part a l'heure mais arrive en retard
     */
    @Test
    void ajoutScoreCourseAvecBonusPonctualiteTest() {

        Course courseMock = mock(Course.class);

        when(courseMock.getDistance()).thenReturn(1);
        when(courseMock.getPonctuel()).thenReturn(true);

        score.ajoutScoreCourse(courseMock);

        assertEquals(score.getScore(),3);
    }


    /**
     * Le vehicule arrive en retard mais arrive a l'heure
     */
    @Test
    void ajoutScoreCourseAvecBonusDeFinTest() {

        Course courseMock = mock(Course.class);

        when(courseMock.getDistance()).thenReturn(1);
        when(courseMock.getPonctuel()).thenReturn(false);

        score.ajoutScoreCourse(courseMock);

        assertEquals(score.getScore(),2);
    }

    /**
     * Le vehicule part a l'heure et arrive dans les temps il a donc tous les bonus
     */
    @Test
    void ajoutScoreCourseToutBonusTest() {

        Course courseMock = mock(Course.class);

        when(courseMock.getDistance()).thenReturn(1);
        when(courseMock.getPonctuel()).thenReturn(true);

        score.ajoutScoreCourse(courseMock);

        assertEquals(score.getScore(),3);
    }

}