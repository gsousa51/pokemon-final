package controller;

import java.awt.CardLayout;
import java.awt.Point;
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
import javax.swing.JPanel;

import interfaceEnumMocks.GameOverOptions;
import model.Game;
import model.Grass;
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

	// Main method - RUNNER
	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
	}

	public GameFrame() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// this.setLayout(new CardLayout());
		promptUserRestoreGame();
		this.setSize(650, 600);
		inBattle = false;
		// game = new Game();
		// MapPanel mapPanel = new MapPanel(game,this);
		// step = new StepCountPanel(game);
		this.mapPanel = new MapPanel(game, this);
		this.battlePanel = new BattleScenePanel(game, this);
		this.step = new InfoPanel(game);

		mapPanel.setLocation(0, 0);
		battlePanel.setLocation(0, 0);

		// this.add(mapPanel);
		this.add(step);
		this.add(mapPanel);
		currentPanel = mapPanel;
		step.setLocation(505, 0);
		// this.add((currentPanel));

		this.setTitle("Pokemon Safari Zone");
		this.setVisible(true);
		this.addWindowListener(new GameExitEvent());
	}

	// Asks user to start from previously saved game state
	private void promptUserRestoreGame() {

		// prompt user to start with previous state
		int userInput = JOptionPane.showConfirmDialog(null, "Start with previously saved game state?",
				"Pokemon Safari Zone", JOptionPane.YES_NO_OPTION);
		// Start new game
		if (userInput == JOptionPane.NO_OPTION) {

			// TODO: Change one to the choice of the user for which map to use
			// 1 = map 1, anything else = map 2
			this.game = new Game(1, GameOverOptions.NO_BALL);

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

	public void switchPanels() {
		if (currentPanel.equals(mapPanel)) {
			inBattle = true;
			System.out.println("Switched panel... or tried to");
			currentPanel = battlePanel;
			this.remove(mapPanel);
			this.add(battlePanel);
			battlePanel.repaint();

		} else {
			inBattle = false;
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

	// TODO refactor this so as to catch all end-of-game conditions for
	// Iteration 2
	public void walkEnded() {
		Point trainerPos = game.getTrainerPosition();
		step.reset();
		repaint();
		if (game.gameOver()) {
			/*
			 * End the game.
			 */
			this.setTitle("Pokemon Safari Zone - Game Over");
			JOptionPane.showMessageDialog(null, "You have no more steps - Game over", "Game Over",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
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
		}

	}

	// When user closes window, inquire if they wish to save the game to play
	// in the future
	private class GameExitEvent extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent event) {
			if (!inBattle) {
				int userInput = JOptionPane.showConfirmDialog(null, "Would you like to save this game?",
						"Pokemon Safari Zone", JOptionPane.YES_NO_OPTION);
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
						// let the playlist know that we closed the window but
						// saved
						// the music.
						// juke.getSongQueue().userClosedWindow();
						// output.writeObject(juke);
						// output.writeObject(queue);
						// output.writeObject(accounts);
						// output.writeObject(GameFrame.this.mapPanel);
						// output.writeObject(GameFrame.this.step);
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
}
