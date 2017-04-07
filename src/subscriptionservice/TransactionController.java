/*
 *
 */
package subscriptionservice;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.UUID;

/**
 * Organises and tracks transactions, uses a queue structure for quick date lookups
 * @author Patrick Goodson
 */
public class TransactionController
{
    // Create a formatter to read the string from the transaction.date string
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // Using LinkedLists as transactionQueue resizes frequently
    static Queue<Transaction> transactionQueue = new LinkedList<>();
    
    /**
     * Generates a transaction, if no ID or date provided a unique ID is generated
     * and the current date is used.
     * @param transactionID
     * @param itemID
     * @param memberID
     * @param date 
     */
    static void createTransaction(String transactionID, int itemID, int memberID, String date)
    {
        // Generate random transaction ID if one is not supplied
        if (transactionID == null)
        {
            transactionID = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
            
            // Ensure transaction ID is unique
            boolean noRepeats = false;
            while (noRepeats == false)
            {
                noRepeats = true;
                for (Transaction transaction : transactionQueue)
                {
                    if (transactionID.equals(transaction.transactionID))
                    {
                        transactionID = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
                        noRepeats = false;
                    }
                }
            }
            
        }
        // Create a date if none provided
        if (date == null)
        {
            date = LocalDate.now().format(formatter);
        }
        // Add to the queue
        transactionQueue.add(new Transaction(transactionID, itemID, memberID, date)); 
    }
    
    //Startup purposes
    /**
     * On startup ensures all transactions in the transaction queue are valid
     * gets items and members and subscribes each member to its item.
     */
    static void setUpExisting()
    {
        transactionQueue.stream().forEach((transaction) ->
        {
            Item item = ItemController.getItem(transaction.itemID);
            Member member = MemberController.getMember(transaction.memberID);
            member.getSubscribedItems().put(item.ID, item.title);
        });
    }
    
    /**
     * Peeks at the top of the Queue, if that transaction is overdue, returns item and
     * checks the next transaction until no overdue transactions exist.
     * @throws ParseException 
     */
    static void returnOverdue() throws ParseException
    {
        // Get the date from a week passed
        LocalDate lastweek = LocalDate.now().minusWeeks(1);

        // Get the date from the first item in the queue
        if (!transactionQueue.isEmpty())
        {
            LocalDate transactionDate = LocalDate.parse(transactionQueue.peek().date, formatter);
            // Compare the transaction date with last weeks dates
            // If the transaction is older than last weeks date, return the overdue item
            while (transactionDate.compareTo(lastweek) < 0 && !transactionQueue.isEmpty())
            {

                Transaction overdueTransaction = transactionQueue.remove();
                int overdueMemberID = overdueTransaction.memberID;
                MemberController.getMemberList().stream().filter((member) -> (member.ID == overdueMemberID)).forEach((member) ->
                {
                    member.returnItem(overdueTransaction.itemID);
                });
                if (!transactionQueue.isEmpty())
                {
                    transactionDate = LocalDate.parse(transactionQueue.peek().date, formatter);
                }
            }
        }

    }

    public static Queue<Transaction> getTransactionQueue()
    {
        return transactionQueue;
    }

    public static void setTransactionQueue(Queue<Transaction> transactionQueue)
    {
        TransactionController.transactionQueue = transactionQueue;
    }
    
    
    
}
