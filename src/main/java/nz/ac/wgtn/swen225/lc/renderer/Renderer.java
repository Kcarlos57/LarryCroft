package nz.ac.wgtn.swen225.lc.renderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
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
    
    private boolean isLightMode = true;//boolean flag to track the current mode (light or dark)
    private boolean isSoundPlaying = false;
    private String[] soundtracks = {"/music/Itty Bitty 8 Bit.wav", "/music/8bit Dungeon Level.wav"};
    private int currentSoundtrackIndex = 0;
	private Thread soundThread;
    
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
        startSoundtrack(soundtracks[currentSoundtrackIndex]);

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
        
        // Set the background color based on the current mode
        if (isLightMode) {
            setBackground(Color.WHITE);
            g.setColor(Color.BLACK);
        } else {
            setBackground(Color.BLACK);
            g.setColor(Color.WHITE);
        }
        
        // Calculate tile size based on the grid size
        int tileSize = Math.min(getWidth() / TileWidth, getHeight() / TileHeight);

        for (int y = 0; y < level.getHeight(); y++) {
            for (int x = 0; x < level.getWidth(); x++) {
                Tile tile = tiles[x][y];  // Get the tile from data
                if (tile != null) {
                    // Load the tile image

                    Image tileImage = loadImage(tile.getTileImageReference());



                    // Draw the tile image at the specified position
                    g.drawImage(tileImage, x * tileSize, y * tileSize, tileSize, tileSize, null);
                    if(tile.hasItem()) tileImage = loadImage(tile.getItem().getTileImageReference());
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
        
        // Create a button to open the settings dialog
        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createSettingsDialog(); // Show the settings dialog when the button is clicked
            }
        });
        
        contentPanel.add(this, BorderLayout.CENTER);
        contentPanel.add(sidebar, BorderLayout.SOUTH);

        // Add the settings button to the sidebar
        sidebar.add(settingsButton);
        
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

        // Add buttons to the sidebar
        sidebar.add(resetButton);

        return sidebar;
    }
    
    private void createSettingsDialog() {
        JDialog settingsDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(this), "Settings", Dialog.ModalityType.MODELESS);

        // Create components for your settings dialog
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

        // Add a button to toggle light/dark mode
        JButton modeToggleButton = new JButton("Light/Dark Mode");
        modeToggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Toggle the mode when the button is clicked
                isLightMode = !isLightMode;
                repaint(); // Repaint the panel to apply the new mode
            }
        });

        // Add buttons for other settings as needed
        JButton soundButton = new JButton("Switch Soundtrack");
        soundButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle sound settings when the button is clicked
                stopSoundtrack(); // Stop the current soundtrack
                currentSoundtrackIndex = (currentSoundtrackIndex + 1) % soundtracks.length; // Cycle to the next soundtrack
                startSoundtrack(soundtracks[currentSoundtrackIndex]); // Start the new soundtrack
            }
        });

        // Add the buttons to the settings panel
        settingsPanel.add(modeToggleButton);
        settingsPanel.add(soundButton);

        // Add the settings panel to the dialog
        settingsDialog.add(settingsPanel);

        // Pack and display the dialog
        settingsDialog.pack();
        settingsDialog.setLocationRelativeTo(null);
        settingsDialog.setVisible(true);
    }

    private void startSoundtrack(String soundtrackFilePath) {
        // Check if sound is already playing
        if (isSoundPlaying) {
            return;
        }

        isSoundPlaying = true;

        // Create a new thread to play the sound
        soundThread = new Thread(() -> {
            try {
                // Obtain an audio input stream from the audio file
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundtrackFilePath));

                // Get the format of the audio data
                AudioFormat audioFormat = audioInputStream.getFormat();

                // Create a data line info object for the SourceDataLine (used for playback)
                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);

                // Open the data line using the data line info
                SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open(audioFormat);

                // Start the data line
                sourceDataLine.start();

                // Create a buffer for reading audio data
                int bufferSize = 4096;
                byte[] buffer = new byte[bufferSize];

                int bytesRead;
                while ((bytesRead = audioInputStream.read(buffer, 0, bufferSize)) != -1) {
                    // Check if sound was stopped by the user
                    if (!isSoundPlaying) {
                        break;
                    }
                    // Write audio data to the data line for playback
                    sourceDataLine.write(buffer, 0, bytesRead);
                }

                // Close the data line and audio input stream when done
                sourceDataLine.drain();
                sourceDataLine.close();
                audioInputStream.close();

                isSoundPlaying = false; // Sound playback finished
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        soundThread.start(); // Start the sound thread
    }


    private void stopSoundtrack() {
        // Set the flag to stop sound playback
        isSoundPlaying = false;

        // Interrupt the sound thread to stop playback
        if (soundThread != null) {
            soundThread.interrupt();
            try {
                soundThread.join(); // Wait for the sound thread to finish
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
