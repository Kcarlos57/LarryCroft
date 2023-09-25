package nz.ac.wgtn.swen225.lc.domain.tiles;
import nz.ac.wgtn.swen225.lc.domain.items.Item;

import java.net.URL;

public interface Tile{
    /**
     * Defines if actors are allowed to enter this tile
     * @return
     */
    public boolean isWalkable();

    /**
     * Gets string representation of tile
     * @return
     */
    @Override
    public String toString();

    /**
     * Gets the image data of the tile
     * @return
     */
    public URL getTileImageReference();

    /**
     * Gets the URL of the resource at the specifed path, handles errors
     * @param path
     * @return
     */
    public default URL getResource(String path){
        try{
            return WallTile.class.getResource(path);
        }catch (NullPointerException np) { throw new IllegalArgumentException("Specifed File Not Found"); }
    }

    /**
     * Checks if a tile has an item on it
     * @return
     */
    public boolean hasItem();

    /**
     * Gets the item in possession of a tile
     * @return
     */
    public Item getItem();

    /**
     * Sets the item on this tile
     * @param item
     */
    public void SetItem(Item item);

    /**
     * Removes and returns the item on this tile
     * @return
     */
    public Item RemoveItem();




}
