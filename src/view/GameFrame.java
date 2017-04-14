package view;



import javax.swing.JFrame;

import interfaceEnumMocks.GameInterface;

//import InterfacesAndEnums.GameInterface;

/*
 * frame will be used as the main JFrame to coordinate everything for the game.
 */
public class GameFrame extends JFrame{


	public static void main(String[] args){
		GameFrame frame = new GameFrame();
	}

	private StepCountPanel step;
	
	public GameFrame(){
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setSize(650, 500);
			GameInterface game = new MockGame();
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
		//game.checkIfGameOver()
		/*
		 * Should have a check here to see if the user is out of steps.
		 * Granted, this matter if the end of game condition isn't based on steps.
		 * But for Iteration 1, should have check here.
		 */
	}



	
}
