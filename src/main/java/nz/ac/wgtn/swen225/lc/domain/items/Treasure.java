package nz.ac.wgtn.swen225.lc.domain.items;

import java.net.URL;

public class Treasure implements Item{
    @Override
    public boolean canGoInInventory() {
        return false;
    }

    @Override
    public URL getTileImageReference() {
        return getResource("/Tiles/TreasureItem.png");
    }
}