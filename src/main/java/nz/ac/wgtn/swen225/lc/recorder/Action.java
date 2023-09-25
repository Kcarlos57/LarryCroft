package nz.ac.wgtn.swen225.lc.recorder;

/**
 * Action class to record individual actions performed by the user
 */
public class Action {
    private final String action;
    public Action(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
