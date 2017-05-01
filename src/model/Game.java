package model;

import java.awt.Point;
import java.io.Serializable;

import interfaceEnumMocks.Direction;
import interfaceEnumMocks.GameInterface;
import interfaceEnumMocks.GameOverOptions;
import interfaceEnumMocks.MapObject;

// import InterfacesAndEnums.Direction;
// import InterfacesAndEnums.GameInterface;
// import InterfacesAndEnums.MapObject;

public class Game implements GameInterface, Serializable
{
    private Map gameMap;
    private Trainer trainer;
    private MyPokemonList pokedex;
    private MyItemList items;
    private GameOverOptions endGame;
    private int mapNumber;
    
    public Game(int map, GameOverOptions end)
    {
    	this.mapNumber = map;
        gameMap = new Map(mapNumber);
        trainer = new Trainer(mapNumber);
        pokedex = MyPokemonList.getInstance();
        this.items = MyItemList.getInstance();
        endGame = end;
    }
    
    public int getMapNumber(){
    	return this.mapNumber;
    }
    public GameOverOptions getGameOverCondition(){
    	return endGame;
    }
    public int ballsLeft()
    {
        return trainer.getRemainingBalls();
    }
    
    public void foundItem(Item item)
    {
        items.addItem(item);
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

    public void throwBall()
    {
        trainer.throwBall();
    }

    public void findBall()
    {
        trainer.findBall();
    }

    @Override
    public void moveTrainer(Direction dir)
    {
        trainer.move(dir);
    }

    public Pokemon checkPokemon()
    {
        if (Math.random() < .5)
            return AvailablePokemonList.getInstance().getPokemon();
        else
            return null;
    }

    /**
     * This method will return true if the game is over based on the users end
     * game choice
     * 
     * @return
     * 
     * @author Morgan Henry
     */
    public boolean gameOver()
    {
        if (getRemainingSteps() <= 0 && endGame == GameOverOptions.NO_STEPS)
            return true;
        else if (trainer.getRemainingBalls() <= 0 && endGame == GameOverOptions.NO_BALL)
            return true;
        else if (MyPokemonList.getInstance().size() >= 15 & endGame == GameOverOptions.POKEMON_CAUGHT)
            return true;
        else
            return false;
    }

    public int getPokemonCount()
    {
        return pokedex.size();
    }
}
