package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.Domain;
import nz.ac.wgtn.swen225.lc.recorder.Recorder;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller for the app
 */
public class AppController implements KeyListener {
    private Map<Shortcut, Runnable> keyMap;
    private static App app;

    public AppController(AppFrame appFrame, App app){
        appFrame.addKeyListener(this);
        this.app = app;
        this.keyMap = new HashMap<>();
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

    /**
     * Adds a map of shortcuts to the keymap
     *
     * @param keyMap the map of shortcuts to add
     */
    public void addToKeyMap(Map<Shortcut, Runnable> keyMap) {
        this.keyMap.putAll(keyMap);
    }

    public static void movePlayer(Domain.Direction direction) {
        if (app == null) {
            System.out.println("App is null");
            return;
        }

        Recorder.addAction("Player moved " + direction);
        app.domain.TryMoveLarry(direction);
        app.UpdateRenderer();
    }

    /**
     * Loads a level
     *
     * @param levelNum The level number to load
     */
    public void loadLevel(int levelNum) {
        if (levelNum == -1) {
            // Load from file
            // TODO: implement
            return;
        }

        //Recorder.addAction("Level " + i + " loaded"); TODO
        System.out.println("Level " + levelNum + " loaded");
        app.domain.LoadLevel(levelNum);
    }

    public void saveCurrentLevel() {
        Recorder.addAction("Level saved");
    }

    public void quit() {
        System.out.println("Quitting");
    }

    public void pause() {
        System.out.println("Pausing");
    }

    public void resume() {
        System.out.println("Resuming");
    }
}