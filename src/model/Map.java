package model;

import java.awt.Point;

public abstract class Map
{
    private static final int MAP_WIDTH = 30;
    private static final int MAP_HEIGHT = 30;

    private MapObject[][] mapGrid;
    
    public Map(int map)
    {
        mapGrid = new MapObject[MAP_HEIGHT][MAP_WIDTH];

        if(map == 1)
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
        //TODO: SET TO EMPTY
        mapGrid[remove.y][remove.x] = new MapObject();
    }

    /**
     * Hard code map 1 into mapGrid
     * 
     * @author Morgan Henry
     */
    private void makeMapOne()
    {
        // TODO Auto-generated method stub
        
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
