package nz.ac.wgtn.swen225.lc.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Controller for the app
 */
public class AppController implements KeyListener {
    private Map<Shortcut, Runnable> keyMap;

    public AppController(AppFrame appFrame, App app){
        appFrame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        keyMap.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getSpecialKey().matches(e))
                .filter(entry -> entry.getKey().keyEventMatches(e))
                .forEach(entry -> entry.getValue().run());
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    public void setKeyMap(Map<Shortcut, Runnable> keyMap) {
        this.keyMap = keyMap;
    }
}
