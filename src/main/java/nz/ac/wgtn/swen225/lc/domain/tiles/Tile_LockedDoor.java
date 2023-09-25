package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.items.Item;
import nz.ac.wgtn.swen225.lc.domain.items.Key;

import java.net.URL;

public class Tile_LockedDoor implements Tile{

    public Tile_LockedDoor(Key key){
        requiredKey = key;
    }

    //Keeps track of if the door is open or not
    private boolean doorOpen = false;

    //Keeps Track of the type of key needed to open the door
    private Item requiredKey = null;

    /**
     * Tries to open the door with the given key, returns if it is open or not
     * @param key
     * @return
     */
    public boolean OpenDoor(Key key){
        if(requiredKey.equals(key)){
            doorOpen = true;
        }
        return doorOpen;
    }


    @Override
    public boolean isWalkable() {
        return doorOpen;
    }

    @Override
    public URL getTileImageReference() {
        String target = (doorOpen)? "EmptyTile" : "ExitTile";
        return getResource("/Tiles/" + target + ".png");
    }

    @Override
    public String toString() {
        return (doorOpen)? "D" : "X";
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
