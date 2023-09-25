package nz.ac.wgtn.swen225.lc.app;


import nz.ac.wgtn.swen225.lc.domain.Domain;
import nz.ac.wgtn.swen225.lc.recorder.Recorder;
import nz.ac.wgtn.swen225.lc.renderer.Renderer;

import javax.swing.*;
import java.awt.*;

public class App {
    private Domain domain;
    private Renderer renderer;
    private Recorder recorder;

    private JFrame frame;

    public App(){
        Dimension size = new Dimension(800,600);
        frame = new AppFrame("Larry Crofts Adventures", this, size);
        frame.setVisible(true);
    }

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

    public void setupShortCuts(){


    }
}
