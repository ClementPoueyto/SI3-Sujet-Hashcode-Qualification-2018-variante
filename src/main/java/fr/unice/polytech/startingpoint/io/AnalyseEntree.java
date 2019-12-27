package fr.unice.polytech.startingpoint.io;

import java.util.Scanner;

/**
 * Classe permettant d'encapsuler la classe Scanner qui est celle finale et permettant notamment de mocker cette classe
 * afin de réaliser des tests unitaires.
 * @author Loic
 */
public class AnalyseEntree{
    private Scanner sc;

    public AnalyseEntree(Scanner sc){
        this.sc = sc;
    }

    public String nextLine(){
        return sc.nextLine();
    }
}