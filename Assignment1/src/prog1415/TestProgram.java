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

//Object Oriented Programming
//P Vanscoy


//the following program test the public interface
//for bank account objects and demonstrates how to
//implement polymorphism

//The benefit of writing polymorphic programs is that the program
//can work with common object types even ones that have not yet been
//created.  The program only calls those methods that are common to all
//objects.  Polymorphic methods have different implementions depending on the object type.
//Polymorphic methods include overloaded, virtual, and abstract methods.

//Window Forms
//using System;
//using System.Windows.Forms;

//UWP
//using Windows.UI.Xaml;
//using Windows.UI.Popups;

//Java
//import javax.swing.*; //use JOptionPane to display a message dialog

//Note: MessageBox.Show for Forms in this example is the same as 
//MessageDialog msg = new MessageDialog(...) in UWP

//Assignment 1 Requirements:
//1. Convert all C# to Java
//2. Display the output in Test Program using JOptionPane or using the Console
public class TestProgram {
    public static void main(String[] args) {
        // test SavingsAccount
        SavingsAccount save = new SavingsAccount(3333, LocalDate.now());
        save.setRate(new BigDecimal("0.02"));
        save.deposit(new BigDecimal("300.00"));
        save.applyInterest();
        save.withdraw(new BigDecimal("25.00"));
        JOptionPane.showMessageDialog(null, save.toString(), BankAccount.getBankName(), JOptionPane.INFORMATION_MESSAGE);

        // test ChequingAccount
        ChequingAccount ch = new ChequingAccount(4444, LocalDate.now());
        ch.setFee(new BigDecimal("5.55"));
        ch.deposit(new BigDecimal("100.00"));
        ch.applyFee();
        ch.withdraw(new BigDecimal("50.00"));
        JOptionPane.showMessageDialog(null, ch.toString(), BankAccount.getBankName(), JOptionPane.INFORMATION_MESSAGE);

        // test SuperChequingAccount
        SuperChequingAccount superCh = new SuperChequingAccount(5555, LocalDate.now());
        superCh.setFee(new BigDecimal("3.25"));
        superCh.setOverDraft(new BigDecimal("200.00"));
        superCh.deposit(new BigDecimal("200.00"));
        superCh.applyFee();
        superCh.withdraw(new BigDecimal("350.00"));
        JOptionPane.showMessageDialog(null, superCh.toString(), BankAccount.getBankName(), JOptionPane.INFORMATION_MESSAGE);

        // Polymorphic programming - use a base class reference
        BankAccount b = new ChequingAccount(7777, LocalDate.now());
        b.deposit(new BigDecimal("100.00"));
        b.withdraw(new BigDecimal("50.00"));
        JOptionPane.showMessageDialog(null, b.toString(), BankAccount.getBankName(), JOptionPane.INFORMATION_MESSAGE);

        // Polymorphic programming - use a base class array to
        // store and retrieve any derived bank account type
        BankAccount[] accounts = new BankAccount[3];
        accounts[0] = new ChequingAccount(1234, LocalDate.now());
        accounts[1] = new SuperChequingAccount(5678, LocalDate.now());
        accounts[2] = new SavingsAccount(9876, LocalDate.now());
        for (BankAccount account : accounts) {
            JOptionPane.showMessageDialog(null, account.toString(), BankAccount.getBankName(), JOptionPane.INFORMATION_MESSAGE);
        }

        // If you want to call unique methods defined in the derived class
        // you must check and cast the base reference into a derived reference
        // Use the instanceof operator to check what type of class a reference
        // points to
        if (accounts[0] instanceof ChequingAccount) {
            // cast the object to its correct type
            ChequingAccount temp = (ChequingAccount) accounts[0];
            // access specific derived class members
            temp.applyFee();
            JOptionPane.showMessageDialog(null, temp.toString(), BankAccount.getBankName(), JOptionPane.INFORMATION_MESSAGE);
        }
    }
}