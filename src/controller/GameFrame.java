package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Game;

import view.MapPanel;
import view.StepCountPanel;

//import InterfacesAndEnums.GameInterface;

/*
 * frame will be used as the main JFrame to coordinate everything for the game.
 */
public class GameFrame extends JFrame{

    // Instance variables
    private StepCountPanel step;
    private Game game;
    private MapPanel mapPanel;

    // Main method - RUNNER
    public static void main(String[] args){
        GameFrame frame = new GameFrame();
    }


    public GameFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        promptUserRestoreGame();
        this.setSize(650, 500);
        game = new Game();
        MapPanel mapPanel = new MapPanel(game,this);
        step = new StepCountPanel(game);
        this.add(step);
        step.setLocation(500, 0);
        this.add((mapPanel));
        this.setVisible(true);
        this.addWindowListener(new GameExitEvent());
    }


    // TODO fill in this method body for pokemon
    private void promptUserRestoreGame() {
        int userInput = JOptionPane.showConfirmDialog(null, "Start with previously saved game state?");
        if (userInput == JOptionPane.NO_OPTION) {

            // juke = new Jukebox();
            // this.game = new Game();
            this.game = new Game();
            this.mapPanel = new MapPanel(game,this);
            this.step = new StepCountPanel(game);
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
                //      StepCountPanel
                //      Game
                //      MapPanel
                //this.step = (StepCountPanel) input.readObject();
                this.game = (Game) input.readObject();
                //this.mapPanel = (MapPanel) input.readObject();
                // This call gets the playlist to begin playing automatically
                //juke.getSongQueue().userRestartedSavedJukebox();
                //input.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void walkEnded(){
        step.reset();
        repaint();
        if(game.gameOver()){
            /*
             * Insert asking if they want to save game logic here.
             * End the game.
             */
        }
    }


    // When user closes window, inquire if they wish to save the game to play
    // in the future
    private class GameExitEvent extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent event) {

            int userInput = JOptionPane.showConfirmDialog(null, "Would you like to save this game?");
            if (userInput == JOptionPane.YES_OPTION) {
                FileOutputStream stream = null;
                ObjectOutputStream output = null;

                try {
                    stream = new FileOutputStream("PREVIOUSLY_SAVED_GAME.bin");
                } 

                catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                try {
                    output = new ObjectOutputStream(stream);
                } 

                catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

                try {
                    // let the playlist know that we closed the window but saved
                    // the music.
                    //juke.getSongQueue().userClosedWindow();
                    //output.writeObject(juke);
                    // output.writeObject(queue);
                    // output.writeObject(accounts);
                    output.writeObject(GameFrame.this.game);

                    // TODO debug delete
                    System.out.println("Game state saved");
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }
}
