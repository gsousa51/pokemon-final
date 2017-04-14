package view;

import java.awt.Point;

import InterfacesAndEnums.Direction;
import InterfacesAndEnums.GameInterface;
import InterfacesAndEnums.MapObject;

public class MockGame implements GameInterface {

	final static int MAP_HEIGHT = 30;
	final static int MAP_WIDTH = 40;
	private int stepsRemaining;
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
	replaceWithMapObject[][] mapGrid;
	public MockGame(){
		trainerPosition = new Point(14,15);
		stepsRemaining = 500;
		mapGrid  = new replaceWithMapObject[MAP_HEIGHT][MAP_WIDTH];
		makeMapOne();
	}
	
//
//	private void initializeMap(){
//		for(int r=0 ; r<30; r++){
//			for(int c =0; c < 30; c++){
//				if(c%2==0){
//					if(r%2==0){
//						map[r][c] = new replaceWithMapObject(true);
//					}
//					else{
//						map[r][c] = new replaceWithMapObject(true);
//					}
//				}
//				else{
//					if(r%2==0){
//						map[r][c] = new replaceWithMapObject(true);
//					}
//					else{
//						map[r][c] = new replaceWithMapObject(true);
//					}
//				}
//			}
//		}
//		//make x of rocks.
//		int row, col;
//		row = col = 10;
//		while(row<20){
//			map[row][col] = new replaceWithMapObject(false);
//			row++;
//			col++;
//		}
//		row = 20;
//		col = 10;
//		while(row>10){
//			map[row][col] = new replaceWithMapObject(false);
//			row --;
//			col ++;
//		}
//	}
//	
    private void makeMapOne()
    {
        // initialize everything to Ground
        for (int r = 0; r < MAP_HEIGHT; r++)
        {
            for (int c = 0; c < MAP_WIDTH; c++)
            {
                mapGrid[r][c] = new replaceWithMapObject(true);
            }
        }

        // make rock borders for whole map to avoid walking off the map
        for (int r = 0; r < MAP_HEIGHT; r++)
        {
            mapGrid[r][0] = new replaceWithMapObject(false);
            mapGrid[r][MAP_WIDTH - 1] = new replaceWithMapObject(false);
        }
        for (int c = 0; c < MAP_WIDTH; c++)
        {
            mapGrid[0][c] = new replaceWithMapObject(false);
            mapGrid[MAP_HEIGHT - 1][c] = new replaceWithMapObject(false);
        }

        // Create vertical rock wall in col 10 from row 0-12
        for (int r = 1; r < 13; r++)
            mapGrid[r][10] = new replaceWithMapObject(false);

        // create horizontal rock wall in row 13 to col 7
        for (int c = 1; c < 8; c++)
            mapGrid[13][c] = new replaceWithMapObject(false);

        // make grass patches in top left corner
        //(it's surrounded by rock walls)
        for (int c = 3; c < 6; c++)
            mapGrid[3][c] = new replaceWithMapObject(true);
        for (int c = 2; c < 7; c++)
            mapGrid[4][c] = new replaceWithMapObject(true);
        for (int c = 1; c < 8; c++)
            mapGrid[5][c] = new replaceWithMapObject(true);
        for (int c = 1; c < 7; c++)
            mapGrid[6][c] = new replaceWithMapObject(true);
        for (int c = 2; c < 6; c++)
            mapGrid[7][c] = new replaceWithMapObject(true);
        for (int c = 3; c < 6; c++)
            mapGrid[8][c] = new replaceWithMapObject(true);
        for (int c = 8; c < 10; c++)
            mapGrid[9][c] = new replaceWithMapObject(true);
        for (int c = 7; c < 9; c++)
            mapGrid[10][c] = new replaceWithMapObject(true);
        for (int c = 6; c < 8; c++)
            mapGrid[11][c] = new replaceWithMapObject(true);
        for (int c = 1; c < 3; c++)
            mapGrid[11][c] = new replaceWithMapObject(true);
        for (int c = 1; c < 4; c++)
            mapGrid[12][c] = new replaceWithMapObject(true);
        
        //add grass on the other side of the wall
        for(int r = 1; r < 5; r++)
        {
            for(int c = 11; c < 15; c++)
                mapGrid[r][c] = new replaceWithMapObject(true);
        }

        // add vertical rock walls, bottom left quadrent
        for (int r = 15; r < MAP_HEIGHT - 4; r++)
            mapGrid[r][15] = new replaceWithMapObject(false);
        for (int r = 25; r < MAP_HEIGHT; r++)
            mapGrid[r][8] = new replaceWithMapObject(false);

        // grass in bottom left corner
        for (int r = 17; r < 20; r++)
            mapGrid[r][14] = new replaceWithMapObject(true);
        for (int c = 4; c < 8; c++)
            mapGrid[MAP_HEIGHT - 2][c] = new replaceWithMapObject(true);
        for (int c = 5; c < 8; c++)
            mapGrid[MAP_HEIGHT - 3][c] = new replaceWithMapObject(true);
        for (int c = 6; c < 8; c++)
            mapGrid[MAP_HEIGHT - 4][c] = new replaceWithMapObject(true);

        for (int c = 1; c < 2; c++)
            mapGrid[MAP_HEIGHT - 4][c] = new replaceWithMapObject(true);
        for (int c = 1; c < 4; c++)
            mapGrid[MAP_HEIGHT - 5][c] = new replaceWithMapObject(true);
        for (int c = 1; c < 6; c++)
            mapGrid[MAP_HEIGHT - 6][c] = new replaceWithMapObject(true);
        for (int c = 1; c < 8; c++)
            mapGrid[MAP_HEIGHT - 7][c] = new replaceWithMapObject(true);

        for (int c = 5; c < 9; c++)
            mapGrid[17][c] = new replaceWithMapObject(true);
        for (int c = 5; c < 7; c++)
            mapGrid[18][c] = new replaceWithMapObject(true);
        for (int c = 1; c < 3; c++)
            mapGrid[19][c] = new replaceWithMapObject(true);
        for (int c = 1; c < 3; c++)
            mapGrid[20][c] = new replaceWithMapObject(true);

        // Wall on top right side
        for (int c = 20; c < MAP_WIDTH; c++)
            mapGrid[6][c] = new replaceWithMapObject(false);
        for (int c = 28; c < 33; c++)
            mapGrid[6][c] = new replaceWithMapObject(true);
        for (int r = 1; r < 5; r++)
            mapGrid[r][30] = new replaceWithMapObject(false);

        // grass top right side, surrounded by the rock
        for (int r = 1; r < 4; r++)
        {
            for (int c = MAP_WIDTH - 5; c < MAP_WIDTH - 1; c++)
                mapGrid[r][c] = new replaceWithMapObject(true);
        }
        
        //grass top in the middle
        for(int c = 20; c < 25; c++)
            mapGrid[1][c] = new replaceWithMapObject(true);
        for(int c = 21; c < 24; c++)
            mapGrid[2][c] = new replaceWithMapObject(true);
        
        
        //Walls in the middle right side
        for(int c = 28; c < MAP_WIDTH-3; c++)
            mapGrid[17][c] = new replaceWithMapObject(false);
        for(int r = 13; r < 18; r++)
            mapGrid[r][28] = new replaceWithMapObject(false);
        
        //Grass middle right side
        for(int r = 10; r < 13; r++)
        {
            for(int c = 31; c < 35; c++)
                mapGrid[r][c] = new replaceWithMapObject(true);
        }
        for(int r = 11; r < 13; r++)
            mapGrid[r][35] = new replaceWithMapObject(true);
        for(int c = 32; c < 34; c++)
            mapGrid[13][c] = new replaceWithMapObject(true);
        for(int c = 31; c < 33; c++)
            mapGrid[9][c] = new replaceWithMapObject(true);
        for(int r = 10; r < 12; r++)
            mapGrid[r][30] = new replaceWithMapObject(true);
        
        //Rocks bottom right corner
        for(int r = 21; r < 27; r++)
            mapGrid[r][30] = new replaceWithMapObject(false);
        
        //Grass bottom right corner
        for(int r = 22; r < 26; r++)
        {
            for(int c = 34; c < 37; c++)
                mapGrid[r][c] = new replaceWithMapObject(true);
        }
        for(int r = 23; r<25; r++)
            mapGrid[r][33] = new replaceWithMapObject(true);
        for(int r = 25; r<28; r++)
            mapGrid[r][37] = new replaceWithMapObject(true);
        mapGrid[26][36] = new replaceWithMapObject(true);

        
        //grass middle bottom
        for(int c = 19; c < 25; c++)
            mapGrid[MAP_HEIGHT-2][c] = new replaceWithMapObject(true);
        for(int c = 19; c < 24; c++)
            mapGrid[MAP_HEIGHT-3][c] = new replaceWithMapObject(true);
        for(int c = 18; c < 25; c++)
            mapGrid[MAP_HEIGHT-4][c] = new replaceWithMapObject(true);
        for(int c = 20; c < 23; c++)
            mapGrid[MAP_HEIGHT-5][c] = new replaceWithMapObject(true);
        for(int c = 18; c < 21; c++)
            mapGrid[MAP_HEIGHT-6][c] = new replaceWithMapObject(true);
        for(int c = 20; c < 22; c++)
            mapGrid[MAP_HEIGHT-7][c] = new replaceWithMapObject(true);

        //temp to show middle where trainer starts
        mapGrid[15][20] = new replaceWithMapObject(false);
    }

//	/*
//	 * The exact map that Morgan used in model.
//	 */
//	 private void makeMapOne()
//	    {
//	        for (int r = 0; r < 30; r++)
//	        {
//	            for (int c = 0; c < 30; c++)
//	            {
//	            	//ground
//	                map[r][c] = new replaceWithMapObject(true);
//	            }
//	        }
//	        
//	        for(int r = 0; r < 15; r++)
//	        {
//	        	//rocks
//	            map[r][10] = new replaceWithMapObject(false);
//	        }
//	        
//	        for(int r = 15; r < 30; r++)
//	        {
//	        	//rocks
//	            map[r][25] = new replaceWithMapObject(false);
//	        }
//	        
//	        for(int r = 0; r < 7; r++)
//	        {
//	            for(int c = 0; c < 7; c++)
//	            {
//	            	//grass
//	                map[r][c] = new replaceWithMapObject(true);
//	            }
//	        }
//	    }
	@Override
	public MapObject[][] getMap() {
		return new MapObject[2][2];
	}

	public replaceWithMapObject[][] getMap2(){
		return mapGrid;
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
		return stepsRemaining;
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
		stepsRemaining--;
	}
}
