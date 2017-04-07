/*
 *
 */
package subscriptionservice;

/**
 * Keeps track of user subscriptions and their dates.
 * @author Patrick Goodson
 */
public class Transaction {
    String transactionID;
    int itemID;
    int memberID;
    String date;

    Transaction(String transactionID, int itemID, int memberID, String date)
    {
        this.transactionID = transactionID;
        this.itemID = itemID;
        this.memberID = memberID;
        this.date = date;
    }
    
}
