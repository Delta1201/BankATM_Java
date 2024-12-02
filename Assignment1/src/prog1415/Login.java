package prog1415;
import java.util.*;
import java.text.*;
import javax.swing.*;

import prog1415.BankAccount;
import prog1415.ChequingAccount;
import prog1415.SavingsAccount;
import prog1415.SuperChequingAccount;

import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Login extends JFrame {
    private JLabel header = new JLabel("Bank ATM Login");
    private JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));

    private JLabel passwordLabel = new JLabel("Enter Password:");
    private JPasswordField passwordField = new JPasswordField(10);
    private JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
    //private JPanel center = new JPanel();

    private JLabel passwordNote = new JLabel("<html><div style='text-align: center;'><b>This being an ATM, only passwords are required to login.<br>4-digit Passwords are 1111, 2222... till 9999</b></div></html>");
    //private JPanel center2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
    private JButton enterButton = new JButton("Enter");
    private JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));

    //users list for bank
    private List<User> users;  
    private Random random;

    public Login() {
        // initialize users and the random accounts
        users = new ArrayList<>();
        random = new Random();
        // set up users and accounts
        initializeUsers();  

        // container for this
        Container con = this.getContentPane();

        // north Header
        north.add(header);
        north.setBackground(Color.BLUE);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        con.add(north, BorderLayout.NORTH);

        //center Password 
        //form grid with 2 rows
        center.setLayout(new GridLayout(2, 1)); 
        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        passwordField.setPreferredSize(new Dimension(150, 30));
        center.add(passwordPanel);
        
        passwordNote.setForeground(Color.red);
        center.add(passwordNote); 

        // combined to center
        con.add(center, BorderLayout.CENTER); 

        
        // Add KeyListener for "Enter" key in password field = chat gpt
        passwordField.addActionListener(new EnterAction());

        // south Enter button
        south.add(enterButton);
        con.add(south, BorderLayout.SOUTH);
        enterButton.setPreferredSize(new Dimension(100, 30));
        enterButton.addActionListener(new EnterAction());

        // set JFrame
        this.setTitle("ATM Login");
        this.setSize(700, 500);
        // 	Center on screen = chatgpt
        this.setLocationRelativeTo(null); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // when Enter button is clicked
    class EnterAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String enteredPassword = new String(passwordField.getPassword());

            // authenticate user
            User authenticatedUser = authenticate(enteredPassword);

            if (authenticatedUser != null) {
                //JOptionPane.showMessageDialog(Login.this, "Login successful. Welcome " + authenticatedUser.getName() + "!");
                // close login
                Login.this.dispose();  

                // open MainFrame as per the user
                new MainFrame(authenticatedUser);
            } 
            //if not a match
            else {
                JOptionPane.showMessageDialog(Login.this, "Invalid Password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                // clear the password to refill again
                passwordField.setText("");  
            }
        }
    }

    // authenticate user by password
    public User authenticate(String password) {
        for (User user : users) {
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // initialize users and their accounts
    private void initializeUsers() {
        // user names and their passwords
        String[] names = {"Matsya Agastya", "Kurma Atri", "Varah Bhardwaja", "Narsinh Gautama", "Bali Jamadagni", "Ram Vashistha", "Krishna Vishwamitra", "Buddh Kashyap", "Kalki Solanki"};
        String[] passwords = {"1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999"};

        for (int i = 0; i < names.length; i++) {
            String[] nameParts = names[i].split(" ");
            String firstName = nameParts.length > 0 ? nameParts[0] : "Unknown";
            String lastName = nameParts.length > 1 ? nameParts[1] : "Unknown";

            User user = new User(firstName + " " + lastName, passwords[i]);

            // randomly assign accounts to users from Chequing, Savings, SuperChequing
            //boolean hasChequing = random.nextBoolean();
            boolean hasSavings = random.nextBoolean();            
            boolean hasSuperChequing = random.nextBoolean();
            
            //account open dates
            LocalDate cOpenDate = LocalDate.now().minusDays(random.nextInt(3650));
            //LocalDate sOpenDate = LocalDate.now().minusDays(random.nextInt(3650));
            //LocalDate scOpenDate = LocalDate.now().minusDays(random.nextInt(3650));
            
            //user need a chequing account any way
            BankAccount ca = new ChequingAccount(
                    generateAccountNumber(),
                    //opened within 10 years
                    //LocalDate.now().minusDays(random.nextInt(3650)),
                    cOpenDate,
                    firstName,
                    lastName,
                    ChequingAccount.getDefaultFee()
                );
                ca.deposit(new BigDecimal("500.00"));
                user.addAccount(ca);
            
            //user need atleast one account
//            if (!hasSavings && !hasChequing && !hasSuperChequing) {
//                hasSavings = true;
//            }
                              
//                if (hasChequing) {
//                    BankAccount ca = new ChequingAccount(
//                        generateAccountNumber(),
//                        LocalDate.now().minusDays(random.nextInt(365)),
//                        firstName,
//                        lastName,
//                        ChequingAccount.getDefaultFee()
//                    );
//                 // set initial balance random
//                    //ca.deposit(BigDecimal.valueOf(new Random().nextDouble() * 1000));
//                    ca.deposit(new BigDecimal("500.00")); 
//                    user.addAccount(ca);
//                }
                
              //same as chequing  
            if (hasSavings) {
            	//savings cannot be opened before chequing
            	LocalDate sOpenDate;
                do {
                    sOpenDate = LocalDate.now().minusDays(random.nextInt(3650)); 
                } while (!sOpenDate.isAfter(cOpenDate));
                
                BankAccount sa = new SavingsAccount(
                    generateAccountNumber(),
                    sOpenDate,
                    firstName,
                    lastName,
                    // remove the initial balance
                    SavingsAccount.getDefaultRate()
                    //SavingsAccount.getCharge()                    
                );
                
                sa.deposit(new BigDecimal("1000.00"));                 
                user.addAccount(sa);
                
                
            }                     
            
            //same as savings
            if (hasSuperChequing) {
            	//savings cannot be opened before chequing
            	LocalDate scOpenDate;
                do {
                    scOpenDate = LocalDate.now().minusDays(random.nextInt(3650)); 
                } while (!scOpenDate.isAfter(cOpenDate));
                
                BankAccount sca = new SuperChequingAccount(
                    generateAccountNumber(),
                    //LocalDate.now().minusDays(random.nextInt(2650)),
                    scOpenDate,
                    firstName,
                    lastName,
                    ChequingAccount.getDefaultFee(),
                    new BigDecimal("100.00")
                );
                sca.deposit(new BigDecimal("1500.00")); 
                user.addAccount(sca);
            }

            users.add(user); 
        }
    }

    
    
    // random 6-digit account number
    private int generateAccountNumber() {
        return 100000 + random.nextInt(900000);
    }
}
