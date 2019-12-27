package fr.unice.polytech.startingpoint.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.unice.polytech.startingpoint.grille.Course;
import fr.unice.polytech.startingpoint.grille.Position;
import fr.unice.polytech.startingpoint.grille.Vehicule;
import fr.unice.polytech.startingpoint.io.AnalyseEntree;
import fr.unice.polytech.startingpoint.io.Entree;

/**
 * @author Nathan
 * @author Vincent
 */
class AlgoTest {

    Algo algo;
    Score scoreMock;
    Entree entree;
    Course course1;
    Course course2;
    Course course3;
    ArrayList<Course> listeCible;
    ArrayList<Course> listeTest;
    ArrayList<Vehicule> listeVehicule;
    ArrayList<Vehicule> listeVehiculeTest;



    @BeforeEach
    void setup(){
        String ligne1 = "3 4 2 3 2 0 1 10";
        String ligne2 = "0 0 1 3 2 9";
        String ligne3 = "1 2 1 0 5 9";
        String ligne4 = "2 0 2 2 0 9";
        AnalyseEntree aE = mock(AnalyseEntree.class);
        when(aE.nextLine()).thenReturn(ligne1,ligne2,ligne3,ligne4);
        entree = new Entree(aE);
        scoreMock = mock(Score.class);
        algo = new Algo(entree,scoreMock);

        course1 = new Course(new Position(0,0), new Position(1,3),2,9);
        course2 = new Course(new Position(1,2), new Position(1,0),5,9);
        course3 = new Course(new Position(2,0), new Position(2,2),0,9);

        listeCible = new ArrayList<>();
        listeTest = new ArrayList<>();
        listeVehicule = new ArrayList<>();
        listeVehiculeTest = new ArrayList<>();

    }


    /**
     * Vehicule dont le nombre d'étape pour allez a sa course est superieur a la distance
     */
    @Test
    void incrementEtapeSupTest() {


        Position depart = new Position(2,2);
        Position arrivee = new Position(5,3);

        Vehicule vehicule = new Vehicule();
        Course course = new Course(depart, arrivee, 10, 15);

        algo.incrementEtape(vehicule, course);

        assertEquals(vehicule.getEtape(), 8);

    }


    /**
     * Vehicule dont le nombre d'étape pour allez a sa course est inferieur a la distance
     */
    @Test
    void incrementEtapeInfTest() {


        Position depart = new Position(2, 2);
        Position arrivee = new Position(2, 3);

        Vehicule vehicule = new Vehicule();
        Course course = new Course(depart, arrivee, 1, 3);

        algo.incrementEtape(vehicule, course);

        assertEquals(vehicule.getEtape(), 3);
    }

    @Test
    void supprimerCourseTest(){
        Course c = algo.getListeCourses().get(0);
        algo.supprimerCourse(c);
        assertFalse(algo.getListeCourses().contains(c));

    }

    @Test
    void triCoursesTest(){
        algo.triCourses();
        Position depart = new Position(2,0);
        Position arrivee = new Position(2,2);
        assertEquals(new Course(depart,arrivee,0,9),algo.getListeCourses().get(0));
    }


    @Test
    void distanceTest(){
        //ArrayList<Course> courses = new ArrayList<>();
        //ArrayList<Vehicule> vehicules = new ArrayList<>();
        //Carte carte = new Carte(5,5, courses,vehicules);

        Position a1=new Position(1,1);
        Position b1= new Position(2,4);
        Assert.assertEquals(4, algo.distance(a1,b1));

        Position a2=new Position(4,4);
        Position b2= new Position(2,1);
        Assert.assertEquals(5, algo.distance(a2,b2));
    }

    /**
     * Test vérifiant la condition d'arrivée a l'heure
     */
    @Test
    void arriveATempsTest(){
        Course courseMock = mock(Course.class);
        when(courseMock.getTourDepart()).thenReturn(5);
        when(courseMock.getPositionDepart()).thenReturn(new Position(2,2));

        Vehicule taxi = mock(Vehicule.class);
        when(taxi.getEtape()).thenReturn(0);
        when(taxi.getPosition()).thenReturn(new Position(0,0));

        Algo algo = new Algo(entree, scoreMock);

        assertTrue(algo.arriveATemps(courseMock, taxi));
    }
    @Test
    void arriveATempsTest2(){
        Course courseMock = mock(Course.class);
        when(courseMock.getTourDepart()).thenReturn(4);
        when(courseMock.getPositionDepart()).thenReturn(new Position(2,2));

        Vehicule taxi = mock(Vehicule.class);
        when(taxi.getEtape()).thenReturn(0);
        when(taxi.getPosition()).thenReturn(new Position(0,0));

        Algo algo = new Algo(entree, scoreMock);

        assertFalse(algo.arriveATemps(courseMock, taxi));
    }

