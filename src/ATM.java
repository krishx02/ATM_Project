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

    public static void printUserMenu(User theUser, Scanner sc){

        // print summary of users accounts
        theUser.printAccountSummary();

        // initialize
        int choice;

        //user menu
        do{
            System.out.printf("Welcome %s, what would you like to do?", theUser.getFirstName());
            System.out.println(" 1) Show account transaction history");
            System.out.println(" 2) Withdrawl");
            System.out.println(" 3) Deposit");
            System.out.println(" 4) Transfer");
            System.out.println(" 5) Quit");
            System.out.println();
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if(choice < 1 || choice > 5){
                System.out.println("Invalid choice. Please choose 1-5");
            }

        }
        while(choice < 1 || choice > 5);

        //process choice
        switch (choice){
            case 1:
                ATM.showTransHistory(theUser, sc);
                break;
            case 2:
                ATM.withdrawlFunds(theUser, sc);
                break;
            case 3:
                ATM.depositFunds(theUser, sc);
                break;
            case 4:
                ATM.transferFunds(theUser, sc);
                break;
        }

        //redisplay this menu unless user wants to quit
        if(choice != 5){
            ATM.printUserMenu(theUser, sc);
        }
    }
    public static void showTransHistory(User theUser, Scanner sc){
        int theAcct;
        //get account to display transaction history
        do{
            System.out.printf("Enter the number (1-%d) of the account whose transactions " +
                    "you  want to see: ", theUser.numAccounts());
            theAcct = sc.nextInt()-1;
            if(theAcct < 0 || theAcct >= theUser.numAccounts()){
                System.out.println("Invalid account. Please try again.");

            }
        }
        while(theAcct < 0 || theAcct >= theUser.numAccounts());

        //print transaction history
        theUser.printAccTransHistory(theAcct);
    }
}
