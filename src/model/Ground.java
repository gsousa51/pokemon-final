package model;

import java.io.Serializable;

import interfaceEnumMocks.MapObject;

// import InterfacesAndEnums.MapObject;

public class Ground extends MapObject implements Serializable
{
    private Item item;

    public Ground(Item it)
    {
        super(true);
        item = it;
    }

    /**
     * Will return the Item, null if there is no item
     * 
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
        if (item == null)
            return " ";
        else
            return item.toString();
    }

}
