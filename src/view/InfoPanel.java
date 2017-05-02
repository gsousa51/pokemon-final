/**
 * Class: InfoPanel.java
 * Purpose: Used for displaying steps left and count of pokemon caught.
 * 
 */

package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaceEnumMocks.GameOverOptions;
import model.Game;

//import InterfacesAndEnums.GameInterface;

public class InfoPanel extends JPanel {

	Game game;
	JLabel stepLabel;
	JLabel pokemonCount;
	JLabel safariBallCount;

	public InfoPanel(Game game) {

		this.game = game;
		this.setSize(150, 500);
		this.setBackground(Color.white);
		//Set the text for our labels.
		stepLabel = new JLabel("Steps Left : " + game.getRemainingSteps());
		pokemonCount = new JLabel("Caught : " + game.getPokemonCount());
		safariBallCount = new JLabel("Safari Balls : " + game.getTrainersItems().getItemCount("Safari Ball"));
		//Add them to the game.
		this.add(stepLabel);
		this.add(pokemonCount);
        this.add(safariBallCount);

	}

	/**
	 * Method called by our Controller after every step.
	 */
	public void reset() {
		//Reset the values for the labels in case something has changed.
		stepLabel.setText("Steps Left : " + game.getRemainingSteps());
		pokemonCount.setText("Caught : " + String.valueOf(game.getPokemonCount()));
		safariBallCount.setText("Safari Balls : " + game.getTrainersItems().getItemCount("Safari Ball"));
		repaint();
	}
}
