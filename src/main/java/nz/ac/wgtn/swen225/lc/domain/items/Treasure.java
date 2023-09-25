package nz.ac.wgtn.swen225.lc.domain.items;

public class Treasure implements Item{
    @Override
    public boolean canGoInInventory() {
        return false;
    }
}