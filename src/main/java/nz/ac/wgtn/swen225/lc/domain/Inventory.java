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
    Item[] items;
    int inventorySize;


    /**
     * Adds a item to the first empty slot in the inventory
     * @param item
     */
    public void addItem(Item item){
        if(item==null) throw new IllegalArgumentException("Given item is null");

        int pos = getFirstEmptySlot();
        items[pos] = item;


    }

    /**
     * Adds a item to the inventory at the specified position
     * @param item
     * @param position
     */
    public void addItemAtPosition(Item item, int position){
        if(item==null) throw new IllegalArgumentException("Given item is null");
        if(position < 0 || position > inventorySize) throw new IllegalArgumentException("Specified inventory slot does not exist");

        if(items[position]!=null) throw new IllegalStateException("Inventory slot is taken");
        items[position] = item;
    }

    /**
     * Gets the item at a given slot in the inventory
     * @param pos
     * @return
     */
    public Item getItemAtPos(int pos){
        if(pos < 0 || pos > inventorySize) throw new IllegalArgumentException("Specified inventory slot does not exist");

        Item item = items[pos];
        if(item == null) throw new IllegalStateException("Inventory slot is empty");

        return item;
    }

    /**
     * Gets the size of the inventory
     * @return
     */
    public int getInventorySize(){
        return  inventorySize;
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
}
