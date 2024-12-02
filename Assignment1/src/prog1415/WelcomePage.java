package prog1415;
import java.util.*;
import java.text.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JFrame {
    // Header Label
    private JLabel header = new JLabel("Welcome to Bank Of Dee ATM");
    private JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // message to display
    private JLabel imageLabel = new JLabel();
    private JLabel welcomeMessage = new JLabel("<html><div style='text-align: center;'>"
//            + "Welcome to the Bank Of Dee ATM<br/>"
            + "Please wait while we process your ATM CARD</div></html>");
    private JPanel center = new JPanel(new BorderLayout());

    public WelcomePage() {
        // make container
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());

        // north panel header
        north.add(header);
        north.setBackground(Color.BLUE);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 24));
        con.add(north, BorderLayout.NORTH);

        // centre to display messge and image
        //give image a var
        ImageIcon welcomeIcon = loadImageIcon("bank_logo.png");
        	//display image
            imageLabel.setIcon(welcomeIcon);
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            center.add(imageLabel, BorderLayout.CENTER);
            //display message var
	        welcomeMessage.setForeground(Color.BLUE);
	        welcomeMessage.setFont(new Font("Arial", Font.PLAIN, 16));
	        welcomeMessage.setHorizontalAlignment(JLabel.CENTER);
	        center.add(welcomeMessage, BorderLayout.SOUTH);

	        con.add(center, BorderLayout.CENTER);

        // setup the JFrame
        this.setTitle("Welcome");
        this.setSize(700, 500);
        // 	Center on screen = chatgpt
        this.setLocationRelativeTo(null); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        // start the thread 
        startTransitionThread();
    }
    
    //image
    private ImageIcon loadImageIcon(String path) {
        // load the image
        java.net.URL imgURL = getClass().getResource(path);
        ImageIcon icon = new ImageIcon(imgURL);
        //resize the image to fit the label
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(400, 250, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    //thread to wait for 2 seconds before displaying login
     private void startTransitionThread() {
        Thread transitionThread = new Thread(() -> {
            try {
                // wait for 2 seconds 
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
                // close WelcomePage
                WelcomePage.this.dispose();

                // open login
                new Login();
         });
        transitionThread.start();
    }
}
