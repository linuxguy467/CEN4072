package exceptions;

public class InvalidTransactionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5687950626509047534L;

	public InvalidTransactionException() {
		super("Amount entered is invalid for this type of transaction");
	}
	
}
