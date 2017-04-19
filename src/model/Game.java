package model;

import java.awt.Point;

import interfaceEnumMocks.Direction;
import interfaceEnumMocks.GameInterface;
import interfaceEnumMocks.MapObject;

// import InterfacesAndEnums.Direction;
// import InterfacesAndEnums.GameInterface;
// import InterfacesAndEnums.MapObject;

public class Game implements GameInterface
{
    private static Map gameMap;
    private static Trainer trainer;
    
    public static void main(String[] args)
    {
        gameMap = new Map(1);
        trainer = new Trainer();
        gameMap.printMap();
    }

    public Game(){
    	gameMap = new Map(1);
    	trainer = new Trainer();
    }
    
    @Override
    public MapObject[][] getMap()
    {
        return gameMap.getMap();
    }
    
    public int getMapWidth()
    {
        return gameMap.getMapWidth();
    }
    
    public int getMapHeight()
    {
        return gameMap.getMapHeight();
    }

    @Override
    public Point getTrainerPosition()
    {
        return trainer.getPosition();
    }

    @Override
    public int getRemainingSteps()
    {
        return trainer.getRemainingStep();
    }

    @Override
    public void moveTrainer(Direction dir)
    {
        trainer.move(dir);
    }
    
    /**
     * This method will return true if the trainer
     * has used all steps.
     * @return
     * 
     * @author Morgan Henry
     */
    public boolean gameOver()
    {
        if(getRemainingSteps() <= 0)
            return true;
        else
            return false;
        
    }
}
