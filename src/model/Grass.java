package model;

import java.io.Serializable;

import interfaceEnumMocks.MapObject;

// import InterfacesAndEnums.MapObject;

public class Grass extends MapObject implements Serializable
{ 
    private Pokemon pokemon;

    public Grass(Pokemon pok)
    {
        super(true);
        pokemon = pok;
    }
    
    /**
     * When the pokemon is caught or lost the pokemon 
     * is set to null 
     */
    @Override
    public void removePokemon()
    {
        pokemon = null;
    }
    
    public Pokemon getPokemon()
    {
        return pokemon;
    }

    @Override
    public String toString()
    {
        return "^";
    }

}
