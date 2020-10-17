package ch1.bank2.unitTest;

import ch1.bank2.Bank;

public class BankTest {
	
	private static Bank bank = new Bank();
	
	private static int accNo = bank.newAcc();
	
	private static void verifyBalance(String msg, int expectedValue) {
		
		int balance = bank.getBalance(accNo);
		
		boolean ok = balance == expectedValue;
		
		String result = ok ? "Good!" : "Bad!";
		
		System.out.println("\n" + msg + ": " + result);
	}
	
	private static void verifyLoan(String msg, int loan, boolean expectedValue) {
		
		boolean validLoan = bank.authorizeLoan(accNo, loan);
		
		boolean ok = validLoan == expectedValue;
		
		String result = ok ? "Valid loan" : "Invalid loan";
		
		System.out.println("\n" + msg + ": " + result);
	}
	
	public static void main(String[] args) {
		
		verifyBalance("inital amount", 0);
		
		System.out.println(bank);
		
		bank.deposit(accNo, 10);
		
		verifyBalance("after deposit", 10);
		
		System.out.println(bank);
		
		verifyLoan("authorize bad loan", 22, false);
		
		verifyLoan("authorize good loan", 20, true);
	}

}