    @Test
    void courseRealisableTest(){
        Vehicule taxi = mock(Vehicule.class);
        when(taxi.getPosition()).thenReturn(new Position(0,0));

        Course courseMock = mock(Course.class);
        when(courseMock.getDistance()).thenReturn(2);
        when(courseMock.getPositionDepart()).thenReturn(new Position(2,2));

        when(courseMock.getTourArrivee()).thenReturn(7);

        assertTrue(algo.courseRealisable(taxi, courseMock));
    }

    @Test
    void courseRealisableTest2(){
        Vehicule taxi = mock(Vehicule.class);
        when(taxi.getPosition()).thenReturn(new Position(0,0));

        Course courseMock = mock(Course.class);
        when(courseMock.getDistance()).thenReturn(2);
        when(courseMock.getPositionDepart()).thenReturn(new Position(2,2));

        when(courseMock.getTourArrivee()).thenReturn(6);

        assertFalse(algo.courseRealisable(taxi, courseMock));
    }

    /**
     * La course 1 est identique à la course cible que l'on veut éliminer
     */
    @Test
    void supprimerCourseCibleDedansExact() {

        listeCible.add(course2);
        listeCible.add(course3);

        Course courseCible = new Course(new Position(0,0), new Position(1,3),2, 9);
        algo.supprimerCourse(courseCible);

        assertEquals(algo.getListeCourses(), listeCible);
    }

    /**
     * La course 1 est identique à la course cible que l'on veut éliminer sur les postions de depart et arrivee mais pas au niveau des temps de depart et arrivee
     */
    @Test
    void supprimerCourseCibleDedansNonExactTemps() {

        listeCible.add(course1);
        listeCible.add(course2);
        listeCible.add(course3);

        Course courseCible = new Course(new Position(0,0), new Position(1,3),1, 9);
        algo.supprimerCourse(courseCible);

        assertEquals(algo.getListeCourses(), listeCible);
    }

    /**
     * La course 1 est identique à la course cible que l'on veut éliminer sur les temps de depart et arrivee mais pas au niveau des positions de depart et arrivee
     */
    @Test
    void supprimerCourseCibleDedansNonExactPostition() {

        listeCible.add(course1);
        listeCible.add(course2);
        listeCible.add(course3);

        Course courseCible = new Course(new Position(1,0), new Position(1,3),2, 9);
        algo.supprimerCourse(courseCible);

        assertEquals(algo.getListeCourses(), listeCible);
    }

    /**
     * On trie les course de façon a attendre le moins possible
     */
    @Test
    void triCoursesDistanceVehicule1() {

        listeTest.add(course1); // Commence a 2 en (0,0) distance au vehicule : 4
        listeTest.add(course3); // Commence a 0 en (2,0) distance au vehicule : 2
        listeTest.add(course2); // Commence a 5 en (1,2) distance au vehicule : 3


        Vehicule vehicule1 = new Vehicule(); // vehicule en (0,0)


        algo.triCoursesDistance(algo.getListeCourses(), vehicule1);

        assertEquals(algo.getListeCourses(), listeTest);
    }

    /**
     * On trie les course de façon a attendre le moins possible
     */
    @Test
    void triCoursesDistanceVehicule2() {
        listeTest.add(course2); // Commence a 5 en (1,2) distance au vehicule : 3
        listeTest.add(course3); // Commence a 0 en (2,0) distance au vehicule : 2
        listeTest.add(course1); // Commence a 2 en (1,3) distance au vehicule : 4


        Vehicule vehicule2 = new Vehicule(); // vehicule en (0,0)
        vehicule2.setPosition(new Position(3,5));


        algo.triCoursesDistance(algo.getListeCourses(), vehicule2);

        assertEquals(algo.getListeCourses(), listeTest);
    }

    /**
     * On trie les course de façon a attendre le moins possible
     */
    @Test
    void triVehiculeTest() {

        Vehicule vehicule1 = algo.getListeVehicule().get(0);

        Vehicule vehicule2 = algo.getListeVehicule().get(1);



        listeVehiculeTest.add(vehicule2);
        listeVehiculeTest.add(vehicule1);


        listeVehicule = algo.getListeVehicule();

        listeVehicule.get(0).setPosition(new Position(1,3));
        listeVehicule.get(1).setPosition(new Position(1,2));


        Course course = new Course(new Position(0,0), new Position(1,5), 2, 15);

        algo.triVehicules(course);

        assertEquals(algo.getListeVehicule(), listeVehiculeTest);

    }

}