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
    
    char[][] exampleBoard = {
            {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'},
            {'W', 'P', 'E', 'E', 'E', 'E', 'E', 'E', 'W'},
            {'W', 'E', 'W', 'E', 'W', 'E', 'X', 'X', 'W'},
            {'W', 'E', 'E', 'E', 'W', 'E', 'E', 'E', 'W'},
            {'W', 'E', 'W', 'E', 'W', 'W', 'W', 'E', 'W'},
            {'W', 'E', 'E', 'E', 'E', 'E', 'E', 'E', 'W'},
            {'W', 'E', 'W', 'W', 'W', 'W', 'W', 'E', 'W'},
            {'W', 'E', 'E', 'E', 'E', 'E', 'E', 'K', 'W'},
            {'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'}
        };
    
    public Renderer() {//should pass Domain here
    	initializeGameBoard();
    	createStartingWindow();
    	//getDomain(d);
    	//getLevel();
    	//getPlayer(); Uncomment these when Persistency and Levels are live.
    	
    	 // Set the layout manager to a 9x9 GridLayout
        setLayout(new GridLayout(9, 9, 1, 1));

        updateRenderer();
    }
/*
    private void getDomain(Domain d) {
    	if (d == null) {throw new IllegalStateException("Domain passed shouldn't be null.");}
    	this.domain = d;
    }    
    private void getLevel() {
    	if (domain == null) {throw new IllegalStateException("Domain stored shouldn't be null.");}
    	this.level = domain.getCurrentLevel();
    }
    private void getPlayer() {
    	if (level == null) {throw new IllegalStateException("Level shouldn't be null.");}
    	this.player = level.getLarry();
    }
    */
   
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int tileSize = Math.min(getWidth() / TileWidth, getHeight() / TileHeight);  // Calculate tile size based on the grid size

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                Tile tile = tiles[row][col];  // Get the tile from data
                if (tile != null) {
                    // Load the tile image
                    Image tileImage = loadImage(tile.getTileImageReference());

                    // Draw the tile image at the specified position
                    g.drawImage(tileImage, col * tileSize, row * tileSize, tileSize, tileSize, null);
                }
            }
        }
    }

    private Image loadImage(URL imageUrl) {
        try {
            return ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle image loading error
            return null;
        }
    }
    
    private void initializeGameBoard() {
        // Create a 2D array of Tiles to represent the game board

        tiles = new Tile[9][9];

        // Fill the board with some sample tiles, pngs are currently temporary
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
            	char tileType = exampleBoard[row][col];
                switch (tileType) {
                case 'W': // Wall Tile
                    tiles[row][col] = new WallTile();
                    break;
                case 'X': // Exit Tile
                    tiles[row][col] = new ExitTile();
                    break;
                case 'K': // Key Tile
                    tiles[row][col] = new KeyTile();
                    break;
                case 'P': // Player Tile
                    tiles[row][col] = new PlayerTile();
                    break;
                case 'E': //Empty Tile
                	tiles[row][col] = new EmptyTile();
                	break;
                default:
                	
                    break;
                    }
            }
        }
    }

	public void createStartingWindow() {
        // Create a new JFrame for the game window
        JFrame frame = new JFrame("Chip's Challenge");

        // Set the default close operation (what happens when the user closes the window)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window (adjust this to your desired dimensions)
        frame.setSize(800, 600);
        
        // Create a main content panel that holds the game and sidebar
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Create a sidebar panel with buttons
        JPanel sidebar = createSidebar();
        
        // Add your existing instance of Renderer to the content panel
        contentPanel.add(this, BorderLayout.CENTER);
        
        // Add the sidebar to the content panel on the bottom
        contentPanel.add(sidebar, BorderLayout.SOUTH);

        // Add the content panel to the JFrame
        frame.add(contentPanel);

        // Center the window on the screen
        frame.setLocationRelativeTo(null);

        // Make the window visible
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
