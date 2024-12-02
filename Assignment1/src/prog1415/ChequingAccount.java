package prog1415;
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.*;

//public abstract class ChequingAccount : BankAccount
public class ChequingAccount extends BankAccount {
    // instance data member for storing the monthly fee
    private BigDecimal fee;
    
    // class member for storing the default fee
    private static BigDecimal defaultFee = new BigDecimal("5.00");

    // constructors
    public ChequingAccount(int number, LocalDate dateOpen) {
        super(number, dateOpen);
        this.fee = defaultFee;
    }

    public ChequingAccount(int number, LocalDate dateOpen, String first, String last) {
        super(number, dateOpen, first, last);
        this.fee = defaultFee;
    }

    public ChequingAccount(int number, LocalDate dateOpen, String first, String last, BigDecimal fee) {
        super(number, dateOpen, first, last);
        setFee(fee);
    }

    // object property (getter and setter)
    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal value) {
        this.fee = (value.compareTo(BigDecimal.ZERO) >= 0) ? value : defaultFee;
    }

    // class property (getter and setter)
    public static BigDecimal getDefaultFee() {
        return defaultFee;
    }

    public static void setDefaultFee(BigDecimal value) {
        defaultFee = (value.compareTo(BigDecimal.ZERO) > 0) ? value : defaultFee;
    }

    // public method to apply the fee charged to the account and return the balance
    public BigDecimal applyFee() {
        balance = balance.subtract(this.fee);
        return this.balance;
    }

    // override base class toString method to return account information and fee
    @Override
    public String toString() {
        return super.toString() + String.format("\n\tFee: $%.2f", this.fee);
    }

    // implement the abstract withdraw method
    @Override
    public BigDecimal withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            return amount;
        } else {
            return BigDecimal.ZERO;
        }
    }
}
