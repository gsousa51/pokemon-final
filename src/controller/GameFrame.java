package controller;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import interfaceEnumMocks.GameOverOptions;
import model.Game;
import model.Grass;
import model.Item;
import model.Pokemon;
import view.BattleScenePanel;
import view.InfoPanel;
import view.MapPanel;

//import InterfacesAndEnums.GameInterface;

/*
 * frame will be used as the main JFrame to coordinate everything for the game.
 */
public class GameFrame extends JFrame {

    // Instance variables
    private InfoPanel step;
    private Game game;
    private MapPanel mapPanel;
    private BattleScenePanel battlePanel;
    private JPanel currentPanel;
    private boolean inBattle;
    private Timer transitionTimer;
    private GameOverOptions gameOver;
    private int mapNumber;
    private JButton pokemonViewButton;
    private JButton itemViewButton;
    private Clip gameMusicClip;
    private Clip battleMusicClip;

    // Main method - RUNNER
    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
    }

    public GameFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setLayout(new CardLayout());

        // using absolute locations for placement of elements
        this.setLayout(null);
        promptUserRestoreGame();
        this.setSize(700, 700);
        inBattle = false;
        // game = new Game();
        // MapPanel mapPanel = new MapPanel(game,this);
        // step = new StepCountPanel(game);

        this.battlePanel = new BattleScenePanel(game, this);
        this.step = new InfoPanel(game);

        mapPanel.setLocation(0, 0);
        battlePanel.setLocation(0, 0);

        transitionTimer = new Timer(100, new TransitionListener());
        // this.add(mapPanel);
        this.add(step);
        this.add(mapPanel);
        currentPanel = mapPanel;
        step.setLocation(505, 0);
        // this.add((currentPanel));

        // Allow for opening another panel to view pokemon and items, and use
        // items
        this.pokemonViewButton = new JButton();
        pokemonViewButton.setPreferredSize(new Dimension(75, 50));
        // pokemonViewButton.setSize(new Dimension(75, 50));
        pokemonViewButton.setSize(500, 50);
        pokemonViewButton.setLocation(100, 515);
        pokemonViewButton.setText("View Pokemon and Items");
        pokemonViewButton.addActionListener(new ItemAndPokemonListener());

        this.add(pokemonViewButton);

        // Window adornments
        this.setTitle("Pokemon Safari Zone");
        this.setVisible(true);
        this.addWindowListener(new GameExitEvent());

        // Start Game music - plays in separate thread
        startGameMusic();
    }

    // Asks user to start from previously saved game state
    private void promptUserRestoreGame() {

        // prompt user to start with previous state
        int userInput = JOptionPane.showConfirmDialog(null, "Start with previously saved game state?",
                "Pokemon Safari Zone", JOptionPane.YES_NO_OPTION);
        // Start new game
        if (userInput == JOptionPane.NO_OPTION) {
            Object[] options1 = { "Walk 500 Steps", "Throw 30 Balls", "Catch 15 Pokemon" };
            Object[] options2 = { "Map 1", "Map 2" };
            int gameOverChoice = JOptionPane.showOptionDialog(null, "Choose a Gameover Option!", "Pokemon Safari Zone",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
            // If user chose walk 500 steps
            if (gameOverChoice == JOptionPane.YES_OPTION) {
                gameOver = GameOverOptions.NO_STEPS;
            }
            // User chose to throw 30 balls.
            else if (gameOverChoice == JOptionPane.NO_OPTION) {
                gameOver = GameOverOptions.NO_BALL;
            } else {
                gameOver = GameOverOptions.POKEMON_CAUGHT;
            }

            int mapChoice = JOptionPane.showOptionDialog(null, "Choose Map To Play On!", "Pokemon Safari Zone",
                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options2, null);

            // 1 = map 1, anything else = map 2
            if (mapChoice == JOptionPane.YES_OPTION) {
                mapNumber = 1;
            }
            // User chose map 2
            else {
                mapNumber = 2;
            }
            

            this.game = new Game(mapNumber, gameOver);
            mapPanel = new MapPanel(game, this, mapNumber);
            // this.add(mapPanel);

        }

        else {

            FileInputStream stream = null;
            ObjectInputStream input = null;

            try {
                stream = new FileInputStream("PREVIOUSLY_SAVED_GAME.bin");
            }

            catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                input = new ObjectInputStream(stream);
            }

            catch (IOException e) {
                e.printStackTrace();
            }

            try {
                // juke = (Jukebox) input.readObject();

                // ORDER OF STATE RESTORE:
                // StepCountPanel
                // Game
                // MapPanel
                // this.step = (StepCountPanel) input.readObject();
                this.game = (Game) input.readObject();
                System.out.println("Read in previous game state.");
                // Create a mapPanel, using the map option they chose when they
                // last played the game.
                this.mapPanel = new MapPanel(game, this, game.getMapNumber());
                // this.mapPanel = new MapPanel(game,this);
                // this.battlePanel = new BattleScenePanel(game,this);
                // mapPanel.setLocation(0, 0);
                // battlePanel.setLocation(0,0);
                // this.step = new InfoPanel(game);
                // this.add(mapPanel);

                // this.mapPanel = (MapPanel) input.readObject();
                // This call gets the playlist to begin playing automatically
                // juke.getSongQueue().userRestartedSavedJukebox();
                // input.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Start game battle music in a separate thread
    private void startBattleMusic() {

        Thread musicThread = new Thread(new Runnable() {

            public void run() {

                try {
                    battleMusicClip = AudioSystem.getClip();
                    battleMusicClip.open(AudioSystem.getAudioInputStream(new File("resources/sound/battle_final.wav")));
                    battleMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
                } catch (Exception exc) {
                    exc.printStackTrace(System.out);
                }
            }
        });

        musicThread.start();
    }

    // Start game background music in a separate thread
    private void startGameMusic() {

        Thread musicThread = new Thread(new Runnable() {

            public void run() {

                try {

                    gameMusicClip = AudioSystem.getClip();
                    gameMusicClip.open(AudioSystem.getAudioInputStream(new File("resources/sound/pokemon_safari.wav")));
                    gameMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
                } catch (Exception exc) {
                    exc.printStackTrace(System.out);
                }
            }
        });

        musicThread.start();
    }

    // Stop the background game music
    private void stopGameMusic() {

        gameMusicClip.stop();
    }

    // Stop the background game music
    private void stopBattleMusic() {

        battleMusicClip.stop();
    }

    public void switchPanels() {
        if (currentPanel.equals(mapPanel)) {

            stopGameMusic();

            startBattleMusic();

            inBattle = true;
            System.out.println("Switched panel to battlePanel");
            currentPanel = battlePanel;
            this.remove(mapPanel);
            this.add(battlePanel);
            battlePanel.repaint();

        } else {

            stopBattleMusic();

            startGameMusic();

            inBattle = false;
            System.out.println("Switched panel to mapPanel");
            currentPanel = mapPanel;
            this.remove(battlePanel);
            this.add(mapPanel);
            mapPanel.repaint();
            mapPanel.setFocusable(true);
            mapPanel.requestFocusInWindow();
            mapPanel.setEnabled(true);
            this.setFocusable(false);
        }
        step.setLocation(505, 0);
        step.reset();
        repaint();
    }

    public void walkEnded() {
        Point trainerPos = game.getTrainerPosition();
        // reset our info panel
        step.reset();
        // repaint the frame
        repaint();
        // If the game is over (only would happen if the user is out of steps,
        // here)
        if (game.gameOver()) {

            // Pop our gameOver panel.
            gameOver();
        }
        // Else, check if we're on grass, if we are, check if we found a
        // pokemon.
        else {
            if (game.getMap()[trainerPos.y][trainerPos.x] instanceof Grass) {
                System.out.println("We're in grass");
                Pokemon pokemonAtPosition = game.checkPokemon();
                if (pokemonAtPosition != null) {
                    System.out.println("Saw a " + pokemonAtPosition.toString());
                    switchPanels();
                    battlePanel.setPokemon(pokemonAtPosition);
                    repaint();
                }
            }
            // Else we're on ground, might be an item found
            else {
                Item currItem = game.getMap()[trainerPos.y][trainerPos.x].getItem();
                if (currItem != null) {
                    System.out.println("Found a " + currItem.toString());
                    game.foundItem(currItem);
                    JOptionPane.showMessageDialog(null, "Found a " + currItem.toString() + " !", "Pokemon Safari Zone",
                            JOptionPane.INFORMATION_MESSAGE);
                    game.getMap()[trainerPos.y][trainerPos.x].removeItem();
                } // end if
            } // end else
        }

    }

    // TODO: Implement this.
    // It will be the "Game Summary" that is called whenever the user
    // forfeits the game OR when the user runs of out of steps or balls.

    public void gameOver() {
        this.setTitle("Pokemon Safari Zone - Game Over");
        JOptionPane.showMessageDialog(null, "This game has ended. We still need to show you items and pokemon",
                "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    // When user closes window, inquire if they wish to save the game to play
    // in the future
    private class GameExitEvent extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent event) {
            if (!inBattle && !game.gameOver()) {
                int userInput = JOptionPane.showConfirmDialog(null, "Would you like to save this game?",
                        "Pokemon Safari Zone", JOptionPane.YES_NO_OPTION);
                if (userInput == JOptionPane.YES_OPTION) {
                    FileOutputStream stream = null;
                    ObjectOutputStream output = null;

                    try {
                        stream = new FileOutputStream("PREVIOUSLY_SAVED_GAME.bin");
                    }

                    catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }

                    try {
                        output = new ObjectOutputStream(stream);
                    }

                    catch (IOException e1) {
                        e1.printStackTrace();
                    }

                    try {

                        output.writeObject(GameFrame.this.game);

                    } catch (IOException e1) {

                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    private class TransitionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }


    // Listener for the JButton that shows the user's items and pokemon
    private class ItemAndPokemonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // note the needed setFocusable stuff... if that is not there, clicking
            // the button renders the mapPanel unresponsive

            System.out.println("");
            System.out.println("view pokemon and items");
            // print out all pokemon and items
            for (Pokemon p : GameFrame.this.game.getTrainersPokemon()) {
                System.out.println(p.toString());
                System.out.println(p.getHealth()[0]);
                System.out.println(p.getHealth()[1]);
            }

            for (Item i : GameFrame.this.game.getTrainersItems()) {
                System.out.println(i.toString());
            }
            mapPanel.repaint();
            mapPanel.setFocusable(true);
            mapPanel.requestFocusInWindow();
            mapPanel.setEnabled(true);
            GameFrame.this.setFocusable(false);
        }
    }
}
