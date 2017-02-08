package Homework.homework1.account;

import Homework.homework1.exceptions.*;

public class BankAccount {

	// Constants
	private final static double requiredMinimum = 75.0;
	private final static double premiumAmount = 800.0;
	
	// Attributes
	private double balance;
	
	// Constructs a bank account with seventy-five dollar balance
	public BankAccount() {
		balance = BankAccount.requiredMinimum;
	}
	
	// 
	/**
	 * Constructs a bank account with a given balance
	 * 
	 * @param initialBalance -- given balance
	 */
	public BankAccount (double initialBalance) {
		balance = initialBalance;
	}
	
	/** 
	 * Deposits money into the bank account
	 * 
	 * @param amount -- amount to be deposited
	 * @throws InvalidTransactionException if amount
	 * is less than or equal to 0.
	 */
	public void deposit (double amount) 
			throws InvalidTransactionException{
		if(amount <= 0){
			throw new InvalidTransactionException();
		}
		double newBalance = balance + amount;
		balance = newBalance;
	}

	/**
	 * Withdraws money from the bank account
	 * @param amount -- the amount to withdraw
	 * @throws InvalidTransactionException -- thrown if the amount is less than or equal to zero.
	 * @throws InsufficientFundsException -- if the (balance - amount) is less than 0.
	 * @throws AccountOverdrawnException -- if the (balance - amount is less than the required minimum
	 */
	public void withdraw (double amount) 
			throws InvalidTransactionException, 
			InsufficientFundsException, AccountOverdrawnException{
		if(amount <= 0){
			throw new InvalidTransactionException();
		}
		if((balance - amount) < 0){
			throw new InsufficientFundsException();
		}
		if((balance - amount) < requiredMinimum){
			throw new AccountOverdrawnException();
		}
		double newBalance = balance - amount;
		balance = newBalance;
	}

	/**
	 * Transfers money from one bank account to another.
	 * @param amount - the amount to transfer
	 * @param toAccount - the bank account to transfer the amount.
	 * @throws Exception - exception handler.
	 */
	public void transfer(double amount, BankAccount toAccount) 
			throws Exception{
		this.withdraw(amount);
		toAccount.deposit(amount);
	}

	/**
	 * Determines whether or not an account has premium status.
	 * @return true if account is premium account, false otherwise
	 */
	public boolean isPremiumAccount() {
		return (balance >= BankAccount.premiumAmount);
	}

	/**
	 * Gets the current balance of the bank account.
	 * @return the balance of the account.
	 */
	public double getBalance () {
		return balance;
	}	
	
}
