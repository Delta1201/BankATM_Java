package prog1415;
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String password;
    private List<BankAccount> accounts;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }
}
