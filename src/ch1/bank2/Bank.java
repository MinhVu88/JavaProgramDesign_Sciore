package ch1.bank2;

// The Bank class is responsible for the banking information

import java.util.HashMap;
import java.util.Set;

public class Bank {
	
	// 1st Integer: the account number | 2nd Integer: the account's balance
	private HashMap<Integer, Integer> accounts = new HashMap<>();
	
	private final double INTEREST_RATE = 0.01;
	
	private int nextAcc = 0;
	
	public int newAcc() {
		
		int accNo = nextAcc++;
		
		accounts.put(accNo, 0);
		
		return accNo;
	}
	
	public void deposit(int accNo, int deposit) {
		
		int balance = accounts.get(accNo);
		
		accounts.put(accNo, (balance + deposit));
	}
	
	public void addInterest() {
		
		Set<Integer> accNos = accounts.keySet();
		
		for (int acc : accNos) {
			int balance = accounts.get(acc);
			
			int newBalance = (int) (balance * (1 + INTEREST_RATE));
			
			accounts.put(acc, newBalance);
			
			System.out.println("\n\t. the interest amount: $" + (newBalance - balance));
		}
	}
	
	public boolean authorizeLoan(int accNo, int loan) {
		
		int balance = accounts.get(accNo);
		
		return balance >= loan / 2;
	}
	
	public int getBalance(int accNo) {
		return accounts.get(accNo);
	}

	@Override
	public String toString() {
		
		Set<Integer> accNos = accounts.keySet();
		
		String result = "\n\t* the bank has " + accNos.size() + " account(s)";
		
		for (int acc : accNos) {
			result += "\n\n\t\t-> the balance of account #" + acc + ": $" + accounts.get(acc);
		}
		
		return result;
	}
	
}
