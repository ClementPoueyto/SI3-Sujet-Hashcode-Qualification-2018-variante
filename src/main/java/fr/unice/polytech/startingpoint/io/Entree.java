package fr.unice.polytech.startingpoint.io;

import fr.unice.polytech.startingpoint.grille.Carte;
import fr.unice.polytech.startingpoint.grille.Course;
import fr.unice.polytech.startingpoint.grille.Position;
import fr.unice.polytech.startingpoint.grille.Vehicule;

import java.util.ArrayList;

/**
 * @author Loic
 */
public class Entree {
    private AnalyseEntree aE;
    private Carte carte;
    private int prixDistance;
    private int prixConstant;
    private int bonus;
    private int etapes;

    /**
     * Constructeur.
     * @param aE
     */
    public Entree(AnalyseEntree aE){
        this.aE = aE;
        litFichier();
    }

    /**
     * Genere la carte avec les vehicules et les courses a partir du fichier d'entree.
     */
    private void litFichier(){
        String [] ligne = aE.nextLine().split(" ");
        int nLigne = Integer.parseInt(ligne[0]);
        int nColonne = Integer.parseInt(ligne[1]);

        //Création des véhicules
        ArrayList<Vehicule> vehicules = new ArrayList<>();
        for(int i = 0;i<Integer.parseInt(ligne[2]);i++){
            vehicules.add(new Vehicule());
        }
        int nCourses = Integer.parseInt(ligne[3]);

        bonus = Integer.parseInt(ligne[4]);
        prixConstant = Integer.parseInt(ligne[5]);
        prixDistance = Integer.parseInt(ligne[6]);

        this.etapes = Integer.parseInt(ligne[7]);

        //Création des courses
        ArrayList<Course> courses = new ArrayList<>();
        for(int i=0;i<nCourses;i++){
            ligne = aE.nextLine().split(" ");
            courses.add(
                new Course(
                    new Position(Integer.parseInt(ligne[0]), Integer.parseInt(ligne[1])),
                    new Position(Integer.parseInt(ligne[2]),Integer.parseInt(ligne[3])),
                    Integer.parseInt(ligne[4]),
                    Integer.parseInt(ligne[5]))
                    );
        }
        this.carte = new Carte(nLigne,nColonne,courses,vehicules);
    }

    public Carte getCarte(){
        return this.carte;
    }

    public int getBonus(){return this.bonus;}

    public int getPrixConstant(){return this.prixConstant;}

    public int getPrixDistance(){return this.prixDistance;}


}
