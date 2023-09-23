package nz.ac.wgtn.swen225.lc.recorder;

import nz.ac.wgtn.swen225.lc.app.App;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;

public class Recorder {
    private Persistency persistency;
    private App app;
    /**
     * Sets the references between modules
     * @param persistency
     * @param app
     */
    public void SetModuleLinks(Persistency persistency,App app){
        this.persistency = persistency;
        this.app = app;
    }
}
