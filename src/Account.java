import java.util.ArrayList;

public class Account {
    /*
     *Name of the account
     */
    private String name;
    /*
     *Balance of account
     */
    private double balance;
    /*
     *The account ID number
     */
    private String uuid;
    /*
     *The User object that owns the account
     */
    private User holder;
    /*
     *The list of transactions for this action
     */
    private ArrayList<Transaction> transactions;
}
