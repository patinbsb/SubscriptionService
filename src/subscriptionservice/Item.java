/*
 *
 */
package subscriptionservice;

/**
 * <h1> Item class </h1>
 * The Item class
 * 
 * @param ID integer a unique identifier in which to track each item.
 * @param title String a String for user recognition
 * @param numberLeft integer used to identify how many subscriptions this item has left
 * @author Patrick Goodson
 * @version 1.0
 * @since 04/02/2017
 */
class Item
{
    int ID;
    String title;
    int numberLeft;
    
    Item(int ID, String title, int numberLeft)
    {
        this.ID = ID;
        this.title = title;
        this.numberLeft = numberLeft;
    }

    /**
     * 
     * @return ID
     */
    public int getID()
    {
        return ID;
    }
    
    /**
     * 
     * @param ID 
     */
    public void setID(int ID)
    {
        this.ID = ID;
    }
    
    /**
     * 
     * @return title
     */
    public String getTitle()
    {
        return title;
    }
    
    /**
     * 
     * @param title 
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    /**
     * 
     * @return 
     */
    public int getNumberLeft()
    {
        return numberLeft;
    }
    
    /**
     * 
     * @param numberLeft 
     */
    public void setNumberLeft(int numberLeft)
    {
        this.numberLeft = numberLeft;
    }
    
    @Override
    public String toString()
    {
        return "Item{" + "ID=" + ID + ", title=" + title + ", numberLeft=" + numberLeft + '}';
    }
}
