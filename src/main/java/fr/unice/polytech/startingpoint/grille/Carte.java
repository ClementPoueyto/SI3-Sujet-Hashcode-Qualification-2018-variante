package fr.unice.polytech.startingpoint.grille;

import java.util.ArrayList;

/**
 * Cette classe indique la Carte sous forme de grille avec c colonnes et l lignes
 * @author Clement Poueyto
 */


public class Carte {
    private int colonnes; //inutilisées dans le code avec nos algorithme mais peut servir avec un raisonnement different.
    private int lignes;
    private ArrayList<Course> listeCourses;
    private ArrayList<Vehicule> listeVehicules;

    /**
     * Constructeur
     * @param c nombre de colonnes
     * @param l nombre de lignes
     * @param courses liste des differentes courses
     * @param vehicules liste des differents vehicules
     */
    public Carte(int l,int c, ArrayList<Course> courses, ArrayList<Vehicule> vehicules){
        this.colonnes=c;
        this.lignes=l;
        this.listeCourses= new ArrayList<Course>(courses);
        this.listeVehicules= new ArrayList<Vehicule>(vehicules);
    }


    /**
     * Getters
     */

    public ArrayList<Course> getListeCourses() {
        return listeCourses;
    }

    public ArrayList<Vehicule> getListeVehicules() {
        return listeVehicules;
    }
}
