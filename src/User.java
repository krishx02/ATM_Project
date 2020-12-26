import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
public class User {
    /*
     *First name of user
     */
    private String firstName;
    /*
     *Last name of User
     */
    private String lastName;
    /*
     *ID Number of the User
     */
    private String uuid;
    /*
     *The MD5 hash of the pin number for the user
     */
    private byte pinHash[];
    /*
    *List of accounts the user has
     */
    private ArrayList<Account> accounts;

    /**
    * Creating user
    * @param firstName - User's first name
    * @param lastName  - User's last name
    * @param pin - Users Account Pin Number
    * @param theBank - The Bank Object that the user is a customer of
    *
     */
    public User(String firstName, String lastName, String pin, Bank theBank){
        //Setting the users name
        this.firstName = firstName;
        this.lastName = lastName;

        //Store pin MG5 Hash, rather than the original value, for security purposes
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        }
        catch (NoSuchAlgorithmException e) {
            //These lines of code are useless since we know
            //MD5 is a valid Algorithm, needed to handle exception somehow
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        // Get a new ID for each user
        this.uuid = theBank.getNewUserUUID(); //Still have to complete this method

        //Empty list of accounts
        this.accounts = new ArrayList<Account>();

        //Print log message
        System.out.printf("New User %s, %s with ID %s created.\n", lastName,
                firstName, this.uuid);
    }
    /**
    * Adds an account for the user. Called in the Account Class
    * @param theAccount  Account that is going to be added to the ArrayList of accounts
    *
     */
    public void addAccount(Account theAccount){
        this.accounts.add(theAccount);
    }

    /**
     * Return user ID
     * @return the UUID
     */
    public String getUUID(){
        return this.uuid;
    }

    /**
     * Check if a pin is the user ID pin
     * @param pin - pin to check
     * @return      whether the pin is valid or not
     */
    public boolean validatePin(String pin){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()), this.pinHash);
        }
        catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        return false;

    }

    /**
     * Return the user's first name
     * @return the first name
     */
    public String getFirstName(){
        return this.firstName;
    }

    /**
     * Prints summaries for accounts for the user
     */
    public void printAccountSummary(){

        System.out.printf("\n\n %s's accounts summary\n", this.firstName);
        for(int i = 0; i < this.accounts.size(); i++){
            System.out.printf("  %d) %s\n", i+1, this.accounts.get(i).getSummaryLine());
        }
        System.out.println();
    }

    /**
     * Get the number of accounts of the user
     * @return the number of the accounts that the user has
     */
    public int numAccounts(){
        return this.accounts.size();
    }

    /**
     * Print transaction history for a particular account
     * @param acctIdx the index of the account to use
     */
    public void printAccTransHistory(int acctIdx){
        this.accounts.get(acctIdx).printTransHistory();
    }

    /**
     * Get balance of a particular account
     * @param accIdx      the index of the account to use
     * @return            the balance of the account
     */
    public double getAcctBalance(int accIdx){
        return this.accounts.get(accIdx).getBalance();
    }

    /**
     * Get the UUID of a particular account
     * @param acctIdx   the index of the accoutn to use
     * @return  the string of the account UUID
     */
    public String getAcctUUID(int acctIdx){
        return this.accounts.get(acctIdx).getUUID();
    }

    /**
     * Add a transaction to the particular account
     * @param acctIdx      the index of the account
     * @param amount       the amount of the transaction
     * @param memo         the memo of the transaction
     */
    public void addAcctTransaction(int acctIdx, double amount, String memo){
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
}
