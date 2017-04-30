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
	GameOverOptions gameOverCondition;

	public InfoPanel(Game game) {

		this.setSize(150, 500);
		this.setBackground(Color.white);
		gameOverCondition = game.getGameOverCondition();
		stepLabel = new JLabel("Steps Left : " + game.getRemainingSteps());

		pokemonCount = new JLabel("Caught : " + game.getPokemonCount());
		this.add(stepLabel);
		this.add(pokemonCount);
		this.game = game;
	}

	public void reset() {

		stepLabel.setText("Steps Left : " + game.getRemainingSteps());

		pokemonCount.setText("Caught : " + String.valueOf(game.getPokemonCount()));
		repaint();
	}
}