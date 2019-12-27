package fr.unice.polytech.startingpoint.main;

import fr.unice.polytech.startingpoint.io.AnalyseEntree;
import fr.unice.polytech.startingpoint.io.Entree;
import fr.unice.polytech.startingpoint.grille.Carte;
import fr.unice.polytech.startingpoint.io.Sortie;

import java.util.Scanner;

/**
 * classe main : lancement du programme
 * @author Nathan
 * @author Vincent
 * @author Loic
 * @author Clement
 */

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        AnalyseEntree analyseEntree = new AnalyseEntree(sc);
        Entree entree = new Entree(analyseEntree);
        sc.close();

        Carte carte = entree.getCarte();
        Score score = new Score(entree.getPrixDistance(), entree.getPrixConstant(), entree.getBonus());
        Algo algo = new Algo(entree, score);


        algo.traitement();


        Sortie sortie = new Sortie(score, carte);
        sortie.afficherSortie();


    }


}
