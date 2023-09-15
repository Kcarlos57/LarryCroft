package nz.ac.wgtn.swen225.lc.domain.tiles;

public class Tile_Wall implements Tile{
    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public boolean hasItem() {
        return false;
    }
}
