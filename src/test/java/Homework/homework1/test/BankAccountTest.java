/**
 * 
 */
package Homework.homework1.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Homework.homework1.account.BankAccount;
import Homework.homework1.exceptions.*;

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
	 * @throws java.lang.Exception
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
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		firstbankaccount = null;
		secondbankaccount = null;
	}

	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#BankAccount()}.
	 */
	@Test
	public void testBankAccount() {
		assertEquals(75.0, firstbankaccount.getBalance(), 0);
	}

	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#BankAccount(double)}.
	 */
	@Test
	public void testBankAccountDouble() {
		assertEquals(800.0, secondbankaccount.getBalance(), 0);
	}

	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#deposit(double)}.
	 * @throws Exception 
	 */
	@Test
	public void testDeposit() throws Exception {
		firstbankaccount.deposit(100.00);
		assertEquals(175.00, firstbankaccount.getBalance(), 0);
	}

	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#deposit(double)}.
	 * @throws Exception 
	 */
	@Test(expected=InvalidTransactionException.class)
	public void testDepositWithITE() throws Exception {
		firstbankaccount.deposit(-100.00);
	}
	
	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#withdraw(double)}.
	 * @throws Exception 
	 */
	@Test
	public void testWithdraw() throws Exception {
		secondbankaccount.withdraw(100.00);
		assertEquals(700.00, secondbankaccount.getBalance(), 0);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected=InsufficientFundsException.class)
	public void testWithdrawWithIFE() throws Exception{
		firstbankaccount.withdraw(100.00);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@Test(expected=InvalidTransactionException.class)
	public void testWithdrawWithITE() throws Exception{
		firstbankaccount.withdraw(-25.00);
	}
	
	/**
	 * Tests method withdraw to throw the AccountOverdrawnException
	 * @throws Exception - Handles thrown exceptions
	 */
	@Test(expected=AccountOverdrawnException.class)
	public void testWithdrawWithAOE() throws Exception{
		firstbankaccount.withdraw(25.00);
	}

	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#transfer(double, Homework.homework1.account.BankAccount)}.
	 * @throws Exception 
	 */
	@Test
	public void testTransfer() throws Exception {
		secondbankaccount.transfer(100.00, firstbankaccount);
		assertEquals(700.00, secondbankaccount.getBalance(), 0);
		assertEquals(175.00, firstbankaccount.getBalance(), 0);
	}

	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#transfer(double, Homework.homework1.account.BankAccount)}.
	 * @throws Exception 
	 */
	@Test(expected=AccountOverdrawnException.class)
	public void testTransferWithAOE() throws Exception {
		secondbankaccount.transfer(750.00, firstbankaccount);
	}
	
	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#transfer(double, Homework.homework1.account.BankAccount)}.
	 * @throws Exception 
	 */
	@Test(expected=InvalidTransactionException.class)
	public void testTransferWithITE() throws Exception {
		secondbankaccount.transfer(-50.00, firstbankaccount);
	}
	
	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#transfer(double, Homework.homework1.account.BankAccount)}.
	 * @throws Exception 
	 */
	@Test(expected=InsufficientFundsException.class)
	public void testTransferWithIFE() throws Exception {
		secondbankaccount.transfer(850.00, firstbankaccount);
	}
	
	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#isPremiumAccount()}.
	 */
	@Test
	public void testIsPremiumAccount() {
		assertTrue(secondbankaccount.isPremiumAccount());
	}

	/**
	 * Test method for {@link Homework.homework1.account.BankAccount#getBalance()}.
	 */
	@Test
	public void testGetBalance() {
		assertEquals(75.00, firstbankaccount.getBalance(), 0);
	}

}
