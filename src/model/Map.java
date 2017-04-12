package model;

import java.awt.Point;

import InterfacesAndEnums.MapObject;

public class Map
{
    private static final int MAP_WIDTH = 30;
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
        for (int r = 0; r < MAP_HEIGHT; r++)
        {
            for (int c = 0; c < MAP_WIDTH; c++)
            {
                mapGrid[r][c] = new Ground(null);
            }
        }
        
        for(int r = 0; r < MAP_HEIGHT/2; r++)
        {
            mapGrid[r][10] = new Rock();
        }
        
        for(int r = 15; r < MAP_HEIGHT; r++)
        {
            mapGrid[r][25] = new Rock();
        }
        
        for(int r = 0; r < 7; r++)
        {
            for(int c = 0; c < 7; c++)
            {
                mapGrid[r][c] = new Grass(null);
            }
        }
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
                System.out.print("[" + mapGrid[r][c] + "]" );
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
