package ch1.bank2;

import java.util.Scanner;

// the BankClient class is responsible for I/O processing

public class BankClient {
	
	private int currentAcc = -1;
	
	private boolean done = false;
	
	private Scanner sc = new Scanner(System.in);
	
	private Bank bank = new Bank();
	
	private void newAcc() {
		
		currentAcc = bank.newAcc();
		
		System.out.println("\n\tyour new acc number: " + currentAcc);
	}
	
	private void selectAcc() {
		
		System.out.print("\n\tenter your account number: #");
		
		int accNo = sc.nextInt();
		
		System.out.println("\n\tthe balance of account #" + accNo + ": $" + bank.getBalance(accNo));
	}
	
	private void deposit() {
		
		System.out.print("\n\tenter your deposit: $");
		
		int deposit = sc.nextInt();
		
		bank.deposit(currentAcc, deposit);
	}
	
	private void addInterest() { bank.addInterest(); }
	
	private void authorizeLoan() {
		
		System.out.print("\n\tenter your loan: $");
		
		int loan = sc.nextInt();
		
		if (bank.authorizeLoan(currentAcc, loan)) {
			System.out.println("\n\t\tyour loan is approved");
		} else {
			System.out.println("\n\t\tyour loan is declined");
		}
	}
	
	private void showAll() { System.out.println(bank); }
	
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
		
		while (!done) {
			
			System.out.print("\nenter command (0=quit, 1=new, 2=select, 3=deposit, 4=loan, 5=interest, 6=show): ");

			int cmd = sc.nextInt();

			processCommand(cmd);
		}
		
		close();
	}
	
	private void close() { sc.close(); }
}
