package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.items.Item;

import java.net.URL;

public class WallTile implements Tile{
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
        return "W";
    }

    @Override
    public boolean hasItem(){
        return false;
    }

    @Override
    public Item getItem(){
        throw new IllegalStateException("This Tile cannot contain Items");
    }

    @Override
    public void SetItem(Item item) {
        throw new IllegalStateException("This Tile cannot contain Items");
    }

    @Override
    public Item RemoveItem() {
        throw new IllegalStateException("This Tile cannot contain Items");
    }
}
