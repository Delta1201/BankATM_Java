package prog1415;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.math.*;



public class MainFrame extends JFrame {
    private User user;
    private JLabel header = new JLabel("Welcome, ");
    private JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel center = new JPanel();
    private JLabel accountDetailsLabel = new JLabel();
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JButton exitButton = new JButton("Exit");
    private JPanel south = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private ClockPanel clockPanel; 
    private JButton backButton = new JButton("Back");
    private JButton exitAccountButton = new JButton("Log Out");

    public MainFrame(User user) {
        this.user = user;

        Container con = this.getContentPane();
        // initialize the back button to go back to main page if the user has more than 1 account
        //give dimension
        backButton.setPreferredSize(new Dimension(100, 30));
        //event
        backButton.addActionListener(new BackButtonAction());
        //add to the panel
        buttonPanel.add(backButton);
        //hide it as it is not necessary
        backButton.setVisible(false);
        
        //north
        north.add(header);
        north.setBackground(Color.BLUE);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        con.add(north, BorderLayout.NORTH);

        //east
        clockPanel = new ClockPanel();
        con.add(clockPanel, BorderLayout.EAST);

        //center
        center.setLayout(new BorderLayout());
        accountDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        accountDetailsLabel.setVerticalAlignment(SwingConstants.TOP);
        center.add(accountDetailsLabel, BorderLayout.CENTER);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        center.add(buttonPanel, BorderLayout.SOUTH);
        con.add(center, BorderLayout.CENTER);

        //south
        south.add(exitButton);
        //con.add(south, BorderLayout.SOUTH);
        exitButton.setPreferredSize(new Dimension(100, 30));
        exitButton.addActionListener(new ExitAction());
        
        //ExitAccountAction
        south.add(exitAccountButton);        
        exitAccountButton.setPreferredSize(new Dimension(150, 30));
        exitAccountButton.addActionListener(new ExitAccountAction());
        
        //add both the buttons
        con.add(south, BorderLayout.SOUTH);

        // header text
        header.setText("Welcome, " + user.getName());

        // buttons or account details based on the number of accounts
        if (user.getAccounts().size() > 1) {
            for (BankAccount account : user.getAccounts()) {
                JButton accountButton = new JButton(account.getClass().getSimpleName() + " (" + account.getNumber() + ")");
                accountButton.setPreferredSize(new Dimension(200, 30));
                accountButton.addActionListener(new AccountButtonAction(account));
                buttonPanel.add(accountButton);
                }
            
            //buttonPanel.add(exitAccountButton);  
            accountDetailsLabel.setText("<html>Select an account to view details.</html>");
        } else if (user.getAccounts().size() == 1) {
            BankAccount account = user.getAccounts().get(0);
            displayAccountDetails(account);
        } else {
            accountDetailsLabel.setText("<html>No accounts available.</html>");
        }
        //set Jframe
        this.setTitle("Dee's ATM");
        this.setBounds(100, 100, 700, 500);
        // 	Center on screen = chatgpt
        this.setLocationRelativeTo(null); 

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void displayAccountDetails(BankAccount account) {
        StringBuilder details = new StringBuilder("<html>");
        details.append("<h2>").append(account.getClass().getSimpleName()).append("</h2>");
        details.append("<p>Account Number: ").append(account.getNumber()).append("</p>");
        details.append("<p>Name: ").append(account.getFirst()).append(" ").append(account.getLast()).append("</p>");
        details.append("<p>Balance: ").append(account.getBalance()).append("</p>");
        details.append("<p>Date Opened: ").append(account.getDateOpen()).append("</p>");
        
        if (account instanceof SavingsAccount) {
            SavingsAccount sa = (SavingsAccount) account;
            details.append("<p>Interest Rate: ").append(sa.getRate().multiply(new BigDecimal("100"))).append("%</p><p>(Please note there is a fee of $0.50 for each withdrawl) ");
        }
        if (account instanceof ChequingAccount) {
            ChequingAccount ca = (ChequingAccount) account;
            details.append("<p>Fee: $").append(ca.getFee()).append("</p>");
        }
        if (account instanceof SuperChequingAccount) {
            SuperChequingAccount sca = (SuperChequingAccount) account;
            details.append("<p>Overdraft: $").append(sca.getOverDraft()).append("</p>");
        }
        details.append("</html>");

        accountDetailsLabel.setText(details.toString());
        
        // show back button
        //backButton.setVisible(true);
        
        // remove other buttons as only one account
        buttonPanel.removeAll();

        // buttons for transactions
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        //JButton exitAccountButton = new JButton("Exit Account");

        depositButton.setPreferredSize(new Dimension(100, 30));
        withdrawButton.setPreferredSize(new Dimension(100, 30));
        //exitAccountButton.setPreferredSize(new Dimension(150, 30));

        depositButton.addActionListener(new TransactionAction(account, "Deposit"));
        withdrawButton.addActionListener(new TransactionAction(account, "Withdraw"));
        //exitAccountButton.addActionListener(new ExitAccountAction());

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
//        buttonPanel.add(exitAccountButton);
        
        // back button only for user with more than 1 account
        if (user.getAccounts().size() > 1) {
        	buttonPanel.add(backButton);  
        	backButton.setVisible(true);
              
        }     

        // take back to mainframe of that account
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
    
    // back button returns to the logged in account
    class BackButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // clear account details and show account buttons
            accountDetailsLabel.setText("<html>Select an account to view details.</html>");
            // hide back button
            backButton.setVisible(false); 

            buttonPanel.removeAll();
            for (BankAccount account : user.getAccounts()) {
                JButton accountButton = new JButton(account.getClass().getSimpleName() + " (" + account.getNumber() + ")");
                accountButton.setPreferredSize(new Dimension(200, 30));
                accountButton.addActionListener(new AccountButtonAction(account));
                buttonPanel.add(accountButton);
            }
//            JButton exitAccountButton = new JButton("Exit Account");
//            exitAccountButton.setPreferredSize(new Dimension(150, 30));
//            exitAccountButton.addActionListener(new ExitAccountAction());
//            buttonPanel.add(exitAccountButton);  
            
            buttonPanel.revalidate();
            buttonPanel.repaint();
        }
    }

