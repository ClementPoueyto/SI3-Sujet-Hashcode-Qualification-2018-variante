package fr.unice.polytech.startingpoint.io;

import fr.unice.polytech.startingpoint.main.Score;
import fr.unice.polytech.startingpoint.grille.Carte;
import fr.unice.polytech.startingpoint.grille.Course;
import fr.unice.polytech.startingpoint.grille.Vehicule;

/**
 * @author Nathan
 */

public class Sortie {
    private Score score;
    private Carte carte;

    public Sortie(Score score, Carte carte) {
        this.score=score;
        this.carte=carte;
    }


    public String creerSortie(){
        //Affichage du score en ligne 1
        String affichage = "";
        affichage += score.getScore();


        // Affichage des courses réalisées pour chaque véhicule
        for (Vehicule vehicule : carte.getListeVehicules()) {
            String id = "";
            if (vehicule.getCourses().size() != 0) {
                for (Course course : vehicule.getCourses()) {
                    id += (" " + course.getId());
                }
            }
            affichage += ("\n" + vehicule.getNombreCoursesAttribuees() + id);
        }
        return affichage;

    }

    public void afficherSortie(){
        System.out.println(creerSortie());
    }

}
