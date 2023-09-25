package nz.ac.wgtn.swen225.lc.app;

import nz.ac.wgtn.swen225.lc.domain.Domain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class AppFrame extends JFrame {
    private final App app;
    private final AppController controller;

    public AppFrame(String title, App app, Dimension size){
        super(title);
        setSize(size);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.app = app;
        controller = new AppController(this,app);

        addMovementShortcuts();
        addApplicationShortcuts();

        /*

CTRL-X  - exit the game, the current game state will be lost, the next time the game is started, it will resume from the last unfinished level
CTRL-S  - exit the game, saves the game state, game will resume next time the application will be started
CTRL-R  - resume a saved game -- this will pop up a file selector to select a saved game to be loaded
CTRL-1 - start a new game at level 1
CTRL-2 - start a new game at level 2
SPACE - pause the game and display a “game is paused” dialog
ESC - close the “game is paused” dialog and resume the game
UP, DOWN, LEFT, RIGHT ARROWS -- move Chap within the maze


         */
    }

    /**
     * Adds all the shortcuts related to movement
     */
    public void addMovementShortcuts(){
        //TODO: implement
        controller.addToKeyMap(Map.of(
                new Shortcut("Move up")
                        .setKey(KeyEvent.VK_UP)
                        .setSpecialKey(Shortcut.SpecialKeys.NONE),
                () -> AppController.movePlayer(Domain.Direction.Up),
                new Shortcut("Move down")
                        .setKey(KeyEvent.VK_DOWN)
                        .setSpecialKey(Shortcut.SpecialKeys.NONE),
                () -> AppController.movePlayer(Domain.Direction.Down),
                new Shortcut("Move left")
                        .setKey(KeyEvent.VK_LEFT)
                        .setSpecialKey(Shortcut.SpecialKeys.NONE),
                () -> AppController.movePlayer(Domain.Direction.Left),
                new Shortcut("Move right")
                        .setKey(KeyEvent.VK_RIGHT)
                        .setSpecialKey(Shortcut.SpecialKeys.NONE),
                () -> AppController.movePlayer(Domain.Direction.Right)
        ));
    }

    /**
     * Adds all the other shortcuts not related to movement
     */
    public void addApplicationShortcuts(){
        controller.addToKeyMap(Map.of(
                new Shortcut("Save")
                        .setKey(KeyEvent.VK_S)
                        .setSpecialKey(Shortcut.SpecialKeys.CTRL),
                () -> controller.saveCurrentLevel(),
                new Shortcut("Load")
                        .setKey(KeyEvent.VK_L)
                        .setSpecialKey(Shortcut.SpecialKeys.CTRL),
                () -> controller.loadLevel(-1),
                new Shortcut("Quit")
                        .setKey(KeyEvent.VK_X)
                        .setSpecialKey(Shortcut.SpecialKeys.CTRL),
                () -> controller.quit(),
                new Shortcut("Pause")
                        .setKey(KeyEvent.VK_SPACE)
                        .setSpecialKey(Shortcut.SpecialKeys.NONE),
                () -> controller.pause(),
                new Shortcut("Resume")
                        .setKey(KeyEvent.VK_ESCAPE)
                        .setSpecialKey(Shortcut.SpecialKeys.NONE),
                () -> controller.resume(),
                new Shortcut("Start level 1")
                        .setKey(KeyEvent.VK_1)
                        .setSpecialKey(Shortcut.SpecialKeys.CTRL),
                () -> controller.loadLevel(1),
                new Shortcut("Start level 2")
                        .setKey(KeyEvent.VK_2)
                        .setSpecialKey(Shortcut.SpecialKeys.CTRL),
                () -> controller.loadLevel(2),
                new Shortcut("Print recording")
                        .setKey(KeyEvent.VK_R)
                        .setSpecialKey(Shortcut.SpecialKeys.CTRL),
                Recorder::printActions
        ));
    }


}
