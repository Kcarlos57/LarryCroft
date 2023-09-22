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
    public Level(){

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
     * Causes all non-player actors to make a move
     */
    public void Step(){

    }


}
