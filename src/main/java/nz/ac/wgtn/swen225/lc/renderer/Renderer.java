package nz.ac.wgtn.swen225.lc.renderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import nz.ac.wgtn.swen225.lc.app.App;
import nz.ac.wgtn.swen225.lc.app.AppController;
import nz.ac.wgtn.swen225.lc.app.AppFrame;
import nz.ac.wgtn.swen225.lc.domain.*;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;

public class Renderer extends JPanel {
    private Domain domain;
    private Level level;
    private Tile[][] tiles;
    private Actor player;
    
    final int TileHeight = 11;
    final int TileWidth = 11;    

    
    public Renderer() {
        // Jonathan - Because of circular dependancies constructor cannot get links to other modules

    }

    /**
     * Initlizes the module
     */
    public JPanel Setup(){
        this.level = domain.getCurrentLevel();
        this.player = level.getLarry();

        initializeGameBoard();
        createStartingWindow();

        setLayout(new GridLayout(TileHeight, TileWidth, 1, 1));
        updateRenderer();
        return this;
    }

    private void initializeGameBoard() {
        // Create a 2D array of Tiles to represent the game board

        tiles = level.getTiles();
    }

    private void createStartingWindow() {
        // Create the main JFrame
        JFrame frame = createMainFrame();

        if (frame != null) {
            // Create the content panel with game board and sidebar
            JPanel contentPanel = createContentPanel();

            if (contentPanel != null) {
                // Create and set the menu bar
                JMenuBar menuBar = createMenuBar();
                if (menuBar != null) {
                    frame.setJMenuBar(menuBar);
                }

                // Add the content panel to the JFrame
                frame.add(contentPanel);

                // Center and make the window visible
                centerAndShowFrame(frame);
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate tile size based on the grid size
        int tileSize = Math.min(getWidth() / TileWidth, getHeight() / TileHeight);

        if(level==null) return;

        for (int y = 0; y < level.getHeight(); y++) {
            for (int x = 0; x < level.getWidth(); x++) {
                Tile tile = tiles[x][y];  // Get the tile from data
                if (tile != null) {
                    // Load the tile image

                    Image tileImage = loadImage(tile.getTileImageReference());

                    if(tile.hasItem()) tileImage = loadImage(tile.getItem().getTileImageReference());

                    // Draw the tile image at the specified position
                    g.drawImage(tileImage, x * tileSize, y * tileSize, tileSize, tileSize, null);
                }
            }
        }

        g.drawImage(loadImage(player.getImageReference()), player.getPosition().getX() * tileSize, player.getPosition().getY() * tileSize, tileSize,tileSize,null);


        /*for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 11; col++) {
                Tile tile = tiles[row][col];  // Get the tile from data
                if (tile != null) {
                    // Load the tile image

                    Image tileImage = loadImage(tile.getTileImageReference());

                    // Draw the tile image at the specified position
                    g.drawImage(tileImage, col * tileSize, row * tileSize, tileSize, tileSize, null);
                }
            }
        }*/
    }


    private Image loadImage(URL imageUrl) {
        try {
            if (imageUrl != null) {
                return ImageIO.read(imageUrl);
            } else {
                // Handle case where imageUrl is null
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle image loading error
            return null;
        }
    }


    private JFrame createMainFrame() {
        return App.getFrame();
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel sidebar = createSidebar();
        contentPanel.add(this, BorderLayout.CENTER);
        contentPanel.add(sidebar, BorderLayout.SOUTH);
        return contentPanel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle new game menu item click
            	int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to begin again?", "Back to Level 1.", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                	String message = "TODO: This should put you back in Level 1, if not the start menu.";
                    showMessageDialog("New Game", message);
                }
            }
        });
        JMenuItem AboutItem = new JMenuItem("About");
        AboutItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String message = "Project by: TEAM 27 A.K.A Team Epic Saucy Swaglords.\n"
        				+ "Domain: Strongjona\n"
        				+ "App: burkehami\n"
        				+ "Renderer: carloskhal\n"
        				+ "Persistency: Hyewon\n"
        				+ "Recorder: chambenath\n\n\n"
        				+ "Description: This is a GUI adventure game where the player is Larry Croft, a treasure hunter. \nThe player will be able to move around the map, collect items, and interact with the environment. The player will be able to save and load their progress.";
                showMessageDialog("New Game", message);
        	}
        });
        fileMenu.add(AboutItem);
        fileMenu.add(newGameMenuItem);
        menuBar.add(fileMenu);
        return menuBar;
    }

    private void centerAndShowFrame(JFrame frame) {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.X_AXIS));

        // Create buttons for the sidebar
        JButton resetButton = new JButton("Reset");
        JButton soundButton = new JButton("Sound");
        // Add more buttons as needed

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle reset button click
                int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the game?", "Reset Level", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                	String message = "TODO: Have to implement ResetLevel(), reset the specific level.";
                    showMessageDialog("Reset", message);
                }
            }
        });

        soundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle sound button click
            	String message = "TODO: The sound button is used to control game sound settings. Will probably have a slider + ON/OFF";
                showMessageDialog("Sound", message);
            }
        });

        // Add buttons to the sidebar
        sidebar.add(resetButton);
        sidebar.add(soundButton);
        // Add more buttons as needed

        return sidebar;
    }
    
    // Helper method to show a message dialog
    private void showMessageDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
    
    public void updateRenderer() {
        repaint();
    }

    /**
     * Sets the references between modules
     * @param domain
     */
    public void SetModuleLinks(Domain domain){
        this.domain = domain;
    }
    
    
}
