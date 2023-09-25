package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.net.URL;

public class EmptyTile implements Tile{
    @Override
    public boolean isWalkable() {
        return true;
    }

    @Override
    public URL getTileImageReference() {
        return getResource("/Tiles/EmptyTile.png");
    }

    @Override
    public String toString() {
        return "E";
    }
}
