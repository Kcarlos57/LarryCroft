package nz.ac.wgtn.swen225.lc.domain;

public class Actor{
    private Position position;


    Actor(){

    }


    /**
     * Moves the actor in the specified direction
     */
    public void Move(Domain.Direction direction){

    }

    /**
     * Moves the actor to the specified position
     */
    public void SetPosition(Position position){
        this.position = position;
    }

    /**
     * Gets the actors current position
     */
    public Position GetPosition(){
        return this.position;
    }




}