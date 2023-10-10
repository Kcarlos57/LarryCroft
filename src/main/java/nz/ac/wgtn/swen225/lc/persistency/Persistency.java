package nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.domain.Domain;

import java.util.List;

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

    private static final String level2String =
                    "#######" +
                    "#OOTOO#" +
                    "#OOOOO#" +
                    "#OOOOO#" +
                    "#X#####" +
                    "#OOTOO#" +
                    "#OOOLO#" +
                    "#OOOOO#" +
                    "#OOOOT#" +
                    "#TOOTK#" +
                    "#######";

    private static List<String> levels = List.of(level1String,level2String);


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
        if (lvlNumber>levels.size()) throw new RuntimeException("Level " + lvlNumber + " doesn't exist");
        return levels.get(lvlNumber-1);
    }
}
