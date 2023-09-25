package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import java.util.function.Supplier;

/**
 * Class contains all methods that link to other packages
 */
public class Domain {
    /*
     *   public void TryMoveLarry(Direction direction)  -   Handles attempting to move chap in the specified direction
     *   public Level GetCurrentLevel();
     *
     */



    public enum  Direction {Up, Down, Left, Right};



    private Persistency persistency = null;
    private Level currentLevel = null;



    /**
     * Sets the references between modules
     * @param persistency
     */
    public void SetModuleLinks(Persistency persistency){
        this.persistency = persistency;
    }

    /**
     * Handles Setting up the domain class variables, SetModuleLinksShould be called before this or it will fail
     */
    public void Setup(){
        if(persistency==null) throw new IllegalStateException("Domain cannot access Persistency class");

        //TODO Cannot impement properly until the format of the persistency class is created, temp for now so I can keep working

       // String defaultLevel = "Level1";
        //LoadLevel(defaultLevel);
        currentLevel = new Level("#######" +
                "#OOTOO#" +
                "#OOOOO#" +
                "#OOOOO#" +
                "###X###" +
                "#OOTOO#" +
                "#OOOLO#" +
                "#KOOOO#" +
                "#OOOOO#" +
                "#TOOOO#" +
                "#######",7,11);

        System.out.println(currentLevel.toString());
        TryMoveLarry(Direction.Up);

        TryMoveLarry(Direction.Up);

        TryMoveLarry(Direction.Up);
        TryMoveLarry(Direction.Up);

        System.out.println(currentLevel.toString());
        TryMoveLarry(Direction.Left);

        TryMoveLarry(Direction.Up);
        System.out.println(currentLevel.toString());

        TryMoveLarry(Direction.Left);

        TryMoveLarry(Direction.Left);

        TryMoveLarry(Direction.Down);
        TryMoveLarry(Direction.Down);
        TryMoveLarry(Direction.Down);


        System.out.println(currentLevel.toString());

        TryMoveLarry(Direction.Up);
        TryMoveLarry(Direction.Up);
        TryMoveLarry(Direction.Up);
        TryMoveLarry(Direction.Up);
        TryMoveLarry(Direction.Up);

        System.out.println(currentLevel.toString());

        TryMoveLarry(Direction.Right);
        TryMoveLarry(Direction.Right);

        TryMoveLarry(Direction.Up);
        TryMoveLarry(Direction.Up);
        System.out.println(currentLevel.toString());

    }

    /**
     * Loads a new Level into the Domain, removes all traces of prevoiusly loaded level if any, call SaveLevel first if you want to save previous
     * @param levelName //TODO do not know what final format is for this, guessing string though
     */
    public void LoadLevel(String levelName){
        //TODO Needs Persistency

        //Level newLevel = ConvertToLevel(persistency.LoadLevel(levelName)); //Need to do sometinh here
        Level newLevel = null;

        if(newLevel == null) throw new IllegalStateException("The level being loaded is null");
        currentLevel = newLevel;
    }

    /**
     *  Saves the current level with all actors in their current place and state
     */
    public void SaveCurrentLevel(){
        //TODO Needs Persistency
        //persistency.Save(currentLevel.toPersistencyFormat());
    }


    /**
     * Gets the current level
     * @return
     */
    public Level getCurrentLevel(){
        return currentLevel;
    }



    /**
     * Handles attempting to move larry in the specified direction
     * @param direction
     */
    public void TryMoveLarry(Direction direction){
        if(currentLevel == null) throw new IllegalStateException("Cannot move larry, level is not loaded");
        try {
            currentLevel.MoveLarry(direction);
        }catch (InvalidMovementException e) { System.out.println(e); }


    }

    /**
     * Gets the current level
     * @return
     */
    public Level GetCurrentLevel(){
        if(currentLevel == null) throw new IllegalStateException("Level is null");
        return currentLevel;
    }
}
