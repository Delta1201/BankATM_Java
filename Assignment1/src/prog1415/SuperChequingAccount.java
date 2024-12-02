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

public final class SuperChequingAccount extends ChequingAccount {
    // instance data member for storing the overdraft limit
    private BigDecimal overDraft;

    // constructors
    public SuperChequingAccount(int number, LocalDate dateOpen) {
        super(number, dateOpen);
        this.overDraft = BigDecimal.ZERO;
    }

    public SuperChequingAccount(int number, LocalDate dateOpen, String first, String last) {
        super(number, dateOpen, first, last);
        this.overDraft = BigDecimal.ZERO;
    }

    public SuperChequingAccount(int number, LocalDate dateOpen, String first, String last, BigDecimal fee) {
        super(number, dateOpen, first, last, fee);
        this.overDraft = BigDecimal.ZERO;
    }

    public SuperChequingAccount(int number, LocalDate dateOpen, String first, String last, BigDecimal fee, BigDecimal overDraft) {
        super(number, dateOpen, first, last, fee);
        setOverDraft(overDraft);
    }

    // properties
    public BigDecimal getOverDraft() {
        return this.overDraft;
    }

    public void setOverDraft(BigDecimal value) {
        this.overDraft = (value.compareTo(BigDecimal.ZERO) >= 0) ? value : BigDecimal.ZERO;
    }

    // override base class toString method to return account information and overdraft
    @Override
    public String toString() {
        return super.toString() + String.format("\n\tDraft: $%.2f", this.overDraft);
    }

    // implement the abstract withdraw method
    @Override
    public BigDecimal withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && 
            (balance.add(this.overDraft)).compareTo(amount) >= 0) {
            balance = balance.subtract(amount);
            return amount;
        } else {
            return BigDecimal.ZERO;
        }
    }
}