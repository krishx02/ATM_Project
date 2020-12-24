import java.util.ArrayList;
import java.util.Random;
public class Bank {
    /*
     * Name of bank
     */
    private String name;
    /*
     * Users with said bank
     */
    private ArrayList<User> users;
    /*
     * Accounts associated with the bank
     */
    private ArrayList<Account> accounts;

    /**
     * Creates a new bank obj with empty users
     * @param name    the name of the bank
     */
    public Bank(String name){

        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }
    /**
     * Generates unique ID for user
     * @return UUID
     */
    public String getNewUserUUID(){

        //initialize return
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        // Continue looping until you get a unique ID
        do{
            //Generate random num

            uuid = "";
            for(int i = 0; i < len; i++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            //check unique
            nonUnique = false;
            if(this.users.contains(uuid))
            for(User u : this.users){
                if(uuid.compareTo(u.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        }
        while(nonUnique);

        return uuid;
    }

    /**
     * Generate a new universally unique ID for an account
     * @return
     */
    public String getNewAccountUUID(){
        //initialize return
        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        // Continue looping until you get a unique ID
        do{
            //Generate random num

            uuid = "";
            for(int i = 0; i < len; i++){
                uuid += ((Integer)rng.nextInt(10)).toString();
            }

            //check unique
            nonUnique = false;
            for(Account a : this.accounts){
                if(uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        }
        while(nonUnique);

        return uuid;
    }

    /**
     * Add an account
     * @param theAccount   the account to add
     */
    public void addAccount(Account theAccount){
        this.accounts.add(theAccount);
    }
    /**
     * Creates new user of bank
     * @param firstName Users firstName
     * @param lastName Users lastName
     * @param pin - User's pin
     * @return new user object
     */
    public User addUser(String firstName, String lastName, String pin){
        //creates user object and is added to the list
        User newUser = new User(firstName, lastName, pin, this);
        this.users.add(newUser);

        //create a savings account and adds bank accounts to list
        Account newAccount = new Account("Savings", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newUser;
    }

    /**
     * Get the user object associated with a particular userID and pin, if valid
     * @param userID   the UUID of the user login
     * @param pin      the pin of th euser
     * @return         the user associated with the login, or null if unsuccessful
     */
    public User userLogin(String userID, String pin){

        //search through list of users
        for(User u: this.users){

            //check user ID
            if(u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
                return u;
            }
        }
        //No user found with matching ID and pin
        return null;
    }

    /**
     * Returns the name of the bank
     * @return
     */
    public String getName(){
        return this.name;
    }
}
