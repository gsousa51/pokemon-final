package model;

import java.awt.Point;
import java.io.Serializable;

import interfaceEnumMocks.Direction;

// import InterfacesAndEnums.Direction;

public class Trainer implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int stepsRemaining;
    private int currentRow;
    private int currentCol;
    private MyItemList items;
    private MyPokemonList pokemon;
    
    /**
     * Steps start at 500. Currently the starting
     * point is hard coded, can be changed later
     * 
     * @author Morgan Henry
     */
    public Trainer()
    {
    	
        stepsRemaining = 500;
        currentRow = 14;
        currentCol = 20;
        
        items = MyItemList.getInstance();
        pokemon = MyPokemonList.getInstance();
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
     * Decrement remaining balls by one
     */
    public void throwBall()
    {
        items.removeItem("Safari Ball");
    }
    
    /**
     * add one to remaining balls
     */
    public void findBall()
    {
       items.addItem(new SafariBall());
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

    public void addPokemon(Pokemon pok)
    {
        pokemon.addPokemon(pok);
    }

    public int getRemainingBalls()
    {
        return items.getItemCount("Safari Ball");
    }

}
