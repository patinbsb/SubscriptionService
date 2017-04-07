/*
 *
 */
package subscriptionservice;
import java.util.ArrayList;
/**
 * 
 * @author Patrick Goodson
 */
public class ItemController
{
    static ArrayList<Item> itemList = new ArrayList<>();
    
    /**
     * 
     * @param ID The unique item identifier
     * @param title name of the item
     * @param numberLeft how many more subscriptions allowed
     */
    void createItem(int ID, String title, int numberLeft)
    {
        itemList.add(new Item(ID,title,numberLeft));
    }
    
    /**
     * searched for and returns an item by its ID
     * @param itemID
     * @return item/null
     */
    static Item getItem(int itemID)
    {
        for (Item item : itemList)
        {
            if (itemID == item.ID)
            {
                return (item);
            }
        }
        return null;
    }
    
    
    static ArrayList<Item> getItemList()
    {
        return itemList;
    }

    static void setItemList(ArrayList<Item> itemList)
    {
        ItemController.itemList = itemList;
    }
    
}
