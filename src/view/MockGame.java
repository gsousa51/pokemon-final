package view;

import java.awt.Point;

import InterfacesAndEnums.Direction;
import InterfacesAndEnums.GameInterface;
import InterfacesAndEnums.MapObject;

public class MockGame implements GameInterface {

	public class replaceWithMapObject{
		boolean canWalk;
		
		public replaceWithMapObject(Boolean canWalk){
			this.canWalk = canWalk;
		}
		public boolean isWalkable(){
			return canWalk;
		}
		
	}
	Point trainerPosition;
	//MapObject[][] map ;
	replaceWithMapObject[][] map;
	public MockGame(){
		trainerPosition = new Point(14,15);
		map  = new replaceWithMapObject[30][30];
		makeMapOne();
	}

	private void initializeMap(){
		for(int r=0 ; r<30; r++){
			for(int c =0; c < 30; c++){
				if(c%2==0){
					if(r%2==0){
						map[r][c] = new replaceWithMapObject(true);
					}
					else{
						map[r][c] = new replaceWithMapObject(true);
					}
				}
				else{
					if(r%2==0){
						map[r][c] = new replaceWithMapObject(true);
					}
					else{
						map[r][c] = new replaceWithMapObject(true);
					}
				}
			}
		}
		//make x of rocks.
		int row, col;
		row = col = 10;
		while(row<20){
			map[row][col] = new replaceWithMapObject(false);
			row++;
			col++;
		}
		row = 20;
		col = 10;
		while(row>10){
			map[row][col] = new replaceWithMapObject(false);
			row --;
			col ++;
		}
	}
	
	/*
	 * The exact map that Morgan used in model.
	 */
	 private void makeMapOne()
	    {
	        for (int r = 0; r < 30; r++)
	        {
	            for (int c = 0; c < 30; c++)
	            {
	            	//ground
	                map[r][c] = new replaceWithMapObject(true);
	            }
	        }
	        
	        for(int r = 0; r < 15; r++)
	        {
	        	//rocks
	            map[r][10] = new replaceWithMapObject(false);
	        }
	        
	        for(int r = 15; r < 30; r++)
	        {
	        	//rocks
	            map[r][25] = new replaceWithMapObject(false);
	        }
	        
	        for(int r = 0; r < 7; r++)
	        {
	            for(int c = 0; c < 7; c++)
	            {
	            	//grass
	                map[r][c] = new replaceWithMapObject(true);
	            }
	        }
	    }
	@Override
	public MapObject[][] getMap() {
		return new MapObject[2][2];
	}

	public replaceWithMapObject[][] getMap2(){
		return map;
	}
	

	@Override
	public Point getPlayerPosition() {
		return null;
	}

	@Override
	public Point getTrainerPosition() {
		return trainerPosition;
	}

	@Override
	public int getRemaingSteps() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void moveTrainer(Direction dir) {
		if(dir.equals(Direction.NORTH)){
			trainerPosition.y-=1;
		}
		else if(dir.equals(Direction.SOUTH)){
			trainerPosition.y+=1;
		}
		else if(dir.equals(Direction.EAST)){
			trainerPosition.x+=1;
		}
		else{
			trainerPosition.x-=1;
		}
	}
}
