package animationSandBox;

public class createFakeMap {

	public static void main(String[] args){
		createFakeMap map = new createFakeMap();

	}
	char[][] mapGrid = new char[MAP_HEIGHT][MAP_WIDTH];
	final static int MAP_HEIGHT = 30;
	final static int MAP_WIDTH = 40;
	
	public createFakeMap(){
		makeMapOne();
	}
	
	public char[][] getMap(){
		return mapGrid;
	}
	
	
	
    private void makeMapOne()
    {
        // initialize everything to Ground
        for (int r = 0; r < MAP_HEIGHT; r++)
        {
            for (int c = 0; c < MAP_WIDTH; c++)
            {
                mapGrid[r][c] = 'h';
            }
        }

        // make rock borders for whole map to avoid walking off the map
        for (int r = 0; r < MAP_HEIGHT; r++)
        {
            mapGrid[r][0] = 'r';
            mapGrid[r][MAP_WIDTH - 1] = 'r';
        }
        for (int c = 0; c < MAP_WIDTH; c++)
        {
            mapGrid[0][c] = 'r';
            mapGrid[MAP_HEIGHT - 1][c] = 'r';
        }

        // Create vertical rock wall in col 10 from row 0-12
        for (int r = 1; r < 13; r++)
            mapGrid[r][10] = 'r';

        // create horizontal rock wall in row 13 to col 7
        for (int c = 1; c < 8; c++)
            mapGrid[13][c] = 'r';

        // make grass patches in top left corner
        //(it's surrounded by rock walls)
        for (int c = 3; c < 6; c++)
            mapGrid[3][c] = 'g';
        for (int c = 2; c < 7; c++)
            mapGrid[4][c] = 'g';
        for (int c = 1; c < 8; c++)
            mapGrid[5][c] = 'g';
        for (int c = 1; c < 7; c++)
            mapGrid[6][c] = 'g';
        for (int c = 2; c < 6; c++)
            mapGrid[7][c] = 'g';
        for (int c = 3; c < 6; c++)
            mapGrid[8][c] = 'g';
        for (int c = 8; c < 10; c++)
            mapGrid[9][c] = 'g';
        for (int c = 7; c < 9; c++)
            mapGrid[10][c] = 'g';
        for (int c = 6; c < 8; c++)
            mapGrid[11][c] = 'g';
        for (int c = 1; c < 3; c++)
            mapGrid[11][c] = 'g';
        for (int c = 1; c < 4; c++)
            mapGrid[12][c] = 'g';
        
        //add grass on the other side of the wall
        for(int r = 1; r < 5; r++)
        {
            for(int c = 11; c < 15; c++)
                mapGrid[r][c] = 'g';
        }

        // add vertical rock walls, bottom left quadrent
        for (int r = 15; r < MAP_HEIGHT - 4; r++)
            mapGrid[r][15] = 'r';
        for (int r = 25; r < MAP_HEIGHT; r++)
            mapGrid[r][8] = 'r';

        // grass in bottom left corner
        for (int r = 17; r < 20; r++)
            mapGrid[r][14] = 'g';
        for (int c = 4; c < 8; c++)
            mapGrid[MAP_HEIGHT - 2][c] = 'g';
        for (int c = 5; c < 8; c++)
            mapGrid[MAP_HEIGHT - 3][c] = 'g';
        for (int c = 6; c < 8; c++)
            mapGrid[MAP_HEIGHT - 4][c] = 'g';

        for (int c = 1; c < 2; c++)
            mapGrid[MAP_HEIGHT - 4][c] = 'g';
        for (int c = 1; c < 4; c++)
            mapGrid[MAP_HEIGHT - 5][c] = 'g';
        for (int c = 1; c < 6; c++)
            mapGrid[MAP_HEIGHT - 6][c] = 'g';
        for (int c = 1; c < 8; c++)
            mapGrid[MAP_HEIGHT - 7][c] = 'g';

        for (int c = 5; c < 9; c++)
            mapGrid[17][c] = 'g';
        for (int c = 5; c < 7; c++)
            mapGrid[18][c] = 'g';
        for (int c = 1; c < 3; c++)
            mapGrid[19][c] = 'g';
        for (int c = 1; c < 3; c++)
            mapGrid[20][c] = 'g';

        // Wall on top right side
        for (int c = 20; c < MAP_WIDTH; c++)
            mapGrid[6][c] = 'r';
        for (int c = 28; c < 33; c++)
            mapGrid[6][c] = 'h';
        for (int r = 1; r < 5; r++)
            mapGrid[r][30] = 'r';

        // grass top right side, surrounded by the rock
        for (int r = 1; r < 4; r++)
        {
            for (int c = MAP_WIDTH - 5; c < MAP_WIDTH - 1; c++)
                mapGrid[r][c] = 'g';
        }
        
        //grass top in the middle
        for(int c = 20; c < 25; c++)
            mapGrid[1][c] = 'g';
        for(int c = 21; c < 24; c++)
            mapGrid[2][c] = 'g';
        
        
        //Walls in the middle right side
        for(int c = 28; c < MAP_WIDTH-3; c++)
            mapGrid[17][c] = 'r';
        for(int r = 13; r < 18; r++)
            mapGrid[r][28] = 'r';
        
        //Grass middle right side
        for(int r = 10; r < 13; r++)
        {
            for(int c = 31; c < 35; c++)
                mapGrid[r][c] = 'g';
        }
        for(int r = 11; r < 13; r++)
            mapGrid[r][35] = 'g';
        for(int c = 32; c < 34; c++)
            mapGrid[13][c] = 'g';
        for(int c = 31; c < 33; c++)
            mapGrid[9][c] = 'g';
        for(int r = 10; r < 12; r++)
            mapGrid[r][30] = 'g';
        
        //Rocks bottom right corner
        for(int r = 21; r < 27; r++)
            mapGrid[r][30] = 'r';
        
        //Grass bottom right corner
        for(int r = 22; r < 26; r++)
        {
            for(int c = 34; c < 37; c++)
                mapGrid[r][c] = 'g';
        }
        for(int r = 23; r<25; r++)
            mapGrid[r][33] = 'g';
        for(int r = 25; r<28; r++)
            mapGrid[r][37] = 'g';
        mapGrid[26][36] = 'g';

        
        //grass middle bottom
        for(int c = 19; c < 25; c++)
            mapGrid[MAP_HEIGHT-2][c] = 'g';
        for(int c = 19; c < 24; c++)
            mapGrid[MAP_HEIGHT-3][c] = 'g';
        for(int c = 18; c < 25; c++)
            mapGrid[MAP_HEIGHT-4][c] = 'g';
        for(int c = 20; c < 23; c++)
            mapGrid[MAP_HEIGHT-5][c] = 'g';
        for(int c = 18; c < 21; c++)
            mapGrid[MAP_HEIGHT-6][c] = 'g';
        for(int c = 20; c < 22; c++)
            mapGrid[MAP_HEIGHT-7][c] = 'g';

        //temp to show middle where trainer starts
        mapGrid[15][20] = 'r';
    }

//	private void initializeMap(){
//		for(int r=0 ; r<30; r++){
//			for(int c =0; c < 30; c++){
//				if(c%2==0){
//					if(r%2==0){
//						map[r][c] = 'g';
//					}
//					else{
//						map[r][c]= 'h';
//					}
//				}
//				else{
//					if(r%2==0){
//						map[r][c] = 'h';
//					}
//					else{
//						map[r][c] = 'g';
//					}
//				}
//			}
//		}
//		//make x of rocks.
//		int row, col;
//		row = col = 10;
//		while(row<20){
//			map[row][col] = 'r';
//			row++;
//			col++;
//		}
//		row = 20;
//		col = 10;
//		while(row>10){
//			map[row][col] = 'r';
//			row --;
//			col ++;
//		}
//	}
//	
//	
	//create a layout that's the exact layout morgan made in model
	// h==ground
	// g==grass
	// r==rock
//	 private void makeMapOne()
//	    {
//	        for (int r = 0; r < 30; r++)
//	        {
//	            for (int c = 0; c < 30; c++)
//	            {
//	                map[r][c] = 'h';
//	            }
//	        }
//	        
//	        for(int r = 0; r < 30/2; r++)
//	        {
//	            map[r][10] = 'r';
//	        }
//	        
//	        for(int r = 15; r < 30; r++)
//	        {
//	            map[r][25] = 'r';
//	        }
//	        
//	        for(int r = 0; r < 7; r++)
//	        {
//	            for(int c = 0; c < 7; c++)
//	            {
//	                map[r][c] = 'g';
//	            }
//	        }
//	    }
//	    
//	private void intializeMap(){
//
//		for(int r=0 ; r<100; r++){
//			for(int c =0; c < 100; c++){
//				if(c%2==0){
//					if(r%2==0){
//						map[r][c] = 'g';
//					}
//					else{
//						map[r][c]= 'h';
//					}
//				}
//				else{
//					if(r%2==0){
//						map[r][c] = 'h';
//					}
//					else{
//						map[r][c] = 'g';
//					}
//				}
//			}
//		}
//	}
//	
//	private void createMap(){
//		for(int r = 0; r<100 ; r++){
//			map[r][0] = 'r';
//			map[r][99]= 'r';
//		}
//		for(int c = 0; c < 100; c++){
//			map[0][c] = 'r';
//			map[99][c]= 'r';
//		}
//	}
//	
//	public void printMap(){
//		for(int r=0 ; r<100; r++){
//			for(int c =0; c < 100; c++){
//				System.out.print("[ " + map[r][c] + "] ");
//			}
//			System.out.println("");
//		}
//	}
	
}
