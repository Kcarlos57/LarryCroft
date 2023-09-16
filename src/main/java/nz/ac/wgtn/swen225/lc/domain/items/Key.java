package nz.ac.wgtn.swen225.lc.domain.items;


/**
 * Implements the item 'Key' which can open locked doors of the same color
 */
public class Key implements Item{

    /**
     * Possible Key Colors
     */
    public enum KeyColor {Red, Blue}
    KeyColor keyColor;


    /**
     * Creates a key of a specified color
     * @param keyColor
     */
    public Key(KeyColor keyColor){
        this.keyColor = keyColor;
    }

    /**
     * Gets the coor of a key
     * @return
     */
    public KeyColor getKeyColor(){
        return  this.keyColor;
    }

    /**
     * Gets if an object is a key of a specific color
     * @param object
     * @param color
     * @return
     */
    public static boolean isKey(Object object, KeyColor color){
        if(object == null) return false;
        if( !(object instanceof Key) ) return false;

        Key k = (Key) object;
        return k.getKeyColor() == color;
    }


    @Override
    public boolean canGoInInventory() {
        return true;
    }
}
