package nz.ac.wgtn.swen225.lc.domain.items;

import nz.ac.wgtn.swen225.lc.domain.tiles.WallTile;

import java.net.URL;

/**
 * Interface implements items, items can be picked up by the player
 */
public interface Item {

    /**
     * Gets if an item can go into the inventory.
     * @return
     */
    public boolean canGoInInventory();

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
     * Gets the image data of the item
     * @return
     */
    public URL getTileImageReference();

}