    // account buttons
    class AccountButtonAction implements ActionListener {
        private BankAccount account;

        public AccountButtonAction(BankAccount account) {
            this.account = account;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            displayAccountDetails(account);
        }
    }

    // transactions
    class TransactionAction implements ActionListener {
        private BankAccount account;
        private String type;

        public TransactionAction(BankAccount account, String type) {
            this.account = account;
            this.type = type;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String amountStr = JOptionPane.showInputDialog(MainFrame.this, "Enter amount to " + type.toLowerCase() + ":");
            if (amountStr != null) {
                try {
                    BigDecimal amount = new BigDecimal(amountStr);
                    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                        JOptionPane.showMessageDialog(MainFrame.this, "Amount must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (type.equals("Deposit")) {
                        account.deposit(amount);
                        JOptionPane.showMessageDialog(MainFrame.this, "Deposited $" + amount);
                    } else if (type.equals("Withdraw")) {
                        BigDecimal result = account.withdraw(amount);
                        if (result.compareTo(BigDecimal.ZERO) > 0) {
                            JOptionPane.showMessageDialog(MainFrame.this, "Withdrew $" + amount);
                        } else {
                            JOptionPane.showMessageDialog(MainFrame.this, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    // reload details
                    displayAccountDetails(account);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // exit account and go back to account selection
    class ExitAccountAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // new instance of login
            new WelcomePage(); 
            // close mainframe
            MainFrame.this.dispose();
        }
    }

    // exit button
    class ExitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(MainFrame.this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    // clockPanel
    class ClockPanel extends JPanel {
        private JLabel timeLabel;

        public ClockPanel() {
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            this.add(timeLabel);
            updateClock(); 
            Timer timer = new Timer(1000, e -> updateClock());
            timer.start(); 
        }

        private void updateClock() {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            timeLabel.setText(now.format(formatter));
        }
    }
}
