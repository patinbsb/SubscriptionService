/*
 *
 */
package subscriptionservice;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;
/**
 *
 * @author Patrick Goodson
 */
public class SubscriptionService
{

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws FileNotFoundException, ParseException { 
        
        // Constructing members from example csv data
        String members = "src/subscriptionservice/Data/members.csv";
        try (Scanner scannerMembers = new Scanner(new File(members)))
        {
            scannerMembers.useDelimiter(",|\r\n");
            scannerMembers.nextLine();
            while (scannerMembers.hasNext())
            {
                MemberController.createMember(Integer.parseInt(scannerMembers.next()),
                        scannerMembers.next());
            }
        }
        
        // Constructing items from example csv data
        String items = "src/subscriptionservice/Data/items.csv";
        try (Scanner scannerItems = new Scanner(new File(items)))
        {
            scannerItems.useDelimiter(",|\r\n");
            ItemController itemController = new ItemController();
            scannerItems.nextLine();
            while (scannerItems.hasNext())
            {
                itemController.createItem(Integer.parseInt(scannerItems.next()),
                        scannerItems.next(), Integer.parseInt(scannerItems.next()));
                
            }
        }
        
        // Constructing transactions from example csv data
        String transactions = "src/subscriptionservice/Data/transactions.csv";
        try (Scanner scannertransactions = new Scanner(new File(transactions)))
        {
            scannertransactions.useDelimiter(",|\r\n");
            scannertransactions.nextLine();
            while (scannertransactions.hasNext())
            {
                TransactionController.createTransaction(
                        scannertransactions.next(),
                        Integer.parseInt(scannertransactions.next()),
                        Integer.parseInt(scannertransactions.next()),
                        scannertransactions.next());
            }
        }
        
        // Add transaction existing items to each member
        TransactionController.setUpExisting();
    }
    
}
