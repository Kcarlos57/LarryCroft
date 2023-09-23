package nz.ac.wgtn.swen225.lc.app;


import nz.ac.wgtn.swen225.lc.domain.Domain;
import nz.ac.wgtn.swen225.lc.recorder.Recorder;
import nz.ac.wgtn.swen225.lc.renderer.Renderer;

/*
    Jonathan - This is here so I can setup links between the modules, look at Main in domain if you want to move it to Main in this package
 */
public class App {
    private Domain domain;
    private Renderer renderer;
    private Recorder recorder;

    /**
     * Sets the references between modules
     * @param domain
     * @param renderer
     * @param recorder
     */
    public void SetModuleLinks(Domain domain, Renderer renderer, Recorder recorder){
        this.domain = domain;
        this.renderer = renderer;
        this.recorder = recorder;
    }
}
