package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.app.App;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

import java.util.ArrayList;


public class Recorder {
    private Persistency persistency;
    private App app;
    private static ArrayList<Action> actions = new ArrayList<Action>();

    /**
     * Sets the references between modules
     * @param persistency
     * @param app
     */
    public void SetModuleLinks(Persistency persistency,App app){
        this.persistency = persistency;
        this.app = app;
    }

    /**
     *
     * @param action - string describing action performed
     * records an action
     */
    public static void addAction(String action) {
        Action a = new Action(action);
        actions.add(a);
    }

    /**
     * prints all the recorded actions
     */
    public static void printActions() {
        for(Action a : actions) {
            System.out.println(a.getAction());
        }
    }
}
