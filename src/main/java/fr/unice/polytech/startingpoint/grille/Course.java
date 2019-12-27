package fr.unice.polytech.startingpoint.grille;

/**
 * Classe qui décrit une course
 * @author Vincent Delmotte
 */
public class Course{
    private static int cId=0;
    private int id;
    private Position positionDepart;
    private Position positionArrivee;
    private int tourDepart;
    private int tourArrivee;
    private int distance;
    private boolean ponctuel;
    private boolean attribuee;

    /**
     * Constructeur
     * @param depart Position de depart
     * @param arrivee Position d'arrivee
     * @param tourDepart Le tour auquelle on peut prendre le client;
     * @param tourArrivee Le tour auquelle il faut deposer le client
     */
    public Course(Position depart, Position arrivee, int tourDepart, int tourArrivee) {
        this.id = cId;
        cId++;
        this.positionDepart = depart;
        this.positionArrivee = arrivee;
        this.tourDepart = tourDepart;
        this.tourArrivee = tourArrivee;
        this.distance = distance(positionDepart, positionArrivee);
        this.ponctuel = false;
        this.attribuee = false;
    }
    //##################################### METHODE #############################################\\

    /**
     * Calcul la distance d'un trajet entre deux positons
     * @param depart Position de depart
     * @param arrivee Position d'arrivee
     * @return distance
     */
    public int distance(Position depart, Position arrivee) {
        return Math.abs(arrivee.getX() - depart.getX()) + Math.abs(arrivee.getY() - depart.getY());
    }
    //###################################### GETTER #############################################\\

    public Position getPositionDepart() {
        return this.positionDepart;
    }

    public Position getPositionArrivee() {
        return this.positionArrivee;
    }

    public int getTourDepart() {
        return this.tourDepart;
    }

    public int getTourArrivee() {
        return this.tourArrivee;
    }

    public int getDistance(){return this.distance;}

    public boolean getPonctuel(){return this.ponctuel;}

    public boolean getAttribuee(){return this.attribuee;}

    public void setAttribuee(boolean b){
        this.attribuee=b;
    }

    public void setPonctuel(){
        this.ponctuel=true;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj!=null && obj.getClass()==Course.class){
            Course o = (Course) obj;
            if(this.positionDepart.equals(o.positionDepart)
               && this.positionArrivee.equals(o.positionArrivee)
               && this.tourDepart==o.tourDepart
               && this.tourArrivee==o.tourArrivee) {
                   return true;
               }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Position de depart: "+positionDepart
        + "\nPosition d'Arrivee: "+positionArrivee
        + "\nTour de depart: "+tourDepart
        + "\nTour d'arrivee: "+tourArrivee;
    }

}
