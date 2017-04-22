package model;

import java.awt.Point;
import java.io.Serializable;

import interfaceEnumMocks.Direction;
import interfaceEnumMocks.GameInterface;
import interfaceEnumMocks.MapObject;

// import InterfacesAndEnums.Direction;
// import InterfacesAndEnums.GameInterface;
// import InterfacesAndEnums.MapObject;

public class Game implements GameInterface, Serializable
{
    private  Map gameMap;
    private  Trainer trainer;
    private MyPokemonList pokedex;
    

    public Game(int map){
    	gameMap = new Map(map);
    	trainer = new Trainer();
    	pokedex = MyPokemonList.getInstance();
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
    
    public void addPokemon(Pokemon pok)
    {
        trainer.addPokemon(pok);
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
    public int getPokemonCount(){
    	return pokedex.size();
    }
}
