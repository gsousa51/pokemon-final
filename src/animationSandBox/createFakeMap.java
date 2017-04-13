package animationSandBox;

public class createFakeMap {

	public static void main(String[] args){
		createFakeMap map = new createFakeMap();

	}
	char[][] map = new char[100][100];
	
	public createFakeMap(){
		makeMapOne();
	}
	
	public char[][] getMap(){
		return map;
	}
	
	private void initializeMap(){
		for(int r=0 ; r<30; r++){
			for(int c =0; c < 30; c++){
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
	
	
	//create a layout that's the exact layout morgan made in model
	// h==ground
	// g==grass
	// r==rock
	 private void makeMapOne()
	    {
	        for (int r = 0; r < 30; r++)
	        {
	            for (int c = 0; c < 30; c++)
	            {
	                map[r][c] = 'h';
	            }
	        }
	        
	        for(int r = 0; r < 30/2; r++)
	        {
	            map[r][10] = 'r';
	        }
	        
	        for(int r = 15; r < 30; r++)
	        {
	            map[r][25] = 'r';
	        }
	        
	        for(int r = 0; r < 7; r++)
	        {
	            for(int c = 0; c < 7; c++)
	            {
	                map[r][c] = 'g';
	            }
	        }
	    }
	    
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
