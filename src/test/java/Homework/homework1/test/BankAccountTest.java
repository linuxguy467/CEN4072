/**
 * 
 */
package Homework.homework1.test;

import Homework.homework1.account.BankAccount;
import Homework.homework1.exceptions.AccountOverdrawnException;
import Homework.homework1.exceptions.InsufficientFundsException;
import Homework.homework1.exceptions.InvalidTransactionException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author 0031
 *
 */
public class BankAccountTest {

	private BankAccount firstbankaccount;
	private BankAccount secondbankaccount;
	
	/**
	 * Sets up the bank account objects.
	 * The first bank account is initialized
	 * with the default minimum value of $75.00.
	 * The second bank account is initialized with
	 * a user defined amount of $800.00, the premium
	 * account minimum.
	 * 
	 * @throws java.lang.Exception -- May be thrown if set up fails
	 */
	@Before
	public void setUp() throws Exception {
		firstbankaccount = new BankAccount();
		secondbankaccount = new BankAccount(800.0);
	}

	/**
	 * Tears down the bank account objects after the
	 * test cases have concluded.
	 * 
	 * @throws java.lang.Exception -- May be thrown if tear down fails
	 */
	@After
	public void tearDown() throws Exception {
		firstbankaccount = null;
		secondbankaccount = null;
	}

	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#BankAccount()}.
	 * Asserts the first bank account has $75 required minimum balance.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 */
	@Test
	public void testBankAccount() {
		assertEquals(75.0, firstbankaccount.getBalance(), 0);
	}

	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#BankAccount(double)}.
	 * Asserts the first bank account has $800 premium account balance.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 */
	@Test
	public void testBankAccountDouble() {
		assertEquals(800.0, secondbankaccount.getBalance(), 0);
	}

	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#deposit(double)}.
	 * Deposit $100.00 into the first bank account. Then assert
	 * that the first bank account will have $175.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 * @throws Exception -- exception handler
	 */
	@Test
	public void testDeposit() throws Exception {
		firstbankaccount.deposit(100.00);
		assertEquals(175.00, firstbankaccount.getBalance(), 0);
	}

	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#deposit(double)}.
	 * Tests the deposit method for an invalid transaction exception by
	 * depositing -$100 into the first bank account and
	 * $0 into the second bank account.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 * @throws Exception -- handles thrown exception
	 */
	@Test(expected=InvalidTransactionException.class)
	public void testDepositWithITE() throws Exception {
		secondbankaccount.deposit(0.00);
		firstbankaccount.deposit(-100.00);
	}
	
	/**
	 * Test method for {@link BankAccount#withdraw(double)}.
	 * Withdraws $100 from the second bank account.
	 * Then asserts the second bank account has $700.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 * @throws Exception -- exception handler
	 */
	@Test
	public void testWithdraw() throws Exception {
		secondbankaccount.withdraw(100.00);
		assertEquals(700.00, secondbankaccount.getBalance(), 0);
	}
	
	/**
	 * Test method for {@link BankAccount#withdraw(double)}.
	 * Tests the withdraw function to see if an expected
	 * InsufficientFundsException will be thrown
	 * by withdrawing $100 from the first bank account.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 * @throws Exception -- exception handler
	 */
	@Test(expected=InsufficientFundsException.class)
	public void testWithdrawWithIFE() throws Exception{
		firstbankaccount.withdraw(100.00);
	}
	
	/**
	 * Test method for {@link BankAccount#withdraw(double)}.
	 * Withdraws $0 from the second bank account and -$25 from
	 * the first bank account which will trigger the expected
	 * InvalidTransactionException.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 * @throws Exception -- exception handler
	 */
	@Test(expected=InvalidTransactionException.class)
	public void testWithdrawWithITE() throws Exception{
		secondbankaccount.withdraw(0.00);
		firstbankaccount.withdraw(-25.00);
	}
	
	/**
	 * Test method for {@link BankAccount#withdraw(double)}.
	 * Withdraws $25 from first bank account
	 * to throw the expected AccountOverdrawnException.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 * @throws Exception - Handles thrown exceptions
	 */
	@Test(expected=AccountOverdrawnException.class)
	public void testWithdrawWithAOE() throws Exception{
		firstbankaccount.withdraw(25.00);
	}

	/**
	 * Test method for {@link BankAccount#transfer(double, BankAccount)}.
	 * Transfers $100.00 from the second bank account to the first bank account
	 * Then asserts the first bank account has $175.00 and the second bank
	 * account has $700.00.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 * @throws Exception 
	 */
	@Test
	public void testTransfer() throws Exception {
		secondbankaccount.transfer(100.00, firstbankaccount);
		assertEquals(700.00, secondbankaccount.getBalance(), 0);
		assertEquals(175.00, firstbankaccount.getBalance(), 0);
	}

	/**
	 * Test method for {@link BankAccount#transfer(double, BankAccount)}.
	 * Attempts to transfer $750.00 from the second bank account to the
	 * first bank account which will result in the asserted
	 * AccountOverdrawnException being thrown.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 * @throws Exception -- exception handler
	 */
	@Test(expected=AccountOverdrawnException.class)
	public void testTransferWithAOE() throws Exception {
		secondbankaccount.transfer(750.00, firstbankaccount);
	}
	
	/**
	 * Test method for {@link BankAccount#transfer(double, BankAccount)}.
	 * Attempts to transfer $0 from the first bank account to the
	 * second bank account and transfer -$50 from the second bank account to
	 * the first bank account. This will result in the asserted
	 * InvalidTransactionException being thrown.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 * @throws Exception -- exception handler
	 */
	@Test(expected=InvalidTransactionException.class)
	public void testTransferWithITE() throws Exception {
		firstbankaccount.transfer(0, secondbankaccount);
		secondbankaccount.transfer(-50.00, firstbankaccount);
	}
	
	/**
	 * Test method for {@link BankAccount#transfer(double, BankAccount)}.
	 * Attempts to transfer $850.00 from the second bank account to
	 * the first bank account. This will result in the asserted
	 * InsufficientFundsException being thrown.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 * @throws Exception -- exception handler
	 */
	@Test(expected=InsufficientFundsException.class)
	public void testTransferWithIFE() throws Exception {
		secondbankaccount.transfer(850.00, firstbankaccount);
	}
	
	/**
	 * Test method for {@link BankAccount#isPremiumAccount()}.
	 * Tests if the first bank account is not a premium account
	 * and the second bank account is a premium account.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 */
	@Test
	public void testIsPremiumAccount() {
		assertTrue(secondbankaccount.isPremiumAccount());
		assertFalse(firstbankaccount.isPremiumAccount());
	}
	
	/**
	 * Test method for {@link BankAccount#getBalance()}.
	 * Asserts the first bank account has the minimum required
	 * balance of $75.00.
	 * AssertionException is thrown if fails.
	 * Test run successfully passed.
	 */
	@Test
	public void testGetBalance() {
		assertEquals(75.00, firstbankaccount.getBalance(), 0);
	}

}
