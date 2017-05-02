package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameFrame;

import model.Game;

public class PokemonAndItemPanel extends JPanel {

	// Instance variables
	private static final long serialVersionUID = 1L;
	private Game game;
	private GameFrame container;


	// Constructor
	public PokemonAndItemPanel(Game game, GameFrame container) {

		this.game = game;
		this.container = container;
		displayPanel();
	}

	private void displayPanel() {
        
        JFrame frame = new JFrame();

		frame.setSize(800, 800);
		frame.setLayout(null);
        frame.setVisible(true);
        frame.add(this);

		repaint();
	}
}
