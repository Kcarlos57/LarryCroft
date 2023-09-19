package nz.ac.wgtn.swen225.lc.app;

import java.awt.event.KeyEvent;

public class Shortcut {
    SpecialKeys specialKey;
    KeyEvent key;

    public Shortcut(String name) {
        specialKey = SpecialKeys.NONE;
        key = null;
    }

    public Shortcut setKey(KeyEvent e) {
        key = e;
        return this;
    }

    public Shortcut setSpecialKey(SpecialKeys key) {
        specialKey = key;
        return this;
    }

    enum SpecialKeys {
        CTRL,
        ALT,
        SHIFT,
        NONE;

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
}
