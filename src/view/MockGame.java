package view;

import java.awt.Point;

import InterfacesAndEnums.Direction;
import InterfacesAndEnums.GameInterface;
import InterfacesAndEnums.MapObject;

public class MockGame implements GameInterface {

	Point trainerPosition;
	//MapObject[][] map ;
	char[][] map;
	public MockGame(){
		trainerPosition = new Point(15,15);
		map  = new char[30][30];
		initializeMap();
	}
	
	private void initializeMap(){
		for(int r=0 ; r<19; r++){
			for(int c =0; c < 19; c++){
				if(c%2==0){
					if(r%2==0){
						map[r][c] = 'g';
					}
					else{
						map[r][c]= 'h';
					}
				}
				else{
					if(r%2==0){
						map[r][c] = 'h';
					}
					else{
						map[r][c] = 'g';
					}
				}
			}
		}
		//make x of rocks.
		int row, col;
		row = col = 10;
		while(row<20){
			map[row][col] = 'r';
			row++;
			col++;
		}
		row = 20;
		col = 10;
		while(row>10){
			map[row][col] = 'r';
			row --;
			col ++;
		}
	}
	@Override
	public MapObject[][] getMap() {
		// TODO Auto-generated method stub
		return null;
	}
	public char[][] getMapC(){
		return map;
	}
	

	@Override
	public Point getPlayerPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getTrainerPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRemaingSteps() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveTrainer(Direction dir) {
		// TODO Auto-generated method stub
		
	}

}
