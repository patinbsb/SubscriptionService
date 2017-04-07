/*
 *
 */
package subscriptionservice;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Patrick Goodson
 */

public class MemberController
{
    int currentMember;
    static String username = "patrick";
    static String password = "brown";
    //LinkedList to allow cheap dynamic restructuring of the memberlist
    static LinkedList<Member> memberList = new LinkedList<>();
    
    /**
     * Basic authentication
     * @param user the username
     * @param pass the password
     * @return True on user&password match
     */
    static boolean authenticate(String user, String pass)
    {
        return username.equals(user) && password.equals(pass);
    }
    
    /**
     * Adds a new member to the memberList, if no ID provided a new ID will be generated
     * ensures ID's are unique.
     * @param ID
     * @param name 
     */
    static void createMember(int ID, String name)
    {
        // Check if no ID is given
        if (ID == -1)
        {
            // Create uniqueID
            int uniqueID = ThreadLocalRandom.current().nextInt(1000,100000);
            // Check for uniqueness in the list
            boolean noRepeats = false;
            while (noRepeats == false)
            {
                noRepeats = true;
                for(Member member : memberList)
                {
                    if (uniqueID == member.getID())
                    {
                        uniqueID = ThreadLocalRandom.current().nextInt(1000,100000);
                        noRepeats = false;
                    }
                }
            }
            memberList.add(new Member(uniqueID, name));
        }
        else
        {
            memberList.add(new Member(ID,name));
        }
    }
    
    /**
     * Removes a member by ID
     * Ensures Subscribed items are returned.
     * @param memberID 
     */
    static void removeMember(int memberID)
    {
        Member member = getMember(memberID);
        member.clearSubscriptions();
        memberList.remove(member);
    }
    
    static Member getMember(int memberID)
    {
        for (Member member : memberList)
        {
            if (member.ID == memberID)
            {
                return member;
            }
        }
        return null;
    }

    public static LinkedList<Member> getMemberList()
    {
        return memberList;
    }

    public static void setMemberList(LinkedList<Member> memberList)
    {
        MemberController.memberList = memberList;
    }
    
    
}
