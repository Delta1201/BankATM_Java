package prog1415;
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.*;

public class SavingsAccount extends BankAccount {
    // instance data member for storing the interest rate
    private BigDecimal rate;

    // class member for storing the default interest rate
    private static BigDecimal defaultRate = new BigDecimal("0.04");

    // class member for storing the default transaction charge
    private static BigDecimal charge = new BigDecimal("0.50");

    // constructors
    public SavingsAccount(int number, LocalDate dateOpen) {
        super(number, dateOpen);
        this.rate = defaultRate;
    }

    public SavingsAccount(int number, LocalDate dateOpen, String first, String last) {
        super(number, dateOpen, first, last);
        this.rate = defaultRate;
    }

    public SavingsAccount(int number, LocalDate dateOpen, String first, String last, BigDecimal rate) {
        super(number, dateOpen, first, last);
        setRate(rate);
    }

    // object properties
    public BigDecimal getRate() {
        return this.rate;
    }

    public void setRate(BigDecimal value) {
        this.rate = (value.compareTo(BigDecimal.ZERO) >= 0) ? value : defaultRate;
    }

    // class properties
    public static BigDecimal getDefaultRate() {
        return defaultRate;
    }

    public static void setDefaultRate(BigDecimal value) {
        if (value.compareTo(BigDecimal.ONE) > 0) {
            defaultRate = value.divide(new BigDecimal("100"));
        } else if (value.compareTo(BigDecimal.ZERO) > 0) {
            defaultRate = value;
        }
    }

    public static BigDecimal getCharge() {
        return charge;
    }

    public static void setCharge(BigDecimal value) {
        charge = (value.compareTo(BigDecimal.ZERO) > 0) ? value : charge;
    }

    // public method to calculate the interest earned and add to the account balance
    public BigDecimal applyInterest() {
        BigDecimal interest = balance.multiply(this.rate);
        balance = balance.add(interest);
        return interest;
    }

    // override base class toString method to return account information and rate
    @Override
    public String toString() {
        return super.toString() + String.format("\n\tRate: %.2f%%", this.rate.multiply(new BigDecimal("100")));
    }

    // implement the abstract withdraw method
    public BigDecimal withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && balance.compareTo(amount.add(charge)) >= 0) {
            amount = amount.add(charge);
            balance = balance.subtract(amount);
            return amount;
        } else {
            return BigDecimal.ZERO;
        }
    }

	public BigDecimal withdraw1(BigDecimal amount) {
		// TODO Auto-generated method stub
		return null;
	}
}
