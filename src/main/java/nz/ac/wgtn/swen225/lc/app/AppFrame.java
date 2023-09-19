package nz.ac.wgtn.swen225.lc.app;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private App app;
    private AppController controller;

    public AppFrame(String title, App app, Dimension size){
        super(title);
        setSize(size);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.app = app;
        controller = new AppController(this,app);
    }


}
