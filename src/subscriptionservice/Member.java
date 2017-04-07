/*
 *
 */
package subscriptionservice;

import java.util.HashMap;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 * Contains user information
 * @author  Patrick Goodson
 */
class Member
{
    int ID;
    String name;
    // Member need only know item ID and Title, hashmaps provide quick lookup
    HashMap subscribedItems = new HashMap();
    LinkedList<Item> requestedItems = new LinkedList<>();

    Member(int ID, String name)
    {
        this.ID = ID;
        this.name = name;
    }
    
        @Override
    public String toString()
    {
        return "Member{" + "ID=" + ID + ", name=" + name + ", subscribedItems=" + subscribedItems + ", requestedItems=" + requestedItems + '}';
    }
    
    /**
     * Checks if the members subscribed items are <=5
     * @return true if <=5 items
     */
    boolean canSubscribe()
    {
        if (subscribedItems.size() < 5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Checks if the members Queue size is <=5
     * @return true if <=5
     */
    boolean canQueue()
    {
        if (requestedItems.size() < 5 && !(subscribedItems.size() <= 5))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Attempts to subscribe to the item, if the item has >0 uses left, sends a transaction
     * to the transaction controller and adds the item to subscribedItems and
     * decrements numberLeft.
     * @param itemID 
     */
    void subscribeToItem(int itemID)
    {
        for (Item item : ItemController.getItemList())
        {
            if (item.ID == itemID)
            {
                if (item.numberLeft > 0 && canSubscribe())
                {
                    TransactionController.createTransaction(null, item.ID, ID, null);
                    item.numberLeft --;
                    subscribedItems.put(item.ID, item.title);
                }
            }
        }
    }
    
    /**
     * Attempts to remove item from subscribedItems and increments numberLeft
     * @param itemID 
     */
    void returnItem(int itemID)
    {
        subscribedItems.remove(itemID);
        for (Item item : ItemController.getItemList())
        {
            if (item.ID == itemID)
            {
                item.numberLeft++;
            }
        }
    }

    /**
     * Attempts to Queue an item, adds the item to the requestedItems list.
     * @param itemID 
     */
    void queueItem(int itemID)
    {
        for (Item item : ItemController.getItemList())
        {
            if (item.ID == itemID && canQueue())
            {
                requestedItems.add(item);
            }
            else if (!canQueue())
            {
                JOptionPane.showMessageDialog(null, "Cannot queue item,"
                        + "over 5 items queued");
                break;
            }
        }
    }
    
    /**
     * Attempts to remove an item from the requestedItems list.
     * @param itemID 
     */
    void unQueueItem(int itemID)
    {
        requestedItems.remove(ItemController.getItem(itemID));
    }

    public HashMap getSubscribedItems()
    {
        return subscribedItems;
    }

    public void setSubscribedItems(HashMap subscribedItems)
    {
        this.subscribedItems = subscribedItems;
    }

    public LinkedList getQueuedItems()
    {
        return requestedItems;
    }

    public void setQueuedItems(LinkedList<Item> requestedItems)
    {
        this.requestedItems = requestedItems;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns all subscribed items in this members subscription list.
     */
    void clearSubscriptions()
    {
        while (subscribedItems.keySet().iterator().hasNext())
        {
            returnItem((int) subscribedItems.keySet().iterator().next());
        }
    }
    

}
