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

    /**
     * Get summary line for the account
     * @return the string summary
     */
    public String getSummaryLine(){

        //get account balance
        double balance = this.getBalance();

        // format summary line, depeneding on balance amount
        if(balance >= 0){
            return String.format("%s, $%.02f : %s", this.uuid, balance, this.name
            );
        }
        else{
            return String.format("%s, $(%.02f) : %s", this.uuid, balance, this.name
            );
        }
    }

    /**
     * Get the balance of the account, add the transactions all together
     * @return the total balance value
     */
    public double getBalance(){
        double balance = 0;
        for(Transaction s : this.transactions){
            balance += s.getAmount();
        }
        return balance;
    }

    /**
     * Print the transaction history of the account
     */
    public void printTransHistory(){
        System.out.printf("\n Transaction history for account %s\n", this.uuid);
        for(int i = this.transactions.size()-1; i >= 0; i--){
            System.out.printf(this.transactions.get(i).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Add a new transaction in the account
     * @param amount     the amount transacted
     * @param memo       the transaction memo
     */
    public void addTransaction(double amount, String memo){
        //create new transaction object and add it to the lsit
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactions.add(newTrans);

    }
}
