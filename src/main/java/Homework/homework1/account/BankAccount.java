package Homework.homework1.account;

public class BankAccount {

	// Constants
	private final static double requiredMinimum = 75.0;
	private final static double premiumAmount = 800.0;
	
	// Attributes
	private double balance;
	
	// Constructs a bank account with fifty dollar balance
	public BankAccount () {
		balance = BankAccount.requiredMinimum;
	}
	
	// Constructs a bank account with a given balance
	public BankAccount (double initialBalance) {
		balance = initialBalance;
	}
	
	// Deposits money into the bank account
	public void deposit (double amount) {
		double newBalance = balance + amount;
		balance = newBalance;
	}
	
	// Withdraws money from the bank account
	public void withdraw (double amount) {
		double newBalance = balance - amount;
		balance = newBalance;
	}
	
	// Transfers money
	public void transfer(double amount, BankAccount toAccount) {
		this.withdraw(amount);
		toAccount.deposit(amount);
	}
	
    // Determines whether or not an account has premium status
	public boolean isPremiumAccount() {
		return (balance > BankAccount.premiumAmount);
	}
	
	// Gets the current balance of the bank account
	public double getBalance () {
		return balance;
	}	
	
}
