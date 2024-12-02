# DeeBankATM_Java
 This project is an ATM Bank Machine simulation developed in Java using the Swing framework. It provides a user-friendly interface for managing basic bank transactions such as deposits, withdrawals, and balance inquiries. The application uses multithreading to enhance the user experience with simple animations and dynamic graphical elements.

Objectives
The project was designed to meet the following objectives:

User-Friendly Interface: Develop a clean, intuitive interface using Java Swing components.
Graphics and Animations: Enhance the interface with custom graphics and a simple animation using Java's Graphics class and threading.
Event-Driven Programming: Implement event listeners to handle user input and update the interface dynamically.
Bank Account Management: Instantiate and manage different types of bank accounts and perform transactions based on user input.
Real-Time Updates: Display updated account balances after each transaction.
Features
Graphical User Interface (GUI):

Built using Java Swing, providing a sleek and interactive ATM interface.
Custom graphics and animations to simulate an engaging ATM experience.
Account Management:

Supports three types of bank accounts: Chequing, Savings, and Super Chequing.
Users can log in using a valid account number and perform transactions on their bank accounts.
Core Functionalities:

Login: Authenticate users based on their account number.
Deposit: Allow users to deposit money into their accounts.
Withdraw: Enable users to withdraw funds while ensuring sufficient balance.
Balance Inquiry: Display the current account balance after each transaction.
Exit: Allow users to log out and either enter another account or shut down the ATM.
Threaded Animation:

Implements a simple thread to animate the interface, such as updating a clock display or animating text to simulate transaction processing.
Technical Details
Programming Language: Java
Framework: Swing for the GUI, Graphics for custom drawings.
Threading: Used to animate the ATM interface for a dynamic user experience.
Collection Management: Stores multiple instances of bank accounts in a List collection for easy access and manipulation.
Bank Account Classes: Includes classes for managing different types of accounts (ChequingAccount, SavingsAccount, SuperChequingAccount), each with methods for deposit, withdrawal, and balance inquiry.

Credits: Credits Developed by: Dhaval Tailor Course: PROG1415 Developing Mobile Applications Fall 2024 Instructor: Peter Vanscoy
