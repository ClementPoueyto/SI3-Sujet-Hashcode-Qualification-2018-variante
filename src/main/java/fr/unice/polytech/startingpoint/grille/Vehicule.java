package fr.unice.polytech.startingpoint.grille;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Clement
 */

public class Vehicule{
    private static int cId = 0;
    private int id;
    private Position position;
    private List<Course> coursesAttribuees;
    private int etape;
    
    /**
     * Constructeur.
     */
    public Vehicule(){
        coursesAttribuees = new ArrayList<>();
        this.position = new Position(0, 0);
        cId++;
        this.id = cId;
        this.etape = 0;
    }


    @Override
    public boolean equals(Object obj){
        if((obj!=null)&& obj.getClass()==Vehicule.class){
            Vehicule v = (Vehicule) obj;
            if(v.getId()==this.getId()){
                return true;
            }
        }
        return false;
    }

    /**
     * Attribue une course au Vehicule courant.
     * @param course
     */
    public void attribuerCourse(Course course){
        this.coursesAttribuees.add(course);
        course.setAttribuee(true);
    }


    /**
     *
     * @param c Course
     * @return la distance entre la position actuelle du vehicule
     * et la position du depart de sa prochaine course
     */
    public int distanceJusquaProchaineCourse(Course c){
        Position posV= this.getPosition();
        Position posC = c.getPositionDepart();
        return Math.abs(posV.getX()-posC.getX())+Math.abs(posV.getY()-posC.getY());
    }

    /**
     * Deplace la position du vehicule vers
     * la position de depart de la course
     * et incremente le temps
     * @param c Course
     */
    public void deplacerVersDepart(Course c){
        this.etape+=distanceJusquaProchaineCourse(c);
        if(etape<c.getTourDepart()){
            etape = (c.getTourDepart());

        }
        setPosition(c.getPositionDepart());
    }
    /**
     * Deplace la position du vehicule vers
     * la position de fin de la course
     * et incremente le temps
     * @param c Course
     */
    public void deplacerVersFin(Course c){
        setPosition(c.getPositionArrivee());
        this.etape+=c.getDistance();
    }

    /**
     * 
     * @return la derniere course attribuee au vehicule courant.
     */
    public Course courseActuelle(){
        return coursesAttribuees.get(coursesAttribuees.size()-1);
    }


    /*------------GETTERS ----------------*/

    public int getEtape(){
        return this.etape;
    }

    public void setEtape(int etape) {
        this.etape = etape;
    }

    public int getNombreCoursesAttribuees(){
        return coursesAttribuees.size();
    }

    public int getId(){
        return this.id;
    }

    public Position getPosition(){
        return this.position;
    }

    public void setPosition(Position p){
        this.position=p;
    }

    public List<Course> getCourses(){
        return this.coursesAttribuees;
    }

}