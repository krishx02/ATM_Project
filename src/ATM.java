import java.util.Scanner;


public class ATM {
    public static void main(String[] args) {
        //initialize scanner
        Scanner sc = new Scanner(System.in);

        //initialize bank
        Bank theBank = new Bank("Bank of Gringotts");

        //add users to bank, creates savings account
        User aUser = theBank.addUser("John", "Potter", "1234");

        //add checking account
        Account newAccount = new Account("Checking", aUser, theBank);
        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User curUser;
        while(true){

            // stay in login prompt until successful login
            curUser = ATM.mainMenuPrompt(theBank, sc);

            // stay in main menu until user quits
            ATM.printUserMenu(curUser, sc);
        }
    }

    /**
     * Print ATM's login menu
     * @param theBank  the Bank object whose accounts to use
     * @param sc       the Scanner object to use for user input
     * @return         the authenticated user object
     */
    public static User mainMenuPrompt(Bank theBank, Scanner sc){
        //initialize
        String userID;
        String pin;
        User authUser;

        //prompt the user ID and pin until it is correct
        System.out.printf("\n\nWelcome to the %s\n\n", theBank.getName());
        do {

            System.out.print("Enter user ID: ");
            userID = sc.nextLine();
            System.out.print("Enter pin: ");
            pin = sc.nextLine();

            //try to get the user obj corresponding to the ID and pin combo
            authUser = theBank.userLogin(userID, pin);
            if(authUser == null){
                System.out.println("Incorrect userID/pin combo, please try again.");
            }
        }
        while( authUser == null); //continue looping until successful login

        return authUser;
    }
}
