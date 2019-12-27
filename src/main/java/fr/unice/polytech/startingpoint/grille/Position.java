package fr.unice.polytech.startingpoint.grille;


/**
 * Cette classe indique la position des véhicules
 * @author Nathan
 */

public class Position {
    private int tabPosition[];

    /**
     * Constructeur
     * @param x l'abcsisse
     * @param y l'ordonnée
     */
    public Position(int x, int y){
        tabPosition = new int[2];
        tabPosition[0]=x;
        tabPosition[1]=y;
    }


    /**
     * Getters
     */

    public int getX(){
        return this.tabPosition[0];
    }


    public int getY(){
        return this.tabPosition[1];
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj.getClass()==Position.class){
            Position o = (Position) obj;
            if(this.getX()==o.getX() && this.getY() == o.getY()) {
                   return true;
               }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Abscisse: "+getX()+"  Ordonnee: "+getY();
    }
}
