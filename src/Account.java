import java.util.ArrayList;

public class Account {
    /*
     *Name of the account
     */
    private String name;
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
    /**
    * Creating a new account
    * @param name - name of account
    * @param holder - User object that holds the account
    * @param theBank - the bank that issues the account
    */
    public Account(String name, User holder, Bank theBank){
        //Sets the account name, holder
        this.name = name;
        this.holder = holder;

        //get the account UUID
        this.uuid = theBank.getNewAccountUUID();

        //initialize transactions to empty list
        this.transactions = new ArrayList<Transaction>();

        //Add the account to the holder and the bank list
        //This new object that you are creating with the constructor is the term (this) and you can call methods from
        //other classes, and in this case we are going to add the holder and the bank account associated with that obj.
        //It is the same object. Adding same account in each list.
        //Moved to Bank class
    }

    /**
     * Get the account ID
     * @return uuid
     */
    public String getUUID(){
        return this.uuid;
    }
}
