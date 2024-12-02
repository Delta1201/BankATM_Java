package prog1415;
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.*;

public abstract class BankAccount extends Object {
	//*****************************************************
	//instance variables or data fields
	//Note:  use the private keyword eventhough it is the
	//       default scope when a modifier is not specified
	//*****************************************************
	    private final int number;
	    private String first;
	    private String last;
	    protected BigDecimal balance;

	  //*****************************************************
	  //Containment - one class declares an instance of another
	  //*****************************************************
	    private final LocalDate dateOpen;

	  //*****************************************************
	  //static and constant members - shared by all accounts
	  //*****************************************************
	    private static int count = 0;
	    private static final String BANKNAME = "Bank Of Vanscoy";

	  //*****************************************************
	  //constructors
	  //*****************************************************
	    public BankAccount(int number, LocalDate dateOpen) {
	        setAccount();
	        this.number = (number > 0) ? number : 0;
	        this.dateOpen = (dateOpen.isBefore(LocalDate.now()) || dateOpen.isEqual(LocalDate.now())) ? dateOpen : LocalDate.now();
	    }

	    public BankAccount(int number, LocalDate dateOpen, String first, String last) {
	        setAccount();
	        this.number = (number > 0) ? number : 0;
	        this.dateOpen = (dateOpen.isBefore(LocalDate.now()) || dateOpen.isEqual(LocalDate.now())) ? dateOpen : LocalDate.now();
	        this.setFirst(first);
	        this.setLast(last);
	    }

	  //*****************************************************
	  //private method called by constructors to initialize fields
	  //*****************************************************
	    private void setAccount() {
	        this.first = "Unknown";
	        this.last = "Unknown";
	        this.balance = BigDecimal.ZERO;
	        count++;
	    }
	    
	  //*****************************************************
	  //destructor == it says there are no deconstructors = called finalizers
	  //*****************************************************
	  		//~BankAccount()
	  		//{
	  		//	count--;
	  		//}
	  
	  	//*****************************************************
		//public interface
		//*****************************************************
	    @Override
	    protected void finalize() throws Throwable {
	        count--;
	        super.finalize();
	    }

		//public properties	    
	    public String getFirst() {
	        return this.first;
	    }

	    public void setFirst(String first) {
	        this.first = (first.length() > 0) ? first : this.first;
	    }

	    public String getLast() {
	        return this.last;
	    }

	    public void setLast(String last) {
	        this.last = (last.length() > 0) ? last : this.last;
	    }

	  //read-only properties
	    public int getNumber() {
	        return this.number;
	    }

	    public String getDateOpen() {
	        return this.dateOpen.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
	    }

	    public String getBalance() {
	        return String.format("$%.2f", this.balance);
	    }

	    // static read-only properties
	    public static String getBankName() {
	        return BANKNAME;
	    }

	    public static int getCount() {
	        return count;
	    }

	    // public methods
	    public BigDecimal deposit(BigDecimal amount) {
	        if (amount.compareTo(BigDecimal.ZERO) > 0) {
	            this.balance = this.balance.add(amount);
	            return this.balance;
	        } else {
	            return BigDecimal.ZERO;
	        }
	    }

	    // override the base class toString method to return Account information
	    @Override
	    public String toString() {
	        return String.format("\n\tAccount: %d\n\tName: %s\n\tBalance: %s\n\tOpened: %s\n",
	                this.getNumber(), this.getFirst() + " " + this.getLast(), this.getBalance(), this.getDateOpen());
	    }

	    // Override the base class equals method
	    // Two accounts are considered equal if they have the same account number
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        BankAccount temp = (BankAccount) obj;
	        return this.number == temp.number;
	    }

	    // Abstract method to ensure all derived classes implement a withdraw method
	    public abstract BigDecimal withdraw(BigDecimal amount);
	    
}

