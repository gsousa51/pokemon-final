package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import InterfacesAndEnums.GameInterface;

public class StepCountPanel extends JPanel {

	GameInterface game;
	JLabel stepLabel;

	
	public StepCountPanel(GameInterface game){
		this.setSize(150,500);
		stepLabel = new JLabel("Steps Left : "+ game.getRemaingSteps());
	//	stepField = new JTextField(game.getRemaingSteps());
		this.add(stepLabel);
		

		
		this.game = game;
	}
	
	public void reset(){
		stepLabel.setText("Steps Left : " + String.valueOf(game.getRemaingSteps()));
	}
}
