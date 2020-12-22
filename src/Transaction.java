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
}
