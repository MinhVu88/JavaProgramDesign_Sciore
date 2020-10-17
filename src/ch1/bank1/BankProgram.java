package ch1.bank1;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/*
- This program holds a map that stores the balances of several accounts held by a bank 

- Each element in the map is a key-value pair

- The key is an integer that denotes the account number & its value is the balance of that account, in cents
*/

public class BankProgram {
	
	// 1st Integer: the account number | 2nd Integer: the account's balance
	private HashMap<Integer, Integer> accounts = new HashMap<>(); 

	private final double INTEREST_RATE = 0.01;

	private int nextAcc = 0, currentAcc = -1;

	private boolean done = false;

	private Scanner sc;

	// 7 command methods: quit, newAcc, select, deposit, authorizeLoan, addInterest & showAll

	// newAcc() allocates a new account number, makes it current & assigns it to the map with an initial balance of 0 cent
	private void newAcc() {
		
		currentAcc = nextAcc++;

		accounts.put(currentAcc, 0);

		System.out.println("\n\tYour new account number: " + currentAcc);
	}

	// select() makes an existing account current & print its balance
	private void selectAcc() {
		
		System.out.print("\n\tenter account #");

		currentAcc = sc.nextInt();

		System.out.println("\n\tthe balance of account #" + currentAcc + ": $" + accounts.get(currentAcc));
	}

	// deposit() increases the balance of the current account by a specified number of cents
	private void deposit() {
		
		System.out.print("\n\tenter deposit amount: $");

		int cents = sc.nextInt();

		int balance = accounts.get(currentAcc) + cents;

		accounts.put(currentAcc, balance);
	}

	// addInterest() increases the balance of each account by a fixed interest rate
	private void addInterest() {
		
		// keySet() returns the keys of the HashMap in a Set collection, which contains unique elements (the account numbers)
		Set<Integer> accNos = accounts.keySet();
		
		for(int acc : accNos) {
			
			int balance = accounts.get(acc);

			int newBalance = (int) (balance * (1 + INTEREST_RATE));

			accounts.put(acc, newBalance);
			
			System.out.println("\n\t. the interest amount: $" + (newBalance - balance));
		}
	}

	/* 
	 * authorizeLoan() determines whether the current account has enough money to be used as collateral for a loan
	 * 
	 * collateral (n-u): property that someone borrowing money will give to the loan company/bank, if he or she cannot pay the debt
	 * 
	 * the criterion is that the account must contain at least half of the loan amount
	 * 
	 */

	private void authorizeLoan() {
		
		System.out.print("\n\tenter loan amount: $");

		int loan = sc.nextInt();

		int balace = accounts.get(currentAcc);

		if(balace >= (loan / 2)) {
			System.out.println("\n\t* your loan is approved -> current balace: $" + balace + " & loan amount: $" + loan);
		} else {
			System.out.println("\n\t* your loan is denied -> current balace: $" + balace + " & loan amount: $" + loan);
		}
		
	}

	// showAll() prints the balance of every account
	private void showAll() {
		
		Set<Integer> accts = accounts.keySet();

		System.out.println("\n\t* the bank has " + accts.size() + " account(s)");

		for(int acc : accts) {
			System.out.println("\n\t\t-> the balance of account #" + acc + ": $" + accounts.get(acc));
		}
	}

	// quit() sets done to true, causing the loop to terminate
	private void quit() {
		
		done = true;

		System.out.println("\nGoodbye");
	}

	private void processCommand(int cmd) {
		
		if (cmd == 0) {
			quit();
		} else if(cmd == 1) {
			newAcc();
		} else if(cmd == 2) {
			selectAcc();
		} else if(cmd == 3) {
			deposit();
		} else if(cmd == 4) {
			authorizeLoan();
		} else if(cmd == 5) {
			addInterest();
		} else if(cmd == 6) {
			showAll();
		} else {
			System.out.println("\n\tillegal command");
		}
		
	}

	public void run() {
		
		sc = new Scanner(System.in);

		while (!done) {
			
			System.out.print("\nenter command (0=quit, 1=new, 2=select, 3=deposit, 4=loan, 5=interest, 6=show): ");

			int cmd = sc.nextInt();

			processCommand(cmd);
		}

		sc.close();
	}

	public static void main(String[] args) {
		
		BankProgram bank = new BankProgram();

		bank.run();
	}
	
}
