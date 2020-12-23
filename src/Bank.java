import java.util.ArrayList;
import java.util.Random;
public class Bank {
    /*
     *
     */
    private String name;
    /*
     *
     */
    private ArrayList<User> user;
    /*
     *
     */
    private ArrayList<Account> accounts;
    /*
     *
     */
    private String address;

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
            if(this.user.contains(uuid))
            for(User u : this.user){
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
     *
     * @param theAccount - the account to add
     */
    public void addAccount(Account theAccount){
        this.accounts.add(theAccount);
    }

    public User addUser(String firstName, String lastName, String pin){
        //creates user object and is added to the list
        User newUser = new User(firstName, lastName, pin, this);
        this.user.add(newUser);

    }
}
