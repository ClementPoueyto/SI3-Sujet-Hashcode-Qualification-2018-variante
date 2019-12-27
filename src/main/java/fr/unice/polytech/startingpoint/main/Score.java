package fr.unice.polytech.startingpoint.main;


import fr.unice.polytech.startingpoint.grille.Course;

/**
 * Classe permettant de calculer le score
 * @author Nathan
 */

public class Score {
    private int score;
    private int prixDistance;
    private int prixConstant;
    private int bonusPonctualite;


    /**
     * Constructeur
     * @param prixDistance
     * @param prixConstant
     * @param bonus
     */
    public Score(int prixDistance, int prixConstant, int bonus){
        this.prixDistance=prixDistance;
        this.prixConstant=prixConstant;
        this.bonusPonctualite=bonus;
        this.score=0;
    }

    /**
     * Met à jour le score lorsqu'une course se termine
     * @param course
     */
    public void ajoutScoreCourse(Course course){
        this.score+=course.getDistance()*prixDistance + prixConstant;//
        
        // Ajout du bonus si le chauffeur arrive à l'heure
        if(course.getPonctuel()){
            score+=bonusPonctualite;
        }
    }


    /**
     * renvoie le score final
     * @return int le score
     */
    public int getScore(){return this.score;}



}
