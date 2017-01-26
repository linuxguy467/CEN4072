package Homework.homework1.exceptions;

public class AccountOverdrawnException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3073893446951649972L;

	public AccountOverdrawnException() {
		super("Account cannot be less than $75.00");
	}
	
}
