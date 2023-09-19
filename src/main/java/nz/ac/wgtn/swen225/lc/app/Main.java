package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.Domain;
import nz.ac.wgtn.swen225.lc.persistency.Persistency;
import nz.ac.wgtn.swen225.lc.recorder.Recorder;
import nz.ac.wgtn.swen225.lc.renderer.Renderer;

class Main{
    public static void main(String args[]){
        Domain domain = new Domain();
        App app = new App();
        Renderer renderer = new Renderer();
        Persistency persistency = new Persistency();
        Recorder recorder = new Recorder();

        domain.SetModuleLinks(persistency);
        app.SetModuleLinks(domain,renderer,recorder);
        renderer.SetModuleLinks(domain);
        persistency.SetModuleLinks(domain);
        recorder.SetModuleLinks(persistency,app);
    }
}