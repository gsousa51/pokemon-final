package model;

import interfaceEnumMocks.MapObject;

// import InterfacesAndEnums.MapObject;

public class Ground extends MapObject
{
    private Item item;

    public Ground(Item it)
    {
        super(true);
        item = it;
    }
    
    /**
     * Will return the Item, null if there is no item
     * @return
     * 
     * @author Morgan Henry
     */
    public Item getItem()
    {
        return item;
    }
    
    /**
     * Will make the item null
     * 
     * @author Morgan Henry
     */
    public void removeItem()
    {
        item = null;
    }

    @Override
    public String toString()
    {
        return " ";
    }
    
}
