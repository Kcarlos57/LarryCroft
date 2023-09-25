package nz.ac.wgtn.swen225.lc.renderer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

import nz.ac.wgtn.swen225.lc.domain.*;
import nz.ac.wgtn.swen225.lc.domain.tiles.*;

public class Renderer extends JPanel {
    private Domain domain;
    private Level level;
    private Tile[][] tiles;
    private Actor player;
    
    final int TileHeight = 9;
    final int TileWidth = 9;    

    
    public Renderer() {
        // Jonathan - Because of circular dependancies constructor cannot get links to other modules

    }

    /**
     * Initlizes the module
     */
    public void Setup(){
        this.level = domain.getCurrentLevel();
        this.player = level.getLarry();

        initializeGameBoard();
        createStartingWindow();

        setLayout(new GridLayout(11, 11, 1, 1));
        updateRenderer();
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

        for (int y = 0; y < level.getHeight(); y++) {
            for (int x = 0; x < level.getWidth(); x++) {
                Tile tile = tiles[x][y];  // Get the tile from data
                if (tile != null) {
                    // Load the tile image

                    Image tileImage = loadImage(tile.getTileImageReference());

                    if(tile.hasItem()) tileImage = loadImage(Renderer.class.getResource("/Tiles/KeyTile.png"));

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
        JFrame frame = new JFrame("Chip's Challenge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        return frame;
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
                // You can start a new game or perform any other relevant actions here
            }
        });
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
        JButton menuButton = new JButton("Menu");
        JButton resetButton = new JButton("Reset");
        JButton soundButton = new JButton("Sound");
        // Add more buttons as needed

        // Add action listeners to the buttons
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle menu button click
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle reset button click
                int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset the game?", "Reset Game", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    //resetGame(); // Implement the resetGame method to reset the game state
                }
            }
        });

        soundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle sound button click
            }
        });

        // Add buttons to the sidebar
        sidebar.add(menuButton);
        sidebar.add(resetButton);
        sidebar.add(soundButton);
        // Add more buttons as needed

        return sidebar;
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
