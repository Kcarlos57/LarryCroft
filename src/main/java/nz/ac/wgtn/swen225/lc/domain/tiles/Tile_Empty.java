package nz.ac.wgtn.swen225.lc.domain.tiles;

public class Tile_Empty implements Tile{
    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public boolean hasItem() {
        return false;
    }
}
