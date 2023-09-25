package nz.ac.wgtn.swen225.lc.domain.items;

/**
 * Interface implements items, items can be picked up by the player
 */
public interface Item {

    /**
     * Gets if an item can go into the inventory.
     * @return
     */
    public boolean canGoInInventory();


}