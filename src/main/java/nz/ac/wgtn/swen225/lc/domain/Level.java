package nz.ac.wgtn.swen225.lc.domain;
import nz.ac.wgtn.swen225.lc.domain.tiles.Tile;

/**
 * Holds the data containing a level
 */
public class Level {


    //TODO Modify to take from persistency
	
    /**
     * Creates a level from relevant data
     */
    public Level(int stage){
    	
    }
    
    private Tile[][] tiles;
    private Actor larry;
    private Actor[] actors;

    int treasureNum;

    /**
     * Returns a 2d array of tile objects containing the tile data o the level
     * @return
     */
    public Tile[][] getTiles(){
        return tiles;
    }

    /**
     * Gets larry
     * @return
     */
    public Actor getLarry(){
        return  larry;
    }

    /**
     * Gets all non larry actors
     * @return
     */
    public Actor[] getNPCs(){
        return actors;
    }

    /**
     * Gets an array of all actors, npcs and larry
     * @return
     */
    public Actor[] getAllActors(){
        int aSize = 0;
        if(getNPCs()==null){
            aSize = 1;
        } else {
            aSize = getNPCs().length + 1;
        }

        Actor[] out = new Actor[aSize];
        out[0] = getLarry();
        if(aSize==1) return out;

        for (int i = 0; i < getNPCs().length; i++){
            out[i+1] = actors[i];
        }
        return out;
    }


    /**
     * Causes all non-player actors to make a move
     */
    public void Step(){

    }


}
