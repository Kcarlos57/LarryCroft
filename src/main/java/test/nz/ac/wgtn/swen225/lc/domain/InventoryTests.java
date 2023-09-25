package test.nz.ac.wgtn.swen225.lc.domain;

import nz.ac.wgtn.swen225.lc.domain.Inventory;
import nz.ac.wgtn.swen225.lc.domain.items.Item;
import nz.ac.wgtn.swen225.lc.domain.items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryTests {
    @Test
    public void addItemTest1() {
        Inventory inventory = new Inventory();
        Item a = new Key(Key.KeyColor.Red);
        Item t = new Treasure();
        Item n = null;

        inventory.addItem(a);

        assertThrows(IllegalArgumentException.class, () -> inventory.addItem(n));
        assertThrows(IllegalStateException.class, () -> inventory.addItem(t));

    }
    @Test
    public void addItemTest2() {
        Inventory inventory = new Inventory();

        for(int i = 0; i < inventory.getInventorySize();i++){
            inventory.addItem(new Key(Key.KeyColor.Red));
        }

        assertThrows(IllegalStateException.class, () -> inventory.addItem(new Key(Key.KeyColor.Red)));

    }

    @Test
    public void addItemAtPositionTest(){
        Inventory inventory = new Inventory();

        Item a = new Key(Key.KeyColor.Red);
        Item t = new Treasure();
        Item n = null;

        inventory.addItemAtPosition(a,0);
        inventory.addItemAtPosition(a,7);

        assertThrows(IllegalArgumentException.class, () -> inventory.addItemAtPosition(n,0));

        assertThrows(IllegalArgumentException.class, () -> inventory.addItemAtPosition(a,-1));
        assertThrows(IllegalArgumentException.class, () -> inventory.addItemAtPosition(a,8));

        assertThrows(IllegalStateException.class, () -> inventory.addItemAtPosition(t,3));
        assertThrows(IllegalStateException.class, () -> inventory.addItemAtPosition(a,0));

    }

    @Test
    public void getItemAtPosTest1(){
        Inventory inventory = new Inventory();

        Item a = new Key(Key.KeyColor.Red);
        Item b = new Key(Key.KeyColor.Blue);

        inventory.addItem(a);
        inventory.addItem(b);

        assertEquals(a,inventory.getItemAtPos(0));
        assertEquals(b,inventory.getItemAtPos(1));
    }

    @Test
    public void getItemAtPosTest2(){
        Inventory inventory = new Inventory();

        Item a = new Key(Key.KeyColor.Red);
        Item b = new Key(Key.KeyColor.Blue);

        inventory.addItemAtPosition(b,4);
        inventory.addItem(a);

        assertEquals(a,inventory.getItemAtPos(0));
        assertEquals(b,inventory.getItemAtPos(4));
    }
    @Test
    public void getItemAtPosTest3(){
        Inventory inventory = new Inventory();

        assertThrows(IllegalStateException.class, () -> inventory.getItemAtPos(0));
    }

    @Test
    public void getInventory(){
        Inventory inventory = new Inventory();

        assertEquals(inventory.getInventorySize(),8);
    }

    @Test
    public void clearTest(){
        Inventory inventory = new Inventory();

        inventory.addItem(new Key(Key.KeyColor.Red));
        inventory.addItemAtPosition(new Key(Key.KeyColor.Red),5);

        inventory.clear();

        assertThrows(IllegalStateException.class, () -> inventory.getItemAtPos(0));
        assertThrows(IllegalStateException.class, () -> inventory.getItemAtPos(5));
    }
}
