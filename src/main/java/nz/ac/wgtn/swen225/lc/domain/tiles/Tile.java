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
     * Gets the image data of the tile
     * @return
     */
    public URL getTileImageReference();

    /**
     * Gets string representation of tile
     * @return
     */
    @Override
    public String toString();

    Item currentItem = null;
    /**
     * Checks if a tile has an item on it
     * @return
     */
    public default boolean hasItem(){
        return currentItem != null;
    }

    /**
     * Gets the item in possession of a tile
     * @return
     */
    public default Item getItem(){
        if(!hasItem())throw new IllegalStateException("This Tile does not contain an Item");
        if(currentItem==null)throw new IllegalStateException("Should not have got here, accessing null item");

        return currentItem;
    }

    /**
     * Gets the URL of the resource at the specifed path, handles errors
     * @param path
     * @return
     */
    public default URL getResource(String path){
        try{
            return Tile_Wall.class.getResource(path);
        }catch (NullPointerException np) { throw new IllegalArgumentException("Specifed File Not Found"); }
    }




}
