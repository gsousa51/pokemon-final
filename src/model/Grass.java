package model;

import interfaceEnumMocks.MapObject;

// import InterfacesAndEnums.MapObject;

public class Grass extends MapObject
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
