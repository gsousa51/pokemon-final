package view;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaceEnumMocks.GameInterface;

//import InterfacesAndEnums.GameInterface;

public class StepCountPanel extends JPanel {

	GameInterface game;
	JLabel stepLabel;

	
	public StepCountPanel(GameInterface game){
		this.setSize(150,500);
		this.setBackground(Color.white);
		stepLabel = new JLabel("Steps Left : "+ game.getRemainingSteps());
		this.add(stepLabel);
		this.game = game;
	}
	
	public void reset(){
		stepLabel.setText("Steps Left : " + String.valueOf(game.getRemainingSteps()));
	}
}
