package fr.unice.polytech.startingpoint.main;


import java.util.ArrayList;
import java.util.Comparator;

import fr.unice.polytech.startingpoint.grille.Carte;
import fr.unice.polytech.startingpoint.grille.Course;
import fr.unice.polytech.startingpoint.grille.Position;
import fr.unice.polytech.startingpoint.grille.Vehicule;
import fr.unice.polytech.startingpoint.io.Entree;

/**
 * Classe permettant de lancer les differents algorithmes.
 * @author Nathan
 * @author Vincent
 * @author Loic
 * @author Clement
 */
public class Algo {
    Carte carte;
    ArrayList<Vehicule> listeVehicules;
    ArrayList<Course> listeCourses;
    private int prixDistance;
    private int prixConstant;
    private int bonus;
    private Score score;
    int tolerance = 200;



    /**
     *Constructeur
     * @param e entree du programme traitee par la classe Entree
     */
    public Algo(Entree e, Score score){
        this.carte = e.getCarte();
        this.listeVehicules= new ArrayList<>(carte.getListeVehicules());
        this.listeCourses = new ArrayList<>(carte.getListeCourses());
        this.prixDistance = e.getPrixDistance();
        this.prixConstant = e.getPrixConstant();
        this.bonus = e.getBonus();
        this.score = score;
    }


    /**
     * methode permettant lancer un algorithme particulier
     * selon les parametres du fichier
     */
    public void traitement() {
        if(bonus==1 && prixConstant!=1 && prixDistance==1) {// si on vise les petites courses
            gloutonDistance();
        } else{
            gloutonCourse();
        }
    }

    /**
     * algorithme simple attribuant les courses
     * aux vehicules lorsqu'une course est réalisable
     */
    void gloutonVehicule() {
        triCourses();
        ArrayList<Course> copie = new ArrayList<>(listeCourses);
        for (Vehicule vehicule : this.listeVehicules) {
            for (Course course : copie) {
                if (courseRealisable(vehicule, course) && !course.getAttribuee()) {//Si la course peut etre terminée à temps et qu'elle n'est pas déja attribuée
                    realiseCourse(vehicule, course);
                }
            }
        }
    }

    /**
     * algorithme simple attribuant les courses
     * aux vehicules lorsqu'une course est réalisable et qu'elle apporte un bonus
     */
    void gloutonBonus(){
        triCourses();
        ArrayList<Course> copie = new ArrayList<>(listeCourses);
        for (Vehicule vehicule : this.listeVehicules) {
            for (Course course : copie) {
                if (courseRealisable(vehicule, course) && !course.getAttribuee()&& arriveATemps(course,vehicule)) {
                    //Si la course peut etre terminée à temps et qu'elle n'est pas déja attribuée et qu'elle apport un bonus
                    realiseCourse(vehicule, course);
                }
            }
        }
        for (Vehicule vehicule : this.listeVehicules){
            copie = new ArrayList<>(listeCourses);

            for(Course course : copie){
                //si la course restantes sont réalisables à temps on les fait.
                if(courseRealisable(vehicule, course) && !course.getAttribuee()){
                    realiseCourse(vehicule, course);
                }
            }
        }
    }

    /**
     * algorithme parcourant les vehicules et supprimant les courses avec une distance trop grande
     */
    void gloutonDistance(){
        int moyenne = moyenne(listeCourses);
        ArrayList<Course> copie3 = new ArrayList<>(listeCourses);
        for (Course c: copie3){
            if(30*moyenne < c.getDistance()){
                listeCourses.remove(c);
            }
        }
        for (Vehicule vehicule : this.listeVehicules) {
            // On trie une premiere fois
            ArrayList<Course> copie = new ArrayList<>();
            for(Course course : listeCourses){
                if(courseRealisable(vehicule, course)) copie.add(course);
            }
            triCoursesDistance(copie,vehicule);
            // tant que on a des courses avec un bonus et faisable
                while(!copie.isEmpty())
                {
                    int index = 0;
                    for(int i=0;i<Math.min(12,copie.size());i++){
                        Course course = copie.get(i);
                        if(arriveATemps(course,vehicule)){
                            index=i;
                            break;
                        }
                        i+=1;
                    }
                    Course course = copie.get(index);
                    if(courseRealisable(vehicule, course)) realiseCourse(vehicule, course);
                    copie.remove(course);
                    ArrayList<Course> copie2 = new ArrayList<>(copie);
                    for(Course c : copie2){
                        if(!(courseRealisable(vehicule, c))) copie.remove(c);
                    }
                    triCoursesDistance(copie,vehicule);
                }
            }
    }

    /**
     * algorithme parcourant les courses et attribuant des vehicules pour les courses.
     */
    void gloutonCourse(){
        firstTriCourses();
        ArrayList<Course> copie = new ArrayList<>(listeCourses);
        int i = 0;
        while(i<listeVehicules.size()){
            for(Course course : copie){
                if(courseRealisable(listeVehicules.get(i), course) && !course.getAttribuee()){
                    realiseCourse(listeVehicules.get(i), course);
                    break;
                }
            }
            i++;
        }
        triCourses2();
        copie = new ArrayList<>(listeCourses);
        for(Course course : copie){
            triVehicules(course);
            for(Vehicule v : listeVehicules){
                if(courseRealisable(v, course) && !course.getAttribuee() && arriveATemps(course, v)){
                    realiseCourse(v, course);
                    break;
                }
                if(listeVehicules.indexOf(v)>340) break;
            }
            for(Vehicule v : listeVehicules){
                if(courseRealisable(v, course) && !course.getAttribuee()){
                    realiseCourse(v, course);
                    break;
                }
            }
        }
    }

