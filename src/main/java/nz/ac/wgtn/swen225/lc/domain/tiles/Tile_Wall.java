package nz.ac.wgtn.swen225.lc.domain.tiles;

import java.net.URL;

public class Tile_Wall implements Tile{
    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public URL getTileImageReference() {
        return getResource("/Tiles/WallTile.png");
    }

    @Override
    public String toString() {
        return "#";
    }
}
