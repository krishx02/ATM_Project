import java.util.Date;

public class Transaction {
    /*
     *The amount of this transaction
    */
    private double amount;
    /*
     *The date and time of the transaction
     */
    private Date timestamp;
    /*
     * A memo for the transaction
     */
    private String memo;
    /*
     *The account in which the transaction is held.
     */
    private Account inAccount;

    /**
     * Creates a new transaction
     * @param amount - amount being transacted
     * @param inAccount - in the account that the transaction belongs to
     */
    public Transaction(double amount, Account inAccount){

        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
        this.memo = "";
    }

    /**
     * Creates a new transaction
     * @param amount - amount transacted
     * @param memo - the memo for the transaction
     * @param inAccount - the account the transaction belongs to
     */
    public Transaction(double amount, String memo, Account inAccount){
        // call the two-arg constructor. this is another way to call a constructor if you overload the constructor
        this(amount, inAccount);
        //set memo
        this.memo = memo;
    }

    /**
     * The amount of the transaction
     * @return the amount
     */
    public double getAmount(){
        return this.amount;
    }

    /**
     * Get a string summarizing the transaction
     * @return    the summary string
     */
    public String getSummaryLine(){

        if(this.amount >= 0){
            return String.format("%s : $%.02f : %s ", this.timestamp.toString(),
            this.amount, this.memo);
        }
        else{
            return String.format("%s : $(%.02f) : %s ", this.timestamp.toString(),
                    this.amount, this.memo);
        }
    }
}
