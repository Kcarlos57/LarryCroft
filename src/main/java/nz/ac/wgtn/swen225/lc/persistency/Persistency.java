package nz.ac.wgtn.swen225.lc.persistency;

import nz.ac.wgtn.swen225.lc.domain.Domain;

public class Persistency {

    private Domain domain;

    /**
     * Sets the references between modules
     * @param domain
     */
    public void SetModuleLinks(Domain domain){
        this.domain = domain;
    }
}
