package controller;



import javax.swing.JFrame;

import interfaceEnumMocks.GameInterface;
import model.Game;
import view.MapPanel;
import view.StepCountPanel;

//import InterfacesAndEnums.GameInterface;

/*
 * frame will be used as the main JFrame to coordinate everything for the game.
 */
public class GameFrame extends JFrame{


	public static void main(String[] args){
		GameFrame frame = new GameFrame();
	}

	private StepCountPanel step;
	private Game game;
	public GameFrame(){
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(650, 500);
			game = new Game();
			MapPanel mapPanel = new MapPanel(game,this);
			step = new StepCountPanel(game);
			this.add(step);
			step.setLocation(500, 0);
			this.add((mapPanel));
			this.setVisible(true);
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



	
}
