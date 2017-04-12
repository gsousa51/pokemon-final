package view;

import javax.swing.JPanel;

import InterfacesAndEnums.GameInterface;
import InterfacesAndEnums.MapObject;

public class MapPanel extends JPanel {

	GameInterface game;
	MapObject[][] map;
	
	
	public MapPanel(GameInterface game){
		this.game = game;
		map = game.getMap();
	}
}
