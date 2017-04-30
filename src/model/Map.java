package model;

import java.awt.Point;
import java.io.Serializable;
import java.util.Random;

import interfaceEnumMocks.MapObject;

// import InterfacesAndEnums.MapObject;

public class Map implements Serializable
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

    public int getMapWidth()
    {
        return MAP_WIDTH;
    }

    public int getMapHeight()
    {
        return MAP_HEIGHT;
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

    /**
     * Delete and do the same thing as Pokemon...
     * 
     * @param remove
     *            public void removeItem(Point remove) {
     *            mapGrid[remove.y][remove.x].removeItem(); }
     */

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
        // (it's surrounded by rock walls)
        for (int c = 3; c < 6; c++)
            mapGrid[3][c] = new Grass();
        for (int c = 2; c < 7; c++)
            mapGrid[4][c] = new Grass();
        for (int c = 1; c < 8; c++)
            mapGrid[5][c] = new Grass();
        for (int c = 1; c < 7; c++)
            mapGrid[6][c] = new Grass();
        for (int c = 2; c < 6; c++)
            mapGrid[7][c] = new Grass();
        for (int c = 3; c < 6; c++)
            mapGrid[8][c] = new Grass();
        for (int c = 8; c < 10; c++)
            mapGrid[9][c] = new Grass();
        for (int c = 7; c < 9; c++)
            mapGrid[10][c] = new Grass();
        for (int c = 6; c < 8; c++)
            mapGrid[11][c] = new Grass();
        for (int c = 1; c < 3; c++)
            mapGrid[11][c] = new Grass();
        for (int c = 1; c < 4; c++)
            mapGrid[12][c] = new Grass();

        // add grass on the other side of the wall
        for (int r = 1; r < 5; r++)
        {
            for (int c = 11; c < 15; c++)
                mapGrid[r][c] = new Grass();
        }

        // add vertical rock walls, bottom left quadrent
        for (int r = 15; r < MAP_HEIGHT - 4; r++)
            mapGrid[r][15] = new Rock();
        for (int r = 25; r < MAP_HEIGHT; r++)
            mapGrid[r][8] = new Rock();

        // grass in bottom left corner
        for (int r = 17; r < 20; r++)
            mapGrid[r][14] = new Grass();
        for (int c = 4; c < 8; c++)
            mapGrid[MAP_HEIGHT - 2][c] = new Grass();
        for (int c = 5; c < 8; c++)
            mapGrid[MAP_HEIGHT - 3][c] = new Grass();
        for (int c = 6; c < 8; c++)
            mapGrid[MAP_HEIGHT - 4][c] = new Grass();

        for (int c = 1; c < 2; c++)
            mapGrid[MAP_HEIGHT - 4][c] = new Grass();
        for (int c = 1; c < 4; c++)
            mapGrid[MAP_HEIGHT - 5][c] = new Grass();
        for (int c = 1; c < 6; c++)
            mapGrid[MAP_HEIGHT - 6][c] = new Grass();
        for (int c = 1; c < 8; c++)
            mapGrid[MAP_HEIGHT - 7][c] = new Grass();

        for (int c = 5; c < 9; c++)
            mapGrid[17][c] = new Grass();
        for (int c = 5; c < 7; c++)
            mapGrid[18][c] = new Grass();
        for (int c = 1; c < 3; c++)
            mapGrid[19][c] = new Grass();
        for (int c = 1; c < 3; c++)
            mapGrid[20][c] = new Grass();

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
                mapGrid[r][c] = new Grass();
        }

        // grass top in the middle
        for (int c = 20; c < 25; c++)
            mapGrid[1][c] = new Grass();
        for (int c = 21; c < 24; c++)
            mapGrid[2][c] = new Grass();

        // Walls in the middle right side
        for (int c = 28; c < MAP_WIDTH - 3; c++)
            mapGrid[17][c] = new Rock();
        for (int r = 13; r < 18; r++)
            mapGrid[r][28] = new Rock();

        // Grass middle right side
        for (int r = 10; r < 13; r++)
        {
            for (int c = 31; c < 35; c++)
                mapGrid[r][c] = new Grass();
        }
        for (int r = 11; r < 13; r++)
            mapGrid[r][35] = new Grass();
        for (int c = 32; c < 34; c++)
            mapGrid[13][c] = new Grass();
        for (int c = 31; c < 33; c++)
            mapGrid[9][c] = new Grass();
        for (int r = 10; r < 12; r++)
            mapGrid[r][30] = new Grass();

        // Rocks bottom right corner
        for (int r = 21; r < 27; r++)
            mapGrid[r][30] = new Rock();

        // Grass bottom right corner
        for (int r = 22; r < 26; r++)
        {
            for (int c = 34; c < 37; c++)
                mapGrid[r][c] = new Grass();
        }
        for (int r = 23; r < 25; r++)
            mapGrid[r][33] = new Grass();
        for (int r = 25; r < 28; r++)
            mapGrid[r][37] = new Grass();
        mapGrid[26][36] = new Grass();

        // grass middle bottom
        for (int c = 19; c < 25; c++)
            mapGrid[MAP_HEIGHT - 2][c] = new Grass();
        for (int c = 19; c < 24; c++)
            mapGrid[MAP_HEIGHT - 3][c] = new Grass();
        for (int c = 18; c < 25; c++)
            mapGrid[MAP_HEIGHT - 4][c] = new Grass();
        for (int c = 20; c < 23; c++)
            mapGrid[MAP_HEIGHT - 5][c] = new Grass();
        for (int c = 18; c < 21; c++)
            mapGrid[MAP_HEIGHT - 6][c] = new Grass();
        for (int c = 20; c < 22; c++)
            mapGrid[MAP_HEIGHT - 7][c] = new Grass();

        // temp to show middle where trainer starts
        mapGrid[15][20] = new Rock();

        addItems();
    }

    private void addItems()
    {
        Random rand = new Random();

        int y = rand.nextInt(MAP_WIDTH - 2) + 1;
        int x = rand.nextInt(MAP_HEIGHT - 2) + 1;

        int numBalls = 0;
        int numFossils = 0;
        int numPotion = 0;

        while (numBalls < 10)
        {
            if (mapGrid[x][y] instanceof Ground && mapGrid[x][y].getItem() == null)
            {
                mapGrid[x][y] = new Ground(new SafariBall());
                numBalls++;
            }
            y = rand.nextInt(MAP_WIDTH - 2) + 1;
            x = rand.nextInt(MAP_HEIGHT - 2) + 1;
        }
        
        while(numFossils < 7)
        {
            if (mapGrid[x][y] instanceof Ground && mapGrid[x][y].getItem() == null)
            {
                mapGrid[x][y] = new Ground(new HelixFossil());
                numFossils++;
            }
            y = rand.nextInt(MAP_WIDTH - 2) + 1;
            x = rand.nextInt(MAP_HEIGHT - 2) + 1;
        }

        while(numPotion < 7)
        {
            if (mapGrid[x][y] instanceof Ground && mapGrid[x][y].getItem() == null)
            {
                mapGrid[x][y] = new Ground(new Potion());
                numPotion++;
            }
            y = rand.nextInt(MAP_WIDTH - 2) + 1;
            x = rand.nextInt(MAP_HEIGHT - 2) + 1;
        }

    }

    /**
     * System.out.print the map using chars
     * 
     * @author Morgan Henry
     */
    public void printMap()
    {
        for (int i = 0; i < 40; i++)
        {
            if (i < 10)
                System.out.print(" " + i + " ");
            else
                System.out.print(i + " ");
        }
        System.out.println();
        for (int r = 0; r < MAP_HEIGHT; r++)
        {
            for (int c = 0; c < MAP_WIDTH; c++)
            {
                System.out.print("[" + mapGrid[r][c] + "]");
            }
            System.out.println(r + " ");
        }
    }

    /**
     * Hard code map 2 into mapGrid
     * 
     * @author Morgan Henry
     */
    private void makeMapTwo()
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

        // Make the smaler inside box
        for (int c = 15; c < 24; c++)
            mapGrid[11][c] = new Rock();
        for (int c = 15; c < 24; c++)
            mapGrid[18][c] = new Rock();
        for (int r = 12; r < 14; r++)
        {
            mapGrid[r][23] = new Rock();
            mapGrid[r][15] = new Rock();
        }
        for (int r = 16; r < 18; r++)
        {
            mapGrid[r][23] = new Rock();
            mapGrid[r][15] = new Rock();
        }
        mapGrid[11][19] = new Ground(null);
        mapGrid[18][19] = new Ground(null);

        // Add grass to small box
        // bottom right corner
        for (int c = 20; c < 23; c++)
            mapGrid[17][c] = new Grass();
        for (int c = 21; c < 23; c++)
            mapGrid[16][c] = new Grass();
        // top left corner
        for (int c = 16; c < 19; c++)
            mapGrid[12][c] = new Grass();
        for (int c = 16; c < 18; c++)
            mapGrid[13][c] = new Grass();
        // bottom left corner
        mapGrid[16][16] = new Grass();
        mapGrid[17][16] = new Grass();
        mapGrid[17][17] = new Grass();

        // make bigger box
        for (int c = 6; c < 34; c++)
            mapGrid[6][c] = new Rock();
        for (int c = 6; c < 34; c++)
            mapGrid[23][c] = new Rock();
        for (int r = 7; r < 13; r++)
        {
            mapGrid[r][33] = new Rock();
            mapGrid[r][6] = new Rock();
        }
        for (int r = 17; r < 23; r++)
        {
            mapGrid[r][33] = new Rock();
            mapGrid[r][6] = new Rock();
        }
        for (int c = 11; c < 13; c++)
            mapGrid[23][c] = new Ground(null);
        for (int c = 26; c < 28; c++)
            mapGrid[23][c] = new Ground(null);
        for (int c = 17; c < 22; c++)
            mapGrid[6][c] = new Ground(null);

        // rock walls between boxes
        for (int r = 12; r < 18; r++)
        {
            mapGrid[r][27] = new Rock();
            mapGrid[r][11] = new Rock();
        }

        // add grass next to walls between boxes
        for (int r = 13; r < 17; r++)
        {
            mapGrid[r][28] = new Grass();
            mapGrid[r][29] = new Grass();
            mapGrid[r][10] = new Grass();
            mapGrid[r][9] = new Grass();
        }

        // add walls between edge and big boxx
        for (int r = 13; r < 17; r++)
        {
            mapGrid[r][36] = new Rock();
            mapGrid[r][3] = new Rock();
        }
        // add grass next to walls
        for (int r = 13; r < 17; r++)
        {
            mapGrid[r][37] = new Grass();
            mapGrid[r][38] = new Grass();
            mapGrid[r][2] = new Grass();
            mapGrid[r][1] = new Grass();
        }

        // add grass to big box
        // bottom middle
        for (int c = 17; c < 22; c++)
        {
            mapGrid[22][c] = new Grass();
            mapGrid[21][c] = new Grass();
        }
        for (int c = 18; c < 21; c++)
            mapGrid[20][c] = new Grass();

        // bottom and top left corner
        for (int c = 7; c < 11; c++)
        {
            mapGrid[7][c] = new Grass();
            mapGrid[22][c] = new Grass();
        }
        for (int c = 7; c < 10; c++)
        {
            mapGrid[8][c] = new Grass();
            mapGrid[9][c] = new Grass();
            mapGrid[21][c] = new Grass();
            mapGrid[20][c] = new Grass();
        }
        for (int c = 7; c < 9; c++)
        {
            mapGrid[10][c] = new Grass();
            mapGrid[19][c] = new Grass();
        }

        // top right corner and bottom right corner
        for (int c = 30; c < 33; c++)
        {
            mapGrid[7][c] = new Grass();
            mapGrid[22][c] = new Grass();
        }
        for (int c = 31; c < 33; c++)
        {
            mapGrid[21][c] = new Grass();
            mapGrid[8][c] = new Grass();
        }
        for (int c = 32; c < 33; c++)
        {
            mapGrid[20][c] = new Grass();
            mapGrid[9][c] = new Grass();
        }

        // Add grass outside of boxes
        // bottom middle
        for (int c = 16; c < 23; c++)
        {
            mapGrid[28][c] = new Grass();
            if (c > 16 && c < 22)
                mapGrid[27][c] = new Grass();
            mapGrid[26][c] = new Grass();
            if (c > 17 && c < 21)
                mapGrid[27][c] = new Grass();
        }

        // top middle
        for (int c = 1; c < 10; c += 2)
        {
            mapGrid[1][c] = new Grass();
            mapGrid[2][c + 1] = new Grass();
            mapGrid[3][c] = new Grass();

            mapGrid[28][c] = new Grass();
            mapGrid[27][c + 1] = new Grass();
            mapGrid[26][c] = new Grass();
        }

        for (int c = 30; c < 39; c += 2)
        {
            mapGrid[1][c] = new Grass();
            mapGrid[2][c - 1] = new Grass();
            mapGrid[3][c] = new Grass();

            mapGrid[28][c] = new Grass();
            mapGrid[27][c - 1] = new Grass();
            mapGrid[26][c] = new Grass();
        }

        addItems();

    }

    /*
    public static void main(String[] args)
    {
        Map map = new Map(2);
        map.printMap();

        System.out.println("\n\n");

        Map map1 = new Map(1);
        map1.printMap();
    }
    
    */

}
