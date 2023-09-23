package test.nz.ac.wgtn.swen225.lc.domain;


import nz.ac.wgtn.swen225.lc.domain.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PositionTests {

    @Test
    public void DefaultConstructorTest() {
        Position p = new Position();
        assertEquals(p.getX()==0, p.getY()==0);
    }
    @Test
    public void ConstructorTest() {
        Position p = new Position(1,2);
        assertEquals(p.getX()==1, p.getY()==2);
    }
    @Test
    public void SetXTest() {
        Position p = new Position(1,2);
        p.setX(10);

        assertEquals(p.getX()==10, p.getY()==2);
    }

    @Test
    public void SetYTest() {
        Position p = new Position(1,2);
        p.setY(10);

        assertEquals(p.getX()==1, p.getY()==10);
    }

    @Test
    public void toStringTest() {
        Position a = new Position(5,6);
        Position b = new Position(8,3);

        assertEquals(a.toString(), "Position - X: 5 Y: 6");
        assertEquals(b.toString(), "Position - X: 8 Y: 3");
    }

    @Test
    public void equalsTest() {
        Position a = new Position(5,6);
        Position b = new Position(8,3);
        Object o = new Object();

        assertEquals(a.equals(b),false);
        assertEquals(a.equals(o),false);

        a.setY(3);
        b.setX(5);

        assertEquals(a.equals(b),true);
    }


}
