package test.nz.ac.wgtn.swen225.lc.domain;


import nz.ac.wgtn.swen225.lc.domain.items.Key;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTests {

    @Test
    public void getKeyColorTest(){
        Key key = new Key(Key.KeyColor.Red);

        assertSame(key.getKeyColor(),Key.KeyColor.Red);
        assertNotSame(key.getKeyColor(),Key.KeyColor.Blue);
    }

    @Test
    public void isKeyTest(){
        Key key = new Key(Key.KeyColor.Red);
        Object o = new Object();

        assertTrue(Key.isKey(key, Key.KeyColor.Red));
        assertFalse(Key.isKey(key, Key.KeyColor.Blue));

        assertFalse(Key.isKey(o, Key.KeyColor.Red));
    }

}