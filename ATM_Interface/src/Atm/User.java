package Atm;

import java.util.HashMap;
import java.util.Scanner;

public class User {
	 private String userID;
	    private String userPIN;
	    private double accountBalance;

	    public User(String userID, String userPIN, double accountBalance) {
	        this.userID = userID;
	        this.userPIN = userPIN;
	        this.accountBalance = accountBalance;
	    }

	    public String getUserID() {
	        return userID;
	    }

	    public String getUserPIN() {
	        return userPIN;
	    }

	    public double getAccountBalance() {
	        return accountBalance;
	    }

	    public void setAccountBalance(double accountBalance) {
	        this.accountBalance = accountBalance;
	    }
	}

	class ATM {
	    private HashMap<String, User> users;
	    private User currentUser;
	    private Scanner scanner;

	    public ATM() {
	        users = new HashMap<>();
	        // Sample user data (replace with your actual user data)
	        users.put("123456", new User("123456", "1234", 1000.0));
	        scanner = new Scanner(System.in);
	    }

	    public void start() {
	        System.out.println("Welcome to the ATM");
	        authenticateUser();
	    }

	    private void authenticateUser() {
	        System.out.println("Please enter your user ID:");
	        String userID = scanner.nextLine();
	        System.out.println("Please enter your PIN:");
	        String pin = scanner.nextLine();

	        if (users.containsKey(userID)) {
	            User user = users.get(userID);
	            if (user.getUserPIN().equals(pin)) {
	                currentUser = user;
	                System.out.println("Authentication successful.");
	                showMainMenu();
	            } else {
	                System.out.println("Incorrect PIN. Please try again.");
	                authenticateUser();
	            }
	        } else {
	            System.out.println("User not found. Please try again.");
	            authenticateUser();
	        }
	    }

	    private void showMainMenu() {
	        System.out.println("Main Menu:");
	        System.out.println("1. Check Balance");
	        System.out.println("2. Withdraw Money");
	        System.out.println("3. Deposit Money");
	        System.out.println("4. Exit");
	        System.out.println("Enter your choice:");

	        int choice = scanner.nextInt();
	        switch (choice) {
	            case 1:
	                checkBalance();
	                break;
	            case 2:
	                withdrawMoney();
	                break;
	            case 3:
	                depositMoney();
	                break;
	            case 4:
	                System.out.println("Thank you for using the ATM. Goodbye!");
	                System.exit(0);
	            default:
	                System.out.println("Invalid choice. Please try again.");
	                showMainMenu();
	        }
	    }

	    private void checkBalance() {
	        System.out.println("Your current balance is: $" + currentUser.getAccountBalance());
	        showMainMenu();
	    }

	    private void withdrawMoney() {
	        System.out.println("Enter the amount to withdraw:");
	        double amount = scanner.nextDouble();
	        if (amount > currentUser.getAccountBalance()) {
	            System.out.println("Insufficient funds.");
	        } else {
	            currentUser.setAccountBalance(currentUser.getAccountBalance() - amount);
	            System.out.println("Withdrawal successful. Remaining balance: $" + currentUser.getAccountBalance());
	        }
	        showMainMenu();
	    }

	    private void depositMoney() {
	        System.out.println("Enter the amount to deposit:");
	        double amount = scanner.nextDouble();
	        currentUser.setAccountBalance(currentUser.getAccountBalance() + amount);
	        System.out.println("Deposit successful. New balance: $" + currentUser.getAccountBalance());
	        showMainMenu();
	    }
}
