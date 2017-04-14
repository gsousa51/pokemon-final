package model;

import java.awt.Point;

import interfaceEnumMocks.MapObject;

// import InterfacesAndEnums.MapObject;

public class Map
{
    private static final int MAP_WIDTH = 40;
    private static final int MAP_HEIGHT = 30;

    private MapObject[][] mapGrid;

    public Map(int map)
    {
        mapGrid = new MapObject[MAP_HEIGHT][MAP_WIDTH];

        if (map == 1)
            makeMapOne();
        else
            makeMapTwo();
    }

    /**
     * Return a 2d array of the board
     * 
     * @author Morgan Henry
     */
    public MapObject[][] getMap()
    {
        return mapGrid;
    }

    public void removePokemon(Point remove)
    {
        mapGrid[remove.y][remove.x].removePokemon();
    }

    public void removeItem(Point remove)
    {
        mapGrid[remove.y][remove.x].removeItem();
    }

    /**
     * When a pokemon runs away, this will put it at a new grass location and
     * removes it from the previous location
     * 
     * @param loc
     *            - location of the pokemon previously
     */
    public void movePokemon(Point loc)
    {
        Pokemon remmovePok = mapGrid[loc.y][loc.x].getPokemon();

        // TODO
    }

    /**
     * Hard code map 1 into mapGrid
     * 
     * @author Morgan Henry
     */
    private void makeMapOne()
    {
        // initialize everything to Ground
        for (int r = 0; r < MAP_HEIGHT; r++)
        {
            for (int c = 0; c < MAP_WIDTH; c++)
            {
                mapGrid[r][c] = new Ground(null);
            }
        }

        // make rock borders for whole map to avoid walking off the map
        for (int r = 0; r < MAP_HEIGHT; r++)
        {
            mapGrid[r][0] = new Rock();
            mapGrid[r][MAP_WIDTH - 1] = new Rock();
        }
        for (int c = 0; c < MAP_WIDTH; c++)
        {
            mapGrid[0][c] = new Rock();
            mapGrid[MAP_HEIGHT - 1][c] = new Rock();
        }

        // Create vertical rock wall in col 10 from row 0-12
        for (int r = 1; r < 13; r++)
            mapGrid[r][10] = new Rock();

        // create horizontal rock wall in row 13 to col 7
        for (int c = 1; c < 8; c++)
            mapGrid[13][c] = new Rock();

        // make grass patches in top left corner
        //(it's surrounded by rock walls)
        for (int c = 3; c < 6; c++)
            mapGrid[3][c] = new Grass(null);
        for (int c = 2; c < 7; c++)
            mapGrid[4][c] = new Grass(null);
        for (int c = 1; c < 8; c++)
            mapGrid[5][c] = new Grass(null);
        for (int c = 1; c < 7; c++)
            mapGrid[6][c] = new Grass(null);
        for (int c = 2; c < 6; c++)
            mapGrid[7][c] = new Grass(null);
        for (int c = 3; c < 6; c++)
            mapGrid[8][c] = new Grass(null);
        for (int c = 8; c < 10; c++)
            mapGrid[9][c] = new Grass(null);
        for (int c = 7; c < 9; c++)
            mapGrid[10][c] = new Grass(null);
        for (int c = 6; c < 8; c++)
            mapGrid[11][c] = new Grass(null);
        for (int c = 1; c < 3; c++)
            mapGrid[11][c] = new Grass(null);
        for (int c = 1; c < 4; c++)
            mapGrid[12][c] = new Grass(null);
        
        //add grass on the other side of the wall
        for(int r = 1; r < 5; r++)
        {
            for(int c = 11; c < 15; c++)
                mapGrid[r][c] = new Grass(null);
        }

        // add vertical rock walls, bottom left quadrent
        for (int r = 15; r < MAP_HEIGHT - 4; r++)
            mapGrid[r][15] = new Rock();
        for (int r = 25; r < MAP_HEIGHT; r++)
            mapGrid[r][8] = new Rock();

        // grass in bottom left corner
        for (int r = 17; r < 20; r++)
            mapGrid[r][14] = new Grass(null);
        for (int c = 4; c < 8; c++)
            mapGrid[MAP_HEIGHT - 2][c] = new Grass(null);
        for (int c = 5; c < 8; c++)
            mapGrid[MAP_HEIGHT - 3][c] = new Grass(null);
        for (int c = 6; c < 8; c++)
            mapGrid[MAP_HEIGHT - 4][c] = new Grass(null);

        for (int c = 1; c < 2; c++)
            mapGrid[MAP_HEIGHT - 4][c] = new Grass(null);
        for (int c = 1; c < 4; c++)
            mapGrid[MAP_HEIGHT - 5][c] = new Grass(null);
        for (int c = 1; c < 6; c++)
            mapGrid[MAP_HEIGHT - 6][c] = new Grass(null);
        for (int c = 1; c < 8; c++)
            mapGrid[MAP_HEIGHT - 7][c] = new Grass(null);

        for (int c = 5; c < 9; c++)
            mapGrid[17][c] = new Grass(null);
        for (int c = 5; c < 7; c++)
            mapGrid[18][c] = new Grass(null);
        for (int c = 1; c < 3; c++)
            mapGrid[19][c] = new Grass(null);
        for (int c = 1; c < 3; c++)
            mapGrid[20][c] = new Grass(null);

        // Wall on top right side
        for (int c = 20; c < MAP_WIDTH; c++)
            mapGrid[6][c] = new Rock();
        for (int c = 28; c < 33; c++)
            mapGrid[6][c] = new Ground(null);
        for (int r = 1; r < 5; r++)
            mapGrid[r][30] = new Rock();

        // grass top right side, surrounded by the rock
        for (int r = 1; r < 4; r++)
        {
            for (int c = MAP_WIDTH - 5; c < MAP_WIDTH - 1; c++)
                mapGrid[r][c] = new Grass(null);
        }
        
        //grass top in the middle
        for(int c = 20; c < 25; c++)
            mapGrid[1][c] = new Grass(null);
        for(int c = 21; c < 24; c++)
            mapGrid[2][c] = new Grass(null);
        
        
        //Walls in the middle right side
        for(int c = 28; c < MAP_WIDTH-3; c++)
            mapGrid[17][c] = new Rock();
        for(int r = 13; r < 18; r++)
            mapGrid[r][28] = new Rock();
        
        //Grass middle right side
        for(int r = 10; r < 13; r++)
        {
            for(int c = 31; c < 35; c++)
                mapGrid[r][c] = new Grass(null);
        }
        for(int r = 11; r < 13; r++)
            mapGrid[r][35] = new Grass(null);
        for(int c = 32; c < 34; c++)
            mapGrid[13][c] = new Grass(null);
        for(int c = 31; c < 33; c++)
            mapGrid[9][c] = new Grass(null);
        for(int r = 10; r < 12; r++)
            mapGrid[r][30] = new Grass(null);
        
        //Rocks bottom right corner
        for(int r = 21; r < 27; r++)
            mapGrid[r][30] = new Rock();
        
        //Grass bottom right corner
        for(int r = 22; r < 26; r++)
        {
            for(int c = 34; c < 37; c++)
                mapGrid[r][c] = new Grass(null);
        }
        for(int r = 23; r<25; r++)
            mapGrid[r][33] = new Grass(null);
        for(int r = 25; r<28; r++)
            mapGrid[r][37] = new Grass(null);
        mapGrid[26][36] = new Grass(null);

        
        //grass middle bottom
        for(int c = 19; c < 25; c++)
            mapGrid[MAP_HEIGHT-2][c] = new Grass(null);
        for(int c = 19; c < 24; c++)
            mapGrid[MAP_HEIGHT-3][c] = new Grass(null);
        for(int c = 18; c < 25; c++)
            mapGrid[MAP_HEIGHT-4][c] = new Grass(null);
        for(int c = 20; c < 23; c++)
            mapGrid[MAP_HEIGHT-5][c] = new Grass(null);
        for(int c = 18; c < 21; c++)
            mapGrid[MAP_HEIGHT-6][c] = new Grass(null);
        for(int c = 20; c < 22; c++)
            mapGrid[MAP_HEIGHT-7][c] = new Grass(null);

        //temp to show middle where trainer starts
        mapGrid[15][20] = new Rock();
    }

    /**
     * System.out.print the map using chars
     * 
     * @author Morgan Henry
     */
    public void printMap()
    {
        for (int r = 0; r < MAP_HEIGHT; r++)
        {
            for (int c = 0; c < MAP_WIDTH; c++)
            {
                System.out.print("[" + mapGrid[r][c] + "]");
            }
            System.out.println();
        }
    }

    /**
     * Hard code map 2 into mapGrid
     * 
     * @author Morgan Henry
     */
    private void makeMapTwo()
    {
        // TODO Auto-generated method stub

    }

}
