package nz.ac.wgtn.swen225.lc.app;

import java.awt.event.KeyEvent;

/**
 * Shortcut
 *
 *
 */
public class Shortcut {
    private SpecialKeys specialKey;
    private int key;

    private String name;

    enum SpecialKeys {
        CTRL,
        ALT,
        SHIFT,
        NONE;

        /**
         * Checks if the keyevent has any special keys pressed
         *
         * @param e Keyevent just pressed
         * @return
         */
        public boolean matches(KeyEvent e) {
            switch (this) {
                case CTRL:
                    return e.isControlDown();
                case ALT:
                    return e.isAltDown();
                case SHIFT:
                    return e.isShiftDown();
                case NONE:
                    return !e.isControlDown() && !e.isAltDown() && !e.isShiftDown();
                default:
                    return false;
            }
        }
    }

    /**
     * Create a new shortcut with the given name.
     *
     * @param name The name of the shortcut.
     */
    public Shortcut(String name) {
        this.name = name;
        specialKey = SpecialKeys.NONE;
        key = -1;
    }

    /**
     * Set the key for this shortcut.
     *
     * @param e The key event to set.
     * @return This shortcut.
     */
    public Shortcut setKey(int e) {
        key = e;
        return this;
    }

    /**
     * Set the special key for this shortcut.
     *
     * @param key The special key to set.
     * @return This shortcut.
     */
    public Shortcut setSpecialKey(SpecialKeys key) {
        specialKey = key;
        return this;
    }

    /**
     * Get the name of this shortcut.
     *
     * @return The name of this shortcut.
     */

    public String getName() {
        return name;
    }

    public SpecialKeys getSpecialKey() {
        return specialKey;
    }

    /**
     * Check if the given key event matches this shortcut.
     *
     * @param e The key event to check.
     * @return
     */
    public boolean keyEventMatches(KeyEvent e) {
        return specialKey.matches(e) && (key == -1 || key == e.getKeyCode());
    }

    /**
     * Check if the given key event matches this shortcut.
     *
     * @param e The key event to check.
     * @return
     */
    public boolean matches(KeyEvent e) {
        return specialKey.matches(e) && (key == -1 || key == e.getKeyCode());
    }
}
