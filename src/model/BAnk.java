package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

import dataStructures.Hash;
import dataStructures.PriorityQueue;
import dataStructures.Queue;
import dataStructures.Stack;

public class BAnk {
	
	public final int SIZE_PRIORITY_QUEUE = 50;
	public final int SIZE_HASH = 100;
	
	private Queue<User> queue;
	private PriorityQueue<User> prioQueue;
	private Stack<Transaction> undoStack;
	private Hash<User,String> canceledAccounts;
	private Hash<User,String> dataBase;
	
	public BAnk() {
		queue = new Queue<User>();
		prioQueue = new PriorityQueue<>(User.class, SIZE_PRIORITY_QUEUE);
		undoStack = new Stack<Transaction>();
		canceledAccounts = new Hash<User,String>(SIZE_HASH);
		dataBase = new Hash<User,String>(SIZE_HASH);
		
		try {
			chargeDatabase();
		} catch (IOException e) {
			System.out.println("Error charging the data");
		}
	}
	
	public void newEntry() {
		
	}
	
	private void chargeDatabase() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Data.txt"));
		String[] string = br.readLine().split("ppp");
		while(string!=null){
			String name = string[0];
			String iD = string[1];
			String bankAccountNum = string[2];
			double debitCard = Double.parseDouble(string[3]);
			double creditCard =Double.parseDouble(string[4]);
			String date[] = string[5].split("/");
			LocalDate dateOfPayCC = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
			date = string[6].split("/");
			LocalDate dateOfJoin = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
			User u = new User(name, iD, bankAccountNum, debitCard, creditCard, dateOfPayCC, dateOfJoin);
			dataBase.insert(u, iD);
			String nl = br.readLine();
			if(nl!=null) {
				string = nl.split("ppp");
			}else
				string = null;
		}
		
	}
	
}
