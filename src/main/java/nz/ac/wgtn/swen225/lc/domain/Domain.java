package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import java.util.function.Supplier;

/**
 * Class contains all methods that link to other packages
 */
public class Domain {
    /*
     *   public void TryMoveChap(Direction direction)  -   Handles attempting to move chap in the specified direction
     *
     *
     */

    private Persistency persistency;
    public Domain(){

    }

    /**
     * Sets the references between modules
     * @param persistency
     */
    public void SetModuleLinks(Persistency persistency){
        this.persistency = persistency;
    }





    //Public variables
    public enum  Direction {Up, Down, Left, Right};


    //Private



    /**
     * Handles attempting to move larry in the specified direction
     * @param direction
     */
    public void TryMoveLarry(Direction direction){

    }


    public void Setup(){

    }





}
