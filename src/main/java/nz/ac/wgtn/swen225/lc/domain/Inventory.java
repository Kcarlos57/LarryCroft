package nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.items.Item;

/**
 * Stores and manages items the player has picked up
 */
public class Inventory {
    /**
     * Manages the items that the player has picked up
     */
    public Inventory(){
        inventorySize = 8;
        items = new Item[inventorySize];
    }
    private Item[] items;
    private int inventorySize = 8;
    private int numItems = 0;


    /**
     * Adds a item to the first empty slot in the inventory
     * @param item
     */
    public void addItem(Item item){
        if(item==null) throw new IllegalArgumentException("Given item is null");
        if(!item.canGoInInventory()) throw new IllegalStateException("This item cannot be put into the inventory");


        int pos = getFirstEmptySlot();
        items[pos] = item;
        numItems++;


    }

    /**
     * Adds a item to the inventory at the specified position
     * @param item
     * @param position
     */
    public void addItemAtPosition(Item item, int position){
        if(item==null) throw new IllegalArgumentException("Given item is null");
        if(!item.canGoInInventory()) throw new IllegalStateException("This item cannot be put into the inventory");

        if(position < 0 || position >= inventorySize) throw new IllegalArgumentException("Specified inventory slot does not exist");

        if(items[position]!=null) throw new IllegalStateException("Inventory slot is taken");
        items[position] = item;
        numItems++;
    }

    /**
     * Gets the item at a given slot in the inventory
     * @param pos
     * @return
     */
    public Item getItemAtPos(int pos){
        if(pos < 0 || pos >= inventorySize) throw new IllegalArgumentException("Specified inventory slot does not exist");

        return items[pos];
    }

    /**
     * Gets the size of the inventory
     * @return
     */
    public int getInventorySize(){
        return  inventorySize;
    }

    /**
     * Clears all items in the inventory
     */
    public void Clear(){
        for(int i = 0; i < getInventorySize();i++){
            items[i] = null;
        }
        numItems = 0;
    }

    /**
     * Gets the first empty available slot in the inventory
     * @return
     */
    private int getFirstEmptySlot(){
        for(int i = 0; i < inventorySize; i++){
            if(items[i]==null)return i;
        }
        throw new IllegalStateException("Cannot add item to Inventory, it is full");
        // return -1;
    }

    /**
     * Returns if the inventory is full
     * @return
     */
    public boolean isFull(){
        return numItems >= inventorySize;
    }

    /**
     * Remove
     */
    public void RemoveItemAt(int pos){
        if(pos < 0 || pos >= inventorySize) throw new IllegalArgumentException("Specified inventory slot does not exist");

        items[pos] = null;
    }

}