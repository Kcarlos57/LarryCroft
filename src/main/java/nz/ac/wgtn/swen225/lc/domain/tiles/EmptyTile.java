package nz.ac.wgtn.swen225.lc.domain.tiles;

import nz.ac.wgtn.swen225.lc.domain.items.Item;

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
        return "_";
    }


    private Item currentItem = null;
    @Override
    public boolean hasItem(){
        return currentItem != null;
    }

    @Override
    public Item getItem(){
        if(!hasItem())throw new IllegalStateException("This Tile does not contain an Item");
        if(currentItem==null)throw new IllegalStateException("Should not have got here, accessing null item");

        return currentItem;
    }
    @Override
    public void SetItem(Item item){
        if(hasItem()) throw new IllegalStateException("Trying to add item to a tile which already has one");
        currentItem = item;

    }
    @Override
    public Item RemoveItem(){
        Item i = getItem();
        currentItem = null;
        return i;
    }

}
