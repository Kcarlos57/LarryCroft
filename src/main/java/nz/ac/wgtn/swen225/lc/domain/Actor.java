package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.tiles.Tile_Wall;

import java.net.URL;

/**
 * Controls and mangages non-player controled characters
 */
//TODO Add Tests
public class Actor{
    private Position position;
    private Domain.Direction directionFacing;   // The direction the actor is facing

    private URL imageReference;

    /**
     * Creates a new actor
     * @param position
     * @param directionFacing
     * @param path
     */
    public Actor(Position position, Domain.Direction directionFacing, String path){
        this.position = position;
        this.directionFacing = directionFacing;

        try{
            this.imageReference = Tile_Wall.class.getResource(path);
        }catch (NullPointerException np) { throw new RuntimeException("Could not find given path for actor"); }

    }


    /**
     * Moves the actor in the specified direction, does no checking
     */
    public void Move(Domain.Direction direction){
        switch (direction){
            case Up -> position.setX(position.getY()-1);
            case Down -> position.setX(position.getY()+1);
            case Left -> position.setX(position.getX()-1);
            case Right -> position.setX(position.getX()+1);
        }
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

    /**
     * Returns the reference to the image of the actor
     * @return
     */
    public URL getImageReference(){ return  imageReference; }




}