    /**
     *
     * @param c liste de course
     * @return la moyenne de la distance de la liste de course c
     */
    int moyenne(ArrayList<Course> c){
        int i = 0;
        for(Course course : c) i+=course.getDistance();
        return (i/c.size());
    }

    /**
     * un méthode permettant de savoir si l'on va arriver à l'heure
     * @param course
     * @param vehicule
     * @return
     */
    public boolean arriveATemps(Course course, Vehicule vehicule){
        if(course.getTourDepart() > (vehicule.getEtape() + distance(vehicule.getPosition(), course.getPositionDepart()))) {// si le chauffeur arrive avant le debut de la course
            return true;//alors on retourne true
        }
        return false;
    }


    /**
     * Indique si une course est réalisable ie: se termine à temps.
     * @param vehicule actuel
     * @param course prochaine course
     * @return true si la course peut etre realisee a temps
     */
    public boolean courseRealisable(Vehicule vehicule, Course course) {
        int distanceJusquAuClient = distance(vehicule.getPosition(), course.getPositionDepart());
        int distanceCourse = course.getDistance();

        int temps = vehicule.getEtape() + distanceJusquAuClient;

        if(temps <course.getTourDepart()) {
            temps = course.getTourDepart();
        }

        if ( temps + distanceCourse < course.getTourArrivee())
            return true;
        return false;
    }

    /**
     * calcul de la distance entre deux points
     * @param depart
     * @param arrivee
     * @return
     */
    public int distance(Position depart, Position arrivee) {
        return Math.abs(arrivee.getX() - depart.getX()) + Math.abs(arrivee.getY() - depart.getY());
    }

    /**
     * Incremente les etapes du vehicule
     * @param vehicule
     * @param course
     */
    public void incrementEtape(Vehicule vehicule, Course course) {
        int distanceVC = distance(vehicule.getPosition(),course.getPositionDepart()); // Distance entre le vehicule et sa course
        int distanceC = course.getDistance(); // Distance de la course

        // Si le vehicule arrive en retard ou juste a l'heure on ajoute la distance du vehicule a la course plus la distance de la course
        if (course.getTourDepart() - vehicule.getEtape() >= distanceVC) {
            vehicule.setEtape(vehicule.getEtape() + distanceVC + distanceC);
        }

        // Sinon le vehicule arrive en avance et on prend directement le temps de fin de course
        else {
            vehicule.setEtape(course.getTourArrivee());
        }

    }

    /**
     * Supprime la course passsee en parametre
     * de la liste de course de l'algorithme
     * @param c Course
     */
    public void supprimerCourse(Course c){
        if(this.listeCourses.contains(c)) {
            this.listeCourses.remove(c);
        }
    }

    /**
     * Fait réaliser la course c à la voiture v
     * @param v
     * @param c
     */
    public void realiseCourse(Vehicule v, Course c){
        if(arriveATemps(c,v)) c.setPonctuel();
        v.attribuerCourse(c);
        v.deplacerVersDepart(c);
        v.deplacerVersFin(c);
        score.ajoutScoreCourse(c);
        supprimerCourse(c);
    }

    /**
     * trie la liste de course par ordre croissant de l'etape de depart
     */
    public void triCourses(){
        listeCourses.sort(Comparator.comparingInt(Course::getTourDepart));
    }

    /**
     * trie la liste de course par ordre croissant de l'etape de depart plus la distance de la course
     */
    public void triCourses2(){
        listeCourses.sort(Comparator.comparingInt(a -> a.getTourDepart() + a.getDistance()));
    }

    /**
     * trie la liste de course avec le maximum entre la distance du vehicule jusqu'a
     * la prochaine course et l'attente du vehicule jusqu'au debut de la course
     * @param courses liste de courses
     * @param vehicule
     */
    void triCoursesDistance(ArrayList<Course> courses, Vehicule vehicule){
        courses.sort(Comparator.comparingInt(a -> Math.max(vehicule.distanceJusquaProchaineCourse(a), a.getTourDepart() - vehicule.getEtape())));
    }

    /**
     * trie la liste de vehicule par maximum entre l'attente d'une
     * course et la distance du vehicule jusqu'a la prochaine course
     * @param course
     */
    public void triVehicules(Course course){
        listeVehicules.sort(Comparator.comparingInt(a -> Math.max(course.getTourDepart() - a.getEtape(), a.distanceJusquaProchaineCourse(course))));
    }

    /**
     * trie la liste des courses par distance de la position de depart (0,0) plus le tour de depart
     */
    void firstTriCourses(){
        listeCourses.sort(Comparator.comparingInt(a -> a.getTourDepart() + distance(a.getPositionDepart(), new Position(0, 0))));
    }


    /* ---------------Getters--------------*/

    public ArrayList<Course> getListeCourses() {
        return listeCourses;
    }

    public ArrayList<Vehicule> getListeVehicule() {
        return listeVehicules;
    }

}
