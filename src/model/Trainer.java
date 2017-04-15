package model;

import java.awt.Point;

import interfaceEnumMocks.Direction;

// import InterfacesAndEnums.Direction;

public class Trainer
{
    private int stepsRemaining;
    private int currentRow;
    private int currentCol;
    
    /**
     * Steps start at 500. Currently the starting
     * point is hard coded, can be changed later
     * 
     * @author Morgan Henry
     */
    public Trainer()
    {
        stepsRemaining = 500;
        //TODO: set to correct location or add parameters!!!
        currentRow = 14;
        currentCol = 20;
    }

    /**
     * Return the postion of the training in a Point
     * The x is the current col and the y is the current
     * row. 
     * 
     * @return
     * 
     * @author Morgan Henry
     */
    public Point getPosition()
    {
        return new Point(currentCol, currentRow);
    }

    /**
     * Return the amount of steps remaining. Starts
     * at 500 and decrements one every move
     * @return
     * 
     * @author Morgan Henry
     */
    public int getRemainingStep()
    {
        return stepsRemaining;
    }

    /**
     * Move the trainer in the direction
     * This doesn't do any error checking for how many moves
     * or if the location is movable!
     * @param dir
     * 
     * @author Morgan Henry
     */
    public void move(Direction dir)
    {
        if(dir == Direction.NORTH)
            currentRow--;
        else if(dir == Direction.SOUTH)
            currentRow++;
        else if(dir == Direction.EAST)
            currentCol++;
        else if(dir == Direction.WEST)
            currentCol--;

        stepsRemaining--;
    }

}
