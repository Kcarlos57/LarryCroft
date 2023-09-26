package nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.domain.Domain;

public class Persistency {

    private Domain domain;


    private static final String level1String =
                            "#######" +
                            "#OOTOO#" +
                            "#OOOOO#" +
                            "#OOOOO#" +
                            "###X###" +
                            "#OOTOO#" +
                            "#OOOLO#" +
                            "#KOOOO#" +
                            "#OOOOO#" +
                            "#TOOOO#" +
                            "#######";

    /**
     * Sets the references between modules
     * @param domain
     */
    public void SetModuleLinks(Domain domain){
        this.domain = domain;
    }

    /**
     * Loads the level from the persistency class
     * @param lvlNumber - The level number to load
     */
    public static String getLevel(int lvlNumber) {
        //TODO Load level from persistency class
        if (lvlNumber == 1) {
            return level1String;
        } else {
            return null;
        }
    }
}
